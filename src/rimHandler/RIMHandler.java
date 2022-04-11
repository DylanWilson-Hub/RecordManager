
/* Created by Dylan Wilson */

package rimHandler;
 
import java.awt.*;



import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

//Java extension packages
import javax.swing.*;

import rimHandler.RIMGui;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.stream.Collectors;
import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RIMHandler {
public static String GlobalID;
	 static JFrame f;
	 static ArrayList<String> IDListFinal = new ArrayList<String>();
	 static int lValue;
    static ArrayList<ArrayList> emailString = new ArrayList<ArrayList>();


	 static String user, pass, level, email;
	public static void main(String[] args) {
			RimLogin.loginMain();
		//RIMGui.guiMain();

	}

	 
	//submit
	public static void insert(String barcode1, String altbarcode, String busUnit, String recCode, String recType, String recDesc, String yearsOfRecords, String employeeName, String assignedLoc, String curLoc, String oldLoc, String checkInDate, String checkOutDate, String legalHold, String user1, String pass1, String level1) throws Exception {
		
		try {		

	 	  
		if ( !barcode1.isEmpty() && !busUnit.isEmpty() && !recCode.isEmpty() &&  !recType.isEmpty() && !yearsOfRecords.isEmpty() && !legalHold.equals("  (Select One)")) { 
		  	// load database driver class 
	    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	
	    	 
	         // connect to database
	         Connection connection = DriverManager.getConnection(
	 	            "");
		
	         // Pull the ID for that name
	       
	         
	         
	         Statement stmt = null;
	         String query2 = "SELECT ID FROM RimRecordsTable WHERE Barcode = "+
			          "'" +
			          barcode1 +
			          "'"
			          ;	
	 //        System.out.println(query2);
	         stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(query2);
	         
	         
//CHANGE EMPLOYEE NAME FROM USER INPUT TO PULLING FROM THEIR USER PROFILE
	         
	         if (rs.next() == false) {

	         String query1 = "INSERT INTO RimRecordsTable (Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOfRecords,EmployeeName,CurrentLocation, AssignedLocation, OldLocation, CheckIn, CheckOut, legalHold) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	         PreparedStatement preparedStmt = connection.prepareStatement(query1);	     
	         preparedStmt.setString(1, barcode1);
	         preparedStmt.setString(2, altbarcode);
	         preparedStmt.setString(3, busUnit);
	         preparedStmt.setString(4, recCode);
	         preparedStmt.setString(5, recType);
	         preparedStmt.setString(6, recDesc);
	         preparedStmt.setString(7, yearsOfRecords);
	         preparedStmt.setString(8, employeeName);
	         preparedStmt.setString(9, curLoc);
	         preparedStmt.setString(10, assignedLoc);
	         preparedStmt.setString(11, oldLoc);
	         preparedStmt.setString(12, checkInDate);
	         preparedStmt.setString(13, checkOutDate);
	         preparedStmt.setString(14, legalHold);

	         preparedStmt.execute();
	         preparedStmt.close();
	         System.out.println("Works here");
	         String query12 = "INSERT INTO AuditLog (Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOfRecords,EmployeeName,CurrentLocation, AssignedLocation, OldLocation, CheckIn, CheckOut, legalHold, ChangedBy, DateChanged, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	         PreparedStatement preparedStmt1 = connection.prepareStatement(query12);	     
	         preparedStmt1.setString(1, barcode1);
	         preparedStmt1.setString(2, altbarcode);
	         preparedStmt1.setString(3, busUnit);
	         preparedStmt1.setString(4, recCode);
	         preparedStmt1.setString(5, recType);
	         preparedStmt1.setString(6, recDesc);
	         preparedStmt1.setString(7, yearsOfRecords);
	         preparedStmt1.setString(8, employeeName);
	         preparedStmt1.setString(9, curLoc);
	         preparedStmt1.setString(10, assignedLoc);
	         preparedStmt1.setString(11, oldLoc);
	         preparedStmt1.setString(12, checkInDate);
	         preparedStmt1.setString(13, checkOutDate);
	         preparedStmt1.setString(14, legalHold);
	         LocalDateTime now = LocalDateTime.now();  	         
	         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.ENGLISH); 
	         String timeStamp = now.format(dtf);
	         SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	         java.util.Date date = sdf1.parse(timeStamp);
	         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
	         preparedStmt1.setDate(16, sqlStartDate);//get system date
	         preparedStmt1.setString(17, "Insert");// Insert
	         System.out.println("Works here1");

	         preparedStmt1.execute();
	         preparedStmt1.close();
	         
	         
	         connection.close();
	     	
	         f = new JFrame();
		     JOptionPane.showMessageDialog(f, "Entered!");
				//System.exit(0);				
	         }
				
	         
	         else {
	        	 f = new JFrame();
			     JOptionPane.showMessageDialog(f, "Barcode already exists");
			     RIMGui.frame.dispose();
			     RIMGui.guiMain(user,pass,level);
	         }
				
	         
	         
	         
	         
	         
	         
	         
				
 	 } else {
 		
	         f = new JFrame();
		     JOptionPane.showMessageDialog(f, "Please Fill In All Fields");
 	 }

   } 
	
	catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
		user = user1;
		pass = pass1;
		level=level1;
	} 
	//delete
	
	
	
	
	
	public static void delete(String searchKeySel, String searchKey) throws ParseException {
		
		try {
		      
		 	  if ( !searchKey.isEmpty() && !searchKeySel.equals("  (Select one)")) { 
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	             "");
			        

			         // Pull the ID for that name
			         Statement stmt = null;
			         String query2 = "SELECT ID FROM RimRecordsTable WHERE " +
					          searchKeySel +
					         " = " +
					          "'" +
					          searchKey +
					          "'"
					          ;	
			         stmt = connection.createStatement();
			         ResultSet rs = stmt.executeQuery(query2);
			         rs.next();
			         String ID = rs.getString("ID");
			         System.out.println(ID);
			         
			         // query database
			         ResultSet resultSet =
			            stmt.executeQuery( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
			            		+ " FROM RimRecordsTable"
			            		+ " WHERE ID =" + ID);
			        resultSet.next();
			         
			         
			         
			         String barcode1 = resultSet.getString("Barcode");
			         System.out.println(barcode1);
			         String altbarcode = resultSet.getString("AltBarcode");
			         
			         String busUnit = resultSet.getString("BusinessUnit");
			         
			         String recCode = resultSet.getString("RecordCode");
			         
			         String recType = resultSet.getString("RecordType");
			         
			         String recDesc = resultSet.getString("RecordDescription");
			         
			         String yearsOfRecords = resultSet.getString("YearsOfRecords");
			         
			         String employeeName = resultSet.getString("EmployeeName");
			         
			         String curLoc = resultSet.getString("CurrentLocation");
			         
			         String assignedLoc = resultSet.getString("AssignedLocation");
			         
			         String oldLoc = resultSet.getString("OldLocation");
			         
			         String checkInDate = resultSet.getString("CheckIn");
			         
			         String checkOutDate = resultSet.getString("CheckOut");
			         
					 String legalHold = resultSet.getString("legalHold");
					 
					
					
					System.out.println("Works here" + barcode1);
			         
			         
			         String query12 = "INSERT INTO AuditLog (Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOfRecords,EmployeeName,CurrentLocation, AssignedLocation, OldLocation, CheckIn, CheckOut, legalHold, ChangedBy, DateChanged, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			         PreparedStatement preparedStmt1 = connection.prepareStatement(query12);	     
			         preparedStmt1.setString(1, barcode1);
			         preparedStmt1.setString(2, altbarcode);
			         preparedStmt1.setString(3, busUnit);
			         preparedStmt1.setString(4, recCode);
			         preparedStmt1.setString(5, recType);
			         preparedStmt1.setString(6, recDesc);
			         preparedStmt1.setString(7, yearsOfRecords);
			         preparedStmt1.setString(8, optionPage.first+" "+optionPage.last);
			         preparedStmt1.setString(9, curLoc);
			         preparedStmt1.setString(10, assignedLoc);
			         preparedStmt1.setString(11, oldLoc);
			         preparedStmt1.setString(12, checkInDate);
			         preparedStmt1.setString(13, checkOutDate);
			         preparedStmt1.setString(14, legalHold);
			         preparedStmt1.setString(15, RimLogin.user);//username
			         LocalDateTime now = LocalDateTime.now();  	         
			         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.ENGLISH); 
			         String timeStamp = now.format(dtf);
			         SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			         java.util.Date date = sdf1.parse(timeStamp);
			         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			         preparedStmt1.setDate(16, sqlStartDate);//get system date
			         preparedStmt1.setString(17, "Delete");// Insert
			         System.out.println("Works here1");

			         preparedStmt1.execute();
			         preparedStmt1.close();
			         

			         // close statement
			         stmt.close();
			         // Using ID, delete all records for that ID

			         // For the Names table
			         // Setup SQL for Deletion query1
			         String query1 = "DELETE FROM RimRecordsTable WHERE ID=?";
			         // Plug fields into query1
			         PreparedStatement preparedStmt = connection.prepareStatement(query1);
			         preparedStmt.setString(1, ID);
			         // execute the query1 preparedstatement
			         preparedStmt.execute();
			         // close statement
			         preparedStmt.close();
			         
			         
			         
			         
			      // set up popup display window
			   		 JFrame f = new JFrame();
			   		 JOptionPane.showMessageDialog(f, "Deleted!");
			   		
			         
		 	 } else {
			         
			       // set up popup display window
				   JFrame f = new JFrame();
				   JOptionPane.showMessageDialog(f, "Enter a search filter");
				   
		 	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		   
		} // end DbConnection constructor definition
	
	
	
	
	
	
	
	
	public static void updated(String barcode1, String altbarcode, String busUnit, String recCode, String recType, String recDesc, String yearsOfRecords, String employeeName, String assignedLoc, String curLoc, String oldLoc, String checkInDate, String checkOutDate, String legalHold, String searchKeySel, String searchKey) throws Exception {
		
		   try {
			      
			 	  // Pull data from form

			 	  
			 	  // Using the 'empty' method, check to see if some fields are empty or populated
			   if ( !searchKey.isEmpty() && !searchKeySel.equals("  (Select one)")) { 
					  	// load database driver class
				    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				
				         // connect to database
				         Connection connection = DriverManager.getConnection(
					 	             "");
				        

				         // Pull the ID for that name
				         Statement stmt = null;
				    //     System.out.println("Work1");
				         String query2 = "SELECT ID FROM RimRecordsTable WHERE " +
						          searchKeySel +
						         " = " +
						          "'" +
						          searchKey +
						          "'"
						          ;	
				  //       System.out.println(query2);
				         stmt = connection.createStatement();
				         ResultSet rs = stmt.executeQuery(query2);
				         
				         
				         
				         if (rs.next() == false) {
					         // set up popup display window
					         f = new JFrame();
						     JOptionPane.showMessageDialog(f, "No Records Found!");
						     
				         }			
				         
				         else {
				  //       System.out.println("Work2");

				         String ID = rs.getString("ID");
				       

				         stmt.close();
				         
				         
				         
				         
				         // Using ID, place the rest of the values into the other tables
				         
				         // If a barcode
				         if ( !barcode1.isEmpty() ) {
					         // Setup SQL for Insertion query1
				        	 String query1 = "UPDATE RimRecordsTable SET Barcode=? WHERE ID=?";
				        //	 System.out.println(query1);
					         // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, barcode1);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				         // If a alt barcode
				         if ( !altbarcode.isEmpty() ) {
					         // Setup SQL for Insertion query1
					         String query21 = "UPDATE RimRecordsTable SET AltBarcode=? WHERE ID=?";
					         // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query21);
					         preparedStmt.setString(1, altbarcode);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				         // If a barcode BusinessUnit
				         if ( !busUnit.isEmpty() ) {
				     //   	 System.out.println(busUnit);
					         // Setup SQL for Insertion query1
					         String query21 = "UPDATE RimRecordsTable SET BusinessUnit=? WHERE ID=?";
					         // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query21);
					         preparedStmt.setString(1, busUnit);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
					         
				         }
				         // If a RecordCode
				         if ( !recCode.isEmpty() ) {
					         // Setup SQL for Insertion query1
					         String query21 = "UPDATE RimRecordsTable SET RecordCode=? WHERE ID=?";
					         // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query21);
					         preparedStmt.setString(1, recCode);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				      //  	 System.out.println(busUnit);

				         }
				         // RecordType
				         if ( !recType.isEmpty() ) {
					         // Setup SQL for Insertion query1
					         String query21 = "UPDATE RimRecordsTable SET RecordType=? WHERE ID=?";
					         // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query21);
					         preparedStmt.setString(1, recType);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         // RecordDesc
				         if ( !recDesc.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET RecordDescription=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, recDesc);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         // YearsOfRecords
				         if ( !yearsOfRecords.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET YearsOfRecords=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, yearsOfRecords);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				      // EmployeeName, always update the employee based on the user editing the value
				         if ( 1==1) {
				        	 String query1 = "UPDATE RimRecordsTable SET EmployeeName=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, optionPage.first+" "+optionPage.last);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				         
				         
				         
				         
				         
				      // CurrentLoc
				         if ( !curLoc.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET CurrentLocation=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, curLoc);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				      // AssignedLoc
				         if ( !assignedLoc.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET AssignedLocation=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, assignedLoc);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				      // OldLoc
				         if ( !oldLoc.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET OldLocation=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, oldLoc);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				      // CheckIn
				         if ( !checkInDate.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET CheckIn=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, checkInDate);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				      // Checkout
				         if ( !checkOutDate.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET CheckOut=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, checkOutDate);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         
				      // legalHold
				         if ( !legalHold.isEmpty() ) {
				        	 String query1 = "UPDATE RimRecordsTable SET legalHold=? WHERE ID=?";
				        	 // Plug fields into query1
					         PreparedStatement preparedStmt = connection.prepareStatement(query1);
					         preparedStmt.setString(1, legalHold);
					         preparedStmt.setString(2, ID);
					         // execute the query1 preparedstatement
					         preparedStmt.execute();
					         // close statement
					         preparedStmt.close();
				         }
				         String query12 = "INSERT INTO AuditLog (Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOfRecords,EmployeeName,CurrentLocation, AssignedLocation, OldLocation, CheckIn, CheckOut, legalHold, ChangedBy, DateChanged, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				         PreparedStatement preparedStmt1 = connection.prepareStatement(query12);	     
				         preparedStmt1.setString(1, barcode1);
				         preparedStmt1.setString(2, altbarcode);
				         preparedStmt1.setString(3, busUnit);
				         preparedStmt1.setString(4, recCode);
				         preparedStmt1.setString(5, recType);
				         preparedStmt1.setString(6, recDesc);
				         preparedStmt1.setString(7, yearsOfRecords);
				         preparedStmt1.setString(8, employeeName);
				         preparedStmt1.setString(9, curLoc);
				         preparedStmt1.setString(10, assignedLoc);
				         preparedStmt1.setString(11, oldLoc);
				         preparedStmt1.setString(12, checkInDate);
				         preparedStmt1.setString(13, checkOutDate);
				         preparedStmt1.setString(14, legalHold);
				         preparedStmt1.setString(15, RimLogin.user);//username
				         LocalDateTime now = LocalDateTime.now();  	         
				         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.ENGLISH); 
				         String timeStamp = now.format(dtf);
				         SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
				         java.util.Date date = sdf1.parse(timeStamp);
				         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
				         preparedStmt1.setDate(16, sqlStartDate);//get system date
				         preparedStmt1.setString(17, "Update");// Update
				         System.out.println("Works here1");

				         preparedStmt1.execute();
				         preparedStmt1.close();
				         
				         
				         
				         
				         connection.close();
				         
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "Updated!\n"
					     		+ "Don't forget to update the search if you'd like to update again!");
				         }
				         
			 	  } else {
			   		  	         
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "Change atleast one value to update");
			   	 }

			   }  // end try

			   // detect problems interacting with the database
			   catch ( SQLException sqlException ) {
			      JOptionPane.showMessageDialog( null,
			         sqlException.getMessage(), "Database Error",
			         JOptionPane.ERROR_MESSAGE );
			      System.exit( 1 );
			   }

			   catch(ClassNotFoundException cnfex) {

			       System.out.println("Problem in loading or "
			               + "registering MS Access JDBC driver");
			       cnfex.printStackTrace();
			   }
			} // end DbConnection constructor definition 
		
		
	
	
	
	
	
	
	public static void search(String searchKeySel, String searchKey) { 
		
		 try {
		      
		 	  // Pull data from form

		 	  
		 	  // Using the 'empty' method, check to see if some fields are empty or populated
		 	  if ( !searchKey.isEmpty() && !searchKeySel.equals("  (Select one)")) { 
				  	// load database driver class
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	            "");
			        

			         // Pull the ID for that name
			         Statement stmt = null;
			         
			         
			         String query2 = "SELECT ID FROM RimRecordsTable WHERE " +
					          searchKeySel +
					         " = " +
					          "'" +
					          searchKey +
					          "'"
					          ;	
			         System.out.println(query2);
			         
			         
			         
			         stmt = connection.createStatement();
			         
			         
			         
			         
			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
			         

			         
			         
			         if (rs.next() == false) {
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "No Records Found!");
					     
			         } else {

			        	 String ID = rs.getString("ID");
				      
			        	 
			        	 String query3 =( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
				            		+ " FROM RimRecordsTable"
				            		+ " WHERE ID =" + ID);
			        	
				         
				         
				         // Using ID, search for the rest of the values from the other tables
				         
				         Statement statement = connection.createStatement();
				         // query database
				         ResultSet resultSet =
				            statement.executeQuery( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
				            		+ " FROM RimRecordsTable"
				            		+ " WHERE ID =" + ID);
				         

				         
				         
				         // process query results and use Setters in Gui to populate fields
				         resultSet.next();
				         
				         
				         
				         
				         
				         
				         String bcode = resultSet.getString("Barcode");
				         searchGUI.setBarcode(bcode);
				         
				         String acode = resultSet.getString("AltBarcode");
				         searchGUI.setAltBarcode(acode);
				         
				         String bunit = resultSet.getString("BusinessUnit");
				         searchGUI.setbUnit(bunit);
				         
				         String rcode = resultSet.getString("RecordCode");
				         searchGUI.setrCode(rcode);
				         
				         String rtype = resultSet.getString("RecordType");
				         searchGUI.setrType(rtype);
				         
				         String recd = resultSet.getString("RecordDescription");
				         searchGUI.setrDesc(recd);
				         
				         String yor = resultSet.getString("YearsOfRecords");
				         searchGUI.setyearOfRec(yor);
				         
				         
				         
				         
				         String empn = resultSet.getString("EmployeeName");
				         searchGUI.setempName(empn);
				         
				         
				         
				         
				         
				         String cloc = resultSet.getString("CurrentLocation");
				         searchGUI.setcLoc(cloc);
				         
				         String aloc = resultSet.getString("AssignedLocation");
				         searchGUI.setaLoc(aloc);	
				         
				         String oLoc = resultSet.getString("OldLocation");
				         searchGUI.setoLoc(oLoc);
				         
				         String cin = resultSet.getString("CheckIn");
				         searchGUI.setcIn(cin);
				         
				         String cOut = resultSet.getString("CheckOut");
				         searchGUI.setcOut(cOut);		
				         
						 String legalHold = resultSet.getString("legalHold");
						 
						
						  if (legalHold.equals("Yes")) {
							lValue = 1;
						}
						 else{
								lValue = 2;
							}
						
					//	System.out.println(lValue);
						  searchGUI.setlHold(lValue); 

				         
				         
				         
				         
				         
				         // close statement and connection
				         statement.close();
				         connection.close();
				
			         } while (rs.next());
			         
			         // close statement
			         stmt.close();
			         
			         
			         
		 	  } else {
		   		  // set up popup display window
		 		  JFrame f = new JFrame();
				      JOptionPane.showMessageDialog(f, "Enter a search filter!");
		   	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		} // end DbConnection constructor definition

	

public static void btchSearch(String searchKeySel, String searchKey) { 
	
	 try {
	      
	 	  // Pull data from form

	 	  
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !searchKey.isEmpty() && !searchKeySel.equals("  (Select one)") || searchKeySel.equals("ALL RECORDS")) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	             "");
		        

		         if (searchKeySel.equals("ALL RECORDS")) {
		        	// Pull the ID for that name
			         Statement stmt = null;
			         
			         
			         String query2 = "SELECT * FROM RimRecordsTable"
					          ;	
			      //   System.out.println(query2);
			         
			         
			          
			         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
		        	 
			    	  ArrayList<String> IDList = new ArrayList<String>();

			        	  
			        	 while (rs.next()) {
			        		 String ID = rs.getString("ID");
			        		 IDList.add(ID);}
			        
			        	 
			        	 if (!rs.previous()) {
			        		  JFrame f = new JFrame();
						      JOptionPane.showMessageDialog(f, "Nothing found");	
						      batchSearch.frame.dispose();
			        	 }

			        	 else {
			        	// System.out.print(IDList);
				         Statement statement = connection.createStatement();

			        	 for (int i=0; i<IDList.size(); i++) {
			        		 String ID = IDList.get(i);
			        	//	 System.out.println(ID);
					         ResultSet resultSet =
							            statement.executeQuery( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
							            		+ " FROM RimRecordsTable"
							            		+ " WHERE ID =" + ID); 
					         int columnCount = resultSet.getMetaData().getColumnCount();
					      
					         while (resultSet.next()) {
					             ArrayList<Object> row = new ArrayList<>();
					             Vector<String> vString = new Vector<String>();
					             for (int i1 = 1; i1 <= columnCount; i1++) {
					               //  row.add(resultSet.getObject(i1));
					                 vString.addElement(resultSet.getString(i1));

	             }
					     		batchSearch.batchMain(); 

					       //      System.out.println(row); 
					             batchSearch.tableModel.addRow(vString); 

					         }

			        	 }
			        	 batchSearch.getOriginal();
			        	  GlobalID = rs.getString("ID");
				      

				         statement.close();
				         connection.close();
				
			        	 } while (rs.next());
			         
			         // close statement
			         stmt.close();
		         }

		         else {
		         
		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM RimRecordsTable WHERE " +
				          searchKeySel +
				         " LIKE " +
				          "'" + "%"+
				          searchKey +
				         "%" +"'"
				          ;	
		         System.out.println(query2);
		         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		         
		         
		         
		         
		         ResultSet rs = stmt.executeQuery(query2);
		         
		         
	        	 
		    	  ArrayList<String> IDList = new ArrayList<String>();

		        	  
		        	 while (rs.next()) {
		        		 String ID = rs.getString("ID");
		        		 IDList.add(ID);}
		        	 System.out.println(IDList);
		        	 
		        	 if (!rs.previous()) {
		        		  JFrame f = new JFrame();
					      JOptionPane.showMessageDialog(f, "Nothing found");	
					      batchSearch.frame.dispose();
		        	 }
		        	 
	 	  
		        	 
		        	 else {
		        	// System.out.print(IDList);
			         Statement statement = connection.createStatement();
			         
			         
			         
			         
		        	 for (int i=0; i<IDList.size(); i++) {
		        		 String ID = IDList.get(i);
		        	//	 System.out.println(ID);
				         ResultSet resultSet =
						            statement.executeQuery( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
						            		+ " FROM RimRecordsTable"
						            		+ " WHERE ID =" + ID); 
				         int columnCount = resultSet.getMetaData().getColumnCount();
				      
				         while (resultSet.next()) {
				             ArrayList<Object> row = new ArrayList<>();
				             Vector<String> vString = new Vector<String>();
				             for (int i1 = 1; i1 <= columnCount; i1++) {
				                 row.add(resultSet.getObject(i1));
				                 vString.addElement(resultSet.getString(i1));
				                		             }

				     		batchSearch.batchMain();

				             System.out.println(row); 
				             batchSearch.tableModel.addRow(vString); 
    
				         }
			        	 batchSearch.getOriginal();

		

		        	 
		        	 }
		        	  GlobalID = rs.getString("ID");
			      

			         statement.close();
			         connection.close();
			
		        	 } while (rs.next());
		         
		         // close statement
		         stmt.close(); }
		         searchGUI.frame.dispose();
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Enter a search filter!");
			      
	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	} // end DbConnection constructor definition







@SuppressWarnings("unused")
public static void btchUpdate(ArrayList<String> data) throws Exception {
	
	   try {

		 	  // Pull data from form

		 	  
		 	  // Using the 'empty' method, check to see if some fields are empty or populated
		   if ( !data.isEmpty()) { 
		         Object barcode1, altbarcode, busUnit, recCode, recType, recDesc, yearsOfRecords,employeeName,curLoc,assignedLoc,oldLoc,checkInDate,checkOutDate,legalHold;
				  	// load database driver class
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	             "");
			         
			       Object[] dataString = data.toArray();
			  //     Object OG;

			     //  for (int i = 0; i<14; i++) {
			       //OG = dataString[i];
			       
			       
			       
			       

			       	 barcode1 = dataString[0];//.toString();
			       
			       	 altbarcode = dataString[1];//.toString();
			         
			         busUnit= dataString[2];//.toString();
			         
			         recCode= dataString[3];//.toString();
			         
			         recType= dataString[4];//.toString();
			         
			         recDesc= dataString[5];//.toString(); 
			         
			         yearsOfRecords= dataString[6];//.toString();
			         
			         employeeName= dataString[7];//.toString();
			         
			         curLoc= dataString[8];//.toString();
			         
			         assignedLoc= dataString[9];//.toString();
			         
			         oldLoc= dataString[10];//.toString();
			         
			         checkInDate= dataString[11];//.toString();
			         
			         checkOutDate= dataString[12];//.toString();
			         
			         legalHold= dataString[13];//.toString();
		
			      // }
			         
			         
			      
			         
			         
			         // Pull the ID for that name
			         Statement stmt = null;
			         String query2 = "SELECT ID FROM RimRecordsTable WHERE Barcode" +" = " + "'" + barcode1 +"'" +
			        		  "OR "+"AltBarcode" +" = " + "'" + altbarcode + "'" ;	
			         stmt = connection.createStatement();
			         ResultSet rs = stmt.executeQuery(query2);
			         

			         
			         if (rs.next() == false) {
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "There are no barcodes/altbarcodes that equal that");
					     
			         }			
			         
			         else {
			   //      System.out.println("Work2");

			         String ID = rs.getString("ID"); 
			       

			         stmt.close();
			         
			         
			         
			         
			         // Using ID, place the rest of the values into the other tables
			         
			         // If a barcode
			         if (barcode1 != null) {
				         // Setup SQL for Insertion query1
			        	 String query1 = "UPDATE RimRecordsTable SET Barcode=? WHERE ID=?";
			       // 	 System.out.println(query1);
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, barcode1.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();

			         }
			         else {
			        	 //do nothing	 
			         }
			         
			         // If a alt barcode
			         if (altbarcode != null) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE RimRecordsTable SET AltBarcode=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, altbarcode.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();

			         }
			         else {
			        	 //do nothing	 
			         }
			         // If a barcode BusinessUnit
			         if (busUnit !=null) {
			        	// System.out.println(busUnit);
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE RimRecordsTable SET BusinessUnit=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, busUnit.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
				         
			         }
			         else {
			        	 //do nothing	 
			         }
			         // If a RecordCode
			         if (recCode != null) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE RimRecordsTable SET RecordCode=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, recCode.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			        //	 System.out.println(busUnit);

			         }
			         else {
			        	 //do nothing	 
			         }
			         // RecordType
			         if (recType != null) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE RimRecordsTable SET RecordType=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, recType.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			         // RecordDesc
			         if (recDesc != null) {
			        	 String query1 = "UPDATE RimRecordsTable SET RecordDescription=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, recDesc.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			         // YearsOfRecords
			         if (yearsOfRecords !=null) {
			        	 String query1 = "UPDATE RimRecordsTable SET YearsOfRecords=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, yearsOfRecords.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			      // EmployeeName
			         if (1==1) {
			        	 String query1 = "UPDATE RimRecordsTable SET EmployeeName=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, optionPage.first+" "+optionPage.last);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			      // CurrentLoc
			         if (curLoc !=null)   {
			        	 String query1 = "UPDATE RimRecordsTable SET CurrentLocation=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, curLoc.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			      // AssignedLoc
			         if ( assignedLoc != null) {
			        	 String query1 = "UPDATE RimRecordsTable SET AssignedLocation=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, assignedLoc.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing
			         }
			         
			      // OldLoc
			         if (oldLoc !=null) {
			        	 String query1 = "UPDATE RimRecordsTable SET OldLocation=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, oldLoc.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing
			         }
			         
			      // CheckIn
			         if (checkInDate != null) {
			        	 String query1 = "UPDATE RimRecordsTable SET CheckIn=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, checkInDate.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			      // Checkout
			         if (checkOutDate !=null) {
			        	 String query1 = "UPDATE RimRecordsTable SET CheckOut=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, checkOutDate.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			      // legalHold
			         if (legalHold !=null) {
			        	 String query1 = "UPDATE RimRecordsTable SET legalHold=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, legalHold.toString());
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         else {
			        	 //do nothing	 
			         }
			         
			         
			         String searchKey = ( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
			            		+ " FROM RimRecordsTable"
			            		+ " WHERE ID =" + ID);
			                 stmt = connection.createStatement();
			         ResultSet rs1 = stmt.executeQuery(searchKey);
			         rs.next();
			         
			         //SCAN THROUGH ALL, ONLY CHANGE ONES THAT WERE EDITED/UPDATED
			         
			         
			         String query12 = "INSERT INTO AuditLog (Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOfRecords,EmployeeName,CurrentLocation, AssignedLocation, OldLocation, CheckIn, CheckOut, legalHold, ChangedBy, DateChanged, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			         PreparedStatement preparedStmt1 = connection.prepareStatement(query12);	     
			         preparedStmt1.setString(1, (String) barcode1);
			         preparedStmt1.setString(2, (String) altbarcode);
			         preparedStmt1.setString(3, (String) busUnit);
			         preparedStmt1.setString(4, (String) recCode);
			         preparedStmt1.setString(5, (String) recType);
			         preparedStmt1.setString(6, (String) recDesc);
			         preparedStmt1.setString(7, (String) yearsOfRecords);
			         preparedStmt1.setString(8, (String) optionPage.first+" "+optionPage.last);
			         preparedStmt1.setString(9, (String) curLoc);
			         preparedStmt1.setString(10, (String) assignedLoc);
			         preparedStmt1.setString(11, (String) oldLoc);
			         preparedStmt1.setString(12, (String) checkInDate);
			         preparedStmt1.setString(13, (String) checkOutDate);
			         preparedStmt1.setString(14, (String) legalHold);
			         preparedStmt1.setString(15, RimLogin.user);//username
			         LocalDateTime now = LocalDateTime.now();  	         
			         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.ENGLISH); 
			         String timeStamp = now.format(dtf);
			         SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			         java.util.Date date = sdf1.parse(timeStamp);
			         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			         preparedStmt1.setDate(16, sqlStartDate);//get system date
			         preparedStmt1.setString(17, "Update");// Update

			         preparedStmt1.execute();
			         preparedStmt1.close();
			         f = new JFrame();
				    // JOptionPane.showMessageDialog(f, "Updated ");
			         
			         
			         connection.close();
			         
			        
			         }
			         
		 	  } else {
		   		  	         
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Change atleast one value to update");
		   	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		} // end DbConnection constructor definition 




public static void btchDelete(String user, int rowCount) throws Exception {
	
	   try {
		      
		 	  // Pull data from form
		 	  
		 	  // Using the 'empty' method, check to see if some fields are empty or populated
		   if ( !user.isEmpty()) { 
			   System.out.println(user);

				  	// load database driver class
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	             "");
			       
			       
			        String barcode1 = user;
			        
			         // Pull the ID for that name
			         Statement stmt = null;
			     //    System.out.println("Work1");
			         String query2 = "SELECT ID FROM RimRecordsTable WHERE Barcode" +" = " + "'" + barcode1 + "'";	
			     //    System.out.println(query2);
			         stmt = connection.createStatement();
			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
			         
			         if (rs.next() == false) {
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "There are no barcodes/altbarcodes that equal that");
			         }			
			         
			         else {
			    //     System.out.println("Work2");

			         String ID = rs.getString("ID"); 
			      // query database
			         ResultSet resultSet =
			            stmt.executeQuery( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold"
			            		+ " FROM RimRecordsTable"
			            		+ " WHERE ID =" + ID);
			        resultSet.next();
			         
			         
			         
			         String barcode12 = resultSet.getString("Barcode");
			         System.out.println(barcode12);
			         String altbarcode = resultSet.getString("AltBarcode");
			         
			         String busUnit = resultSet.getString("BusinessUnit");
			         
			         String recCode = resultSet.getString("RecordCode");
			         
			         String recType = resultSet.getString("RecordType");
			         
			         String recDesc = resultSet.getString("RecordDescription");
			         
			         String yearsOfRecords = resultSet.getString("YearsOfRecords");
			         
			         String employeeName = resultSet.getString("EmployeeName");
			         
			         String curLoc = resultSet.getString("CurrentLocation");
			         
			         String assignedLoc = resultSet.getString("AssignedLocation");
			         
			         String oldLoc = resultSet.getString("OldLocation");
			         
			         String checkInDate = resultSet.getString("CheckIn");
			         
			         String checkOutDate = resultSet.getString("CheckOut");
			         
					 String legalHold = resultSet.getString("legalHold");
					 
					
					
					System.out.println("Works here" + barcode1);
			         
			         
			         String query12 = "INSERT INTO AuditLog (Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOfRecords,EmployeeName,CurrentLocation, AssignedLocation, OldLocation, CheckIn, CheckOut, legalHold, ChangedBy, DateChanged, Status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			         PreparedStatement preparedStmt1 = connection.prepareStatement(query12);	     
			         preparedStmt1.setString(1, barcode1);
			         preparedStmt1.setString(2, altbarcode);
			         preparedStmt1.setString(3, busUnit);
			         preparedStmt1.setString(4, recCode);
			         preparedStmt1.setString(5, recType);
			         preparedStmt1.setString(6, recDesc);
			         preparedStmt1.setString(7, yearsOfRecords);
			         preparedStmt1.setString(8, optionPage.first+" "+optionPage.last);
			         preparedStmt1.setString(9, curLoc);
			         preparedStmt1.setString(10, assignedLoc);
			         preparedStmt1.setString(11, oldLoc);
			         preparedStmt1.setString(12, checkInDate);
			         preparedStmt1.setString(13, checkOutDate);
			         preparedStmt1.setString(14, legalHold);
			         preparedStmt1.setString(15, RimLogin.user);//username
			         LocalDateTime now = LocalDateTime.now();  	         
			         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.ENGLISH); 
			         String timeStamp = now.format(dtf);
			         SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			         java.util.Date date = sdf1.parse(timeStamp);
			         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
			         preparedStmt1.setDate(16, sqlStartDate);//get system date
			         preparedStmt1.setString(17, "Delete");// Insert
			         System.out.println("Works here1");

			         preparedStmt1.execute();
			         preparedStmt1.close();
			         
			         
			         
			         // Setup SQL for Deletion query1
			         String query1 = "DELETE FROM RimRecordsTable WHERE ID=?";
			         // Plug fields into query1
			         PreparedStatement preparedStmt = connection.prepareStatement(query1);
			         preparedStmt.setString(1, ID);
			         // execute the query1 preparedstatement
			         preparedStmt.execute();
			         // close statement
			         preparedStmt.close();
			         
			         
			         f = new JFrame();
				    // JOptionPane.showMessageDialog(f, "Deleted " +rowCount+"'s");

			      // set up popup display window
			   		
			         
			         connection.close();
			         
			        
			         }
			         
		 	  } else {
		   		  	         
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Change atleast one value to update");
		   	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		} 





public static void login(String user, String pass) { 
	
	 try {
	      
	 	  // Pull data from form

	 	  
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !user.isEmpty() && !pass.isEmpty()) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	          "");
		        

		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM Users WHERE Username =" +  "'" +    user + "'" + "AND Password =" + "'" + pass + "'"
				          ;	
		         System.out.println(query2);
		         
		         
		         
		         stmt = connection.createStatement();

		         ResultSet rs = stmt.executeQuery(query2);
		         

		         
		         if (rs.next() == false) {
			         // set up popup display window
			         f = new JFrame();
			         JOptionPane.showMessageDialog(f, "Account does not exist\n"
					      		+ "Contact an admin for help");
			         RimLogin.loginMain();
				     
		         } else {
		        	 RimLogin.frame.dispose();
		        	 String ID = rs.getString("ID");
			      
			         
			         Statement statement = connection.createStatement();
			         // query database
			         ResultSet resultSet =
					            statement.executeQuery( "SELECT level, email, first, last FROM Users WHERE ID =" +  "'" +    ID +"'");
					         
					         resultSet.next();
			         String level = resultSet.getString("level");
			         email = resultSet.getString("email");
			         String first = resultSet.getString("first");
			         String last = resultSet.getString("last");
			         
						RimLogin.frame.dispose();

			         if(level.equals("Admin") || level.equals("SuperUser")) {
			        	 optionPage.optionMain(user,pass, level,first,last);
				         }
			          else if(level.equals("User")) {
				        	 optionPage.optionMain(user,pass, level,first,last);				         
			         }
			          else if(level.equals("Viewer")) {
				        	 optionPage.optionMain(user,pass, level,first,last);				         
			         }
			         
				         else {
				        	 JFrame f = new JFrame(); 
						      JOptionPane.showMessageDialog(f, "Account does not exist\n"
						      		+ "Contact an admin for help");
						      RimLogin.frame.dispose();
				         }
			         
			         
			         
			         // close statement and connection
			         statement.close();
			         connection.close();
			
		         } while (rs.next());
		         
		         // close statement
		         stmt.close();
		         
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Username or password not found");
	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	} // end DbConnection constructor definition





public static void admin(String user, String pass) { 
	 
	 try {
	      
	 	  // Pull data from form

	 	  System.out.println("Her1");
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !user.isEmpty() && !pass.isEmpty()) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	          "");
		        

		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM Users WHERE Username =" +  "'" +    user + "'" + "AND Password =" + "'" + pass + "'";
				          ;	
		         System.out.println(query2);
		         
		         
		         
		         stmt = connection.createStatement();
		         
		         
		         
		         
		         ResultSet rs = stmt.executeQuery(query2);
		         
		         
		         

		         
		         
		         if (rs.next() == false) {
			         // set up popup display window
		        	  JOptionPane.showMessageDialog(f, "Account does not exist\n"
					      		+ "Contact an admin for help");
				     
		         } else {

		        	 String ID = rs.getString("ID");
		        	 System.out.println(ID);
			         // Using ID, search for the rest of the values from the other tables
			         
			         Statement statement = connection.createStatement();
			         // query database
			         ResultSet resultSet =
			            statement.executeQuery( "SELECT level FROM Users WHERE ID =" +  "'" +    ID +"'");
			         
			         resultSet.next(); 
			         String level = resultSet.getString("level");

			         System.out.println(level);
			     
			         if(level.equals("Admin") || level.equals("SuperUser")) {
			         RimAdmin.adminMain(user,pass,level); 
			         }
			         else {
			        	 JFrame f = new JFrame();
					      JOptionPane.showMessageDialog(f, "Account used is not an admin account\n"
					      		+ "Contact an admin for help");
			         }
			         
			         
			         
			         
			         // close statement and connection
			         statement.close();
			         connection.close();
			
		         } while (rs.next());
		         
		         // close statement
		         stmt.close();
		         
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Username or password not found");
	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   } }





public static void adminInsert(String user, String pass, String level, String email, String first, String last) throws Exception {
	
	try {		

 	  System.out.println("THIS IS THE " +level);
	if ( !user.isEmpty() && !pass.isEmpty() && !level.equals("  (Select One)") && !email.isEmpty()&& !first.isEmpty()&& !last.isEmpty()) { 
	  	// load database driver class 
    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

         // connect to database
         Connection connection = DriverManager.getConnection(
 	           "");
	
         

         
         Statement stmt = null;
         String query2 = "SELECT ID FROM Users WHERE Username = "+
		          "'" +
		          user +
		          "'"
		          ;	
 //        System.out.println(query2);
         stmt = connection.createStatement();
         ResultSet rs = stmt.executeQuery(query2);
         
         

         
         if (rs.next() == false) {

         String query1 = "INSERT INTO Users (Username, Password, level,email,first,last) VALUES (?,?,?,?,?,?)";
         PreparedStatement preparedStmt = connection.prepareStatement(query1);	     
         preparedStmt.setString(1, user);
         preparedStmt.setString(2, pass);
         preparedStmt.setString(3, level);
         preparedStmt.setString(4, email);
         preparedStmt.setString(5, first);
         preparedStmt.setString(6, last);


         preparedStmt.execute();
         preparedStmt.close();
         
         
         
         connection.close();
     	
         f = new JFrame();
	     JOptionPane.showMessageDialog(f, "Entered!");
         RimAdmin.username.setText("");
         RimAdmin.password.setText("");
         RimAdmin.levelSel.setSelectedIndex(0);
         RimAdmin.email.setText("");
         RimAdmin.first.setText("");
         RimAdmin.last.setText("");
         }
         
         else {
        	 f = new JFrame();
    	     JOptionPane.showMessageDialog(f, "Username already exists");
             RimAdmin.username.setText("");
             RimAdmin.password.setText("");
             RimAdmin.levelSel.setSelectedIndex(0);
             RimAdmin.email.setText("");
             RimAdmin.first.setText("");
             RimAdmin.last.setText("");
         }
         
	     
	     
	 } else {
		
         f = new JFrame();
	     JOptionPane.showMessageDialog(f, "Please Fill In All Fields");
	 }

} 

catch ( SQLException sqlException ) {
      JOptionPane.showMessageDialog( null,
         sqlException.getMessage(), "Database Error",
         JOptionPane.ERROR_MESSAGE );
      System.exit( 1 );
   }

   catch(ClassNotFoundException cnfex) {

       System.out.println("Problem in loading or "
               + "registering MS Access JDBC driver");
       cnfex.printStackTrace();
   }

} 



public static void adminSearch(String user) { 
	
	 try {
	      
	 	  // Pull data from form

	 	  
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !user.isEmpty()) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	          "");
		        

		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM Users WHERE Username" +
				         " = " +
				          "'" +
				          user +
				          "'"
				          ;	

		         
		         stmt = connection.createStatement();
		         

		         ResultSet rs = stmt.executeQuery(query2);
		         

		         if (rs.next() == false) {
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "No Records Found!");
				     
		         } else {

		        	 String ID = rs.getString("ID");
			      

		        	
			         
			         // Using ID, search for the rest of the values from the other tables
			         
			         Statement statement = connection.createStatement();
			         // query database
			         ResultSet resultSet =
			            statement.executeQuery( "SELECT Username, Password, level, email,first,last"
			            		+ " FROM Users"
			            		+ " WHERE ID =" + ID);
			         

			         
			         
			         // process query results and use Setters in Gui to populate fields
			         resultSet.next();
			         
			         
			         RimAdmin.setUser(user);
			         
			         String pass = resultSet.getString("Password");
			         RimAdmin.setPass(pass);
			         
			         String email = resultSet.getString("email");
			         RimAdmin.setEmail(email);
			         
					 String level = resultSet.getString("level");
					 
					
					  if (level.equals("User")) {
						lValue = 2;
					}
					 else if (level.equals("Admin")){
							lValue = 3;
						}
					 else if (level.equals("Viewer")){
						 	lValue=1;
					 }
					 else {
						 lValue = 4;
					 }
					
					  String first = resultSet.getString("first");
					  RimAdmin.setFirst(first);
					  String last = resultSet.getString("last");
					  RimAdmin.setLast(last);
					  
					  
				//	System.out.println(lValue);
					 RimAdmin.setlHold(lValue); 

			         
			         
			         
			         
			         
			         // close statement and connection
			         statement.close();
			         connection.close();
			
		         } while (rs.next());
		         
		         // close statement
		         stmt.close();
		         
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Enter a search filter!");
	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	} // end DbConnection constructor definition



public static void adminDelete(String user) {
	
	try {
	      
	 	  if ( !user.isEmpty()){ 
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	        "");
		        

		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         String query2 = "SELECT ID FROM Users WHERE Username" +
				         " = " +
				          "'" +
				          user +
				          "'"
				          ;	
		 //        System.out.println(query2);
		         stmt = connection.createStatement();
		         ResultSet rs = stmt.executeQuery(query2);
		         rs.next();
		         String ID = rs.getString("ID");

		         // close statement
		         stmt.close();
		         // Using ID, delete all records for that ID

		         // For the Names table
		         // Setup SQL for Deletion query1
		         String query1 = "DELETE FROM Users WHERE ID=?";
		         // Plug fields into query1
		         PreparedStatement preparedStmt = connection.prepareStatement(query1);
		         preparedStmt.setString(1, ID);
		         // execute the query1 preparedstatement
		         preparedStmt.execute();
		         // close statement
		         preparedStmt.close();
		         
		         
		         
		         
		      // set up popup display window
		   		 JFrame f = new JFrame();
		   		 JOptionPane.showMessageDialog(f, "Deleted!");
	             RimAdmin.username.setText("");
	             RimAdmin.password.setText("");
	             RimAdmin.levelSel.setSelectedIndex(0);
	             RimAdmin.email.setText("");
		         
	 	 } else {
		         
		       // set up popup display window
			   JFrame f = new JFrame();
			   JOptionPane.showMessageDialog(f, "Enter a user you wish to delete");
			   
	 	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	   
	} // end DbConnection constructor definition

public static void adminUpdate(String user, String pass, String level, String email, String first, String last) throws Exception {
	
	   try {
		      
		 	  // Pull data from form

		 	  
		 	  // Using the 'empty' method, check to see if some fields are empty or populated
		   if ( !user.isEmpty() && !level.equals("  (Select One)")) { 
				  	// load database driver class
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	            "");
			        

			         // Pull the ID for that name
			         Statement stmt = null;
			    //     System.out.println("Work1");
			         String query2 = "SELECT ID FROM Users WHERE Username" +
					         " = " +
					          "'" +
					          user +
					          "'"
					          ;	
			  //       System.out.println(query2);
			         stmt = connection.createStatement();
			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
			         
			         if (rs.next() == false) {
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "No Records Found!");
					     
			         }			
			         
			         else {
			  //       System.out.println("Work2");

			         String ID = rs.getString("ID");
			       

			         stmt.close();
			         
			         
			         
			         
			         // Using ID, place the rest of the values into the other tables
			         
			         // If a barcode
			         if ( !user.isEmpty() ) {
				         // Setup SQL for Insertion query1
			        	 String query1 = "UPDATE Users SET Username=? WHERE ID=?";
			        //	 System.out.println(query1);
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, user);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         
			         // If a alt barcode
			         if ( !pass.isEmpty() ) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE Users SET Password=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, pass);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         
			         // If a barcode BusinessUnit
			         if ( !level.isEmpty() ) {
			        	 String query1 = "UPDATE Users SET level=? WHERE ID=?";
			        	 // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, level);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }

			         if ( !email.isEmpty() ) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE Users SET email=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, email);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         if ( !first.isEmpty() ) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE Users SET first=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, first);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         if ( !last.isEmpty() ) {
				         // Setup SQL for Insertion query1
				         String query21 = "UPDATE Users SET last=? WHERE ID=?";
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query21);
				         preparedStmt.setString(1, last);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();
			         }
			         
			         
			         
			         
			         
			         connection.close();
			         
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Updated!\n"
				     		+ "Don't forget to update the search if you'd like to update again!");
				     
				     
				     
		             RimAdmin.username.setText("");
		             RimAdmin.password.setText("");
		             RimAdmin.levelSel.setSelectedIndex(0);
		             RimAdmin.email.setText("");
		             RimAdmin.first.setText("");
		             RimAdmin.last.setText("");

			         }
			         
		 	  } else {
		   		  	         
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Change atleast one value to update");
		   	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		} // end DbConnection constructor definition 
	






public static void auditSearch(String searchKeySel, String searchKey, String user1, String pass1, String level1) {
	user = user1;
	pass = pass1;
	level = level1;
			
	
	 try {
	      
	 	  // Pull data from form

	 	  
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !searchKey.isEmpty() && !searchKeySel.equals("  (Select one)") || searchKeySel.equals("ALL RECORDS")) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	            "");
		        

		         if (searchKeySel.equals("ALL RECORDS")) {
		        	// Pull the ID for that name
			         Statement stmt = null;
			         
			         
			         String query2 = "SELECT * FROM AuditLog"
					          ;	
			      //   System.out.println(query2);
			         
			         
			         
			         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
		        	 
			    	  ArrayList<String> IDList = new ArrayList<String>();

			        	  
			        	 while (rs.next()) {
			        		 String ID = rs.getString("ID");
			        		 IDList.add(ID);
			        		 
			        	 }
			        
			        	 
			        	 
			        	 if (!rs.previous()) {
			        		  JFrame f = new JFrame();
						      JOptionPane.showMessageDialog(f, "Nothing found");	
						      batchSearch.frame.dispose();
			        	 }

			        	 else {
			        	// System.out.print(IDList);
				         Statement statement = connection.createStatement();

			        	 for (int i=0; i<IDList.size(); i++) {
			        		 String ID = IDList.get(i);
			        	//	 System.out.println(ID);
					         ResultSet resultSet =
							            statement.executeQuery( "SELECT Barcode, AltBarcode, ChangedBy, DateChanged, Status"
							            		+ " FROM AuditLog"
							            		+ " WHERE ID =" + ID); 
					         int columnCount = resultSet.getMetaData().getColumnCount();
					      
					         while (resultSet.next()) {
					             ArrayList<Object> row = new ArrayList<>();
					             Vector<String> vString = new Vector<String>();
					             for (int i1 = 1; i1 <= columnCount; i1++) {
					                 row.add(resultSet.getObject(i1));
					                 vString.addElement(resultSet.getString(i1));

	             }
					     		RimAudit.auditMain(user,pass,level);

					       //      System.out.println(row); 
					             RimAudit.tableModel.addRow(vString); 

					         }

			        	 }
			        	  GlobalID = rs.getString("ID");
				      

				         statement.close();
				         connection.close();
				
			        	 } while (rs.next());
			         
			         // close statement
			         stmt.close();
		         }
		         

		         else {
		         
		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM AuditLog WHERE " +
				          searchKeySel + //maybe change this to * to search ALL instances where like etc
				         " LIKE " +
				          "'" + "%"+
				          searchKey +
				         "%" +"'"
				          ;	
		         System.out.println(query2);
		         
		         
		         
		         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		         
		         
		         
		         
		         ResultSet rs = stmt.executeQuery(query2);
		         
		         
	        	 
		    	  ArrayList<String> IDList = new ArrayList<String>();

		        	  
		        	 while (rs.next()) {
		        		 String ID = rs.getString("ID");
		        		 System.out.println(ID);
		        		 IDList.add(ID);}
		        	 System.out.println(IDList);
		        	 
		        	 if (!rs.previous()) {
		        		  JFrame f = new JFrame();
					      JOptionPane.showMessageDialog(f, "Nothing found");	
					      batchSearch.frame.dispose();
		        	 }

		        	 else {
		        	// System.out.print(IDList);
			         Statement statement = connection.createStatement();

		        	 for (int i=0; i<IDList.size(); i++) {
		        		 String ID = IDList.get(i);
		        	//	 System.out.println(ID);
				         ResultSet resultSet =
						            statement.executeQuery( "SELECT Barcode, AltBarcode, ChangedBy, DateChanged, Status"
						            		+ " FROM AuditLog"
						            		+ " WHERE ID =" + ID); 
				         int columnCount = resultSet.getMetaData().getColumnCount();
				      
				         while (resultSet.next()) {
				             ArrayList<Object> row = new ArrayList<>();
				             Vector<String> vString = new Vector<String>(); 
				             for (int i1 = 1; i1 <= columnCount; i1++) {
				                 row.add(resultSet.getObject(i1));
				                 vString.addElement(resultSet.getString(i1));
				                		             }
				       /*here      
				             String temp = "<html>" +vString.get(5)+"<br>";
				             System.out.println(temp);
				             vString.add(5,temp);
				             vString.remove(6);
				             System.out.println(vString);
				            //here*/
				     		RimAudit.auditMain(user,pass,level);

				            System.out.println(row); 
				            RimAudit.tableModel.addRow(vString); 
				          
				         }
				         	
		

		        	 
		        	 }
		        	  GlobalID = rs.getString("ID");
			      

			         statement.close();
			         connection.close();
			
		        	 } while (rs.next());
		         
		         // close statement
		         stmt.close(); }
		         
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Enter a search filter!");
					RimAudit.frame.dispose();

	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	} // end DbConnection constructor definition




	@SuppressWarnings("unused")
	public static void auditAll(String searchKeySel, String searchKey, String user1, String pass1, String level1) {
	user = user1;
	pass = pass1;
	level = level1;
			
	
	 try {
	 	  // Pull data from form

	 	  
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !searchKey.isEmpty() && !searchKeySel.equals("  (Select one)") || searchKeySel.equals("ALL RECORDS")) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	             "");
		        

		         if (1==1) {
		        	// Pull the ID for that name
			         Statement stmt = null;

			         
			         String query2 =( "SELECT ID,Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold, ChangedBy, DateChanged, Status"
			            		+ " FROM AuditLog"
			            		+ " WHERE Barcode =" + "'"+searchKey+"'");

					          
			         System.out.println(query2);
			         
			         
			         
			         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
		        	 
			    	  ArrayList<String> IDList = new ArrayList<String>();

			        	  
			        	 while (rs.next()) { 
			        		 String ID = rs.getString("ID");
			        		 IDList.add(ID);
			        		 
			        	 }

			        	 
			        	 
			        	 if (!rs.previous()) {
			        		  JFrame f = new JFrame();
						      JOptionPane.showMessageDialog(f, "Nothing found");	
						      batchSearch.frame.dispose();
			        	 }

			        	 else {
			        	// System.out.print(IDList);
				         Statement statement = connection.createStatement();
				         
			        	 for (int i=0; i<IDList.size(); i++) {
			        		 String ID = IDList.get(i);
			        		 System.out.println("IDSize"+IDList.size());
			        		 System.out.println("ID" +ID);
					         ResultSet resultSet =
							            statement.executeQuery( "SELECT Barcode, AltBarcode, BusinessUnit, RecordCode, RecordType, RecordDescription, YearsOFRecords,EmployeeName,CurrentLocation,AssignedLocation,OldLocation,CheckIn,Checkout,legalHold, ChangedBy, DateChanged, Status"
							            		+ " FROM AuditLog"
							            		+ " WHERE ID =" + "'"+ID+"'");
					         System.out.println("Searchkey"+searchKey);
					         int columnCount = resultSet.getMetaData().getColumnCount();
					      
					         while (resultSet.next()) {
					             ArrayList<Object> row = new ArrayList<>();
					             Vector<String> vString = new Vector<String>();
					             for (int i1 = 1; i1 <= columnCount; i1++) {
					                 row.add(resultSet.getObject(i1));
					                 vString.addElement(resultSet.getString(i1));

	             }
					             allAudit.auditMain(user,pass,level);

					       //      System.out.println(row); 
					             allAudit.tableModel.addRow(vString); 

					         }

			        	 }
			        	  GlobalID = rs.getString("ID");
				      

				         statement.close();
				         connection.close();
				
			        	 } while (rs.next());
			         
			         // close statement
			         stmt.close();
		         }
		         

		         else {
		         
		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM AuditLog WHERE " +
				          searchKeySel + //maybe change this to * to search ALL instances where like etc
				         " LIKE " +
				          "'" + "%"+
				          searchKey +
				         "%" +"'"
				          ;	
		         System.out.println(query2);
		         
		         
		         
		         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		         
		         
		         
		         
		         ResultSet rs = stmt.executeQuery(query2);
		         
		         
	        	 
		    	  ArrayList<String> IDList = new ArrayList<String>();

		        	  
		        	 while (rs.next()) {
		        		 String ID = rs.getString("ID");
		        		 System.out.println(ID);
		        		 IDList.add(ID);}
		        	 System.out.println(IDList);
		        	 
		        	 if (!rs.previous()) {
		        		  JFrame f = new JFrame();
					      JOptionPane.showMessageDialog(f, "Nothing found");	
					      batchSearch.frame.dispose();
		        	 }

		        	 else {
		        	// System.out.print(IDList);
			         Statement statement = connection.createStatement();

		        	 for (int i=0; i<IDList.size(); i++) {
		        		 String ID = IDList.get(i);
		        	//	 System.out.println(ID);
				         ResultSet resultSet =
						            statement.executeQuery( "SELECT Barcode, AltBarcode, ChangedBy, DateChanged, Status"
						            		+ " FROM AuditLog"
						            		+ " WHERE ID =" + ID); 
				         int columnCount = resultSet.getMetaData().getColumnCount();
				      
				         while (resultSet.next()) {
				             ArrayList<Object> row = new ArrayList<>();
				             Vector<String> vString = new Vector<String>(); 
				             for (int i1 = 1; i1 <= columnCount; i1++) {
				                 row.add(resultSet.getObject(i1));
				                 vString.addElement(resultSet.getString(i1));
				                		             }
				       /*here      
				             String temp = "<html>" +vString.get(5)+"<br>";
				             System.out.println(temp);
				             vString.add(5,temp);
				             vString.remove(6);
				             System.out.println(vString);
				            //here*/
				     		allAudit.auditMain(user,pass,level);

				            System.out.println(row); 
				            allAudit.tableModel.addRow(vString); 
				          
				         }
				         	
		

		        	 
		        	 }
		        	  GlobalID = rs.getString("ID");
			      

			         statement.close();
			         connection.close();
			
		        	 } while (rs.next());
		         
		         // close statement
		         stmt.close(); }
		         
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Enter a search filter!");
					RimAudit.frame.dispose();

	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	} 





public static void auditClear(String user1, String pass1, String level1) {
	
	try {
	      
  		 System.out.println("Her");

		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	           "");
		        

		         // Pull the ID for that name
		         Statement stmt = null;
		        
		         stmt = connection.createStatement();
		        
		         String query1 = "DELETE FROM AuditLog WHERE 1=1";
		         // Plug fields into query1
		         PreparedStatement preparedStmt = connection.prepareStatement(query1);
		         // execute the query1 preparedstatement
		         preparedStmt.execute();
		         // close statement
		         preparedStmt.close();


		         // close statement
		         stmt.close();
		         // Using ID, delete all records for that ID

		         // For the Names table
		         // Setup SQL for Deletion query1
		         // set up popup display window
		   		 JFrame f = new JFrame();
		   		 JOptionPane.showMessageDialog(f, "Deleted!");
		   		 System.out.println("Here1");
		   		 auditGUI.auditMain(user1, pass1, level1);
		   		 RimAudit.frame.dispose();
		   		 
	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	   
	}








public static void audDelRow(String user) throws Exception {
	
	   try {
		      
		 	  // Pull data from form
		 	  
		 	  // Using the 'empty' method, check to see if some fields are empty or populated
		   if ( !user.isEmpty()) { 
			   System.out.println(user);

				  	// load database driver class
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	            "");
			       
			       
			        String barcode1 = user;
			        
			         // Pull the ID for that name
			         Statement stmt = null;
			     //    System.out.println("Work1");
			         String query2 = "SELECT ID FROM AuditLog WHERE Barcode" +" = " + "'" + barcode1 + "'";	
			     //    System.out.println(query2);
			         stmt = connection.createStatement();
			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
			         
			         if (rs.next() == false) {
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "There are no barcodes/altbarcodes that equal that");
			         }			
			         
			         else {
			    //     System.out.println("Work2");

			         String ID = rs.getString("ID"); 
			      
			         // Setup SQL for Deletion query1
			         String query1 = "DELETE FROM RimRecordsTable WHERE ID=?";
			         // Plug fields into query1
			         PreparedStatement preparedStmt = connection.prepareStatement(query1);
			         preparedStmt.setString(1, ID);
			         // execute the query1 preparedstatement
			         preparedStmt.execute();
			         // close statement
			         preparedStmt.close();
			         
			         
			         
			         
			      // set up popup display window
			   		
			         
			         connection.close();
			         
			        
			         }
			         
		 	  } else {
		   		  	         
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Change atleast one value to update");
		   	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		} 



public static void reqSave(String user, int rowCount) throws Exception {
	
	   try {
		      
		 	  // Pull data from form
		 	  
		 	  // Using the 'empty' method, check to see if some fields are empty or populated
		   if ( !user.isEmpty()) { 
			 //  System.out.println(user);

				  	// load database driver class
			    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			         // connect to database
			         Connection connection = DriverManager.getConnection(
				 	           "");
			       
			       
			        String barcode1 = user;
			        
			         // Pull the ID for that name
			         Statement stmt = null;
			     //    System.out.println("Work1");
			         String query2 = "SELECT ID FROM RimRecordsTable WHERE Barcode" +" = " + "'" + barcode1 + "'";	
			     //    System.out.println(query2);
			         stmt = connection.createStatement();
			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
			         
			         if (rs.next() == false) {
				         // set up popup display window
				         f = new JFrame();
					     JOptionPane.showMessageDialog(f, "There are no barcodes/altbarcodes that equal that");
			         }			
			         
			         else {
			    //     System.out.println("Work2");

			         String ID = rs.getString("ID"); 
			      // query database
			      
			         if (!batchSearch.IDList.contains(ID)) {
					 batchSearch.IDList.add(ID); }
			         
			         ResultSet resultSet =
			            stmt.executeQuery( "SELECT Barcode, RecordDescription,CurrentLocation,AssignedLocation,legalHold"
			            		+ " FROM RimRecordsTable"
			            		+ " WHERE ID =" + ID);
			        resultSet.next();
			         
			         
			         // execute the query1 preparedstatement

				    // JOptionPane.showMessageDialog(f, "Deleted " +rowCount+"'s");

			      // set up popup display window
			   		
			         
			         connection.close();
			         
			        
			         }
			         
		 	  } else {
		   		  	         
			         // set up popup display window
			         f = new JFrame();
				     JOptionPane.showMessageDialog(f, "Change atleast one value to update");
		   	 }

		   }  // end try

		   // detect problems interacting with the database
		   catch ( SQLException sqlException ) {
		      JOptionPane.showMessageDialog( null,
		         sqlException.getMessage(), "Database Error",
		         JOptionPane.ERROR_MESSAGE );
		      System.exit( 1 );
		   }

		   catch(ClassNotFoundException cnfex) {

		       System.out.println("Problem in loading or "
		               + "registering MS Access JDBC driver");
		       cnfex.printStackTrace();
		   }
		} 

public static void recSearch(ArrayList<String> IDList) { 
	
	 try {
		 IDListFinal.clear();
		 IDListFinal.addAll(IDList);
	 	  // Pull data from form
		 
	 	  
	 	  // Using the 'empty' method, check to see if some fields are empty or populated
	 	  if ( !IDList.isEmpty()) { 
			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	          "");
		         
		         // Pull the ID for that name
		     
		        	// System.out.print(IDList);
			         Statement statement = connection.createStatement();
			         
			         
			         emailString.clear();
			         
		        	 for (int i=0; i<IDList.size(); i++) {
		        		 String ID = IDList.get(i);
		        		// System.out.println(ID);
				         ResultSet resultSet =
						            statement.executeQuery( "SELECT Barcode, RecordDescription, CurrentLocation,AssignedLocation"
						            		+ " FROM RimRecordsTable"
						            		+ " WHERE ID =" + ID); 
				         int columnCount = resultSet.getMetaData().getColumnCount();
				      
				         while (resultSet.next()) {
				             ArrayList<Object> row = new ArrayList<>();
				             ArrayList<Object> rowCopy = new ArrayList<>();

				             Vector<String> vString = new Vector<String>();
				            // Vector<String> vString1 = new Vector<String>();


				             for (int i1 = 1; i1 <= columnCount; i1++) {
				                 row.add(resultSet.getObject(i1));
				                 rowCopy.add(resultSet.getObject(i1));
				                 vString.addElement(resultSet.getString(i1));
				                		             }
				             
				             rowCopy.remove(1);
				             emailString.add(rowCopy);
				            //System.out.println(emailString);
				             

				     		recSearch.recMain();

				           //  System.out.println(row); 
				             recSearch.tableModel.addRow(vString); 

				             
				      
				           
				             
				             
				             //System.out.println(emailString);
				         }
			      //  	 batchSearch.getOriginal();
				        
			
		        	 } 
			         statement.close();
			         connection.close();
		         // close statement
		         
		         
		         
	 	  } else {
	   		  // set up popup display window
	 		  JFrame f = new JFrame();
			      JOptionPane.showMessageDialog(f, "Enter a search filter!");
					batchSearch.frame.dispose();

	   	 }

	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	}// end DbConnection constructor definition











/////EMAIL SECTION//////////////////


public static void emailManager(String email, String dOut, String dRet, String loc) throws SQLException, ClassNotFoundException, ParseException {
	
	// Sender's email ID needs to be mentioned
    String username = "";
    String password =""; //BOT email, only sending username and requested barcode value, 
    
   // Recipient's email ID needs to be mentioned.
  String to = "";

  String host = "smtp.gmail.com";

  // Get system properties
  Properties properties = System.getProperties();
  // Setup mail server
  properties.put("mail.smtp.starttls.enable", "true");
  properties.put("mail.smtp.host", host);
  properties.put("mail.smtp.user", username);
  properties.put("mail.smtp.password", password);
  properties.put("mail.smtp.port", "587");
  properties.put("mail.smtp.auth", "true");

  // Get the default Session object.
  Session session = Session.getDefaultInstance(properties);

  try{
     // Create a default MimeMessage object.
     MimeMessage message = new MimeMessage(session);

     // Set From: header field of the header.
     message.setFrom(new InternetAddress(username));

     // Set To: header field of the header.
     message.addRecipient(Message.RecipientType.TO,
                              new InternetAddress(to));

     // Set Subject: header field
     message.setSubject("[RECORD MANAGER]: "+optionPage.user +" is requesting: " +batchSearch.IDList.size()+ " records");

     // Now set the actual message
     ArrayList<String> emailMessage = new ArrayList<>();				
     ArrayList<String> emailMessage1 = new ArrayList<>();				

     StringBuffer sb = new StringBuffer();
    int i = 0;
     for(ArrayList v: emailString)
        {
    	 
    	 if (i == 0) {
    		 //emailMessage.add(0," ");
    		 i++;
    	 }
    	 
           emailMessage.addAll(v);
           emailMessage.add("\n");
        }
		
     
     
		int j = 0;
		int x = 0;
		 int firstLen = 0;
	     int firstLen1 = 0;
	     int firstLen2 = 0;
	     int z = 0;
	     for (String s : emailMessage) {
	    	 int padCalc = 0;
	    	 if (s == null) {
	    		 s = "empty  ";
	    	 }
	    	 
	    	 
	    	 
	    	 
	    	 
	    	 
			if (j == 0 && x ==0) {				
				 padCalc = 25-s.length();
				 firstLen = s.length();
				 if (padCalc >0) {
						sb.append(s+" ".repeat(padCalc/2)).append("|").append(" ".repeat(padCalc/2)); 
					x++;
					j++; }
				else {
					sb.append(s.substring(0,8)+"  "+"|"+" ".repeat(15)); 
					x++;
					j++;			
			}	
			}
			
			
			
			
			
			
			
			else if (j == 0) {
				 padCalc = 25-s.length();
				
				 
				 
				 if (s.length() > firstLen) {
					 int reducer = s.length() - firstLen;
					 if (padCalc >0) {
							sb.append(s+" ".repeat((padCalc/2)-reducer)).append("|").append(" ".repeat((padCalc/2)-reducer)); 
						j++;} 
						
						else {
							sb.append(s.substring(0,8)+"  "+"|"+" ".repeat(15)); 
							j++;} 
	 
				 }
				 
				 else {
				if (padCalc >0) {
					sb.append(s+" ".repeat(padCalc/2)).append("|").append(" ".repeat(padCalc/2)); 
				j++;} 
				
				else {
					sb.append(s.substring(0,8)+"  "+"|"+" ".repeat(15)); 
					j++;} 
			}
			}
			
	
			
			else if (j == 1 && z == 0){
				firstLen1 = s.length();
				 padCalc = 41-s.length();
				 System.out.println(padCalc);
				if (padCalc > 0) {
				sb.append(s+" ".repeat(padCalc/2)).append("|").append(" ".repeat(padCalc/2)); 
				j++; z++;}
				
				else {
					sb.append(s.substring(0,16)+"  "+"|"+" ".repeat(17)); 
					j++; z++;} 
				}
			


			else if (j == 1){
				 padCalc = 41-s.length();
				 
				 if (s.length() > firstLen1)
				 {
					 System.out.println("LONGER"+s);
					 int reducer = s.length() - firstLen1;
					 if (padCalc > 0) {
							sb.append(s+" ".repeat((padCalc/2)-reducer)).append("|").append(" ".repeat((padCalc/2))); 
							j++; }
							
							else {
								sb.append(s.substring(0,16)+"  "+"|"+" ".repeat(17)); 
								j++;} 
							}	 

				 else {
				System.out.println("not longer1"); 
					 
				if (padCalc > 0) {
				sb.append(s+" ".repeat(padCalc/2)).append("|").append(" ".repeat(padCalc/2)); 
				j++; }
				
				else {
					sb.append(s.substring(0,16)+"  "+"|"+" ".repeat(17)); 
					j++;} 
				}
								
			}


			else if (j == 2) {
				padCalc = 17-s.length();

						System.out.println("not longer2"); 

					 if (padCalc > 0) {
						 sb.append(s);
						 j++; }
				
					 else {
						 sb.append(s.substring(0,16));
						 j++;} 
			
				 }
								

			
			else if (j == 3) {
			
				sb.append(s).append("");
				j = 0;
			}
			
			
			else if (j != 3) {
			sb.append(s).append("    "); 
			j++;	
			}	
	
			else {
				//j = 0;
			}
		}	

	     
	     

	     

	     
		
		String newValue = sb.toString();
		System.out.println(newValue);
     
     
     
     message.setText(optionPage.user+" Is requesting the following barcodes\n\nBarcode                       Current Location                  Assigned Location\n"+newValue+"\n\n\nPlease open the RimRecordsManager application to approve/deny");
   //  message.setText(tt.toString());

     // Send message
     Transport transport = session.getTransport("smtp");
     transport.connect(host, username, password);
     transport.sendMessage(message, message.getAllRecipients());
     transport.close();
     System.out.println("Sent message successfully....");
     
	  JFrame f = new JFrame();
      JOptionPane.showMessageDialog(f, "Requested!");
      
      
      for(String ID : IDListFinal) 
      {
      
	    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	
	    	 
	         // connect to database
	         Connection connection = DriverManager.getConnection(
	 	             "");
		
	         // Pull the ID for that name
	       
	         
	         
	         Statement stmt = null;
	         String query2 = "SELECT ID FROM Requests WHERE ID = "+
			          "'" +
			          ID +
			          "'"
			          ;	
	 //        System.out.println(query2);
	         stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(query2);
	
	         
	         
	         if (rs.next() == false) {
		         Statement stmt1 = null;
		         stmt1 = connection.createStatement();
		         String query3 = "SELECT Barcode, CurrentLocation FROM RimRecordsTable WHERE ID = "+
				          "'" +
				          ID +
				          "'";
		         ResultSet rs1 = stmt1.executeQuery(query3);
		         rs1.next();

		         String barcode = rs1.getString("Barcode");
		         String cur = rs1.getString("CurrentLocation");
		         
	         String query1 = "INSERT INTO Requests (Username, Barcode, DateOut, DateReturn, ReqLoc, CurrentLocation, Status, dateRequested) VALUES (?,?,?,?,?,?,?,?)";
	         PreparedStatement preparedStmt = connection.prepareStatement(query1);	     
	         preparedStmt.setString(1, optionPage.user);
	         preparedStmt.setString(2, barcode);
	         preparedStmt.setString(3, dOut);
	         preparedStmt.setString(4, dRet);
	         preparedStmt.setString(5, loc);
	         preparedStmt.setString(6, cur);
	         preparedStmt.setString(7, "In Progress");
	         LocalDateTime now = LocalDateTime.now();  	         
	         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy",Locale.ENGLISH); 
	         String timeStamp = now.format(dtf);
	         SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
	         java.util.Date date = sdf1.parse(timeStamp);
	         java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

	         
	         
	         System.out.println(sqlStartDate);
	         preparedStmt.setDate(8, sqlStartDate);



	         preparedStmt.execute();
	         preparedStmt.close();
	         
	       
	         
      }
	         
	         
	         else {        
	       	  JFrame f1 = new JFrame();
	          JOptionPane.showMessageDialog(f1, "Barcode already requested, contact manager!");
	         }
	         stmt.close();
	         connection.close();
	         
      
      }  
  }catch (MessagingException mex) {
     mex.printStackTrace();
  }
}



public static void reqGUI(String searchKeySel, String user1, String pass1, String level1) {
	user = user1;
	pass = pass1;
	level = level1;
			
	
	 try {

			  	// load database driver class
		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	            "");
		        

		         if (searchKeySel.equals("ALL RECORDS")) {
		        	// Pull the ID for that name
			         Statement stmt = null;
			         
			         
			         String query2 = "SELECT * FROM Requests"
					          ;	
			      //   System.out.println(query2);
			         
			         
			         
			         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			         ResultSet rs = stmt.executeQuery(query2);
			         
			         
		        	 
			    	  ArrayList<String> IDList = new ArrayList<String>();

			        	  
			        	 while (rs.next()) {
			        		 String ID = rs.getString("ID");
			        		 IDList.add(ID);
			        		 
			        	 }
			        
			        	 
			        	 
			        	 if (!rs.previous()) {
			        		  JFrame f = new JFrame();
						      JOptionPane.showMessageDialog(f, "Nothing found");	
						      batchSearch.frame.dispose();
			        	 }

			        	 else {
			        	// System.out.print(IDList);
				         Statement statement = connection.createStatement();

			        	 for (int i=0; i<IDList.size(); i++) {
			        		 String ID = IDList.get(i);
			        	//	 System.out.println(ID);
					         ResultSet resultSet =
							            statement.executeQuery( "SELECT Username, Barcode, DateOut, DateReturn, ReqLoc, CurrentLocation, dateRequested, Status"
							            		+ " FROM Requests"
							            		+ " WHERE ID =" + ID); 
					         int columnCount = resultSet.getMetaData().getColumnCount();
					      
					         while (resultSet.next()) {
					             ArrayList<Object> row = new ArrayList<>();
					             Vector<String> vString = new Vector<String>();
					             for (int i1 = 1; i1 <= columnCount; i1++) {
					                 row.add(resultSet.getObject(i1));
					                 vString.addElement(resultSet.getString(i1));

	             }
					             vString.add("Approve");
					             vString.add("Deny");
					     		reqGUI.reqMain(user,pass,level);

					       //      System.out.println(row); 
					     		reqGUI.tableModel.addRow(vString); 

					         }

			        	 }
			        	  GlobalID = rs.getString("ID");
				      

				         statement.close();
				         connection.close();
				
			        	 } while (rs.next());
			         
			         // close statement
			         stmt.close();
		         }
		         

		         else {
		         
		         // Pull the ID for that name
		         Statement stmt = null;
		         
		         
		         String query2 = "SELECT ID FROM AuditLog WHERE " +
				          searchKeySel + //maybe change this to * to search ALL instances where like etc
				         " LIKE " +
				          "'" + "%"+
				          "null" +
				         "%" +"'"
				          ;	
		         System.out.println(query2);
		         
		         
		         
		         stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		         
		         
		         
		         
		         ResultSet rs = stmt.executeQuery(query2);
		         
		         
	        	 
		    	  ArrayList<String> IDList = new ArrayList<String>();

		        	  
		        	 while (rs.next()) {
		        		 String ID = rs.getString("ID");
		        		 System.out.println(ID);
		        		 IDList.add(ID);}
		        	 System.out.println(IDList);
		        	 
		        	 if (!rs.previous()) {
		        		  JFrame f = new JFrame();
					      JOptionPane.showMessageDialog(f, "Nothing found");	
					      batchSearch.frame.dispose();
		        	 }

		        	 else {
		        	// System.out.print(IDList);
			         Statement statement = connection.createStatement();

		        	 for (int i=0; i<IDList.size(); i++) {
		        		 String ID = IDList.get(i);
		        	//	 System.out.println(ID);
				         ResultSet resultSet =
						            statement.executeQuery( "SELECT Barcode, AltBarcode, ChangedBy, DateChanged, Status"
						            		+ " FROM AuditLog"
						            		+ " WHERE ID =" + ID); 
				         int columnCount = resultSet.getMetaData().getColumnCount();
				      
				         while (resultSet.next()) {
				             ArrayList<Object> row = new ArrayList<>();
				             Vector<String> vString = new Vector<String>(); 
				             for (int i1 = 1; i1 <= columnCount; i1++) {
				                 row.add(resultSet.getObject(i1));
				                 vString.addElement(resultSet.getString(i1));
				                		             }
				       /*here      
				             String temp = "<html>" +vString.get(5)+"<br>";
				             System.out.println(temp);
				             vString.add(5,temp);
				             vString.remove(6);
				             System.out.println(vString);
				            //here*/
				     		RimAudit.auditMain(user,pass,level);

				            System.out.println(row); 
				            RimAudit.tableModel.addRow(vString); 
				          
				         }
				         	
		

		        	 
		        	 }
		        	  GlobalID = rs.getString("ID");
			      

			         statement.close();
			         connection.close();
			
		        	 } while (rs.next());
		         
		         // close statement
		         stmt.close(); }
		         
		         
		         
	 	  } 

	     // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
	} // end DbConnection constructor definition







public static void reqClear(String user1, String pass1, String level1) {
	
	try {
	      
  		 System.out.println("Her");

		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	            "");
		        

		         // Pull the ID for that name
		         Statement stmt = null;
		        
		         stmt = connection.createStatement();
		        
		         String query1 = "DELETE FROM Requests WHERE 1=1";
		         // Plug fields into query1
		         PreparedStatement preparedStmt = connection.prepareStatement(query1);
		         // execute the query1 preparedstatement
		         preparedStmt.execute();
		         // close statement
		         preparedStmt.close();


		         // close statement
		         stmt.close();
		         // Using ID, delete all records for that ID

		         // For the Names table
		         // Setup SQL for Deletion query1
		         // set up popup display window
		   		 JFrame f = new JFrame();
		   		 JOptionPane.showMessageDialog(f, "Deleted!");
		   		 System.out.println("Here1");
		   		 reqGUI.frame.dispose();
		   		 
	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   }
}  


















public static void reqStat(Object barcode, String Status) {
	
	try {
	      

		    	 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		         // connect to database
		         Connection connection = DriverManager.getConnection(
			 	            "");
		         Statement stmt1 = null;
		         stmt1 = connection.createStatement();
		         String query3 = "SELECT ID, Username FROM Requests WHERE Barcode = "+
				          "'" +
				          barcode.toString() +
				          "'";
		         ResultSet rs1 = stmt1.executeQuery(query3);
		         rs1.next();

		         
		         
        		 String ID = rs1.getString("ID");
        		 String username = rs1.getString("username");
		         // Pull the ID for that name
		        
        		 
        		 String query4 = "SELECT email FROM Users WHERE Username = "+
				          "'" +
				          username +
				          "'";
		         ResultSet rs2 = stmt1.executeQuery(query4);
		         rs2.next();
        		 String email = rs2.getString("email");
        		 System.out.println(email);
        		 
        		 
        		 
		         //stmt = connection.createStatement();
		        stmt1.close();
		         String query1 = "UPDATE Requests SET Status=? WHERE ID=?";
			        //	 System.out.println(query1);
				         // Plug fields into query1
				         PreparedStatement preparedStmt = connection.prepareStatement(query1);
				         preparedStmt.setString(1, Status);
				         preparedStmt.setString(2, ID);
				         // execute the query1 preparedstatement
				         preparedStmt.execute();
				         // close statement
				         preparedStmt.close();

		         // close statement
		         // Using ID, delete all records for that ID
					        connection.close();

		         // For the Names table
		         // Setup SQL for Deletion query1
		         // set up popup display window
		   		 JFrame f = new JFrame();
		   		 JOptionPane.showMessageDialog(f, "Approved!");
		   		 responseEmail(user, barcode.toString(),Status,email);
		   		 
		   		 
	   }  // end try

	   // detect problems interacting with the database
	   catch ( SQLException sqlException ) {
	      JOptionPane.showMessageDialog( null,
	         sqlException.getMessage(), "Database Error",
	         JOptionPane.ERROR_MESSAGE );
	      System.exit( 1 );
	   }

	   catch(ClassNotFoundException cnfex) {

	       System.out.println("Problem in loading or "
	               + "registering MS Access JDBC driver");
	       cnfex.printStackTrace();
	   } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}


public static void responseEmail(String user, String barcode, String Status, String email) throws SQLException, ClassNotFoundException, ParseException {
	
	// Sender's email ID needs to be mentioned
    String username = "";
    String password =""; //BOT email, only sending username and requested barcode value, 
    
   // Recipient's email ID needs to be mentioned.
    
    
    
//GOOGLE REMOVING LESS SECURE APP FEATURE, GOING TO HAVE TO USE BETTER AUTHENTICATION TO LOGIN TO BURNER ACCT
    
    
    
  String to = email;

  String host = "smtp.gmail.com";

  // Get system properties
  Properties properties = System.getProperties();
  // Setup mail server
  properties.put("mail.smtp.starttls.enable", "true");
  properties.put("mail.smtp.host", host);
  properties.put("mail.smtp.user", username);
  properties.put("mail.smtp.password", password);
  properties.put("mail.smtp.port", "587");
  properties.put("mail.smtp.auth", "true");

  // Get the default Session object.
  Session session = Session.getDefaultInstance(properties);

  try{
     // Create a default MimeMessage object.
     MimeMessage message = new MimeMessage(session);

     // Set From: header field of the header.
     message.setFrom(new InternetAddress(username));

     // Set To: header field of the header.
     message.addRecipient(Message.RecipientType.TO,
                              new InternetAddress(to));

     // Set Subject: header field
     message.setSubject(barcode+" REQUEST: "+Status);

     // Now set the actual message
    
     
     message.setText("Your request for BARCODE: "+barcode+" was "+Status+"\nPlease check with your manager.");
   //  message.setText(tt.toString());

     // Send message
     Transport transport = session.getTransport("smtp");
     transport.connect(host, username, password);
     transport.sendMessage(message, message.getAllRecipients());
     transport.close();
     System.out.println("Sent message successfully....");
     
	  JFrame f = new JFrame();
     // JOptionPane.showMessageDialog(f, "Requested!");
      
   
	         
	       
	         
      
	         
	       
	         
      
      
  }catch (MessagingException mex) {
     mex.printStackTrace();
  }
}
}
