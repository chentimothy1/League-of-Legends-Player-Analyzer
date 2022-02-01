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

@WebServlet("/region")
public class RegionInfo extends HttpServlet{
	
	protected TopTenDao topTenDao;
	
	@Override
	public void init() throws ServletException {
		topTenDao = TopTenDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String region = req.getParameter("region");
        if (region == null || region.trim().isEmpty()) {
            messages.put("title", "No region selected.");
        } else {
        	switch (region) {
        		case "NA1":
        			messages.put("title", "North America Staticstics");
        			break;
        		case "EUW1":
        			messages.put("title", "Europe West Staticstics");
        			break;
        		case "EUN1":
        			messages.put("title", "Europe North Staticstics");
        			break;
        	}	
        }
        
        List<TopTen> winrate = new ArrayList<TopTen>();
        try {
        	winrate = topTenDao.topTenWin(region);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        String[][] position = new String[5][10];
        try {
        	position = topTenDao.topTenPosition(region);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        String[][] role = new String[5][10];
        try {
        	role = topTenDao.topTenRole(region);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("winrate", winrate);
        req.setAttribute("role", role);
        req.setAttribute("position", position);
        req.getRequestDispatcher("/RegionInfo.jsp").forward(req, resp);
		
	}
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // render JSP
        req.getRequestDispatcher("/RegionInfo.jsp").forward(req, resp);
		
	}
}
