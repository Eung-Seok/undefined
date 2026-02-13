package com.app.service.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.board.BoardDAO;
import com.app.dto.board.Board;
import com.app.service.board.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO boardDao;

	@Override
	public List<Board> findBoardList() {
		List<Board> boardList = boardDao.findBoardList();
		return boardList;
	}

	@Override
	public int saveBoard(Board board) {
		int result = boardDao.saveBoard(board);
		return result;
	}

	@Override
	public Board findBoardById(int id) {
		Board board = boardDao.findBoardById(id);
		return board;
	}

	@Override
	public int removeBoard(int id) {
		int result = boardDao.removeBoard(id);
		return result;
	}

	@Override
	public int modifyBoard(Board board) {
		int result = boardDao.modifyBoard(board);
		return result;
	}
}