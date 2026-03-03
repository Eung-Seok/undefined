package com.app.service.comment.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.comment.CommentDAO;
import com.app.dto.comment.Comment;
import com.app.service.comment.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void saveComment(Comment comment) {
        commentDAO.saveComment(comment);
    }

    @Override
    public void removeComment(int id) {
        commentDAO.removeComment(id);
    }

    @Override
    public void modifyComment(Comment comment) {
        commentDAO.modifyComment(comment);
    }

    @Override
    public List<Comment> findCommentListByPostId(Map<String, Object> param) {
        return commentDAO.findCommentListByPostId(param);
    }

    @Override
    public int getTotalCommentCount(int postId) {
        return commentDAO.getTotalCommentCount(postId);
    }

    @Override
    public void removeCommentsByPostId(int postId) {
        commentDAO.deleteByPostId(postId);
    }
}