package student.kce.erp.attachment;
 
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.kce.erp.model.Attachment;
 
@WebServlet("/downloadAttachment")
public class DownloadAttachmentServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
 
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

           Attachment attachment = (Attachment)request.getAttribute("attachment");
 
           if (attachment == null) {
               // No record found.
               response.getWriter().write("No data found");
               return;
           }
 
           // file1.zip, file2.zip
           String fileName = attachment.getFileName();
           System.out.println("File Name: " + fileName);
 
           // abc.txt => text/plain
           // abc.zip => application/zip
           // abc.pdf => application/pdf
           String contentType = this.getServletContext().getMimeType(fileName);
           System.out.println("Content Type: " + contentType);
 
           response.setHeader("Content-Type", contentType);
 
           try {
			response.setHeader("Content-Length", String.valueOf(attachment.getFileData().length()));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
           response.setHeader("Content-Disposition", "inline; filename=\"" + attachment.getFileName() + "\"");
 
           // For big BLOB data.
           Blob fileData = attachment.getFileData();
           InputStream is=null;
		try {
			is = fileData.getBinaryStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
           byte[] bytes = new byte[1024];
           int bytesRead;
 
           while ((bytesRead = is.read(bytes)) != -1) {
               // Write image data to Response.
               response.getOutputStream().write(bytes, 0, bytesRead);
           }
           is.close();
           
   }
   }