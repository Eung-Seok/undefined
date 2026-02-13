package com.app.service.post.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.post.PostDAO;
import com.app.dto.post.Post;
import com.app.service.post.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostDAO postDao;

	@Override
	public List<Post> findPostList() {
		List<Post> postList = postDao.findPostList();
		return postList;
	}

	@Override
	public int savePost(Post post) {
		int result = postDao.savePost(post);
		return result;
	}

	@Override
	public Post findPostById(int id) {
		Post post = postDao.findPostById(id);
		return post;
	}

	@Override
	public int removePost(int id) {
		int result = postDao.removePost(id);
		return result;
	}

	@Override
	public int modifyPost(Post post) {
		int result = postDao.modifyPost(post);
		return result;
	}
}
