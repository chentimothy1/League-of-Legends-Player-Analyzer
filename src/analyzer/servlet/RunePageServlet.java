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

@WebServlet("/runepage")
public class RunePageServlet extends HttpServlet{
	
	protected RunePageDao runePageDao;
	
	@Override
	public void init() throws ServletException {
		runePageDao = runePageDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<RunePage> allRunes = new ArrayList<RunePage>();
        
        try {
        	allRunes = runePageDao.getAllRunes();
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	req.setAttribute("runepages", allRunes);
		
        // Render JSP
        req.getRequestDispatcher("/RunePages.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        // Render JSP
        req.getRequestDispatcher("/RunePages.jsp").forward(req, resp);
	}

}
