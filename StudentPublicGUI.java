/**
* @author Team Blue
* @version December 10, 2014
* Description: A class that creates the Student/Public GUI
*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class StudentPublicGUI extends PLGUI{
   //Attributes
   private JTabbedPane jt = new JTabbedPane();
   //private DLUser dlUser;
   //private Token token;
   //private PLGUI plgui;
   
   /**
    * Default Constructor that instantiates new objects of the DLUSer, Token, and PLGUI class
    * Createes the GUI
    */
   public StudentPublicGUI(){
      //dlUser = new DLUser();
      //token = new Token(dlUser.getAccessLevel());
      //plgui = new PLGUI();
      createGUI();
   }
   
   /**
    * Method createGUI() creates new JPanel and JFrame
    * Sets GUI properties
    */
   public void createGUI(){
      JPanel panel = new JPanel();
      JFrame frame = new JFrame();
      
      frame.setVisible(true);
      frame.add(createTabPane());
      
      frame.setResizable(true);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setSize(400, 400);
      frame.setTitle("Welcome");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   /**
    * Create Research tab by calling createSPResearchTab() method from PLGUI class
    * @return jt
    */
   public JTabbedPane createTabPane(){
      jt.add("Research", createResearchTab());
      return jt;
   }
   
   /**
    * Main method to create new StudentPublicGUI object
    */
   public static void main(String []args){
      StudentPublicGUI spg = new StudentPublicGUI();
   }
}