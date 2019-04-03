package student.kce.erp.attachment;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;

import student.kce.erp.config.DataConnect;
import student.kce.erp.service.SuperUserService;

 
 
@WebServlet("/uploadToGroupDB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadToGroupDBServlet extends HttpServlet {
	
	@Autowired
	SuperUserService superUserService;
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
 
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	DataConnect dataConnect = superUserService.getDataConnection();
    	String url = dataConnect.getUrl();
    	String driver = dataConnect.getDriver();
    	String username = dataConnect.getUsername();
    	String password = dataConnect.getPassword();
    	HttpSession session=request.getSession();
        Connection conn = null;
        try {
            // Connection to Database
            // (See more in JDBC Tutorial).
        	Class.forName(driver);
        	conn = DriverManager.getConnection(url,username,password);
            conn.setAutoCommit(false);
 
            // Part list (multi files).
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    // File data
                    InputStream is = part.getInputStream();
                    // Write to file
                    this.writeToDB(conn, fileName, is,session);
                }
            }
            conn.commit();
            response.sendRedirect(request.getContextPath() + "/studentsAddResourcePage");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/studentsAddResourcePage");
        } finally {
            this.closeQuietly(conn);
        }
    }
 
    private String extractFileName(Part part) {
        // form-data; name="file"; filename="C:\file1.zip"
        // form-data; name="file"; filename="C:\Note\file2.zip"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                // C:\file1.zip
                // C:\Note\file2.zip
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                // file1.zip
                // file2.zip
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }
 
    private void writeToDB(Connection conn, String fileName, InputStream is,HttpSession session) throws SQLException {
 
        String sql = "insert into group_attachment(admin_id,college_id,student_id,group_id,group_code,batch,department,semester,section,file_name,file_data)"
                + " values (?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println(session.getAttribute("adminId").toString());
        pstm.setInt(1, Integer.parseInt(session.getAttribute("adminId").toString()));
        pstm.setInt(2, Integer.parseInt(session.getAttribute("collegeId").toString()));
        pstm.setInt(3, Integer.parseInt(session.getAttribute("studentId").toString()));
        pstm.setInt(4, Integer.parseInt(session.getAttribute("groupId").toString()));
        pstm.setString(5, session.getAttribute("groupCode").toString());
        pstm.setString(6, session.getAttribute("batch").toString());
        pstm.setString(7, session.getAttribute("department").toString());
        pstm.setString(8, session.getAttribute("semester").toString());
        pstm.setString(9, session.getAttribute("section").toString());
        pstm.setString(10, fileName);
        pstm.setBlob(11, is);
        pstm.executeUpdate();
    }
 
    private void closeQuietly(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }
 
}