package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;

import db.DBConstants;
import db.HibernateConnection;
import entities.Image;
import entities.User;

/**
 * Servlet implementation class FileUploadHandler
 */
@WebServlet("/FileUploadHandler")
public class FileUploadHandler extends HttpServlet {
	private final String UPLOAD_DIRECTORY = DBConstants.uploadDirectory;
	private final String DOWNLOAD_DIRECTORY = DBConstants.downloadDirectory;
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadHandler() {
		super();
		File uploadFolder = new File(UPLOAD_DIRECTORY);
		if (!uploadFolder.exists())
			uploadFolder.mkdir();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Session session = HibernateConnection.getSession();
		String category = "null";
		Integer userId = (Integer) request.getSession().getAttribute("user");
		
		// Process if Multipart Content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				Date date = new Date();
				SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat fileformat = new SimpleDateFormat("yyyyMMddHHmmss");
				String filename = "";
				String name = "";
				
				for (FileItem item : multiparts) {
					
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						System.out.println(item.getFieldName());
						if (fieldName.equals("category")) {
							category = item.getString();
							System.out.println(item.getString());
						}
					} else if (!item.isFormField()) {
						
						name = new File(item.getName()).getName();
						filename = fileformat.format(date) + name;
						String fullpath = UPLOAD_DIRECTORY + File.separator + filename;
						// Create file as image + date for uniqueness
						item.write(new File(fullpath));
					}
				}
				session.beginTransaction();
				
				User theUser = session.get(User.class, userId);
				Image image = new Image();
				image.setUser(theUser);
				image.setReference(DOWNLOAD_DIRECTORY + filename);
				image.setCategory(category);
				image.setDate(sqlformat.format(date));
				image.setFilename(name);
				theUser.addImage(image);
				
				session.save(image);
				session.getTransaction().commit();
				// File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
				request.setAttribute("message", "File Upload Failed due to " + e.getMessage());
			}
			
		} else {
			request.setAttribute("message", "Sorry this Servlet is only for files");
		}
		
		response.sendRedirect("home.jsp");
	}
	
	private void touchTxt() {
		File file = new File(DBConstants.uploadDirectory + File.separator + "touch.txt");
		FileWriter fileWriter;
		if (file.exists()) {
//			String folder = file.getParent();
//			String timeStamp = new Date().toString();
//			File newFile = new File(folder + File.separator + "log-" + timeStamp + ".txt");
//			file.renameTo(newFile);
		
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.err.println("Failed to create a log file");
				e.printStackTrace();
			}
		}
		try {
			fileWriter = new FileWriter(file);
			fileWriter.append('a');
		} catch (IOException e) {
			System.err.println("Failed to create writer");
			e.printStackTrace();
		}
	}
	
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		// Connect and get parameters
//		Connection con = DBConnection.getDBInstance();
//		DBUtility.useDB(con, "gallery");
//		String query;
//		String user = (String) request.getSession().getAttribute("user");
//		String category = (String) request.getParameter("category");
//		System.out.println("CATEGORY: " + category);
//		
//		// Process if Multipart Content
//		if (ServletFileUpload.isMultipartContent(request)) {
//			try {
//				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//				
//				for (FileItem item : multiparts) {
//					if (!item.isFormField()) {
//						Date date = new Date();
//						SimpleDateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						SimpleDateFormat fileformat = new SimpleDateFormat("yyyyMMddHHmmss");
//						
//						String name = new File(item.getName()).getName();
//						String filename = fileformat.format(date) + name;
//						String fullpath = UPLOAD_DIRECTORY + File.separator + filename;
//						// Create file as image + date for uniqueness
//						item.write(new File(fullpath));
//						
//						// Save reference as image + date for uniqueness
//						query = "INSERT INTO image (user, reference, filename, category, date) VALUES ('" + user
//								+ "', '" + fullpath + "', '" + filename + "', '" + category + "', '"
//								+ sqlformat.format(date) + "');";
//						DBUtility.executeUpdate(con, query);
//					}
//				}
//				
//				// File uploaded successfully
//				request.setAttribute("message", "File Uploaded Successfully");
//			} catch (Exception e) {
//				request.setAttribute("message", "File Upload Failed due to " + e.getMessage());
//			}
//			
//		} else {
//			request.setAttribute("message", "Sorry this Servlet is only for files");
//		}
//		
//		response.sendRedirect("home.jsp");
//	}
}
