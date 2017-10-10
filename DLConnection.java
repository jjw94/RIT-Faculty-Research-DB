/**
* @author Team Blue
* @version November 13, 2014
* Description: A class that facilitates the accessing and mutating of data in the Research Data Layer.
*/

import java.sql.*;
import java.util.*;

public class DLConnection{
   
   //attributes
   String uri;
   String driver;
   String userID;
   String userPassword;
   
   Connection conn;
   
   boolean connection, closed;
   
   
   /**
    * A parameterized constructor that passes in a value for the connection
    * @param u URI name
    * @param d Driver name
    * @param uID  User ID
    * @param uPW  User Password
    **/
   public DLConnection(String u, String d, String uID, String uPW){
      uri = u;
      driver = d;
      userID = uID;
      userPassword = uPW;
      
      connection = false;
      closed = false;
   }
   
   /**
    * Returns a boolean showing if it was successful of creating a new connection
    * @return connection
    **/
   public boolean connect(){
      try{
         
         Class.forName(driver);
         conn = DriverManager.getConnection(uri, userID, userPassword);
         
         connection = true;
      }
      
      catch(ClassNotFoundException cnfe){
         System.out.println("Could not find driver: " + driver);
      }
      
      catch(SQLException sqle){
         System.out.println("Could not connect at " + uri + " using ID: " + userID);
      }

      
      finally{
         return connection;
      }
   }
   
   /**
    * Returns a boolean showing if it was successful of closing the connection
    * @return closed
    **/
   public boolean close(){
      if(conn != null){
         try{
            conn.close();
            closed = true;
         }
         
         catch(SQLException sqle){
            System.out.println("Could not close connection");
            
         }
       }
       
       return closed;
   }
   
   /**
    * Returns a 2D ArrayList of the result of the statement
    * @param statement
    * @return result
    **/
   public ArrayList<ArrayList<String>> getData(String statement){
		
      ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
      
      try{
         
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(statement);
         
         ResultSetMetaData rsMeta = rs.getMetaData();
         int col = rsMeta.getColumnCount();
         
         while(rs.next()){
            ArrayList<String> row = new ArrayList<String>();
            
            for(int i=1; i<=col; i++){
               row.add(rs.getString(i));
            }
            
            result.add(row);
         }
      }
      
      catch(SQLException sqle){
         System.out.println("Could not get data");
      }
  
      return result;
   }
   
   
   /**
    * Returns a 2D ArrayList of the result of the prepared statement
    * @param statement
    * @param values
    * @return result
    **/
   public ArrayList<ArrayList<String>> getData(String statement, ArrayList<String> values){
     
      ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
      
      try{
         PreparedStatement prepStmt = prepare(statement, values);
         ResultSet rs = prepStmt.executeQuery();
         
         ResultSetMetaData rsMeta = rs.getMetaData();
         int col = rsMeta.getColumnCount();
         
         //add results
         while(rs.next()){
            ArrayList<String> row = new ArrayList<String>();
            
            for(int i=1; i<=col; i++){
               row.add(rs.getString(i));
            }
            
            result.add(row);
         }
      }
         
      catch(SQLException sqle){
         System.out.println(sqle);
      } 
                      
      return result;
   }
   
   
   /**
    * Returns a boolean showing if the statement successfully ran
    * @param statement
    * @return success
    **/
   public boolean setData(String statement){
      boolean success = false;
      
      try{
      
         Statement stmt = conn.createStatement();
         int rc = stmt.executeUpdate(statement);
         
         if(rc > 0){
            success = true;
         }

      }
      
      catch(SQLException sqle){
         System.out.print(sqle);
         //System.out.print("Could not set data");
      }
      
      return success;
   }
   
   
   /**
    * Returns a boolean showing if the statement successfully ran
    * @param statement
    * @param values
    * @return success
    **/
   public boolean setData(String statement, ArrayList<String> values){
      boolean success = false;
      
      try{
         PreparedStatement prepStmt = prepare(statement, values);
         int rc = prepStmt.executeUpdate();
         
         if(rc > 0){
            success = true;
         }
      }
      
      catch(SQLException sqle){
         System.out.println("Could not set data");
      }
      
      return success;   
   }
   
   /**
    * Returns a PreparedStatement prepared with the statements and values taken in
    * @param statement
    * @param values
    * @return success
    **/
   public PreparedStatement prepare(String statement, ArrayList<String> values){
   
      PreparedStatement prepStmt = null;
      
      try{
         prepStmt = conn.prepareStatement(statement);
         
         for(int i=0; i<values.size(); i++){
            prepStmt.setString(i+1, values.get(i));
         }
         
      }
      
      catch(SQLException sqle){
         System.out.println("Could not execute query");
      }
      return prepStmt;
   }
   
   /**
    * Starts a transaction
    **/
   public void startTrans(){
      try{
         conn.setAutoCommit(false);
      }
      catch(SQLException sqle){
         System.out.print("Could not start transaction");
      }
   }
   
   /**
    * Rolls back a transaction
    **/
   public void rollbackTrans(){
      try{
         conn.rollback();
      }
      catch(SQLException sqle){
         System.out.print("Could not rollback transaction");
      }
   }
   
   /**
    * Ends a transaction
    **/
   public void endTrans(){
      try{
         conn.commit();
      }
      catch(SQLException sqle){
         System.out.print("Could not end transaction");
      }
   }
}