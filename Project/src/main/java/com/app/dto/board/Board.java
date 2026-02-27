package com.app.dto.board;

import lombok.Data;

@Data
public class Board {
	private int id;
	private int boardId;
    private String name;      
    private int postId;      
    private String title;    
    private String content;  
    private int authorUserId;
    private int pmUserId;
    private String authorName; 
    private String createdAt;
    
    public int getPostId() {
        return id;
    }
}
