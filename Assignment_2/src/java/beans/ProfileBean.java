package beans;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ProfileBean implements Serializable {
    private String fullName;
    private String studentId;
    private String program;
    private String email;
    private String hobbies;
    private String introduction;
    private String profileImagePath;
    
    private static final String DB_URL = "jdbc:derby://localhost:1527/student_profiles";
    private static final String DB_USER = "app";
    private static final String DB_PASS = "app";
    
    public ProfileBean() {}
    
    public ProfileBean(String fullName, String studentId, String program, String email, 
            String hobbies, String introduction, String profileImagePath) {
        this.fullName = fullName;
        this.studentId = studentId;
        this.program = program;
        this.email = email;
        this.hobbies = hobbies;
        this.introduction = introduction;
        this.profileImagePath = profileImagePath;
    }
    
    public String getFullName() { 
        return fullName; }
    public void setFullName(String fullName) { 
        this.fullName = fullName; }
    
    public String getStudentId() { 
        return studentId; }
    public void setStudentId(String studentId) { 
        this.studentId = studentId; }
    
    public String getProgram() { 
        return program; }
    public void setProgram(String program) { 
        this.program = program; }
    
    public String getEmail() { 
        return email; }
    public void setEmail(String email) { 
        this.email = email; }
    
    public String getHobbies() { 
        return hobbies; }
    public void setHobbies(String hobbies) { 
        this.hobbies = hobbies; }
    
    public String getIntroduction() { 
        return introduction; }
    public void setIntroduction(String introduction) { 
        this.introduction = introduction; }
    
    public String getProfileImagePath() { 
        return profileImagePath; }
    public void setProfileImagePath(String profileImagePath) { 
        this.profileImagePath = profileImagePath; }
    
   
    
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found");
        }
    }
    
    public boolean saveToDatabase() {
        String sql = "INSERT INTO APP.PROFILES (full_name, student_id, program, email, hobbies, introduction, profile_image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, this.fullName);
            stmt.setString(2, this.studentId);
            stmt.setString(3, this.program);
            stmt.setString(4, this.email);
            stmt.setString(5, this.hobbies);
            stmt.setString(6, this.introduction);
            stmt.setString(7, this.profileImagePath);
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error saving profile: " + e.getMessage());
            return false;
        }
    }
    
    public static List<ProfileBean> getAllProfiles() {
        List<ProfileBean> profiles = new ArrayList<>();
        String sql = "SELECT * FROM APP.PROFILES ORDER BY student_id DESC";


        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            int count = 0;
            while (rs.next()) {
                count++;

                ProfileBean profile = new ProfileBean();
                profile.setFullName(rs.getString("full_name"));
                profile.setStudentId(rs.getString("student_id"));
                profile.setProgram(rs.getString("program"));
                profile.setEmail(rs.getString("email"));
                profile.setHobbies(rs.getString("hobbies"));
                profile.setIntroduction(rs.getString("introduction"));
                profile.setProfileImagePath(rs.getString("profile_image_path"));

                profiles.add(profile);
            }

        } catch (SQLException e) {
            System.err.println("Error getting profiles: " + e.getMessage());
            e.printStackTrace(); 
        }

        return profiles;
    }
 
    public static List<ProfileBean> filterProfiles(String programName, String hobby) {
        List<ProfileBean> profiles = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT * FROM APP.PROFILES WHERE 1=1");
        List<String> params = new ArrayList<>();

        if (programName != null && !programName.trim().isEmpty() && !"any".equalsIgnoreCase(programName.trim())) {
            sql.append(" AND UPPER(program) = ?");
            params.add(programName.trim().toUpperCase());
        }

        if (hobby != null && !hobby.trim().isEmpty() && !"any".equalsIgnoreCase(hobby.trim())) {
            sql.append(" AND UPPER(hobbies) LIKE ?");
            params.add("%" + hobby.trim().toUpperCase() + "%");
        }

        sql.append(" ORDER BY full_name ASC");

        System.out.println("DEBUG: Filter SQL: " + sql.toString());
        System.out.println("DEBUG: Filter params: " + params);

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setString(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                count++;
                ProfileBean profile = new ProfileBean();
                profile.setFullName(rs.getString("full_name"));
                profile.setStudentId(rs.getString("student_id"));
                profile.setProgram(rs.getString("program"));
                profile.setEmail(rs.getString("email"));
                profile.setHobbies(rs.getString("hobbies"));
                profile.setIntroduction(rs.getString("introduction"));
                profile.setProfileImagePath(rs.getString("profile_image_path"));

                profiles.add(profile);
            }

            System.out.println("DEBUG: Filter returned " + count + " profiles");
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error filtering profiles: " + e.getMessage());
            e.printStackTrace();
        }

        return profiles;
    }

    public static List<String> getDistinctHobbies() {
        List<String> hobbies = new ArrayList<>();
        String sql = "SELECT hobbies FROM APP.PROFILES WHERE hobbies IS NOT NULL";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String hobbyString = rs.getString("hobbies");
                if (hobbyString != null && !hobbyString.isEmpty()) {
                    String[] hobbyArray = hobbyString.split(",");
                    for (String hobby : hobbyArray) {
                        String trimmedHobby = hobby.trim().toUpperCase();
                        if (!trimmedHobby.isEmpty() && !hobbies.contains(trimmedHobby)) {
                            hobbies.add(trimmedHobby);
                        }
                    }
                }
            }
            java.util.Collections.sort(hobbies);

        } catch (SQLException e) {
            System.err.println("Error getting distinct hobbies: " + e.getMessage());
        }

        return hobbies;
    }

    public static List<String> getDistinctPrograms() {
        List<String> programs = new ArrayList<>();
        String sql = "SELECT DISTINCT program FROM APP.PROFILES ORDER BY program ASC";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                programs.add(rs.getString("program"));
            }

        } catch (SQLException e) {
            System.err.println("Error getting distinct programs: " + e.getMessage());
        }

        return programs;
    }
    
    public static boolean deleteProfile(String studentId) {
        String sql = "DELETE FROM APP.PROFILES WHERE student_id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, studentId);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting profile: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean studentIdExists(String studentId) {
        String sql = "SELECT COUNT(*) FROM APP.PROFILES WHERE student_id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                boolean exists = rs.getInt(1) > 0;
                rs.close();
                return exists;
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking student ID: " + e.getMessage());
        }
        
        return false;
    }
}