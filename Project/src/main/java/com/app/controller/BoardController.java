package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.board.Board;
import com.app.dto.comment.Comment;
import com.app.dto.user.User;
import com.app.service.board.BoardService;
import com.app.service.comment.CommentService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @Autowired
    private CommentService commentService;

    // 1. 게시판 목록
    @GetMapping("/board")
    public String boardList(
            @RequestParam(value="boardId", required=false) Integer boardId,
            Model model, 
            HttpSession session) {
        
        if (session.getAttribute("loginUser") == null) return "redirect:/login";

        List<Board> list;

        if (boardId == null || boardId == 0) {
            list = boardService.findBoardList();
        } else {
            list = boardService.findBoardListByBoardId(boardId);
        }
        
        model.addAttribute("postList", list);
        model.addAttribute("selectedBoardId", boardId); 
        
        return "board/board";
    }

    // 2. 글쓰기 폼
    @GetMapping("/board/write")
    public String boardWriteForm(HttpSession session) {
        if (session.getAttribute("loginUser") == null) return "redirect:/login";
        return "board/board-write";
    }

    // 3. 글 저장
    @PostMapping("/board/write")
    public String boardWrite(Board board, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) return "redirect:/login";

        board.setAuthorUserId(loginUser.getEmpno());
        boardService.saveBoard(board);
        return "redirect:/board";
    }

    // 4. 상세 보기 ( 페이징 수정 )
    @GetMapping("/board/view")
    public String boardView(@RequestParam("id") int id,
                            @RequestParam(value="page", defaultValue="1") int page,
                            Model model,
                            HttpSession session) {

        if (session.getAttribute("loginUser") == null)
            return "redirect:/login";

        Board board = boardService.findBoardById(id);

        //  6개씩 페이징 계산
        int pageSize = 6;
        int startRow = (page - 1) * pageSize + 1;
        int endRow = page * pageSize;

        Map<String, Object> param = new HashMap<>();
        param.put("postId", id);
        param.put("startRow", startRow);
        param.put("endRow", endRow);

        List<Comment> commentList =
                commentService.findCommentListByPostId(param);

        int totalComments = commentService.getTotalCommentCount(id);
        int totalPages = (int) Math.ceil((double) totalComments / pageSize);

        model.addAttribute("post", board);
        model.addAttribute("commentList", commentList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalComments", totalComments);
        model.addAttribute("totalPages", totalPages);

        return "board/board-view";
    }

    // 5. 수정 폼
    @GetMapping("/board/edit")
    public String boardEditForm(@RequestParam("id") int id,
                                Model model,
                                HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        Board board = boardService.findBoardById(id);

        if (loginUser == null || board == null)
            return "redirect:/board";

        boolean isAuthor = (board.getAuthorUserId() == loginUser.getEmpno());
        boolean isPM = "PM".equalsIgnoreCase(loginUser.getPosition());

        if (!isAuthor && !isPM)
            return "redirect:/board/view?id=" + id;

        model.addAttribute("post", board);
        return "board/board-modify";
    }

    // 6. 게시글 수정
    @PostMapping("/board/update")
    public String boardUpdate(Board board, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        Board original = boardService.findBoardById(board.getId());

        if (loginUser == null || original == null)
            return "redirect:/board";

        boolean isAuthor = (original.getAuthorUserId() == loginUser.getEmpno());
        boolean isPM = "PM".equalsIgnoreCase(loginUser.getPosition());

        if (!isAuthor && !isPM)
            return "redirect:/board/view?id=" + board.getId();

        boardService.modifyBoard(board);
        return "redirect:/board/view?id=" + board.getId();
    }

    // 7. 게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") int id,
                              HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        Board board = boardService.findBoardById(id);

        if (loginUser == null || board == null)
            return "redirect:/board";

        boolean isAuthor = (board.getAuthorUserId() == loginUser.getEmpno());
        boolean isPM = "PM".equalsIgnoreCase(loginUser.getPosition());

        if (!isAuthor && !isPM)
            return "redirect:/board/view?id=" + id;

        //  댓글 삭제
        commentService.removeCommentsByPostId(id);

        //  게시글 삭제
        boardService.removeBoard(id);

        return "redirect:/board";
    }
}