import beans.ProfileBean;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, 
        maxFileSize = 1024 * 1024 * 10,      
        maxRequestSize = 1024 * 1024 * 15    
)
@WebServlet(name = "ProfileServlet", urlPatterns = {"/SaveProfile", "/ViewProfiles", "/DeleteProfile", "/FilterProfiles"})
public class ProfileServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/ViewProfiles".equals(path)) {
            viewAllProfiles(request, response);
        } else if ("/DeleteProfile".equals(path)) {
            deleteProfile(request, response);
        } else if ("/FilterProfiles".equals(path)) {
            filterProfiles(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = request.getServletPath();
        
        if ("/SaveProfile".equals(path) || "/ProfileServlet".equals(path)) {
            saveProfile(request, response);
        }
    }

    private void saveProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String studentId = request.getParameter("studentId");
        String program = request.getParameter("program");
        String email = request.getParameter("email");
        String hobbies = request.getParameter("hobbies");
        String intro = request.getParameter("intro");

        if (fullName == null || fullName.trim().isEmpty() ||
            studentId == null || studentId.trim().isEmpty() ||
            program == null || program.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            hobbies == null || hobbies.trim().isEmpty() ||
            intro == null || intro.trim().isEmpty()) {
            
            request.setAttribute("error", "All fields are required!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        fullName = fullName.toUpperCase();
        program = program.toUpperCase();

        if (ProfileBean.studentIdExists(studentId)) {
            request.setAttribute("error", "Student ID " + studentId + " already exists!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String profileImagePath = "default.jpg";
        try {
            Part filePart = request.getPart("profileImage");
            String fileName = filePart.getSubmittedFileName();

            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                
                filePart.write(uploadPath + File.separator + fileName);
                profileImagePath = UPLOAD_DIR + "/" + fileName;
            }
        } catch (Exception e) {
            System.err.println("File upload error: " + e.getMessage());
        }

        ProfileBean profile = new ProfileBean(fullName, studentId, program, email, hobbies, intro, profileImagePath);
        
        if (profile.saveToDatabase()) {
            request.setAttribute("fullName", fullName);
            request.setAttribute("studentId", studentId);
            request.setAttribute("program", program);
            request.setAttribute("email", email);
            request.setAttribute("hobbiesArray", hobbies.split(","));
            request.setAttribute("intro", intro);
            request.setAttribute("profileImagePath", profileImagePath);
            request.setAttribute("message", "Profile saved successfully!");
            
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to save profile! Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }


    private void viewAllProfiles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProfileBean> profiles = ProfileBean.getAllProfiles();
        List<String> programs = ProfileBean.getDistinctPrograms();
        List<String> hobbies = ProfileBean.getDistinctHobbies();

        request.setAttribute("profiles", profiles);
        request.setAttribute("programs", programs);
        request.setAttribute("hobbies", hobbies);
        request.setAttribute("filterLabel", "All Profiles");
        request.setAttribute("selectedProgram", "any");
        request.setAttribute("selectedHobby", "any");

        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }


    private void deleteProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");

        if (studentId != null && !studentId.trim().isEmpty()) {
            if (ProfileBean.deleteProfile(studentId)) {
                request.setAttribute("message", "Profile deleted successfully!");
            } else {
                request.setAttribute("error", "Failed to delete profile!");
            }
        } else {
            request.setAttribute("error", "Student ID not provided!");
        }

        String programFilter = request.getParameter("programFilter");
        String hobbyFilter = request.getParameter("hobbyFilter");

        if (programFilter == null) programFilter = "any";
        if (hobbyFilter == null) hobbyFilter = "any";

        response.sendRedirect("FilterProfiles?programFilter=" + programFilter + "&hobbyFilter=" + hobbyFilter);
    }


    private void filterProfiles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String programFilter = request.getParameter("programFilter");
        String hobbyFilter = request.getParameter("hobbyFilter");

        List<ProfileBean> profiles;
        List<String> programs = ProfileBean.getDistinctPrograms();
        List<String> hobbies = ProfileBean.getDistinctHobbies();

        if (("any".equals(programFilter) || programFilter == null || programFilter.isEmpty()) &&
            ("any".equals(hobbyFilter) || hobbyFilter == null || hobbyFilter.isEmpty())) {
            profiles = ProfileBean.getAllProfiles();
            request.setAttribute("filterLabel", "All Profiles");
        } else {
            profiles = ProfileBean.filterProfiles(programFilter, hobbyFilter);

            StringBuilder filterLabel = new StringBuilder();
            if (programFilter != null && !"any".equals(programFilter) && !programFilter.isEmpty()) {
                filterLabel.append("Program: ").append(programFilter);
            }
            if (hobbyFilter != null && !"any".equals(hobbyFilter) && !hobbyFilter.isEmpty()) {
                if (filterLabel.length() > 0) filterLabel.append(" AND ");
                filterLabel.append("Hobby: ").append(hobbyFilter);
            }
            request.setAttribute("filterLabel", filterLabel.toString());
        }

        request.setAttribute("profiles", profiles);
        request.setAttribute("programs", programs);
        request.setAttribute("hobbies", hobbies);
        request.setAttribute("selectedProgram", programFilter);
        request.setAttribute("selectedHobby", hobbyFilter);

        request.getRequestDispatcher("viewProfiles.jsp").forward(request, response);
    }
}