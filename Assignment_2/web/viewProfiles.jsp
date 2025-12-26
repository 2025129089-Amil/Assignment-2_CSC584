<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="beans.ProfileBean" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>View All Profiles</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eef7ff;
            margin: 0;
            padding: 20px;
        }
        
        .container {
            max-width: available;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        
        h1 {
            color: #004e92;
            text-align: center;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 2px solid #004e92;
        }
        
        h2 {
            color: #555;
            text-align: center;
            margin-bottom: 20px;
        }
        
        .message {
            padding: 12px;
            margin: 15px 0;
            border-radius: 5px;
            text-align: center;
            font-weight: bold;
        }
        
        .success {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .error {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        
        .filter-section {
            background: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin: 20px 0 30px 0;
            border: 1px solid #dee2e6;
        }
        
        .filter-row {
            display: flex;
            gap: 20px;
            margin-bottom: 15px;
            align-items: flex-end;
            flex-wrap: wrap;
        }
        
        .filter-group {
            display: flex;
            flex-direction: column;
        }
        
        label {
            font-weight: bold;
            margin-bottom: 5px;
            color: #333;
        }
        
        select, input {
            padding: 8px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }
        
        .btn {
            padding: 8px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
        }
        
        .btn-primary {
            background: #007bff;
            color: white;
        }
        
        .btn-primary:hover {
            background: #0056b3;
        }
        
        .btn-secondary {
            background: #6c757d;
            color: white;
        }
        
        .btn-secondary:hover {
            background: #545b62;
        }
        
        .btn-success {
            background: #28a745;
            color: white;
        }
        
        .btn-success:hover {
            background: #1e7e34;
        }
        
        .btn-danger {
            background: #dc3545;
            color: white;
        }
        
        .btn-danger:hover {
            background: #c82333;
        }
        
        .profiles-table-container {
            overflow-x: auto;
            margin: 30px 0;
            border-radius: 8px;
            border: 1px solid #dee2e6;
        }
        
        .profiles-table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
        }
        
        .profiles-table th {
            background-color: #004e92;
            color: white;
            padding: 15px;
            text-align: left;
            font-weight: bold;
            border-bottom: 2px solid #003366;
        }
        
        .profiles-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #dee2e6;
            vertical-align: middle;
        }
        
        .profiles-table tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        
        .profiles-table tr:hover {
            background-color: #e9ecef;
        }
        
        .profiles-table tr:last-child td {
            border-bottom: none;
        }
        
        .action-buttons {
            text-align: center;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #dee2e6;
        }
        
        .action-buttons a {
            display: inline-block;
            margin: 0 10px;
            padding: 12px 25px;
            background: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
        }
        
        .action-buttons a:hover {
            background: #1e7e34;
        }
        
        .empty-state {
            text-align: center;
            padding: 50px;
            color: #666;
            font-size: 16px;
            background: #f8f9fa;
            border-radius: 8px;
            border: 1px dashed #ccc;
            margin: 20px 0;
        }
        
        .current-filter {
            background: #fff3cd;
            padding: 15px;
            border-radius: 6px;
            margin: 20px 0;
            border-left: 4px solid #ffc107;
            font-weight: bold;
        }
        
        .clear-filter {
            float: right;
            color: #dc3545;
            text-decoration: none;
            font-weight: normal;
        }
        
        .clear-filter:hover {
            text-decoration: underline;
        }
        
        .table-hobbies {
            max-width: 200px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        
        .table-intro {
            max-width: 300px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        
        @media (max-width: 768px) {
            .filter-row {
                flex-direction: column;
                align-items: stretch;
            }
            
            .filter-group {
                width: 100%;
            }
            
            .profiles-table th,
            .profiles-table td {
                padding: 8px;
                font-size: 13px;
            }
            
            .container {
                padding: 15px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>📋 All Student Profiles</h1>
        
        <% 
            String message = (String) request.getAttribute("message");
            String errorMsg = (String) request.getAttribute("error");
            String filterLabel = (String) request.getAttribute("filterLabel");
            List<ProfileBean> profiles = (List<ProfileBean>) request.getAttribute("profiles");
            List<String> programs = (List<String>) request.getAttribute("programs");
            List<String> hobbies = (List<String>) request.getAttribute("hobbies");
            String selectedProgram = (String) request.getAttribute("selectedProgram");
            String selectedHobby = (String) request.getAttribute("selectedHobby");
        %>
        
        <% if (message != null) { %>
            <div class="message success">
                <%= message %>
            </div>
        <% } %>
        
        <% if (errorMsg != null) { %>
            <div class="message error">
                <%= errorMsg %>
            </div>
        <% } %>
        
        <% if (filterLabel != null && !filterLabel.isEmpty() && !"All Profiles".equals(filterLabel)) { %>
            <div class="current-filter">
                Currently showing: <strong><%= filterLabel %></strong>
                <a href="ViewProfiles" class="clear-filter">Clear Filter ✕</a>
            </div>
        <% } %>
        
        <div class="filter-section">
            <h3 style="margin-top: 0; color: #004e92;">Filter Profiles</h3>
            
            <form action="FilterProfiles" method="get">
                <div class="filter-row">
                    <div class="filter-group">
                        <label for="programFilter">Filter by Program:</label>
                        <select name="programFilter" id="programFilter" class="form-control">
                            <option value="any">-- Any Program --</option>
                            <% 
                                if (programs != null) {
                                    for (String program : programs) {
                                        boolean isSelected = (selectedProgram != null && program.equals(selectedProgram)) || 
                                                             (selectedProgram == null && "any".equals(program));
                            %>
                                <option value="<%= program %>" <%= isSelected ? "selected" : "" %>>
                                    <%= program %>
                                </option>
                            <% 
                                    }
                                }
                            %>
                        </select>
                    </div>
                    
                    <div class="filter-group">
                        <label for="hobbyFilter">Filter by Hobby:</label>
                        <select name="hobbyFilter" id="hobbyFilter" class="form-control">
                            <option value="any">-- Any Hobby --</option>
                            <% 
                                if (hobbies != null) {
                                    for (String hobby : hobbies) {
                                        boolean isSelected = (selectedHobby != null && hobby.equals(selectedHobby)) || 
                                                             (selectedHobby == null && "any".equals(hobby));
                            %>
                                <option value="<%= hobby %>" <%= isSelected ? "selected" : "" %>>
                                    <%= hobby %>
                                </option>
                            <% 
                                    }
                                }
                            %>
                        </select>
                    </div>
                    
                    <div class="filter-group">
                        <button type="submit" class="btn btn-primary">Apply Filters</button>
                        <a href="ViewProfiles" style="margin-top: 5px;">
                            <button type="button" class="btn btn-secondary">View All</button>
                        </a>
                    </div>
                </div>
                
                <div style="margin-top: 10px; color: #666; font-size: 13px;">
                    <strong>Filter Logic:</strong> Both filters work together. Select "Any Program" or "Any Hobby" to ignore that filter.
                </div>
            </form>
        </div>
        
        <div class="profiles-table-container">
            <% if (profiles != null && !profiles.isEmpty()) { %>
                <table class="profiles-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Program</th>
                            <th>Email</th>
                            <th>Hobbies</th>
                            <th>Self Introduction</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (ProfileBean profile : profiles) { %>
                            <tr>
                                <td><strong><%= profile.getStudentId() %></strong></td>
                                <td><%= profile.getFullName() %></td>
                                <td><%= profile.getProgram() %></td>
                                <td><%= profile.getEmail() %></td>
                                <td class="table-hobbies" title="<%= profile.getHobbies() %>">
                                    <%= profile.getHobbies() != null && !profile.getHobbies().isEmpty() ? 
                                        profile.getHobbies() : "No hobbies" %>
                                </td>
                                <td class="table-intro" title="<%= profile.getIntroduction() %>">
                                    <%= profile.getIntroduction() != null && !profile.getIntroduction().isEmpty() ? 
                                        profile.getIntroduction() : "No introduction" %>
                                </td>
                                <td>
                                    <form action="DeleteProfile" method="get" 
                                          onsubmit="return confirm('Delete profile of <%= profile.getFullName() %>?')" 
                                          style="margin: 0;">
                                        <input type="hidden" name="studentId" value="<%= profile.getStudentId() %>">
                                        <button type="submit" class="btn btn-danger" style="padding: 6px 12px; font-size: 13px;">
                                            Delete
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            <% } else { %>
                <div class="empty-state">
                    <% if (selectedProgram != null || selectedHobby != null) { %>
                        <p>No profiles found with the current filter criteria.</p>
                        <p>Try changing your filter or view all profiles.</p>
                        <a href="ViewProfiles">
                            <button class="btn btn-secondary" style="margin-top: 15px;">View All Profiles</button>
                        </a>
                    <% } else { %>
                        <p>No student profiles found in the database.</p>
                        <p>Be the first to create a profile!</p>
                        <a href="index.jsp">
                            <button class="btn btn-success" style="margin-top: 15px;">Create First Profile</button>
                        </a>
                    <% } %>
                </div>
            <% } %>
        </div>
        
        <div class="action-buttons">
            <a href="index.jsp">Register New Student</a>
            <a href="ViewProfiles">View All Profiles</a>
        </div>
        
        <div style="text-align: center; margin-top: 30px; padding-top: 20px; border-top: 1px solid #dee2e6;">
            <a href="index.jsp" style="color: #007bff; text-decoration: none; font-weight: bold;">
                ← Back to Registration Form
            </a>
        </div>
    </div>
    
    <script>
        function confirmDelete(name) {
            return confirm('Are you sure you want to delete ' + name + "'s profile?");
        }
    </script>
</body>
</html>