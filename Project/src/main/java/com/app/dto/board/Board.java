package com.app.dto.board;

import lombok.Data;

@Data
public class Board {
	int id;
	String name;
	Integer projectId;
	String createdAt;
}
