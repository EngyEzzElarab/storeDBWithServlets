package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import Functionalities.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@jakarta.servlet.annotation.WebServlet("/Servlet")
public class Servlet extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("HELLO WORLDDD!!!!");
	}

	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			Map<String, String[]> inputParams = null;
			// getting the parameters from postman in the form of a Map
			inputParams = request.getParameterMap();
			String command = "";
			if (inputParams != null) {
				Iterator<String> iter = inputParams.keySet().iterator();
				int c = 0;
				// formatting the input string containing the command and the comma-separated
				while (iter.hasNext()) {
					String key = (String) iter.next();
					String[] values = (String[]) inputParams.get(key);
					for (int i = 0; i < values.length; i++) {
						command += values[i];
						if (c == 0 || c == (inputParams.size() - 1)) {
							command += " ";
						} else {
							command += ",";
						}
						out.print("Parameter Name=" + key + ", Value=" + values[i] + "</br>");
					}
					c++;
				}
			}
			Parser.parseCommand(command);
		} catch (Exception e) {
			response.setStatus(500, "Server Error");
		} finally {
			out.close();
		}
	}

}
