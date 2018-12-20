<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.bit.member.model.MemberDto"%>


<% 
MemberDto memberDto = new MemberDto();
memberDto.setId("java2");
memberDto.setName("태규");
memberDto.setEmail("bazel29@naver.com");

session.setAttribute("userInfo", memberDto);

response.sendRedirect(request.getContextPath()+"/badmin/boardmenu.bit");

%>
