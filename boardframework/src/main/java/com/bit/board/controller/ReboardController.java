package com.bit.board.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.bit.board.common.service.CommonService;
import com.bit.board.model.ReboardDto;
import com.bit.board.service.ReboardService;
import com.bit.member.model.MemberDto;
import com.bit.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
public class ReboardController {

  @Autowired
  private CommonService commonservice;

  @Autowired
  private ReboardService reboardService;

  // param 은 계속 갖고 다녀야할 그 4가지 정보를 담고 있다
  @RequestMapping("list.bit")
  public ModelAndView list(@RequestParam Map<String, String> param) {
    ModelAndView mav = new ModelAndView();
    List<ReboardDto> list = reboardService.listArticle(param);
    PageNavigation navigation = commonservice.makePageNavigation(param);
    navigation.setRoot("/board"); // 다양한 방법이 있음
    navigation.makeNavigator();

    mav.addObject("articlelist", list);
    mav.addObject("navigator", navigation);
    mav.setViewName("reboard/list");
    return mav;

  }

  // 리턴타입 - 매핑, 게시판 쓰기 정보
  @RequestMapping(value = "write.bit", method = RequestMethod.GET)
  public String write(@RequestParam Map<String, String> param) {
    return "reboard/write";
  }

  @RequestMapping(value = "write.bit", method = RequestMethod.POST)
  public String write(ReboardDto reboardDto, HttpSession session,
      @RequestParam Map<String, String> param, Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memberDto != null) {
      reboardDto.setId(memberDto.getId());
      reboardDto.setName(memberDto.getName());
      reboardDto.setEmail(memberDto.getEmail());

      int seq = reboardService.writeArticle(reboardDto);

      if (seq != 0) {
        model.addAttribute("wseq", seq);
      } else {
        model.addAttribute("errorMsg", "서버 문제로 글작성 실패!!");
      }

    } else {
      model.addAttribute("errorMsg", "회원전용 게시판입니다!");
    }

    return "reboard/writeOk";
  }

  @RequestMapping("view.bit")
  public String view(@RequestParam int seq, HttpSession session, Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");

    if (memberDto != null) {
      ReboardDto reboardDto = reboardService.viewArticle(seq);
      // sql 작성하고 넘기자
      model.addAttribute("article", reboardDto);
    }

    return "reboard/view";
  }

  @RequestMapping(value="reply.bit", method=RequestMethod.GET)
  public String reply(@RequestParam int seq, HttpSession session,
     Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memberDto != null) {
      ReboardDto reboardDto = reboardService.getArticle(seq);

      model.addAttribute("article", reboardDto);
    }
   
    return "reboard/reply";
  }
  
  @RequestMapping(value = "reply.bit", method = RequestMethod.POST)
  public String reply(ReboardDto reboardDto, HttpSession session,
      @RequestParam Map<String, String> param, Model model) {
    MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
    if (memberDto != null) {
      reboardDto.setId(memberDto.getId());
      reboardDto.setName(memberDto.getName());
      reboardDto.setEmail(memberDto.getEmail());

      int seq = reboardService.replyArticle(reboardDto);

      if (seq != 0) {
        model.addAttribute("wseq", seq);
      } else {
        model.addAttribute("errorMsg", "서버 문제로 글작성 실패!!");
      }

    } else {
      model.addAttribute("errorMsg", "회원전용 게시판입니다!");
    }

    return "reboard/writeOk";
  }

}
