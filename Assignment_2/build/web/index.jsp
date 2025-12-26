<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Student Profile Registration</title>
    <style>
        body {
            background-color: #eef7ff;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 900px;
            background: #fff;
            border-radius: 10px;
            padding: 10px;
            margin: 30px auto;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            margin-bottom: 20px;
            font-weight: 600;
            text-align: center;
            color: #004e92;
        }

        label {
            font-weight: bold;
        }

        .form-row {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }

        .form-group {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .form-control {
            padding: 12px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }

        textarea.form-control {
            resize: vertical;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
            display: block;
            margin-top: 20px;
            width: 100%;
        }

        button:hover {
            background-color: #0056b3;
        }
        
        .error-message {
            background: #f8d7da;
            color: #721c24;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            text-align: center;
            border: 1px solid #f5c6cb;
        }
        
        .nav-link {
            text-align: center;
            margin-top: 20px;
        }
        
        .nav-link a {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        
        .nav-link a:hover {
            text-decoration: underline;
        }
        
        .required::after {
            content: " *";
            color: #dc3545;
        }
        
        .form-note {
            font-size: 14px;
            color: #666;
            margin-top: 5px;
        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Student Profile Registration</h2>
        
        <% 
            String error = (String) request.getAttribute("error");
            if (error != null) { 
        %>
            <div class="error-message">
                <%= error %>
            </div>
        <% } %>
        
        <form action="SaveProfile" method="POST" enctype="multipart/form-data" id="profileForm">
            <div class="form-row">
                <div class="form-group">
                    <label for="fullName" class="required">Full Name</label>
                    <input name="fullName" type="text" class="form-control" id="fullName" placeholder="e.g. Ali Bin Abu" required />
                    <div class="form-note">Will be converted to uppercase</div>
                </div>

                <div class="form-group">
                    <label for="studentId" class="required">Student ID</label>
                    <input name="studentId" type="text" class="form-control" id="studentId" placeholder="e.g. 2025123456" required />
                    <div class="form-note">Must be 10 digits</div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="program" class="required">Program</label>
                    <input name="program" type="text" class="form-control" id="program" placeholder="e.g. Computer Science" required />
                    <div class="form-note">Will be converted to uppercase</div>
                </div>

                <div class="form-group">
                    <label for="email" class="required">Email</label>
                    <input name="email" type="email" class="form-control" id="email" placeholder="e.g. student@uitm.edu.my" required />
                </div>
            </div>

            <div class="form-row">
                <div class="form-group" style="flex: 2;"> 
                    <label for="hobbies" class="required">Hobbies (comma separated)</label>
                    <textarea name="hobbies" class="form-control" id="hobbies" rows="3" placeholder="e.g., Jogging, Swimming, Baking" required></textarea>
                    <div class="form-note">Separate hobbies with commas</div>
                </div>

                <div class="form-group" style="flex: 1;"> 
                    <label for="profileImage" class="required">Profile Image</label>
                    <input name="profileImage" type="file" class="form-control" id="profileImage" accept="image/*" required />
                    <div class="form-note">Max 10MB, JPG/PNG</div>
                </div>
            </div>

            <div class="form-group">
                <label for="intro" class="required">Self-Introduction</label>
                <textarea name="intro" class="form-control" id="intro" rows="5" placeholder="Tell about yourself" required></textarea>
            </div>

            <button type="submit">Submit Profile</button>
        </form>
        
        <div class="nav-link" >
            <a href="ViewProfiles" style="color: #007bff; text-decoration: none; font-weight: bold;">
                View All Profiles
            </a>
        </div>
    </div>
    
    <script>
        document.getElementById('profileForm').addEventListener('submit', function(e) {
            const studentId = document.getElementById('studentId').value;
            const intro = document.getElementById('intro').value;
            
            if (!/^\d{10}$/.test(studentId)) {
                e.preventDefault();
                alert('Student ID must be exactly 10 digits (e.g., 2025123456)');
                return false;
            }
            
            if (intro.trim().length < 10) {
                e.preventDefault();
                alert('Please write a longer introduction (minimum 10 characters)');
                return false;
            }
            
            document.getElementById('fullName').value = document.getElementById('fullName').value.toUpperCase();
            document.getElementById('program').value = document.getElementById('program').value.toUpperCase();
            
            return true;
        });
        
        document.getElementById('profileImage').addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file) {
                const fileSize = file.size / 1024 / 1024; 
                if (fileSize > 10) {
                    alert('File size exceeds 10MB. Please choose a smaller file.');
                    this.value = '';
                }
            }
        });
        
        document.getElementById('fullName').addEventListener('input', function(e) {
            this.value = this.value.toUpperCase();
        });
        
        document.getElementById('program').addEventListener('input', function(e) {
            this.value = this.value.toUpperCase();
        });
    </script>
</body>
</html>