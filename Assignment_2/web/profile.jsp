<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>

    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #eef7ff;
            display: flex;
            justify-content: center;
            padding: 20px;
        }

        .container {
            background: #fff;
            width: 100%;
            max-width: 700px;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.15);
        }

        .header-content {
            display: flex;
            align-items: center;
            gap: 20px;
            margin-bottom: 5px;
            padding: 20px;
        }
        
        .profile-text {
            flex-grow: 1;
        }
        
        .profile-image {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 5px solid #007bff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }

        h1 {
            margin-bottom: 5px;
            font-size: 32px;
            color: #004e92;
        }

        h3 {
            margin-bottom: 15px;
            font-size: 20px;
            font-weight: normal;
            color: #007bff;
        }

        .row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 15px;
            font-size: 16px;
        }

        .label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }

        .value {
            background: #f9faff;
            padding: 10px;
            border-radius: 8px;
            border: 1px solid #ddd;
        }

        .hobbies-list {
            list-style: square;
            padding-left: 25px;
            margin-top: 8px;
        }

        .section {
            margin-bottom: 25px;
        }

        .return-button {
            display: block;
            width: 200px;
            margin: 30px auto 0 auto;
            padding: 12px;
            text-align: center;
            font-size: 16px;
            color: white;
            background-color: #28a745;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            cursor: pointer;
            transition: 0.3s;
        }

        .return-button:hover {
            background-color: #1e7e34;
        }
        
        .message {
            background: #d4edda;
            color: #155724;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            border: 1px solid #c3e6cb;
        }
    </style>
</head>

<body>
<div class="container">
    <% if (request.getAttribute("message") != null) { %>
        <div class="message"><%= request.getAttribute("message") %></div>
    <% } %>
    
    <div class="section value">
        <div class="header-content">
            <img 
                src="<%= request.getAttribute("profileImagePath") != null ? request.getAttribute("profileImagePath") : "default.jpg" %>" 
                alt="Profile Image" 
                class="profile-image" 
            />
            
            <div class="profile-text">
                <h1><%= request.getAttribute("fullName") %></h1>
                <h3><%= request.getAttribute("program") %></h3>
            </div>
        </div>
    </div>
   
    <div class="row">
        <div><span class="label">Student ID:</span> <%= request.getAttribute("studentId") %></div>
        <div><span class="label">Email:</span> <%= request.getAttribute("email") %></div>
    </div>

    <div class="section">
        <span class="label">Hobby:</span>
        <ul class="hobbies-list">
            <% 
                String[] hobbies = (String[]) request.getAttribute("hobbiesArray");
                if (hobbies != null) {
                    for (String hobby : hobbies) { 
                        if (hobby != null && !hobby.trim().isEmpty()) {
            %>
                <li><%= hobby.trim() %></li>
            <% 
                        }
                    }
                } else { 
            %>
                <li>No hobbies listed</li>
            <% } %>
        </ul>
    </div>

    <div class="section">
        <span class="label">Self-Introduction:</span>
        <div class="value"><%= request.getAttribute("intro") %></div>
    </div>

    <a href="index.jsp" class="return-button">Return to Form</a>
    <a href="ViewProfiles" style="display: block; width: 200px; margin: 10px auto 0 auto; padding: 12px; text-align: center; font-size: 16px; color: white; background-color: #007bff; border: none; border-radius: 8px; text-decoration: none; cursor: pointer;">View All Profiles</a>
</div>
</body>
</html>