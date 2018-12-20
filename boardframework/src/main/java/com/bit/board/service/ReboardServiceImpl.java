package com.bit.board.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.bit.board.model.ReboardDto;

@Service
public class ReboardServiceImpl implements ReboardService {

  @Override
  public int writeArticle(ReboardDto reboardDto) {
    //int seq = ; 
    return 0;
  }

  @Override
  public List<ReboardDto> listArticle(Map<String, String> param) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ReboardDto viewArticle(int seq) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int replyArticle(ReboardDto reboardDto) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void modifyArticle(ReboardDto reboardDto) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void deleteArticle(int seq) {
    // TODO Auto-generated method stub
    
  }


}
