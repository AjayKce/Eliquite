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

 
 
@WebServlet("/uploadToDB")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadToDBServlet extends HttpServlet {
	
	@Autowired
	SuperUserService superUserService;
    private static final long serialVersionUID = 1L;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getRequestDispatcher("uploadToDB.jsp");
 
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
 
            // Upload successfully!.
            response.sendRedirect(request.getContextPath() + "/addResource");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/addResource");
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
 
        String sql = "insert into attachment(admin_id,batch,department,semester,section,subject_code,file_name,file_data)"
                + " values (?,?,?,?,?,?,?,?) ";
        PreparedStatement pstm = conn.prepareStatement(sql);
        System.out.println(session.getAttribute("adminId").toString());
        pstm.setInt(1, Integer.parseInt(session.getAttribute("adminId").toString()));
        pstm.setString(2, session.getAttribute("batch").toString());
        pstm.setString(3, session.getAttribute("department").toString());
        pstm.setString(4, session.getAttribute("semester").toString());
        pstm.setString(5, session.getAttribute("section").toString());
        pstm.setString(6, session.getAttribute("subjectCode").toString());
        pstm.setString(7, fileName);
        pstm.setBlob(8, is);
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