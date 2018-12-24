package com.bit.board.service;

import java.util.List;
import java.util.Map;
import com.bit.board.model.ReboardDto;

public interface ReboardService {

  int writeArticle(ReboardDto reboardDto);

  List<ReboardDto> listArticle(Map<String, String> param);

  ReboardDto viewArticle(int seq);

  ReboardDto getArticle(int seq); // 글 수정, 답글 용


  // 답글쓰기 트랜잭션 동시에 이루어줘야 한다
  void updateStep(ReboardDto reboardDto);

  int replyArticle(ReboardDto reboardDto);

  int updateReply(int pseq);

  // 진행예정
  void modifyArticle(ReboardDto reboardDto);

  void deleteArticle(int seq);

}
