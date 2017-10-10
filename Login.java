/**
* @author Team Blue
* @version November 12, 2014
* Description: A class that creates the Login GUI
* Sorry Professor Zilora, but we accidentally drop boxed the wrong version. (This is the one we showed in class) I hope
* you are willing to accept this version for submission.
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Login implements ActionListener{
   //Attributes
   private Hasher hash;
   private JPasswordField pf;
   private JTextField email;
   private JButton login, guest, fp;
   private DLUser dl;
   private JFrame frame;
   
   /**
    * Default constructor that creates the GUI
    * Creates new Hasher and DLUser objects
    */
   public Login(){
      createGUI();
      hash = new Hasher();
      dl = new DLUser();
   }
   
   /**
    * Method createGUI() sets all GUI properties for Login GUI
    * Creates JPanels and add components accordingly
    * Adds action listeners
    */
   public void createGUI(){
      pf = new JPasswordField(20);
      email = new JTextField(20);
      
      JLabel label1 = new JLabel("Email: ");
      JLabel label2 = new JLabel("Password: ");
      
      login = new JButton("User Login");
      guest = new JButton("Guest Login");
      fp = new JButton("Forgot Password?");
      
      JPanel north = new JPanel();
      JPanel center = new JPanel();
      JPanel south = new JPanel();
      
      north.add(label1);
      north.add(email);
      
      center.add(label2);
      center.add(pf);
      
      south.add(login);
      south.add(guest);
      south.add(fp);
      
      login.addActionListener(this);
      guest.addActionListener(this);
      fp.addActionListener(this);
      
      frame = new JFrame();
      frame.add(north,  BorderLayout.NORTH);
      frame.add(center, BorderLayout.CENTER);
      frame.add(south, BorderLayout.SOUTH);
      frame.setVisible(true);
      frame.setResizable(true);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setSize(400, 150);
      frame.setTitle("Please enter your credentials");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      email.requestFocus();
   }
   
   /**
    * Action Listener for the User Login functionalities
    * Checks whether user credentials are correct upon submission of information
    * If credentials are missing or incorrect, error message dialog will display accordingly
    * Will not allow Login without proper credentials
    */
   public void actionPerformed(ActionEvent e){
      if(e.getActionCommand().equals("User Login")){
         String inputEmail = email.getText();
         String inputPassword = hash.MD5(pf.getText());
         
         if(inputEmail.equals("") || inputPassword.equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Please input email and/or password", "Error", JOptionPane.ERROR_MESSAGE);
         }
         else if(dl.checkFaculty(inputEmail, inputPassword)){
            FacultyGUI fg = new FacultyGUI();
            frame.setVisible(false);
         }
         else if(dl.checkStudent(inputEmail, inputPassword)){
            StudentPublicGUI spg = new StudentPublicGUI();
            frame.setVisible(false);
         }
         else{
            JOptionPane.showMessageDialog(new JFrame(), "Inputted email and/or password is incorrect", "Incorrect credentials", JOptionPane.ERROR_MESSAGE);
         }
      }
      else if(e.getActionCommand().equals("Guest Login")){
         StudentPublicGUI spg = new StudentPublicGUI();
         frame.setVisible(false);
      }
      else if(e.getActionCommand().equals("Forgot Password?")){
         PLForgotPassword pfp = new PLForgotPassword();
         pfp.enableButton(fp, false);
      }
   }
   
   /**
    * Main method to create new Login GUI object
    */
   public static void main(String []args){
      Login lg = new Login();
   }
}