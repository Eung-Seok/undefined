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
}