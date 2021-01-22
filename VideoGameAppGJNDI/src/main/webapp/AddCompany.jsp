<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store,no-cache");
%>
<%@ page session="true"%>
<%@ page import="java.util.*,p.dtos.*,p.beans.*"%>
<html>
<head>
    <style type="text/css">
        input[type=text], select{
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        input[type=submit] {
            background-color: #008CBA;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #008C;
        }
        form{
            border-radius: 5px;
            padding: 20px;
            width: 500px;
        }
        td{
            font-size: 20px;
            font-family: Arial, Helvetica, sans-serif;
        }
    </style>
<title>Company</title>
</head>
<body>
    <h2>Add Company</h2>
    <% BeanJSP bean = (BeanJSP)session.getAttribute("bean");
    if (bean != null) { %>
    <form method="post" action="clientJNDI">
        <table>
            <tr>
                <td>Name:</td>
                <td colspan="2"><input type="text" name="companyName" value="" /></td>
            </tr><tr>
                <td>&nbsp;</td>
            </tr><tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="companyAdd" value="Add" /></td>
                <td><input type="submit" name="cancel" value="Cancel" /></td>
            </tr>
        </table>
    </form>
     <% } else { %>
    <form method="post" action="Index.jsp">
        INCORRECT URL!
        <input type="submit" value="RESTART" />
    </form>
     <% } %>
</body>
</html>
