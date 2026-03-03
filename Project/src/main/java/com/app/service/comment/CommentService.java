package com.app.service.comment;

import com.app.dto.comment.Comment;
import java.util.List;
import java.util.Map;

public interface CommentService {

    void saveComment(Comment comment);

    void removeComment(int id);

    void modifyComment(Comment comment);

    List<Comment> findCommentListByPostId(Map<String, Object> param);

    int getTotalCommentCount(int postId);

    void removeCommentsByPostId(int postId);
}