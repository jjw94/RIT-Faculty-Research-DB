import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class PLGUI implements ActionListener{

   //Attributes
   private JTextField keyField, projID, fName, lName, titleField, paperIDfield, titleField2, titleField3, paperIDfield2, paperIDfield3, citeField, citeField2;
   private JButton showResults, upload, upload2, delete, insert, update;
   private JLabel updateLabel, insertLabel;
   private JLabel keyWord, projLabel, fLabel, lLabel, titleLabel;
   private JPanel rPanel;
   private JTable resTable;
   private DefaultTableModel model;
   private ArrayList<String> content = new ArrayList<>();
   private ArrayList<String> columns = new ArrayList<String>();
   private Token token;
   private DLUser dlu;
   private DLPaper dlp;
   private Vector<String> values;
   
   /**
    * Default constructor
    */
   public PLGUI(){
      dlu = new DLUser();
      dlp = new DLPaper();
      token = new Token(dlu.getAccessLevel());
   }
   
   /**
    * Method createResearchTab() creates the Research tab of the Faculty GUI
    * Sets all GUI properties of the Faculty Research tab
    * @return rPanel
    */
   public JPanel createResearchTab(){
      GridLayout gd = new GridLayout(5, 2);
      JPanel researchPanel = new JPanel();
      JPanel resPanel = new JPanel();
      
      rPanel = new JPanel();
      researchPanel.setLayout(gd);
      
      keyWord = new JLabel("Keywords:");
      keyField = new JTextField(10);
      
      projLabel = new JLabel("Paper ID:");
      projID = new JTextField(10);
      
      titleLabel = new JLabel("Title:");
      titleField3 = new JTextField(10);
      
      fLabel = new JLabel("First Name:");
      fName = new JTextField(10);
      
      lLabel = new JLabel("Last Name:");
      lName = new JTextField(10);
      
      showResults = new JButton("Show Results");
      showResults.addActionListener(this);
      
      researchPanel.add(keyWord);
      researchPanel.add(keyField);
      
      researchPanel.add(projLabel);
      researchPanel.add(projID);
      
      researchPanel.add(titleLabel);
      researchPanel.add(titleField3);
      
      researchPanel.add(fLabel);
      researchPanel.add(fName);
      
      researchPanel.add(lLabel);
      researchPanel.add(lName);
      
      rPanel.setLayout(new BorderLayout());
      resPanel.add(showResults);
      
      rPanel.add(researchPanel, BorderLayout.NORTH);
      rPanel.add(resPanel, BorderLayout.SOUTH);
      
      return rPanel;
   }
   
   /**
    * Method createSPResearchTab() creates the Research tab of the Student/Public GUI
    * Sets all GUI properties of the Student/Public Research tab
    * @return rPanel
    */
   /*public JPanel createSPResearchTab(){
      GridLayout gd = new GridLayout(5, 2);
      JPanel researchPanel = new JPanel();
      rPanel = new JPanel();
      JPanel buttonPanel = new JPanel();
      researchPanel.setLayout(gd);
      
      keyWord = new JLabel("Keywords:");
      keyField = new JTextField(10);
      
      projLabel = new JLabel("Paper ID:");
      projID = new JTextField(10);
      
      titleLabel = new JLabel("Title:");
      titleField3 = new JTextField(10);
      
      fLabel = new JLabel("First Name:");
      fName = new JTextField(10);
      
      lLabel = new JLabel("Last Name:");
      lName = new JTextField(10);
      
      showResults = new JButton("Show Result");
      showResults.addActionListener(this);
      
      researchPanel.add(keyWord);
      researchPanel.add(keyField);
      
      researchPanel.add(projLabel);
      researchPanel.add(projID);
      
      researchPanel.add(titleLabel);
      researchPanel.add(titleField3);
      
      researchPanel.add(fLabel);
      researchPanel.add(fName);
      
      researchPanel.add(lLabel);
      researchPanel.add(lName);
      
      buttonPanel.add(showResults);
      
      rPanel.add(researchPanel, BorderLayout.NORTH);
      rPanel.add(buttonPanel, BorderLayout.SOUTH);
      
      return rPanel;
   }
   */
   /**
    * Method createInsertTab() creates the Insert tab for Faculty GUI
    * Sets all GUI properties for the Faculty Insert tab
    * @return insPanel
    */
   public JPanel createInsertTab(){
      GridLayout gd = new GridLayout(5, 2);
      JPanel insertPanel = new JPanel();
      JPanel inPanel = new JPanel();
      
      JPanel insPanel = new JPanel();
      insertPanel.setLayout(gd);
      
      JLabel uplabel = new JLabel("Upload:");
      upload = new JButton("File..");
      upload.addActionListener(this);
      
      JLabel titleLabel = new JLabel("New Title:");
      titleField = new JTextField(10);
      
      JLabel pLabel = new JLabel("New Paper ID:");
      paperIDfield = new JTextField(10);
      
      JLabel cLabel = new JLabel("New Citation :");
      citeField = new JTextField(10);
      
      insert = new JButton("Publish");
      insert.addActionListener(this);
      
      insertLabel = new JLabel();
      JLabel fileLabel = new JLabel("Current File: ");
      
      insertPanel.add(pLabel);
      insertPanel.add(paperIDfield);
      insertPanel.add(uplabel);
      insertPanel.add(upload);
      
      insertPanel.add(titleLabel);
      insertPanel.add(titleField);
      insertPanel.add(cLabel);
      insertPanel.add(citeField);
      insertPanel.add(fileLabel);
      insertPanel.add(insertLabel);
      
      
      inPanel.add(insert);
      insPanel.setLayout(new BorderLayout());
      
      insPanel.add(insertPanel, BorderLayout.NORTH);
      insPanel.add(inPanel, BorderLayout.SOUTH);
      
      return insPanel;
   }
   
   /**
    * Method createUpdateTab() creates the Update tab for Faculty GUI
    * Sets all GUI properties for the Faculty Update tab
    * @return updPanel
    */
   public JPanel createUpdateTab(){
      GridLayout gd = new GridLayout(5, 2);
      JPanel updatePanel = new JPanel();
      JPanel upPanel = new JPanel();
      
      JPanel updPanel = new JPanel();
      updatePanel.setLayout(gd);
      
      JLabel uplabel2 = new JLabel("Upload:");
      upload2 = new JButton("File..");
      upload2.addActionListener(this);
      
      JLabel titleLabel2 = new JLabel("Updated Title:");
      titleField2 = new JTextField(10);
      
      JLabel pLabel2 = new JLabel("Update Paper of Paper ID:");
      paperIDfield2 = new JTextField(10);
      
      update = new JButton("Update");
      update.addActionListener(this);
      
      JLabel cLabel2 = new JLabel("New Citation :");
      citeField2 = new JTextField(10);
     
      updateLabel = new JLabel();
      JLabel fileLabel2 = new JLabel("Current file: ");
      
      updatePanel.add(pLabel2);
      updatePanel.add(paperIDfield2);
      updatePanel.add(uplabel2);
      updatePanel.add(upload2);
      updatePanel.add(titleLabel2);
      updatePanel.add(titleField2);
      updatePanel.add(cLabel2);
      updatePanel.add(citeField2);
      updatePanel.add(fileLabel2);
      updatePanel.add(updateLabel);
      
      upPanel.add(update);
      updPanel.setLayout(new BorderLayout());
      
      updPanel.add(updatePanel, BorderLayout.NORTH);
      updPanel.add(upPanel, BorderLayout.SOUTH);
      
      return updPanel;
   }
   
   /**
    * Method createDeleteTab() creates the Delete tab for the Faculty GUI
    * Sets all GUI properties for the Faculty Delete tab
    * @return delPanel
    */
   public JPanel createDeleteTab(){
      GridLayout gd = new GridLayout(1, 2);
      JPanel deletePanel = new JPanel();
      JPanel dePanel = new JPanel();
      
      JPanel delPanel = new JPanel();
      deletePanel.setLayout(gd);
      
      JLabel pLabel3 = new JLabel("Paper ID:");
      paperIDfield3 = new JTextField(10);
      
      delete = new JButton("Delete");
      delete.addActionListener(this);
      
      deletePanel.add(pLabel3);
      deletePanel.add(paperIDfield3);
      
      dePanel.add(delete);
      delPanel.setLayout(new BorderLayout());
      
      delPanel.add(deletePanel, BorderLayout.NORTH);
      delPanel.add(dePanel, BorderLayout.SOUTH);
      
      return delPanel;
   }
   

   
   /**
    * A method used to retrieve content from desired file
    * @return content
    **/
   public ArrayList<String> getContent(JLabel label)
   {
      JFileChooser fileChoose = new JFileChooser();
      int option = fileChoose.showOpenDialog(null);
      
      
      if(option == JFileChooser.APPROVE_OPTION) 
      {
         File file = fileChoose.getSelectedFile();
         String fileName = file.getName();
         String extension = fileName.substring(fileName.lastIndexOf(".")+1);
      
         if(!(extension.equals("txt") || extension.equals("doc") || extension.equals("docx")))
         {
            JOptionPane.showMessageDialog(null, "File must be .docx, .doc, or .txt");
         
         } 
         else
         {
            label.setText(fileName);
            try
            {
               FileReader fr = new FileReader(file);
               BufferedReader br = new BufferedReader(fr);
               String abstractPaper;
               while ((abstractPaper = br.readLine()) != null) 
               {
                  content.add(abstractPaper);
               }
               br.close();
            
            
            }
            catch(IOException ie)
            {
            
            
            }
         }
        
        
      }
      return content;
   }
   
     
   public boolean isInteger( String input )  
   {  
      try  
      {  
         Integer.parseInt( input );  
         return true;  
      }  
      catch(Exception e)  
      {  
         return false;  
      }  
   }  
   
   /**
    * A method associated with the ActionListener used for the fucntionality of this class
    * @param ae
    **/
   public void actionPerformed(ActionEvent ae)
   {
      ArrayList<String> textList = new ArrayList<>();
      //Activates functionality when the delete button is clicked
      if(ae.getActionCommand().equals("Delete")){
         if(token.checkTime()){
            String id = paperIDfield3.getText();
            if(id.equals(""))
            {
               JOptionPane.showMessageDialog(null, "Please fill in the paper ID");
               paperIDfield3.requestFocus();
            }
            
            else if(!(isInteger(id)))
            {
            
               JOptionPane.showMessageDialog(null, "ID must be an integer value");
               paperIDfield3.requestFocus();
            }
            else
            {
               DLPaper paper = new DLPaper(id);
            
               int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete this record?", "Well do you?",
                  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
               if(response == JOptionPane.YES_OPTION)
               { 
                  boolean success = paper.delete();
               
                  if(success)
                  {
                     JOptionPane.showMessageDialog(null, "Record deleted");
                     paperIDfield3.requestFocus();
                  }
                  else{
                     JOptionPane.showMessageDialog(null, "No Records Found");
                     paperIDfield3.requestFocus();
                  }
               }
               else if(response == JOptionPane.NO_OPTION)
               { 
                  paperIDfield3.requestFocus();
               }
            }
         }
         else{
            JOptionPane.showMessageDialog(null, "Your time has expired");
            System.exit(0);
         }
      }
      
      //Activates when the update button is clicked
      else if(ae.getActionCommand().equals("Update"))
      {
         if(token.checkTime()){
            String id = paperIDfield2.getText();
            String title = titleField2.getText();
            String citation = citeField2.getText();
            String text = "";
            if(id.equals(""))
            {
               JOptionPane.showMessageDialog(null, "Please fill in the paper ID");
               paperIDfield2.requestFocus();
            }
            
            else if(!(isInteger(id)))
            {
            
               JOptionPane.showMessageDialog(null, "ID must be an integer value");
               paperIDfield2.requestFocus();
            }
            
            else
            {
               DLPaper paper = new DLPaper(id);
               paper.setPaperTitle(title);
               paper.setCitation(citation);
            
               for(int i = 0; i < content.size(); i++)
               {
                  text += content.get(i);
                  paper.setAbstract(text);
               }
               boolean success = paper.put();
            
               if(success == false)
               {
                  JOptionPane.showMessageDialog(null, "Record could not be updated. It may not exist");
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Record successfully updated");
               
               }
            }
         }
         else{
            JOptionPane.showMessageDialog(null, "Your time has expired");
            System.exit(0);
         }
      }
      
      //Activates when the insert button is clicked
      else if(ae.getActionCommand().equals("Publish"))
      {
         if(token.checkTime()){
            String id = paperIDfield.getText();
            String citation = citeField.getText();
            String title = titleField.getText();
            String text = "";
         
            if(id.equals(""))
            {
               JOptionPane.showMessageDialog(null, "Please fill in the paper ID");
               paperIDfield.requestFocus();
            }
            
            else if(!(isInteger(id)))
            {
            
               JOptionPane.showMessageDialog(null, "ID must be an integer value");
               paperIDfield.requestFocus();
            }
            
            else
            {
               DLPaper paper = new DLPaper(id);
            
               paper.setPaperTitle(title);
               paper.setCitation(citation);
            
               for(int i = 0; i < content.size(); i++)
               {
                  text += content.get(i);
                  paper.setAbstract(text);
               }
            
               boolean success = paper.post();
            
               if(success == false)
               {
                  JOptionPane.showMessageDialog(null, "Record could not be published. It already exist");
               }
               else
               {
                  JOptionPane.showMessageDialog(null, "Record successfully published");
               
               }
            
            }
         }
         else{
            JOptionPane.showMessageDialog(null, "Your time has expired");
            System.exit(0);
         }
      }
      
      else if(ae.getSource() == showResults)
      {
         columns.clear();
         if(token.checkTime()){
            String id = projID.getText();     
            String keyword = keyField.getText();
            String title = titleField3.getText();
            String first = fName.getText();
            String last = lName.getText();
            
            if(keyword.length() != 0){
               columns.add(keyWord.getText());
               dlp.setKeyword(keyword);
            }
            if(id.length() != 0){
               columns.add(projLabel.getText());
               dlp.setPaperID(id);
            }
            if(title.length() != 0){
               columns.add(titleLabel.getText());
               dlp.setPaperTitle(title);
            }
            if(first.length() != 0){
               columns.add(fLabel.getText());
               dlp.setFirstName(first);
            }
            if(last.length() != 0){
               columns.add(lLabel.getText());
               dlp.setLastName(last);
            }
           
            ArrayList<ArrayList<String>> resultSet = new ArrayList<ArrayList<String>>();
            resultSet = dlp.fetch(columns);
            if(resultSet.size() > 0){
               int n = JOptionPane.showConfirmDialog(null, "You have found " + resultSet.size() + "rows. \nWould you like to show all?", 
                  "Found Results!", JOptionPane.OK_CANCEL_OPTION);
               if(n == JOptionPane.OK_OPTION){
                  for(int i = 0; i<resultSet.size(); i++){
                     String result = "Keyword: " + resultSet.get(i).get(0) + 
                                       "\n\nPaper ID: " + resultSet.get(i).get(1) + 
                                       "\n\nPaper Title: " + resultSet.get(i).get(2) +
                                       "\n\nFirst Name: " + resultSet.get(i).get(3) + 
                                       "\n\nLast Name: " + resultSet.get(i).get(4) +
                                       "\n\nAbstract: " + resultSet.get(i).get(5);
                                       
                     JScrollPane scrollPane = new JScrollPane();
                     scrollPane.setPreferredSize(new Dimension(300, 300));
                     JTextArea txtTemp = new JTextArea(result);
                     txtTemp.setLineWrap(true);
                     txtTemp.setWrapStyleWord(true);
                     scrollPane.getViewport().setView(txtTemp);
                     
                     JOptionPane.showMessageDialog(null, scrollPane);
                  }
               } 
            }
            else{
               JOptionPane.showMessageDialog(null, "No results found");
            }
         }
         else{
            JOptionPane.showMessageDialog(null, "Your time has expired");
            System.exit(0);
         }
      }
      
      //Activates when the upload button is clicked
      else if(ae.getSource() == upload)
      {
         if(token.checkTime()){
            getContent(insertLabel);
         }
         else{
            JOptionPane.showMessageDialog(null, "Your time has expired");
            System.exit(0);
         }
        
      }
      
      //Activates when the upload button is clicked
      else if(ae.getSource() == upload2)
      {
         if(token.checkTime()){
            getContent(updateLabel);
         }
         else{
            JOptionPane.showMessageDialog(null, "Your time has expired");
            System.exit(0);
         }
      }
   
   }
}