package com.app.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.app.dto.board.Board;
import com.app.dto.user.User;
import com.app.service.board.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 1. 게시판 목록
    @GetMapping("/board")
    public String boardList(Model model, HttpSession session) {
        if (session.getAttribute("loginUser") == null) return "redirect:/login";
        model.addAttribute("postList", boardService.findBoardList());
        return "board/board";
    }

    // 2. 글쓰기 폼
    @GetMapping("/board/write")
    public String boardWriteForm(Model model, HttpSession session) {
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

    // 4. 상세 보기
    @GetMapping("/board/view")
    public String boardView(@RequestParam("id") int id, Model model, HttpSession session) {
        if (session.getAttribute("loginUser") == null) return "redirect:/login";
        Board board = boardService.findBoardById(id);
        model.addAttribute("post", board);
        return "board/board-view";
    }

    // 5. 수정 폼으로 이동 (권한 체크 포함)
    @GetMapping("/board/edit")
    public String boardEditForm(@RequestParam("id") int id, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Board board = boardService.findBoardById(id);

        if (loginUser == null || board == null) return "redirect:/board";

        // 작성자 본인이거나 PM인지 확인
        boolean isAuthor = (board.getAuthorUserId() == loginUser.getEmpno());
        boolean isPM = "PM".equalsIgnoreCase(loginUser.getPosition());

        if (!isAuthor && !isPM) {
            return "redirect:/board/view?id=" + id;
        }

        model.addAttribute("post", board);
        return "board/board-modify";
    }

    // 6. 게시글 수정 처리
    @PostMapping("/board/update")
    public String boardUpdate(Board board, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Board original = boardService.findBoardById(board.getId());

        if (loginUser == null || original == null) return "redirect:/board";

        boolean isAuthor = (original.getAuthorUserId() == loginUser.getEmpno());
        boolean isPM = "PM".equalsIgnoreCase(loginUser.getPosition());

        if (!isAuthor && !isPM) return "redirect:/board/view?id=" + board.getId();

        boardService.modifyBoard(board);
        return "redirect:/board/view?id=" + board.getId();
    }

    // 7. 게시글 삭제 처리
    @GetMapping("/board/delete")
    public String boardDelete(@RequestParam("id") int id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Board board = boardService.findBoardById(id);

        if (loginUser == null || board == null) return "redirect:/board";

        boolean isAuthor = (board.getAuthorUserId() == loginUser.getEmpno());
        boolean isPM = "PM".equalsIgnoreCase(loginUser.getPosition());

        if (!isAuthor && !isPM) return "redirect:/board/view?id=" + id;

        boardService.removeBoard(id);
        return "redirect:/board";
    }
}