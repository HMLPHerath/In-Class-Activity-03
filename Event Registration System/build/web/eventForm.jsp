<%-- 
    Document   : eventForm
    Created on : Apr 9, 2025, 9:56:08 PM
    Author     : lahir
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Registration</title>
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 20px;
                color: #333;
            }
            
            .container {
                max-width: 600px;
                margin: 0 auto;
                background: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }
            
            h2 {
                color: #2c3e50;
                text-align: center;
                margin-bottom: 25px;
                border-bottom: 2px solid #3498db;
                padding-bottom: 10px;
            }
            
            .form-group {
                margin-bottom: 20px;
            }
            
            label {
                display: block;
                margin-bottom: 8px;
                font-weight: 600;
                color: #2c3e50;
            }
            
            input[type="text"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 16px;
                box-sizing: border-box;
            }
            
            input[type="text"]:focus {
                border-color: #3498db;
                outline: none;
                box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
            }
            
            input[type="submit"] {
                background-color: #3498db;
                color: white;
                border: none;
                padding: 12px 20px;
                font-size: 16px;
                border-radius: 4px;
                cursor: pointer;
                width: 100%;
                transition: background-color 0.3s;
            }
            
            input[type="submit"]:hover {
                background-color: #2980b9;
            }
            
            .view-link {
                display: block;
                text-align: center;
                margin-top: 20px;
                color: #3498db;
                text-decoration: none;
                font-weight: 600;
            }
            
            .view-link:hover {
                text-decoration: underline;
            }
            
            .hidden-field {
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Register for Event</h2>
            <form action="ParticipantServlet" method="post">
                <div class="hidden-field">
                    <input type="hidden" name="action" value="register"/>
                </div>
                
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" placeholder="Enter your full name"/>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" placeholder="Enter your email address"/>
                </div>
                
                <div class="form-group">
                    <label for="event">Event:</label>
                    <input type="text" id="event" name="event" placeholder="Enter event name"/>
                </div>
                
                <input type="submit" value="Register"/>
            </form>
            <a href="ParticipantServlet" class="view-link">View Participants</a>
        </div>
    </body>
</html>