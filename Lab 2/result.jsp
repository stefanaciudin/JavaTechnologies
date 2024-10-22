<%--
  Created by IntelliJ IDEA.
  User: stefana-ciudin
  Date: 10/19/24
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Shuffled Lines</title>
</head>
<body>
<h2>Shuffled Lines of the File</h2>

<%
  // Retrieve shuffled lines from session
  List<String> shuffledLines = (List<String>) session.getAttribute("shuffledLines");
  if (shuffledLines != null) {
    for (String line : shuffledLines) {
%>
<p><%= line %></p>
<%
  }
} else {
%>
<p>No file uploaded or shuffled lines found.</p>
<%
  }
%>

<a href="input.jsp">Upload Another File</a>
</body>
</html>

