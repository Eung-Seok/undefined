package com.app.dao.board;

import java.util.List;

import com.app.dto.board.Board;

public interface BoardDAO {
	List<Board> findBoardList();

	int saveBoard(Board board);

	Board findBoardById(int id);

	int removeBoard(int id);

	int modifyBoard(Board board);
}
