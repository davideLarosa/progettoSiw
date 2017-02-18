package servlets;

import java.io.IOException;
import java.sql.Date;

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
import java.util.ArrayList;
import java.util.Calendar;
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
	private float lastBid;
	private String path;
	private ArrayList<String> paths;
	private int item_id;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = (String) request.getSession().getAttribute("email");
		// TODO elimina syso
		System.out.println("sell " + email);
		if (email == null) {
			response.sendRedirect("login.jsp");
		} else {
			request.getRequestDispatcher("sell.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.producer = "Producer";
		this.model = "Model";
		this.minimum_buy_price = "0.01";
		this.category = "Category_1";
		this.time = String.valueOf(System.currentTimeMillis());
		this.bid = String.valueOf("0");
		this.buy_now = String.valueOf("0");
		this.description = "Description";
		this.lastBid = 0;
		this.paths = new ArrayList<String>();
		this.item_id = 0;
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

		// // creates the directory if it does not exist
		// File uploadDir = new File(uploadPath);
		// if (!uploadDir.exists()) {
		// uploadDir.mkdir();
		// }

		try {
			// parses the request's content to extract file data

			List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(request));
			if (formItems != null && formItems.size() > 0) {
				// if(true){
				// iterates over form's fields
				for (FileItem item : formItems) {
					if (item.isFormField()) {
						if (item.getFieldName().equals("producer")) {
							if (!item.getString().equals("")) {
								this.producer = item.getString();
							}
						} else if (item.getFieldName().equals("model")) {
							if (!item.getString().equals("")) {
								this.model = item.getString();
							}
						} else if (item.getFieldName().equals("minimum_buy_price")) {
							if (!item.getString().equals("")) {
								this.minimum_buy_price = item.getString();
								if (this.minimum_buy_price.contains(",")) {
									this.minimum_buy_price = this.minimum_buy_price.replace(',', '.');
								}
							}
						} else if (item.getFieldName().equals("category")) {
							if (!item.getString().equals("")) {
								this.category = item.getString();
							}
						} else if (item.getFieldName().equals("time")) {
							if (!item.getString().equals("")) {
								this.time = item.getString();
							}
						} else if (item.getFieldName().equals("bid")) {
							if (!item.getString().equals("")) {
								this.bid = item.getString();
								if (this.bid.equals("on")) {
									this.bid = "true";
								} else {
									this.bid = "false";
								}
							}
						} else if (item.getFieldName().equals("buy_now")) {
							if (!item.getString().equals("")) {
								this.buy_now = item.getString();
								if (this.buy_now.equals("on")) {
									this.buy_now = "true";
								} else {
									this.buy_now = "false";
								}
							}
						} else if (item.getFieldName().equals("description")) {
							this.description = item.getString();
							if (this.description.equals("")) {
								this.description = this.producer + " " + this.model;
							}

						}

					}
					// processes only fields that are not form fields
					else {
						if (this.path == null) {
							this.path = createPath(request);
						}
						String fileName = new File(item.getName()).getName();
						String extension = "";

						int ex = fileName.lastIndexOf('.');
						if (ex > 0) {
							extension = fileName.substring(ex + 1);
						}

						if (fileName != "") {
							String newFileName = File.separator + "thumb" + String.valueOf(this.thumbNumber) + "."
									+ extension;

							String filePath = uploadPath + this.path;

							// creates the directory if it does not exist
							File uploadDir = new File(uploadPath);
							if (!uploadDir.exists()) {
								uploadDir.mkdirs();
							}

							uploadDir = new File(filePath);
							if (!uploadDir.exists()) {
								uploadDir.mkdirs();
							}
							File storeFile = new File(filePath + newFileName);

							// saves the file on disk
							item.write(storeFile);
							this.paths.add(storeFile.getPath());
							this.thumbNumber++;
							request.setAttribute("message", "Upload has been done successfully!");
						}
					}
				}
				this.thumbNumber = 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			request.setAttribute("message", "There was an error please retry later ");
		}
		DBManager.getInstance().getItemDAO().setPath(item_id, paths);
		// redirects client to message page
		getServletContext().getRequestDispatcher("/sell.jsp").forward(request, response);
	}

	private String createPath(HttpServletRequest request) {
		String path = new String();
		String email = (String) request.getSession().getAttribute("email");
		String user = email;
		String itemId = String.valueOf(getItemId(email));

		path = File.separator + this.category + File.separator + user + File.separator + itemId;
		return path;
	}

	private String getItemId(String email) {
		User user = DBManager.getInstance().getUserDAO().findByPrimaryKey(email);
		Calendar calendar = Calendar.getInstance();
		if (this.time.equals("1 Month")) {
			calendar.add(Calendar.MONTH, 1);
		} else if (this.time.equals("2 Months")) {
			calendar.add(Calendar.MONTH, 2);
		} else if (this.time.equals("3 Months")) {
			calendar.add(Calendar.MONTH, 3);
		}
		Date date = new Date(calendar.getTimeInMillis());
		
		System.out.println();
		System.out.println();
		System.out.println("producer : " + this.producer + " \n model : " + this.model + " \n minimum buy price : "
				+ this.minimum_buy_price + " \n category :  " + this.category + " \n time : " + this.time + " \n bid : "
				+ this.bid + " \n buy_now : " + this.buy_now + " \n description :  " + this.description
				+ " \n last bid :  " + this.lastBid + " \n user : " + user.getId());
		System.out.println();
		System.out.println();
		
		int itemId = DBManager.getInstance().getItemDAO()
				.save(new Item(this.producer, this.model, this.minimum_buy_price, this.lastBid, date, this.category,
						user, this.description, this.buy_now, this.bid));
		this.item_id = itemId;
		return String.valueOf(itemId);
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBuy_now() {
		return buy_now;
	}

	public void setBuy_now(String buy_now) {
		this.buy_now = buy_now;
	}
}
