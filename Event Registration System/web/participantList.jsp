<%-- 
    Document   : participantList
    Created on : Apr 9, 2025, 9:56:40 PM
    Author     : lahir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.nsbm.model.Participant" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Participant List</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        
        .container {
            max-width: 1000px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 25px;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        
        .filter-form {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 25px;
        }
        
        .filter-form input[type="text"] {
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
            width: 300px;
        }
        
        .filter-form input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 8px 16px;
            font-size: 14px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        .filter-form input[type="submit"]:hover {
            background-color: #2980b9;
        }
        
        .show-all {
            display: inline-block;
            margin-left: 10px;
            color: #3498db;
            text-decoration: none;
        }
        
        .add-new {
            display: inline-block;
            background-color: #2ecc71;
            color: white;
            padding: 10px 15px;
            border-radius: 4px;
            text-decoration: none;
            margin-bottom: 20px;
            transition: background-color 0.3s;
        }
        
        .add-new:hover {
            background-color: #27ae60;
        }
        
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }
        
        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
        }
        
        th {
            background-color: #3498db;
            color: white;
            font-weight: 600;
        }
        
        tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        
        tr:hover {
            background-color: #f1f1f1;
        }
        
        .no-data {
            color: #e74c3c;
            text-align: center;
            padding: 20px;
            font-size: 16px;
        }
        
        .action-link {
            color: #3498db;
            text-decoration: none;
            margin: 0 5px;
            transition: color 0.3s;
        }
        
        .action-link:hover {
            text-decoration: underline;
            color: #2980b9;
        }
        
        .delete-link {
            color: #e74c3c;
        }
        
        .delete-link:hover {
            color: #c0392b;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Participant Management</h1>

        <div class="filter-form">
            <form action="ParticipantServlet" method="get">
                Filter by Event: 
                <input type="text" name="event" value="<%= request.getParameter("event") != null ? request.getParameter("event") : "" %>"/>
                <input type="hidden" name="action" value="filter"/>
                <input type="submit" value="Filter"/>
                <a href="ParticipantServlet" class="show-all">Show All</a>
            </form>
        </div>

        <a href="eventForm.jsp" class="add-new">Add New Participant</a>

        <%
            List<Participant> participantList = (List<Participant>) request.getAttribute("participantList");
            if (participantList != null && !participantList.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Event</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Participant participant : participantList) {
                    %>
                        <tr>
                            <td><%= participant.getId() %></td>
                            <td><%= participant.getName() %></td>
                            <td><%= participant.getEmail() %></td>
                            <td><%= participant.getEvent() %></td>
                            <td>
                                <a href="ParticipantServlet?action=edit&id=<%= participant.getId() %>" class="action-link">Edit</a>
                                <span>|</span>
                                <a href="ParticipantServlet?action=delete&id=<%= participant.getId() %>"
                                   class="action-link delete-link"
                                   onclick="return confirm('Are you sure you want to delete this participant?')">Delete</a>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <p class="no-data">No participants found in the database.</p>
        <%
            }
        %>
    </div>
</body>
</html>