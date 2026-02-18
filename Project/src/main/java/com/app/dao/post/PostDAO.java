package com.app.dao.post;

import java.util.List;

import com.app.dto.post.Post;

public interface PostDAO {
	List<Post> findPostList();
	
	int savePost(Post post);

	Post findPostById(int id);

	int removePost(int id);

	int modifyPost(Post post);
}
