<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-store,no-cache");
%>
<%@ page session="true"%>
<%@ page import="java.util.*,p.dtos.*,p.beans.*"%>

<html><head>
    <style type="text/css">
        .tbl {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;

        }
        .tbl th, .tbl td{
            border: 3px solid black;
            padding: 8px;
        }
        .tbl th{
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #008CBA;
            color: white;
        }
        #t, #t2{
            border: 3px solid white;
            padding: 8px;
        }
        input[type="submit" i]{
            background-color: #555555;
            border: none;
            color: white;
            padding: 10px 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
        }

    </style>
    <title>General view</title>
    </head>
<body>
    <h2 style="font-size: 30px; font-family: sans-serif; text-align: center; margin-bottom: 100px; text-decoration: underline;">Companies and Games</h2>
    <%! BeanJSP bean; %>
    <% bean = (BeanJSP)session.getAttribute("bean");
    if (bean != null) { %>
    <form method="post" action="controller">
        <table >
            <% for (DeveloperCompanyDTO d : bean.companies) { %>
                <tr>
                    <td><b style=" font-weight: bold; font-size: 20px; font-family: sans-serif;"><%=d.name%></b></td>
                    <td><input type="submit" name="viewCompanyUpdate_<%=d.id%>" value="Update company" /></td>
                    <td><input type="submit" name="viewCompanyDelete_<%=d.id%>" value="Delete company" /></td>
                </tr>
                <tr>
                    <td><table border="1" style="border-spacing: 5px;" class="tbl">
                        <tr>
                            <th>Title</th><th>Year</th><th>Type</th>
                        </tr>
                        <% for (VideoGameDTO e : d.games) { %>
                            <tr>
                                <td><%=e.title%></td><td><%=e.year%></td><td><%=e.type%></td>
                                <td id="t"><input type="submit" name="viewGameUpdate_<%=e.id%>" value="Update game" /></td>
                                <td id="t2"><input type="submit" name="viewGameDelete_<%=e.id%>" value="Delete game" /></td>
                            </tr>
                        <% } %>
                    </table></td>
                </tr>
                <tr>
                    <td><input type="submit" name="viewGameAdd_<%=d.id%>" value="Add an game to company" /></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
            <% } %>
            <tr>
                <td><input type="submit" name="viewCompanyAdd" value="Add a company" /></td>
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
