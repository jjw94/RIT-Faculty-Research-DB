/**
 * @author Team Blue
 * @version November 10, 2014
 * Description: A class that facilitates handling data in the Faculty table
 */

import java.sql.*;
import java.util.*;

public class DLUser
{
   
   /**
    * Instance variables that derived from attributes in the Faculty table
    **/
   private String id;
   private String fName;
   private String lName;
   private String passWord;
   private String email;
   private String question;
   private String answer;
   private String accessLevel;
   private String title;
   
   DLConnection data = new DLConnection("jdbc:mysql://localhost/484project", "com.mysql.jdbc.Driver", "root", "sqltomo12");  
   
   ArrayList<String> params = new ArrayList<String>();
   
   /**
    * A default constructor that initializes all variables
    **/
   public DLUser(){
      data.connect();
   }
   
   /**
    * A parameterized constructor that passes in a value for the id variable
    * @param id
    **/
   public DLUser(String id){
      this.id = id;
      data.connect();
   }
   
   /**
    * A parameterized constructor that passes in a value for all variables
    * @param id 
    * @param fName
    * @param lName
    * @param password
    * @param email
    * @param title
    **/
   public DLUser(String id, String fName, String lName, String passWord, String email, String title){
      this.id = id;
      this.fName = fName;
      this.lName = lName;
      this.passWord = passWord;
      this.email = email;
      this.title = title; 
      data.connect();
   }
      
   /**
    * Returns a string that represents the last name of the user
    * @return lName
    **/
   public String getLastName(){
      return lName;
   }
   
   /**
    * Sets the last name of the user
    * @param lName
    **/
   public void setLastName(String lName){
      this.lName = lName;
   }
   
   /**
    * Returns an integer that represents the id of the user
    * @return id
    **/
   public String getID(){
      return id;
   }
   
   /**
    * Sets the id of the user
    * @param id
    **/
   public void setID(String id)
   {
      this.id = id;
   }
   
   /**
    * Returns a string that represents the first name of the user
    * @return first name
    **/
   
   public String getFirstName(){
      return fName;
   }
   
   /**
    * Sets the first name of the user
    * @param fName
    **/
   public void setFirstName(String fName){
      this.fName = fName;
   }
   
   /**
    * Returns a string that represents the email of the user
    * @return email
    **/
   public String getEmail(){
      return email;
   }
   
   /**
    * Sets the email of the user
    * @param email
    **/
   public void setEmail(String email){
      this.email = email;
   }
   
   /**
    * Returns a string that represents the question of the user security question
    * @return question
    **/
   public String getQuestion(){
      return question;
   }
   
   /**
    * Returns a string that represents the title of the paper requested
    * @return title
    **/
    public String getTitle()
    {
      return title;
    }
    
   /**
    * Sets the title of the paper 
    * @param title
    **/
    public void setTitle(String title)
    {
      this.title = title;
    }
    
   public String getAnswer(){
      return answer;
   }
   
   public void setAccessLevel(String accessLevel){
      this.accessLevel = accessLevel;
   }
   
   public String getAccessLevel(){
      return accessLevel;
   }
   
   /**
    * Retrieves data from the Faculty table based on given values
    **/
   public void fetch(){
      params.clear();
      params.add(id);
      String stmt = "SELECT faculty.id, papers.title, faculty.fName, faculty.lName FROM faculty JOIN authorship" 
      + " ON faculty.id = authorship.facultyId JOIN papers USING(id) WHERE id = ?";
     
      ArrayList<ArrayList<String>> resultsList = data.getData(stmt, params);

      if(resultsList.size() > 1)
      {
         id = resultsList.get(1).get(0);
         title = resultsList.get(1).get(1);
         fName = resultsList.get(1).get(2);
         lName = resultsList.get(1).get(3);
      }
      
   }
   
   /**
    * Inserts new values into the Faculty table and creates a new record
    **/
   public void put()
   {   
      params.clear();
      params.add(id);
      params.add(fName);
      params.add(lName);
      params.add(passWord);
      params.add(email);
      
      String stmt = "INSERT INTO faculty(id, fName, lName, password, email) VALUES(?, ?, ?, ?)";

      data.setData(stmt, params);    
   }
  
   /**
    * Updates the values for each attribute in the Faculty table
    **/
   public void post(){
      params.clear();
      params.add(fName);
      params.add(lName);
      params.add(passWord);
      params.add(email);
      params.add(id);
      
      String stmt = "UPDATE faculty SET fName = ?, lName = ?, password = ?, email = ? WHERE id = ?";
      
      data.setData(stmt, params); 
   }
   
   /**
    * Deletes a record from the Faculty table based on the given value for the id attribute
    **/
   public void delete(){
      params.clear();
      params.add(id);
      
      String stmt = "DELETE FROM faculty WHERE id = ?";
      
      data.setData(stmt, params); 
   }
   
   /**
    * Validates to determine if the credentials belong to anyone from faculty
    **/
   public boolean checkFaculty(String email, String password){
      boolean check = false;
      
      params.clear();
      params.add(email);
      params.add(password);
      
      String stmt = "SELECT email, password FROM faculty WHERE email = ? AND password = ?";
      
      ArrayList<ArrayList<String>> resultsList = data.getData(stmt, params);
      
      if(resultsList.size() > 0){
         check = true;
      }
      return check;   
   }
   
   /**
    * Validates to determine if the credentials belong to a student
    **/
   public boolean checkStudent(String email, String password){
      boolean check = false;
      
      params.clear();
      params.add(email);
      params.add(password);
      
      String stmt = "SELECT email, password FROM student WHERE email = ? AND password = ?";
      
      ArrayList<ArrayList<String>> resultsList = data.getData(stmt, params);
      
      if(resultsList.size() > 0){
         check = true;
      }
      return check; 
   }
   
   /**
    * Validates to determine if the credentials belong to a student
    **/
   public boolean checkStudent(String email){
      boolean check = false;
      
      params.clear();
      params.add(email);
      
      String stmt = "SELECT question, answer FROM student WHERE email = ?";
      ArrayList<ArrayList<String>> resultsList = data.getData(stmt, params);
      
      if(resultsList.size() > 0){
         check = true;
      }
      
      return check;
   }
   
    /**
    * Loads the security question textfield if user forgets the password
    * @params  email
    * @return  question
    */
   public String fetchQuestion(String email){
      params.clear();
      params.add(email);
      
      String stmt = "SELECT question, answer FROM student WHERE email = ?";
      ArrayList<ArrayList<String>> resultsList = data.getData(stmt, params);
      
      if(resultsList.size() > 0){
         question = resultsList.get(0).get(0);
         answer = resultsList.get(0).get(1);
         setAccessLevel("student");
      }
      else{
         stmt = "SELECT question, answer FROM faculty WHERE email = ?";
         resultsList = data.getData(stmt, params);
         
         if(resultsList.size() > 0){
            question = resultsList.get(0).get(0);
            answer = resultsList.get(0).get(1);
            setAccessLevel("faculty");
         }
         
         else{
            question = "";
            answer = "";
         }         
      }
      return question;
   }
   
   /**
    * Updates password from forgot password popup
    * @params  String password
    * @params  String email
    * @return  check
    **/
   public boolean updatePassword(String password, String email){
      boolean check = false;
      String accessLevel = getAccessLevel();
      
      params.clear();
      params.add(password);
      params.add(email);
      
      String stmt = "UPDATE " + accessLevel + " SET password = ? WHERE email = ?";
      
      data.setData(stmt, params); 
      check = true;
      
      return check;
   }

}