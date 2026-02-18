package com.app.dao.post.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.post.PostDAO;
import com.app.dto.post.Post;


@Repository
public class PostDAOImpl implements PostDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Post> findPostList() {
		List<Post> postList = sqlSessionTemplate.selectList("post_mapper.findPostList");
		return postList;
	}

	@Override
	public int savePost(Post post) {
		int result = sqlSessionTemplate.insert("post_mapper.savePost", post);
		return result;
	}

	@Override
	public Post findPostById(int id) {
		Post post = sqlSessionTemplate.selectOne("post_mapper.findPostById", id);
		return post;
	}

	@Override
	public int removePost(int id) {
		int result = sqlSessionTemplate.delete("post_mapper.removePost", id);
		return result;
	}

	@Override
	public int modifyPost(Post post) {
		int result = sqlSessionTemplate.update("post_mapper.modifyPost", post);
		return result;
	}
	
}
