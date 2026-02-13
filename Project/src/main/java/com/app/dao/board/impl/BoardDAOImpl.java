package com.app.dao.board.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.board.BoardDAO;
import com.app.dto.board.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<Board> findBoardList() {
		List<Board> boardList = sqlSessionTemplate.selectList("board_mapper.findBoardList");
		return boardList;
	}
	
}
