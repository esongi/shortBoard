package com.bit.board.dao;

import java.util.List;
import java.util.Map;
import com.bit.board.model.AlbumDto;
import com.bit.board.model.ReboardDto;

public interface AlbumDao {

  int writeArticle(AlbumDto albumDto);

  List<ReboardDto> listArticle(Map<String, String> param);

  AlbumDto viewArticle(int seq);

  // 진행예정
  void modifyArticle(AlbumDto albumDto);

  void deleteArticle(int seq);

}
