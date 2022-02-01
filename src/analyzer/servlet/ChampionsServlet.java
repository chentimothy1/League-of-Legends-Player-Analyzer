package analyzer.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import analyzer.dal.*;
import analyzer.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/champions")
public class ChampionsServlet extends HttpServlet{
	
	protected LOLChampionsDao championsDao;
	
	@Override
	public void init() throws ServletException {
		championsDao = LOLChampionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<LOLChampions> allChampions = new ArrayList<LOLChampions>();
        
        try {
        	allChampions = championsDao.getAllChampions();
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	req.setAttribute("champions", allChampions);
        
        // Render JSP
        req.getRequestDispatcher("/Champions.jsp").forward(req, resp);
        
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        // Render JSP
        req.getRequestDispatcher("/Champions.jsp").forward(req, resp);
	}

}
