package com.bit.common.dao;

public interface CommonDao {
  int getNextSeq();
  
  void updateHit(int seq);
}
