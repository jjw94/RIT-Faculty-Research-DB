import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class FacultyGUI extends PLGUI
{
   //Attributes
   private JTextField keyField, projID, fName, lName, titleField, paperIDfield, titleField2, paperIDfield2, paperIDfield3;
   private JButton showResults, upload, upload2, delete, insert, update;
   private JTabbedPane jt = new JTabbedPane();
   
   /**
    * Default constructor that calls the super method
    * Creates the GUI
    */
   public FacultyGUI()
   {
      super();
      createGUI();
   }
   
   /**
    * Method createGUI() sets all GUI properties of Faculty GUI
    */   
   public void createGUI()
   {
   
      JPanel panel = new JPanel();
   
      JFrame frame = new JFrame();
      
      frame.setVisible(true);
      frame.add(createTabPane());
    
     
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setSize(400, 230);
      frame.setTitle("Welcome");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
   
    /**
    * Method createTabPane() creates the Faculty Research tab and sets all its GUI properties
    * Creates the Update, Insert, and Delete tab by calling the methods from the PLGUI class
    * @return jt
    */
   public JTabbedPane createTabPane()
   {
      jt.add("Research", createResearchTab());
      jt.add("Insert", createInsertTab());
      jt.add("Update", createUpdateTab());
      jt.add("Delete", createDeleteTab());
      return jt;
   
   }
   
   /**
    * Main method to create a new FacultyGUI object
    */
   public static void main(String[]args)
   {
      FacultyGUI lg = new FacultyGUI();
   
   }
   

}