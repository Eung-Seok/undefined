package com.app.dao.comment.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.comment.CommentDAO;
import com.app.dto.comment.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void saveComment(Comment comment) {
        sqlSessionTemplate.insert("comment_mapper.saveComment", comment);
    }

    @Override
    public void removeComment(int id) {
        sqlSessionTemplate.delete("comment_mapper.removeComment", id);
    }

    @Override
    public void modifyComment(Comment comment) {
        sqlSessionTemplate.update("comment_mapper.modifyComment", comment);
    }

    @Override
    public List<Comment> findCommentListByPostId(Map<String, Object> param) {
        return sqlSessionTemplate.selectList("comment_mapper.findCommentListByPostId", param);
    }

    @Override
    public int getTotalCommentCount(int postId) {
        return sqlSessionTemplate.selectOne("comment_mapper.getTotalCommentCount", postId);
    }

    @Override
    public void deleteByPostId(int postId) {
        sqlSessionTemplate.delete("comment_mapper.deleteByPostId", postId);
    }
}
