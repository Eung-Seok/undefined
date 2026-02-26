package com.app.service.board;

import java.util.List;

import com.app.dto.board.Board;

public interface BoardService {
	List<Board> findBoardList();

	int saveBoard(Board board);

	Board findBoardById(int id);

	int removeBoard(int id);

	int modifyBoard(Board board);
	
	List<Board> findBoardListByBoardId(Integer boardId);
}
