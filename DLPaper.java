/**
* @author Team Blue
* @version November 12, 2014
* Description: A class that facilitates the accessing and mutating of data in the Research Data Layer.
*/

import java.sql.*;
import java.util.*;

public class DLPaper{
   
   //Global Variables
   String keyword;
   String paperID;
   String paperTitle;
   String fName;
   String lName;
   String citation;
   String paperAbstract;
   DLConnection data = new DLConnection("jdbc:mysql://localhost/484project", "com.mysql.jdbc.Driver", "root", "sqltomo12");
   
   ArrayList<String> values = new ArrayList<String>(); 
   
   //default constructor
   public DLPaper(){
      openConnection();
   }
   
   //constructor that accepts and sets the paperID
   public DLPaper(String id){
      setPaperID(id);
      openConnection();
   }
   //constructor that accepts and sets all attributes
   public DLPaper(String paperID, String paperTitle, String fName, String lName){
      this.paperID = paperID;
      this.paperTitle = paperTitle;
      this.fName = fName;
      this.lName = lName;
      openConnection();
   }
   
   /** 
    *  Returns a string representing a paper keyword
    *  @return keyword
    **/
   public String getKeyword(){
      return keyword;
   }
   
    /** 
    *  Sets the paper keyword 
    *  @params key
    **/
   public void setKeyword(String key){
      keyword = key;
   }
   
   /** 
    *  Returns a int representing the paper id
    *  @return paperID
    **/
   public String getPaperID(){
      return paperID;
   }
   
   /** 
    *  Sets the paperID
    *  @params id
    **/
   public void setPaperID(String paperID){
      this.paperID = paperID;
   }
   
   /** 
    *  Returns a string representing the paper's title
    *  @return paperTitle
    **/
   public String getPaperTitle(){
      return paperTitle;
   }
   
   /** 
    *  Sets the paper's title 
    *  @params title
    **/
   public void setPaperTitle(String paperTitle){
      this.paperTitle = paperTitle;
   }
   
   /** 
    *  Returns a string representing the paper's citation
    *  @return citation
    **/
   public String getCitation(){
      return citation;
   }
   
   /** 
    *  Sets the paper's citation
    *  @params citation
    **/
   public void setCitation(String citation){
      this.citation = citation;
   }
   
   /** 
    *  Returns a string representing the abstract paper
    *  @return paperAbstract
    **/
   public String getAbstract(){
      return paperAbstract;
   }
   
   /** 
    *  Sets the abstract paper
    *  @params paperAbstract
    **/
   public void setAbstract(String paperAbstract){
      this.paperAbstract = paperAbstract;
   }
   
   /**
    *  Returns a string representing the author's first name for a paper
    *  @return fName
    **/
   public String getFirstName(){
      return fName;
   }
   
   /** 
    *  Sets the author's first name 
    *  @params first
    **/
   public void setFirstName(String fName){
      this.fName = fName;
   }
   
   /** 
    *  Returns a string representing the author's last name for a paper
    *  @return lName
    **/
   public String getLastName(){
      return lName;
   }

   /** 
    *  Sets the author's last name 
    *  @params last
    **/
   public void setLastName(String lName){
      this.lName = lName;
   }
   
   //methods
   
   /** 
    *  Fetches research papers using sql, searches by keyword, paperID, author's first name and last name.
    **/
   public ArrayList<ArrayList<String>> fetch(ArrayList<String> tables){
      boolean check = false;
      
      values.clear();
      
      String stmt = "SELECT paper_keywords.keyword, papers.id, papers.title, faculty.fName, faculty.lName, papers.abstract FROM faculty" + 
                    " INNER JOIN authorship ON faculty.id = authorship.facultyId" +
                    " INNER JOIN papers ON authorship.paperId = papers.id" +
                    " INNER JOIN paper_keywords ON papers.id = paper_keywords.id" +
                    " WHERE 1=1 ";
     
      String colName = "";
      for(int i=0; i < tables.size(); i++){
         switch(tables.get(i)){
            case "Keywords:":
               colName = "keyword";
               values.add(getKeyword());
               break;
            case "Paper ID:":
               colName = "papers.id";
               values.add(getPaperID());
               break;
            case "Title:":
               colName = "title";
               values.add(getPaperTitle());
               break;
            case "First Name:":
               colName = "fName";
               values.add(getFirstName());
               break;
            case "Last Name:":
               colName = "lName";
               values.add(getLastName());
               break;
         }
         stmt = stmt + " AND " + colName + " = ? ";
      }
      
      ArrayList<ArrayList<String>> result = data.getData(stmt, values);
      if(result.size() > 0){
         check = true;
      }
      for(int i = 0; i < result.size(); i++)
      {
         keyword = result.get(i).get(0);
         paperID = result.get(i).get(1);
         paperTitle = result.get(i).get(2);
         fName = result.get(i).get(3);
         lName = result.get(i).get(4);
         paperAbstract = result.get(i).get(5);
      }
      
      return result;
   }
   
   
   
   /** 
    *  Updates information on a current paper using the paper's title and ID
    **/
   public boolean put(){
      values.clear();
      
      String stmt = "UPDATE papers SET title = ?, abstract = ?, citation = ? WHERE id = ?";
      
      values.add(getPaperTitle());
      values.add(getAbstract());
      values.add(getCitation());
      values.add(getPaperID());
      
      boolean success = data.setData(stmt, values);
      return success;
   }
   
   /** 
    *  Inserts a paper into the database; includes keywords, authorship, ID, title abstract, and citation
    **/
   public boolean post(){
      values.clear();
      
      String stmt = "INSERT INTO papers (papers.id, papers.title, papers.abstract, papers.citation) VALUES (?, ?, ?, ?)";
      
      values.add(getPaperID());
      values.add(getPaperTitle());
      values.add(getAbstract());
      values.add(getCitation());
      
      boolean success = data.setData(stmt, values);
      
      return success;
   }
   
   /** 
    *  Deletes a paper from the database using the paperID
    **/
   public boolean delete(){
      boolean success = false;
      try{
         values.clear();
         values.add(getPaperID());
      
         data.startTrans();
      
         String stmt = "DELETE FROM authorship WHERE paperId = ?";
      
         success = data.setData(stmt, values);
      
         stmt = "DELETE FROM paper_keywords WHERE id = ?";
         success = data.setData(stmt, values);
      
         stmt = "DELETE FROM papers WHERE id = ?";
         success = data.setData(stmt, values);
      
         data.endTrans();
      }
      catch(Exception e){
         data.rollbackTrans();
         success = false;
         return success;
      }
      return success;
   }
   
   /** 
    *  Connects to the database using the global connection object
    **/
   public void openConnection(){
      data.connect();
   }
   
   /** 
    *  Disconnects from the database using the global connection object
    **/
   public void closeConnection(){
      data.close();
   }
}