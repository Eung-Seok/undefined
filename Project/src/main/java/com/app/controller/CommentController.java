package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.app.dto.comment.Comment;
import com.app.service.comment.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 등록
    @PostMapping("/create")
    public String createComment(Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/board/view?id=" + comment.getPostId();
    }

    // 댓글 삭제 (GET으로 변경)
    @PostMapping("/delete")
    public String deleteComment(@RequestParam("id") int id,
                                @RequestParam("postId") int postId) {
        commentService.removeComment(id);
        return "redirect:/board/view?id=" + postId;
    }

    // 댓글 수정
    @PostMapping("/update")
    public String updateComment(Comment comment) {
        commentService.modifyComment(comment);
        return "redirect:/board/view?id=" + comment.getPostId();
    }
}