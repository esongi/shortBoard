package com.bit.board.admin.service;

import java.util.List;
import com.bit.board.admin.model.BoardListDto;
import com.bit.board.admin.model.BoardTypeDto;
import com.bit.board.admin.model.CategoryDto;

public interface BoardAdminService {


  List<BoardListDto> getBoardMenu();

  List<CategoryDto> getCategory();

  List<BoardTypeDto> getBoardType();

  // 에러는 에러출력
  void makeCategory(CategoryDto categoryDto);

  void makeBoard(BoardListDto boardListDto);

}
