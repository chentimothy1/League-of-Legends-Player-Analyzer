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

import analyzer.model.*;
import analyzer.dal.*;

@WebServlet("/maps")
public class MapsServlet extends HttpServlet{
	
	protected MapsDao mapsDao;
	
	@Override
	public void init() throws ServletException {
		mapsDao = MapsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Maps> allMaps = new ArrayList<Maps>();
        
        try {
        	allMaps = mapsDao.getAllMaps();
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	req.setAttribute("maps", allMaps);
		
        // Render JSP
        req.getRequestDispatcher("/Maps.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        // Render JSP
        req.getRequestDispatcher("/Maps.jsp").forward(req, resp);
	}

}
