<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>
</head>
<body>
	<h1>�α���</h1>
	<form action="LoginServlet" method="post">
		<label for="memail">�̸��� : </label>
		<input type="email" id="memail" name="m_email" required><br>
		<label for="mno">�α����� ��й�ȣ : </label>
		<input type="password" id="mno" name="m_no" required><br>
		<input type="submit" value="�α���">
	</form>
</body>
</html>