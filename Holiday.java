package starttojava;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import Connectdb;

@WebServlet("/holiday")
public class Holiday extends HttpServlet 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 593072931507996593L;
	int status;
	 protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException
	 {
		 try {
			 Connection con=Connectdb.initializeDatabase();
			 PreparedStatement st = con.prepareStatement("insert into holiday (holidaydate) values(?)");	 
			 
			//st.setInt(1, 4);
			 st.setString(1,  request.getParameter("holiday"));
			// con.commit();
			 //java.sql.Date mySqlDate = new java.sql.Date(newEpisode.origionalAirDate.getTime());
			 
			 //st.setDate(20-05-2021, request.getParameter("holidaydate"));
			  status = st.executeUpdate();
			 st.close();
	         con.close();
		 }
		 catch (Exception e) {
	            e.printStackTrace();
	        }
		 
		 
		 
		   String inputdate = request.getParameter("inputdate");
	       String inputholiday = request.getParameter("holiday");
	       int nod = Integer.parseInt(request.getParameter("nod"));
	       
	       LocalDate result=calculateDate(nod,convertInputToLocalDate(inputdate),convertInputToLocalDate(inputholiday));
            
	       // get response writer
	        PrintWriter writer = response.getWriter();   
	        // return response
	        writer.println(result);  
	 }
	 
	 private LocalDate convertInputToLocalDate(String convertDate)
	 {
		    LocalDate date = LocalDate.parse(convertDate);
	        return date;
	 }
	 private LocalDate calculateDate(int nod,LocalDate date,LocalDate holiday )
	 {
		 LocalDate inputdatetemp;
	     LocalDate newdate;
		 for ( int i=1 ;i<=nod ; i++)
         { 
         	inputdatetemp=date.plusDays(i);
         	if( holiday.equals(inputdatetemp) )
                 {
                     nod=nod+1;
                     continue;
                 }
         	java.time.DayOfWeek day = inputdatetemp.getDayOfWeek();
         	if( day.toString() == "SATURDAY" )
                 nod=nod+1;
             else if( day.toString() == "SUNDAY" )	
                 nod=nod+1;                                      
         }
         newdate=date.plusDays(nod);
         return newdate;
         
	           
	 }
}



