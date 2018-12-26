<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/public.jsp"%>

<c:if test="${article == null }">
	<script>
    alert("글이 삭제되었거나 잘못된 URL 접근입니다");
    document.location.href = "${root}/index.jsp";
  </script>
</c:if>


<c:if test="${article != null }">
	<script>
    $(document)
            .ready(
                    function() {
                      control = "${root}/reboard";

                      // url 연결
                      initVars();

                      // 처음 게시글 보기의 댓글 화면 뿌리기
                      getMemoList();

                      $(".writeBtn").click(
                              function() {
                                $("#commonForm").attr("method", "get").attr(
                                        "action", writepath).submit();
                              });

                      $(".replyBtn").click(
                              function() {
                                $("#seq").val("${article.seq}");
                                $("#commonForm").attr("method", "get").attr(
                                        "action", replypath).submit();
                              });

                      $(".newpage").click(
                              function() {
                                $("#pg").val(1);
                                $("#key").val("");
                                $("#word").val("");

                                $("#commonForm").attr("method", "get").attr(
                                        "action", listpath).submit();
                              });

                      $(".mvpage").click(
                              function() {
                                $("#commonForm").attr("method", "get").attr(
                                        "action", listpath).submit();
                              });

                      //////댓글///////////////////////////////

                      $("#memoBtn").click(function() {
                        //$("#memoview").empty();
                        var seq = "${article.seq}";
                        var mcontent = $("#mcontent").val();
                        var parameter = JSON.stringify({
                          'seq': seq,
                          'mcontent': mcontent
                        });

                        if (mcontent.trim().length != 0) {
                          $.ajax({
                            url: '${root}/memo',
                            type: 'POST',
                            contentType: "application/json;charset=UTF-8",
                            dataType: 'json',
                            data: parameter,
                            success: function(data) {
                              makeList(data);
                              $("#mcontent").val("");
                            }
                          });
                        }

                      });

                      // 수정 시작
                      $(document).on("click", ".viewMemoModifyBtn", function() {
                        var mseq = $(this).parent("td").attr("memo-mseq");
                        $("#div" + mseq).css("display", "");
                      });

                      // 수정 후 취소
                      $(document).on("click", ".memoCancelBtn", function() {
                        var mseq = $(this).parents("td").attr("memo-mseq");
                        $("#div" + mseq).css("display", "none");
                      });

                      // 최종 수정 버튼 클릭
                      $(document).on("click", ".memoModifyBtn", function() {
                        var mseq = $(this).parents("td").attr("memo-mseq");
                        $("#div" + mseq).css("display", "none");
                        var seq = '${article.seq}';
                        var mcontent = $("#mcontent" + mseq).val();
                        var parameter = JSON.stringify({
                          'mseq': mseq,
                          'seq': seq,
                          'mcontent': mcontent
                        });
                        if (mcontent.trim().length != 0) {
                          $.ajax({
                            url: '${root}/memo',
                            type: 'PUT',
                            contentType: "application/json;charset=UTF-8",
                            dataType: 'json',
                            data: parameter,
                            success: function(data) {
                              //$("#memoview").empty();
                              makeList(data);
                            }
                          });
                        }
                      });

                      // 삭제
                      $(document).on("click", ".memoDeleteBtn", function() {
                        if (confirm("정말삭제?")) {
                          var mseq = $(this).parent("td").attr("memo-mseq");
                          $.ajax({
                            url: '${root}/memo/${article.seq}/' + mseq,
                            type: 'DELETE',
                            contentType: "application/json;charset=UTF-8",
                            dataType: 'json',
                            success: function(data) {
                              makeList(data);
                            }
                          });

                        }
                      });

                      function getMemoList() {
                        $.ajax({
                          url: '${root}/memo/${article.seq}',
                          type: 'GET',
                          contentType: "application/json;charset=UTF-8",
                          dataType: 'json',
                          success: function(data) {
                            makeList(data);
                          }
                        });
                      }

                      function makeList(memos) {
                        $("#memoview").empty();
                        var mlist = memos.memolist;
                        var output = '';
                        var len = mlist.length;
                        for (var i = 0; i < len; i++) {
                          output += '<tr>';
                          output += '	<td width="150" height="70">'
                                  + mlist[i].name + '</td>';
                          output += ' <td>' + mlist[i].mcontent + '</td>';
                          output += ' <td width="200">' + mlist[i].mtime
                                  + '</td>';
                          if (mlist[i].id = '${userInfo.id}') {
                            output += '<td width="120" memo-mseq="' + mlist[i].mseq + '">';
                            output += '	<label class="viewMemoModifyBtn">수정</label> / ';
                            output += '	<label class="memoDeleteBtn">삭제</label>';
                            output += '</td>';
                            output += '</tr>';
                            output += '<tr>';
                            output += '	<td colspan="4" memo-mseq="' + mlist[i].mseq + '">';
                            output += ' 	<div id="div' + mlist[i].mseq + '" style="display: none; ">';
                            output += ' 		<textarea id="mcontent' + mlist[i].mseq + '" style=" resize: none; " rows="3" cols="150" "></textarea>';
                            output += ' 		<input type = "button" value="수정" class="memoModifyBtn">';
                            output += ' 	<input type = "button" value="취소" class="memoCancelBtn">';
                            output += ' 	</div>';
                            output += ' </td>';
                          }
                          output += '</tr>';
                          output += '<tr> ';
                          output += '<td class="bg_board_title_02" height="1" colspan="11"';
							output +='	style="overflow: hidden; padding: 0px"></td>';
                          output += '</tr>';
                        }

                        $("#memoview").append(output);

                      }

                    });
  </script>
	<!-- title -->
	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td><img src="${root}/img/board/m_icon_board.gif" width="9"
				height="9" border="0" align="absmiddle" style="margin-top: -2px">
				<b>자유게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운
				글을 올리는 공간입니다<br></td>
			<td align="right"></td>
		</tr>
		<tr>
			<td colspan="2" height="19"></td>
		</tr>
	</table>

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<form name="bbsForm" id="bbsbbs" method="post">
			<input type="hidden" name="" value="">
			<tr>
				<td valign="bottom" nowrap><img
					src="${root}/img/board/btn_write_01.gif" width="64" height="22"
					align="absmiddle" alt="글쓰기" class="writeBtn"> <img
					src="${root}/img/board/btn_reply.gif" width="40" height="22"
					align="absmiddle" alt="답글" class="replyBtn"> <c:if
						test="${userInfo.id == article.id}">
						<img src="${root}/img/board/btn_modify.gif" align="absmiddle"
							alt="글수정" class="modifyBtn">
						<img src="${root}/img/board/btn_delete.gif" border="0"
							align="absmiddle" alt="글삭제" class="deleteBtn">
					</c:if></td>



				<td valign="bottom" width="100%" style="padding-left: 4px"></td>
				<td align="right" nowrap valign="bottom"><label class="newpage">최신목록</label>
					<font color="#c5c5c5">|</font> <labe class="mvpage">목록</labe> <font
					color="#c5c5c5">|</font> <a href="javascript:goBbsRead();"><img
						src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
						hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
					href="javascript:goBbsRead();">아랫글<img
						src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
						hspace="3"></a></td>
			</tr>
			<tr>
				<td colspan="3" height="5" style="padding: 0px"></td>
			</tr>
	</table>

	<table border="0" cellpadding="5" cellspacing="0" width="100%">
		<tr>
			<td class="bg_board_title_02" colspan="2" height="2"
				style="overflow: hidden; padding: 0px"></td>
		</tr>
		<tr height="28">
			<td class="bg_board_title" colspan="2" style="padding-left: 14px">
				<b><font class="text">${article.subject}</font></b>
			</td>
		</tr>
		<tr>
			<td class="bg_board_title_02" colspan="2" height="1"
				style="overflow: hidden; padding: 0px"></td>
		</tr>
		<tr height="26">
			<td width="100%" style="padding-left: 14px"><font class="stext">글번호
					:</font> <font class="text_commentnum">${article.seq}</font> &nbsp; <font
				class="stext">글쓴이 :</font> <a href="javascript:;"
				onClick="showSideView();" class="link_board_02">${article.name}</a><br>
			</td>
			<td style="padding-right: 14px" nowrap class="stext">조회 : <font
				class="text_commentnum">${article.hit}</font> &nbsp; 스크랩 : <font
				class="text_commentnum">0</font> &nbsp; 날짜 : <font
				class="text_commentnum">${article.logtime}</font></td>
		</tr>
		<tr>
			<td class="bg_board_title_02" colspan="2" height="1"
				style="overflow: hidden; padding: 0px"></td>
		</tr>
	</table>

	<table border="0" cellpadding="15" cellspacing="0" width="100%">
		<tr valign="top">
			<td bgcolor="#ffffff" width="100%" class="text"
				style="padding-bottom: 8px; line-height: 1.3" id="clix_content">



				<P>${article.content}</P>



			</td>
			<td nowrap valign="top" align="right" style="padding-left: 0px">

			</td>
		</tr>
	</table>

	<table width="100%" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td class="bg_board_title_02" height="1"
				style="overflow: hidden; padding: 0px"></td>
		</tr>
	</table>

	<!-- 하단 페이징 -->
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tr>
			<td colspan="3" height="5" style="padding: 0px"></td>
		</tr>
		<tr valign="top">
			<td nowrap><img src="${root}/img/board/btn_write_01.gif"
				width="64" height="22" border="0" align="absmiddle" alt="글쓰기"
				class="writeBtn"> <img src="${root}/img/board/btn_reply.gif"
				width="40" height="22" border="0" align="absmiddle" alt="답글"
				class="replyBtn"></td>

			<td style="padding-left: 4px" width="100%"><a href=""
				target="new"><img src="${root}/img/board/btn_print.gif"
					width="30" height="18" border="0" align="absmiddle" alt="인쇄"></a></td>

			<td align="right" nowrap><label class="newpage">최신목록</label> <font
				color="#c5c5c5">|</font> <labe class="mvpage">목록</labe> <font
				color="#c5c5c5">|</font> <a href="javascript:goBbsRead();"><img
					src="${root}/img/board/icon_up.gif" border="0" align="absmiddle"
					hspace="3">윗글</a> <font color="#c5c5c5">|</font> <a
				href="javascript:goBbsRead();">아랫글<img
					src="${root}/img/board/icon_down.gif" border="0" align="absmiddle"
					hspace="3"></a></td>
		</tr>
	</table>
	<br>

	<table cellpadding="5" cellspacing="5" border="0" width="100%">
		<tr>

			<td colspan="4"><textarea id="mcontent" style="resize: none;"
					rows="3" cols="215" placeholder="댓글"></textarea> <input
				type="button" value="작성" id="memoBtn"></td>

		</tr>

		<tbody id="memoview"></tbody>
	</table>


	</body>
	</html>

</c:if>