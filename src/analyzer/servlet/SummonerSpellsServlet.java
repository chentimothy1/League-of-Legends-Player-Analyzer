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

@WebServlet("/summonerspells")
public class SummonerSpellsServlet extends HttpServlet {
	
	protected SummonerSpellDao summonerSpellDao;
	
	@Override
	public void init() throws ServletException {
		summonerSpellDao = SummonerSpellDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<SummonerSpell> allSpells = new ArrayList<SummonerSpell>();
        
        try {
        	allSpells = summonerSpellDao.getAllSpells();
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
    	req.setAttribute("spells", allSpells);
    	
        // Render JSP
        req.getRequestDispatcher("/SummonerSpells.jsp").forward(req, resp);
        
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        // Render JSP
        req.getRequestDispatcher("/SummonerSpells.jsp").forward(req, resp);
	}

}
