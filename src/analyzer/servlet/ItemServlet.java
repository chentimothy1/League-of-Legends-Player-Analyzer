package analyzer.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import analyzer.dal.*;
import analyzer.model.*;

@WebServlet("/items")
public class ItemServlet extends HttpServlet{
	
	protected ItemsDao itemsDao;
	
	@Override
	public void init() throws ServletException {
		itemsDao = ItemsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Items> allItems = new ArrayList<Items>();
        
        try {
        	allItems = itemsDao.getAllItems();
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	req.setAttribute("allitems", allItems);
    	
        // Render JSP
        req.getRequestDispatcher("/Items.jsp").forward(req, resp);
        
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        // Render JSP
        req.getRequestDispatcher("/Items.jsp").forward(req, resp);
	}

}
