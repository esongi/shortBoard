package com.bit.board.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bit.board.model.MemoDto;
import com.bit.board.service.MemoService;
import com.bit.member.model.MemberDto;

// @RestController(@Controller + @ResponseBody)
@Controller
public class MemoController {

  @Autowired
  private MemoService memoService;

  @RequestMapping(value = "memo", method = RequestMethod.POST,
      headers = {"Content-type=application/json"})
  @ResponseBody
  public String write(@RequestBody MemoDto memoDto, HttpSession session) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memoDto != null) {
      memoDto.setId(memberDto.getId());
      memoDto.setName(memberDto.getName());
      int cnt = memoService.writeMemo(memoDto);
    }
    String memolist = memoService.listMemo(memoDto.getSeq());
    return memolist;
  }

  @ResponseBody
  @RequestMapping(value = "memo/{seq}", method = RequestMethod.GET)
  public String list(@PathVariable(value = "seq") int seq) {

    String memolist = memoService.listMemo(seq);
    return memolist;
  }

  @RequestMapping(value = "memo", method = RequestMethod.PUT,
      headers = {"Content-type=application/json"})
  @ResponseBody
  public String modify(@RequestBody MemoDto memoDto, HttpSession session) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memoDto != null) {
      int cnt = memoService.modifyMemo(memoDto);
    }
    String memolist = memoService.listMemo(memoDto.getSeq());
    return memolist;
  }

  @ResponseBody
  @RequestMapping(value = "memo/{seq}/{mseq}", method = RequestMethod.DELETE)
  public String delete(@PathVariable(value = "mseq") int mseq,
      @PathVariable(value = "seq") int seq) {

    memoService.deleteMemo(mseq);
    String memolist = memoService.listMemo(seq);
    return memolist;
  }


}
