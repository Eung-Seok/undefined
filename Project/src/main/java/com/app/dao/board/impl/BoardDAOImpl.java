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

	@Override
	public int saveBoard(Board board) {
		int result = sqlSessionTemplate.insert("board_mapper.saveBoard", board);
		return result;
	}

	@Override
	public Board findBoardById(int id) {
		Board board = sqlSessionTemplate.selectOne("board_mapper.findBoardById", id);
		return board;
	}

	@Override
	public int removeBoard(int id) {
		int result = sqlSessionTemplate.delete("board_mapper.removeBoard", id);
		return result;
	}

	@Override
	public int modifyBoard(Board board) {
		int result = sqlSessionTemplate.update("board_mapper.modifyBoard", board);
		return result;
	}
	
}
