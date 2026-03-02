package com.app.dao.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.app.dto.board.Board;

public interface BoardDAO {
	List<Board> findBoardList();
	
	List<Board> findBoardListByBoardId(@Param("boardId") Integer boardId);

	int saveBoard(Board board);

	Board findBoardById(int id);

	int removeBoard(int id);

	int modifyBoard(Board board);
}
