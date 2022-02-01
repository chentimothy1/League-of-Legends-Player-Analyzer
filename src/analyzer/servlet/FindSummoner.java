package analyzer.servlet;

import analyzer.dal.*;
import analyzer.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findsummoner")
public class FindSummoner extends HttpServlet {
	
	protected SummonerDao summonerDao;
	
	@Override
	public void init() throws ServletException {
		summonerDao = SummonerDao.getInstance();
	}
	

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        Summoner summoner = null;
        
        String summonerName = req.getParameter("summonername");
        if (summonerName == null || summonerName.trim().isEmpty()) {
            messages.put("success", "Please enter a summoner name.");
        } else {
        	// Retrieve summoner
        	try {
            	summoner = summonerDao.getAccountBySummonerName(summonerName);
            	if(summoner == null) {
            		messages.put("success", "Summoner does not exists.");
            	}
            	else{
            		messages.put("success", "Displaying statistics for " + summonerName);
            	}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
       
        }
        req.setAttribute("summoner", summoner);
        
        req.getRequestDispatcher("/FindSummoner.jsp").forward(req, resp);
            
	}
	
	
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        Summoner summoner = null;
        
        String summonerName = req.getParameter("summonername");
        if (summonerName == null || summonerName.trim().isEmpty()) {
            messages.put("success", "Please enter a summoner name");
        } else {
        	// Retrieve summoner
        	try {
            	summoner = summonerDao.getAccountBySummonerName(summonerName);
            	if(summoner == null) {
            		messages.put("success", "Summoner does not exists.");
            	}
            	else{
            		messages.put("success", "Displaying statistics for " + summonerName);
            	}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("summoner", summoner);
        
        req.getRequestDispatcher("/FindSummoner.jsp").forward(req, resp);
	}
	
}
