package com.bit.board.service;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bit.board.dao.ReboardDao;
import com.bit.board.model.ReboardDto;
import com.bit.common.dao.CommonDao;
import com.bit.util.BoardConstance;

@Service
public class ReboardServiceImpl implements ReboardService {

  @Autowired
  private SqlSession sqlSession;

  // @Autowired
  // private ReboardDao reboardDao;

  @Override
  public int writeArticle(ReboardDto reboardDto) {
    int seq = sqlSession.getMapper(CommonDao.class).getNextSeq();
    // seq와 ref는 같은 값을 주자
    reboardDto.setSeq(seq);
    reboardDto.setRef(seq);
    int cnt = sqlSession.getMapper(ReboardDao.class).writeArticle(reboardDto);

    // 정상 이외의 값(에러)은 0, 그리고 페이지나 나머지 값도 가지고 가야한다
    return cnt != 0 ? seq : 0;
  }

  @Override
  public List<ReboardDto> listArticle(Map<String, String> param) {
    // 메소드 호출로
    int pg = Integer.parseInt(param.get("pg"));
    int end = pg * BoardConstance.LIST_COUNT;
    int start = end - BoardConstance.LIST_COUNT;
    param.put("start", start + "");
    param.put("end", end + "");
    return sqlSession.getMapper(ReboardDao.class).listArticle(param);
  }

  @Override
  public ReboardDto viewArticle(int seq) {
    ReboardDto reboardDto = sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
    // ReboardDto reboardDto = reboardDao.viewArticle(seq);
    if (reboardDto != null) {
      sqlSession.getMapper(CommonDao.class).updateHit(seq);
      reboardDto.setContent(reboardDto.getContent().replace("\n", "<br>"));
    }

    return reboardDto;
  }

  @Override
  public ReboardDto getArticle(int seq) {
    return sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
  }


  @Transactional
  @Override
  public int replyArticle(ReboardDto reboardDto) {
    int seq = sqlSession.getMapper(CommonDao.class).getNextSeq();
    reboardDto.setSeq(seq);

    // 순서대로 진행되어야 한다
    ReboardDao reboardDao = sqlSession.getMapper(ReboardDao.class);

    reboardDao.updateStep(reboardDto);
    reboardDao.replyArticle(reboardDto);
    reboardDao.updateReply(reboardDto.getPseq());

    return seq;
  }

  @Override
  public void modifyArticle(ReboardDto reboardDto) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteArticle(int seq) {
    // TODO Auto-generated method stub

  }

  @Override
  public void updateStep(ReboardDto reboardDto) {
    // TODO Auto-generated method stub

  }

  @Override
  public int updateReply(int pseq) {
    // TODO Auto-generated method stub
    return 0;
  }



}
