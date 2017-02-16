package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import model.Item;
import model.User;
import persistence.DBManager;

import java.io.File;
import java.util.List;

public class Sell extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// location to store file uploaded
	private static final String UPLOAD_DIRECTORY = "itemsImages";

	// upload settings
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	private String producer;
	private String model;
	private String minimum_buy_price;
	private String category;
	private String time;
	private String bid;
	private String buy_now;
	private String description;
	private int thumbNumber = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = (String) request.getSession().getAttribute("email");
		// TODO elimina syso
		System.out.println("modifica " + email);
		if (email == null) {
			response.sendRedirect("login.jsp");
		} else {
			request.getRequestDispatcher("sell.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// configures upload settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// sets memory threshold - beyond which files are stored in disk
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// sets temporary location to store files
		factory.setRepository(new File("itemsImages"));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// sets maximum size of upload file
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// sets maximum size of request (include file + form data)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// constructs the directory path to store upload file
		// this path is relative to application's directory

		// String uploadPath = getServletContext().getRealPath("") +
		// File.separator + UPLOAD_DIRECTORY;

		String uploadPath = "/Users/davidelarosa/Documents/git/progettoSiw/ProgettoSIW_mio/WebContent/"
				+ UPLOAD_DIRECTORY;

//		// creates the directory if it does not exist
//		File uploadDir = new File(uploadPath);
//		if (!uploadDir.exists()) {
//			uploadDir.mkdir();
//		}

		try {
			// parses the request's content to extract file data

			List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
			if (formItems != null && formItems.size() > 0) {
				// iterates over form's fields
				for (FileItem item : formItems) {

					if (item.isFormField()) {
						if (item.getFieldName().equals("producer")) {
							this.producer = item.getString();
						} else if (item.getFieldName().equals("model")) {
							this.model = item.getString();
						} else if (item.getFieldName().equals("minimum_buy_price")) {
							this.minimum_buy_price = item.getString();
						} else if (item.getFieldName().equals("category")) {
							this.category = item.getString();
						} else if (item.getFieldName().equals("time")) {
							this.time = item.getString();
						} else if (item.getFieldName().equals("bid")) {
							this.bid = item.getString();
						} else if (item.getFieldName().equals("buy_now")) {
							this.buy_now = item.getString();
						} else if (item.getFieldName().equals("description")) {
							this.description = item.getString();
						} else {
						}

						// System.out.println("item " + item.getName() +
						// "content type" + item.getContentType()
						// + " string " + item.getString() + " inputStream " +
						// item.getInputStream()
						// + " field name" + item.getFieldName());
					}
					// processes only fields that are not form fields
					else {

						String path = createPath(request);
						//createFolders(path);
						// System.out.println(path);
						String fileName = new File(item.getName()).getName();
						String extension = "";

						int ex = fileName.lastIndexOf('.');
						if (ex > 0) {
							extension = fileName.substring(ex + 1);
						}

						if (fileName != "") {
							String newFileName = File.separator + "thumb" + String.valueOf(this.thumbNumber) + "."
									+ extension;

							
							
							
							String filePath = uploadPath + path;
							
							// creates the directory if it does not exist
							File uploadDir = new File(uploadPath);
							if (!uploadDir.exists()) {
								uploadDir.mkdirs();
							}
							
							uploadDir = new File(filePath);
							if (!uploadDir.exists()) {
								uploadDir.mkdirs();
							}

							// String filePath = uploadPath + File.separator +
							// fileName;
							// File storeFile = new File(filePath);

							
							File storeFile = new File(filePath + newFileName);
							System.out.println("Filepath " + storeFile.getPath());
							System.out.println("new filename " + newFileName);

							// saves the file on disk
							item.write(storeFile);

							this.thumbNumber++;
							request.setAttribute("message", "Upload has been done successfully!");
						}
					}
				}
				this.thumbNumber = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			request.setAttribute("message", "There was an error: " + ex.getMessage());
		}
		// redirects client to message page
		getServletContext().getRequestDispatcher("/sell.jsp").forward(request, response);
	}

	private void createFolders(String path) {
		String[] folders = path.split("/");

		for (int i = 0; i < folders.length; i++) {
			if(folders[i]!=""){
				File dir = new File(folders[i]);
				if(!dir.exists()){
					dir.mkdir();
					System.out.println("making directory "+ dir.getPath());
				}
			}
		}

	}

	private String createPath(HttpServletRequest request) {
		String path = new String();
		String email = (String) request.getSession().getAttribute("email");
		String user = email;
		String itemId = String.valueOf(getItemId(email));

		path = File.separator + this.category + File.separator + user + File.separator + itemId;
		System.out.println("path " + path);
		if (path.contains(" ")) {
			System.out.println("contiete spazio");

			char[] charPath = path.toCharArray();
			for (int i = 0; i < charPath.length; i++) {
				if (charPath[i] == ' ') {
					charPath[i] = ' ';
				}
			}
			path = new String(charPath);

		}
		System.out.println("path " + path);
		return path;
	}

	private String getItemId(String email) {
		User user = DBManager.getInstance().getUserDAO().findByPrimaryKey(email);
		System.out.println(user.getEmail());
		int itemId = DBManager.getInstance().getItemDAO().save(new Item(user, category));
		return String.valueOf(itemId);
	}

}
