package com.bit.board.admin.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.board.admin.dao.BoardAdminDao;
import com.bit.board.admin.model.BoardListDto;
import com.bit.board.admin.model.BoardTypeDto;
import com.bit.board.admin.model.CategoryDto;

@Service
public class BoardAdminServiceImpl implements BoardAdminService {

  @Autowired
  private SqlSession sqlSession;
  
  
  @Override
  public List<BoardListDto> getBoardMenu() {
    return sqlSession.getMapper(BoardAdminDao.class).getBoardMenu();
  }

  @Override
  public List<CategoryDto> getCategory() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<BoardTypeDto> getBoardType() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void makeCategory(CategoryDto categoryDto) {
    // TODO Auto-generated method stub

  }

  @Override
  public void makeBoard(BoardListDto boardListDto) {
    // TODO Auto-generated method stub

  }

}
