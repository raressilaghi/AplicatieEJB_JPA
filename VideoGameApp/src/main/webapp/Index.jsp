<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store,no-cache");
%>
<%@ page session="true"%>
<%@ page import="java.util.*,p.dtos.*,p.beans.*"%>
<html><head><title>Start page</title></head>
<body style="display: flex;justify-content: center;">
    <%! BeanJSP bean; %>
    <% bean = new BeanJSP(); session.setAttribute("bean", bean); %>
    <form method="post" action="controller"> <input type="submit" name="start" value="START_APPLICATION" style="
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    background-color: #008CBA;"/> </form>
</body>
</html>
