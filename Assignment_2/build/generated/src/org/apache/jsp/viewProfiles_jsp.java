package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import beans.ProfileBean;
import java.util.List;

public final class viewProfiles_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>View All Profiles</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: Arial, sans-serif;\n");
      out.write("            background: #f4f4f4;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .container {\n");
      out.write("            max-width: 1200px;\n");
      out.write("            margin: auto;\n");
      out.write("            background: white;\n");
      out.write("            padding: 20px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            box-shadow: 0 0 10px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        h1 {\n");
      out.write("            color: #333;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        h2 {\n");
      out.write("            color: #555;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .message {\n");
      out.write("            padding: 10px;\n");
      out.write("            margin: 10px 0;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .success {\n");
      out.write("            background: #d4edda;\n");
      out.write("            color: #155724;\n");
      out.write("            border: 1px solid #c3e6cb;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .error {\n");
      out.write("            background: #f8d7da;\n");
      out.write("            color: #721c24;\n");
      out.write("            border: 1px solid #f5c6cb;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .filter-section {\n");
      out.write("            background: #f8f9fa;\n");
      out.write("            padding: 15px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            margin: 20px 0;\n");
      out.write("            border: 1px solid #ddd;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .filter-box {\n");
      out.write("            margin: 10px 0;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        label {\n");
      out.write("            font-weight: bold;\n");
      out.write("            display: block;\n");
      out.write("            margin: 5px 0;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        select, input, button {\n");
      out.write("            padding: 8px;\n");
      out.write("            margin: 5px 5px 5px 0;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 4px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        input[type=\"text\"] {\n");
      out.write("            width: 200px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        button {\n");
      out.write("            background: #007bff;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        button:hover {\n");
      out.write("            background: #0056b3;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-danger {\n");
      out.write("            background: #dc3545;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-danger:hover {\n");
      out.write("            background: #c82333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-secondary {\n");
      out.write("            background: #6c757d;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-secondary:hover {\n");
      out.write("            background: #5a6268;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-success {\n");
      out.write("            background: #28a745;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .btn-success:hover {\n");
      out.write("            background: #1e7e34;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profiles-grid {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));\n");
      out.write("            gap: 20px;\n");
      out.write("            margin-top: 20px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profile-card {\n");
      out.write("            border: 1px solid #ddd;\n");
      out.write("            padding: 15px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            background: white;\n");
      out.write("            position: relative;\n");
      out.write("            box-shadow: 0 2px 5px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profile-header {\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            padding-bottom: 15px;\n");
      out.write("            border-bottom: 1px solid #eee;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profile-img {\n");
      out.write("            width: 80px;\n");
      out.write("            height: 80px;\n");
      out.write("            border-radius: 50%;\n");
      out.write("            object-fit: cover;\n");
      out.write("            margin-right: 15px;\n");
      out.write("            border: 2px solid #007bff;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profile-info h3 {\n");
      out.write("            margin: 0;\n");
      out.write("            color: #333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profile-info p {\n");
      out.write("            margin: 5px 0;\n");
      out.write("            color: #666;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .profile-details {\n");
      out.write("            margin-top: 10px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .detail-row {\n");
      out.write("            margin: 8px 0;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .label {\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #555;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .delete-btn {\n");
      out.write("            position: absolute;\n");
      out.write("            top: 15px;\n");
      out.write("            right: 15px;\n");
      out.write("            background: #dc3545;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            padding: 5px 10px;\n");
      out.write("            border-radius: 3px;\n");
      out.write("            cursor: pointer;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .delete-btn:hover {\n");
      out.write("            background: #c82333;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .empty-state {\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 40px;\n");
      out.write("            color: #666;\n");
      out.write("            font-size: 18px;\n");
      out.write("            grid-column: 1 / -1;\n");
      out.write("            background: #f8f9fa;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            border: 1px dashed #ddd;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .action-buttons {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 20px;\n");
      out.write("            padding-top: 20px;\n");
      out.write("            border-top: 1px solid #eee;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .action-buttons a {\n");
      out.write("            display: inline-block;\n");
      out.write("            margin: 0 10px;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            background: #28a745;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .action-buttons a:hover {\n");
      out.write("            background: #1e7e34;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .current-filter {\n");
      out.write("            background: #fff3cd;\n");
      out.write("            padding: 10px;\n");
      out.write("            border-radius: 5px;\n");
      out.write("            margin: 10px 0;\n");
      out.write("            border-left: 4px solid #ffc107;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .filter-actions {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 10px;\n");
      out.write("            align-items: center;\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        .form-control {\n");
      out.write("            padding: 8px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 4px;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <h1>📋 Student Profiles</h1>\n");
      out.write("        \n");
      out.write("        ");
 
            String message = (String) request.getAttribute("message");
            String errorMsg = (String) request.getAttribute("error");
        
      out.write("\n");
      out.write("        \n");
      out.write("        ");
 if (message != null) { 
      out.write("\n");
      out.write("            <div class=\"message success\">\n");
      out.write("                ");
      out.print( message );
      out.write("\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        \n");
      out.write("        ");
 if (errorMsg != null) { 
      out.write("\n");
      out.write("            <div class=\"message error\">\n");
      out.write("                ");
      out.print( errorMsg );
      out.write("\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        \n");
      out.write("        <!-- Show current filter -->\n");
      out.write("        ");
 
            String filterLabel = (String) request.getAttribute("filterLabel");
            
            if (filterLabel != null && !filterLabel.isEmpty() && !"All Profiles".equals(filterLabel)) {
        
      out.write("\n");
      out.write("            <div class=\"current-filter\">\n");
      out.write("                Currently showing: <strong>");
      out.print( filterLabel );
      out.write("</strong>\n");
      out.write("                <a href=\"ViewProfiles\" style=\"float: right; color: #dc3545; text-decoration: none;\">\n");
      out.write("                    Clear Filter ✕\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        \n");
      out.write("        <!-- UNIQUE FEATURE: Dual Filter Section -->\n");
      out.write("        <div class=\"filter-section\">\n");
      out.write("            <h3>Filter Profiles</h3>\n");
      out.write("            \n");
      out.write("            <form action=\"FilterProfiles\" method=\"get\">\n");
      out.write("                <div class=\"filter-box\">\n");
      out.write("                    <div style=\"display: flex; gap: 20px; align-items: flex-end; flex-wrap: wrap;\">\n");
      out.write("                        <!-- Program Filter -->\n");
      out.write("                        <div>\n");
      out.write("                            <label for=\"programFilter\">Filter by Program:</label>\n");
      out.write("                            <select name=\"programFilter\" id=\"programFilter\" class=\"form-control\" style=\"width: 200px;\">\n");
      out.write("                                <option value=\"any\">-- Any Program --</option>\n");
      out.write("                                ");
 
                                    List<String> programs = (List<String>) request.getAttribute("programs");
                                    String selectedProgram = (String) request.getAttribute("selectedProgram");
                                    if (programs != null) {
                                        for (String program : programs) {
                                            boolean isSelected = (selectedProgram != null && program.equals(selectedProgram)) || 
                                                                 (selectedProgram == null && "any".equals(program));
                                
      out.write("\n");
      out.write("                                    <option value=\"");
      out.print( program );
      out.write('"');
      out.write(' ');
      out.print( isSelected ? "selected" : "" );
      out.write('>');
      out.print( program );
      out.write("</option>\n");
      out.write("                                ");
 
                                        }
                                    }
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <!-- Hobby Filter -->\n");
      out.write("                        <div>\n");
      out.write("                            <label for=\"hobbyFilter\">Filter by Hobby:</label>\n");
      out.write("                            <select name=\"hobbyFilter\" id=\"hobbyFilter\" class=\"form-control\" style=\"width: 200px;\">\n");
      out.write("                                <option value=\"any\">-- Any Hobby --</option>\n");
      out.write("                                ");
 
                                    List<String> hobbies = (List<String>) request.getAttribute("hobbies");
                                    String selectedHobby = (String) request.getAttribute("selectedHobby");
                                    if (hobbies != null) {
                                        for (String hobby : hobbies) {
                                            boolean isSelected = (selectedHobby != null && hobby.equals(selectedHobby)) || 
                                                                 (selectedHobby == null && "any".equals(hobby));
                                
      out.write("\n");
      out.write("                                    <option value=\"");
      out.print( hobby );
      out.write('"');
      out.write(' ');
      out.print( isSelected ? "selected" : "" );
      out.write('>');
      out.print( hobby );
      out.write("</option>\n");
      out.write("                                ");
 
                                        }
                                    }
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <!-- Filter Buttons -->\n");
      out.write("                        <div style=\"display: flex; gap: 10px;\">\n");
      out.write("                            <button type=\"submit\" style=\"padding: 8px 20px;\">Apply Filters</button>\n");
      out.write("                            <a href=\"ViewProfiles\">\n");
      out.write("                                <button type=\"button\" class=\"btn-secondary\" style=\"padding: 8px 20px;\">View All</button>\n");
      out.write("                            </a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div style=\"margin-top: 10px; color: #666; font-size: 14px;\">\n");
      out.write("                    <strong>Filter Logic:</strong> \n");
      out.write("                    <ul style=\"margin: 5px 0 0 20px; padding: 0;\">\n");
      out.write("                        <li>Both filters are combined with AND logic</li>\n");
      out.write("                        <li>Select \"Any Program\" or \"Any Hobby\" to ignore that filter</li>\n");
      out.write("                        <li>Hobby filter searches within comma-separated hobbies</li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!-- Profiles Grid -->\n");
      out.write("        <div class=\"profiles-grid\">\n");
      out.write("            ");
 
                List<ProfileBean> profiles = (List<ProfileBean>) request.getAttribute("profiles");
                if (profiles != null && !profiles.isEmpty()) {
                    for (ProfileBean profile : profiles) {
            
      out.write("\n");
      out.write("                <div class=\"profile-card\">\n");
      out.write("                    <!-- Delete Button on Right Side -->\n");
      out.write("                    <form action=\"DeleteProfile\" method=\"get\" \n");
      out.write("                          onsubmit=\"return confirm('Delete profile of ");
      out.print( profile.getFullName() );
      out.write("?')\" \n");
      out.write("                          style=\"position: absolute; right: 15px; top: 15px;\">\n");
      out.write("                        <input type=\"hidden\" name=\"studentId\" value=\"");
      out.print( profile.getStudentId() );
      out.write("\">\n");
      out.write("                        <button type=\"submit\" class=\"delete-btn\">✕ Delete</button>\n");
      out.write("                    </form>\n");
      out.write("                    \n");
      out.write("                    <!-- Profile Info -->\n");
      out.write("                    <div class=\"profile-header\">\n");
      out.write("                        <img src=\"");
      out.print( profile.getProfileImagePath() );
      out.write("\" \n");
      out.write("                             class=\"profile-img\" \n");
      out.write("                             onerror=\"this.src='default.jpg'\">\n");
      out.write("                        <div class=\"profile-info\">\n");
      out.write("                            <h3>");
      out.print( profile.getFullName() );
      out.write("</h3>\n");
      out.write("                            <p><strong>ID:</strong> ");
      out.print( profile.getStudentId() );
      out.write("</p>\n");
      out.write("                            <p><strong>Program:</strong> ");
      out.print( profile.getProgram() );
      out.write("</p>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    \n");
      out.write("                    <div class=\"profile-details\">\n");
      out.write("                        <div class=\"detail-row\">\n");
      out.write("                            <span class=\"label\">Email:</span> ");
      out.print( profile.getEmail() );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"detail-row\">\n");
      out.write("                            <span class=\"label\">Hobbies:</span> \n");
      out.write("                            ");
      out.print( profile.getHobbies() != null && !profile.getHobbies().isEmpty() ? 
                                profile.getHobbies() : "No hobbies listed" );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"detail-row\">\n");
      out.write("                            <span class=\"label\">Created:</span> \n");
      out.write("                            ");
      out.print( profile.getCreatedAt() != null ? 
                                profile.getCreatedAt().substring(0, 16).replace("T", " ") : "" );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            ");
 
                    }
                } else { 
            
      out.write("\n");
      out.write("                <div class=\"empty-state\">\n");
      out.write("                    <p>No profiles found.</p>\n");
      out.write("                    ");
 if (selectedProgram != null || selectedHobby != null) { 
      out.write("\n");
      out.write("                        <p>Try changing your filter criteria.</p>\n");
      out.write("                        <a href=\"ViewProfiles\">\n");
      out.write("                            <button class=\"btn-secondary\">View All Profiles</button>\n");
      out.write("                        </a>\n");
      out.write("                    ");
 } else { 
      out.write("\n");
      out.write("                        <p>Create the first profile!</p>\n");
      out.write("                        <a href=\"index.jsp\">\n");
      out.write("                            <button class=\"btn-success\">Create Profile</button>\n");
      out.write("                        </a>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                </div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div class=\"action-buttons\">\n");
      out.write("            <a href=\"index.jsp\">➕ Add New Profile</a>\n");
      out.write("            <a href=\"ViewProfiles\">👁️ View All Profiles</a>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <div style=\"text-align: center; margin-top: 20px; padding-top: 20px; border-top: 1px solid #eee;\">\n");
      out.write("            <a href=\"index.jsp\" style=\"color: #007bff; text-decoration: none;\">← Back to Home</a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <script>\n");
      out.write("        // Show confirmation for delete\n");
      out.write("        function confirmDelete(studentId, name) {\n");
      out.write("            return confirm('Are you sure you want to delete ' + name + '\\'s profile?');\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
