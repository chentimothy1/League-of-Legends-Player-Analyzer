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

import analyzer.dal.TopTenDao;
import analyzer.model.TopTen;

@WebServlet("/champions/topplayers")
public class TopPlayersServlet extends HttpServlet{
	
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
        
        String championName = req.getParameter("champion");
        if (championName == null || championName.trim().isEmpty()) {
            messages.put("title", "No champion selected.");
        } else {
        	messages.put("title","Top 10 Players for " + championName);
        }
        
        List<TopTen> topPlayer = new ArrayList<TopTen>();
        try {
        	topPlayer = topTenDao.topTenPlayer(championName);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
        req.setAttribute("topplayer", topPlayer);
        // Render JSP
        req.getRequestDispatcher("/TopPlayers.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        // Render JSP
        req.getRequestDispatcher("/TopPlayers.jsp").forward(req, resp);
	}
}
