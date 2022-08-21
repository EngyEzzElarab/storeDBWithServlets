package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import Functionalities.Parser;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.coyote.http11.Http11AprProtocol;

/**
 * Servlet implementation class Servlet
 */
@jakarta.servlet.annotation.WebServlet("/Servlet")
public class Servlet extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     * @throws IOException 
     */
//    public Servlet() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("HELLO WORLDDD!!!!");
	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						 PrintWriter out = response.getWriter();
				         try {
				
								     Map<String,String[]> inputParams = null;
								     inputParams = request.getParameterMap(); //// request is object of above mentioned class . in jsp it is an implict object
								     String command = "";
								      if (inputParams != null) {
								          //System.out.println("Total number of parameters: " + inputParams.size());
								      Iterator<String> iter = inputParams.keySet().iterator();
								       int c=0;
								       while (iter.hasNext())
								          {
										       String key = (String) iter.next();  // gets the parameter  name
										       String[] values = (String[]) inputParams.get(key);   // returns the value of a parameter name
										       for (int i = 0; i < values.length; i++) {
										    	   command+=values[i];
										    	   if(c==0 || c==(inputParams.size()-1))
										    	   {
										    		   command+=" ";
										    		   //System.out.println("first and last");
										    	   }
										    	   else
										    	   {
										    		   command+=",";
										    	   }
										    	  
										    	  // command+= (i==0)? " ":",";
										    	   //if(i!=values.length-1)
										    	   //command+=",";
										          // System.out.println("Parameter Name="+ key + " Value=" + values[i]);
										           out.print("Parameter Name="+ key + ", Value=" + values[i]+"</br>");
										          }
									           c++;
										       }
								      } 
								      System.out.println("COMMAND: "+command);	
								      Parser.parseCommand(command);
								    //  Connection conn = MySQLConn.connectToDB();
				         } 
				         catch (Exception e) {
				    	 response.setStatus(500,"Server Error");
				 } finally {
				     out.close();
				 }
			
	}
	 
//	public void service(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws IOException
//	{
//		int a = Integer.parseInt((request.getParameter("num1")));
//		int b = Integer.parseInt((request.getParameter("num2")));
//		int res = a+b;
//		PrintWriter out = response.getWriter();
//		out.println("result is "+ res );
//	}

}
