<%--
  Created by IntelliJ IDEA.
  User: stefana-ciudin
  Date: 10/19/24
  Time: 5:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>File Upload with CAPTCHA</title>
</head>
<body>
<h2>Upload a Text File</h2>
<form action="FileUploadServlet" method="post" enctype="multipart/form-data">
  <%--@declare id="captcha"--%><input type="file" name="file" accept=".txt" required />
  <br/><br/>

  <%
    // Generate simple CAPTCHA (e.g., sum of two random numbers)
    int num1 = (int) (Math.random() * 10);
    int num2 = (int) (Math.random() * 10);
    session.setAttribute("captchaResult", num1 + num2);
  %>
  <label for="captcha">What is <%= num1 %> + <%= num2 %>? </label>
    <label>
      <input type="text" name="captchaAnswer" required />
    </label>
    <br/><br/>

  <input type="submit" value="Upload File" />
</form>
</body>
</html>

