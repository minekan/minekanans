<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="sample.sastruts.test.entity.IdTest" %>
<html>
<head>
<title>Sample</title>
</head>
<script type="text/javascript" src="${f:url('/js/jquery-3.2.1.js')}"></script>
<script type="text/javascript">
$(function(){
	  // ここにプログラムを記述
	  $('[name="detail"]').click(function() {
		  $(':input[name="id"]').val($(this).closest('tr').children('[name="id"]').text());
		  $(this).parents('form').attr('action', '/SAStrutsTest/dbTest/detail');
		  $(this).parents('form').submit();
	  });
	  $('[name="newData"]').click(function() {
		  $(this).parents('form').attr('action', '/SAStrutsTest/dbTest/newData');
		  $(this).parents('form').submit();
	  });
	  $('[name="load"]').click(function() {
		  $.ajax({
			    url: '${f:url("/dbTest/load")}',
			    type: 'GET',
			    data :{'id': $('[name="id"]').val()},
			    dataType: 'json',
			    cache: false, //ブラウザにキャッシュさせません。
			    error: function(){
			        alert('DBの読み込みに失敗しました');
			    },
			    success: function(json){
			        $('#resultList').html('');
			        for( var i=0; i<json.length; i++ ) {
			            $('#resultList').append('<tr>');
			            $('#resultList').append('<td><input type="button" name="detail" value="更新" /></td>');
			            $('#resultList').append('<td name="id">' + json[i].id + '</td>');
			            $('#resultList').append('<td name="val">' + json[i].val + '</td>');
			            $('#resultList').append('</tr>');
			        }
			    }
			});
		});
	});

</script>
<body>
<s:form>
    key:<html:text property="id"/><br/>
    <input type="button" name="load" value="一覧更新" />
    <input type="button" name="newData" value="新規登録" />
    <input type="submit" name="index" value="検索" />
    <br/>
    <br/>
	<table border="1" id="resultList">
	<c:forEach var="item" items="${ resultList }">
		<tr>
		<td><input type="button" name="detail" value="更新" /></td>
		<td name="id">${item.id}</td>
		<td name="val">${item.val}</td></tr>
	</c:forEach>
	</table>
</s:form>
</body>
</html>
