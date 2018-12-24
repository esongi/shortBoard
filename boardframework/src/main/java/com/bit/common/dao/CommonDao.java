package com.bit.common.dao;

import java.util.Map;

public interface CommonDao {
  int getNextSeq();
  
  void updateHit(int seq);
  
  // 검색에서는 모든 글에만 적용
  int getNewArticleCount(int bcode);
  
  int getTotalArticleCount(Map<String, String> map);
  
}
