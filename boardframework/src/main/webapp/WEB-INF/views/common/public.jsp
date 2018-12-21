<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="bcode" value="${param.bcode}" />
<c:set var="pg" value="${param.pg}" />
<c:set var="key" value="${param.key}" />
<c:set var="word" value="${param.word}" />
<c:set var="seq" value="${param.seq}" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="ko">
<head>
<title>게시판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${root}/css/skin_purple.css"
	type="text/css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script src="${root}/resources/board/js/board.js"></script>

</head>

<body>
	<form id="commonForm" method="get" action="">
		<input type="hidden" id="bcode" name="bcode" value="${bcode}">
		<input type="hidden" id="pg" name="pg" value="${pg}"> <input
			type="hidden" id="key" name="key" value="${key}"> <input
			type="hidden" id="word" name="word" value="${word}"> <input
			type="hidden" id="seq" name="seq" value="">
	</form>