/**
* @author Team Blue
* @version December 10, 2014
* Description: A class that creates the GUI that displays when users forget password
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class PLForgotPassword extends JDialog implements ActionListener{
   //Attributes
   private Hasher hash;
   private DLUser dlu;
   private JTextField emailField, securityField, answerField;
   private JPasswordField newPasswordField, confirmPasswordField;
   private JLabel emailLabel, secLabel, ansLabel, newPasswordLabel, confirmPasswordLabel;
   private JButton getSecurity, submitAns, resetPassword;
   
   private JButton fp;
   
   /**
    * Default constructor that creates and sets all GUI properties
    * Creates JPanels and adds components accordingly
    */
   public PLForgotPassword(){
      hash = new Hasher();
      dlu = new DLUser();
      
      setVisible(true);
      setSize(400, 200);
      setLocationRelativeTo(null);
      setTitle("Retrieve Forgotten Password");
       
      
      emailField = new JTextField(20);
      securityField = new JTextField(20);
      answerField = new JTextField(20);
      newPasswordField = new JPasswordField(20);
      confirmPasswordField = new JPasswordField(20);
      
      emailLabel = new JLabel("Email: ");
      secLabel = new JLabel("Security Question: ");
      ansLabel = new JLabel("Answer: ");
      newPasswordLabel = new JLabel("New Password: ");
      confirmPasswordLabel = new JLabel("Confirm Password: ");
      
      getSecurity = new JButton("Get Question");
      submitAns = new JButton("Submit Answer");
      resetPassword = new JButton("Reset Password");
      
      GridLayout gd = new GridLayout(5, 2);
      JPanel secPanel = new JPanel();
      JPanel secuPanel = new JPanel();
      
      secPanel.setLayout(gd);
      
      securityField.setEnabled(false);
      answerField.setEnabled(false);
      newPasswordField.setEnabled(false);
      confirmPasswordField.setEnabled(false);
      submitAns.setEnabled(false);
      resetPassword.setEnabled(false);
      
      getSecurity.addActionListener(this);
      submitAns.addActionListener(this);
      resetPassword.addActionListener(this);
      
      secPanel.add(emailLabel);
      secPanel.add(emailField);
      secPanel.add(secLabel);
      secPanel.add(securityField);
      secPanel.add(ansLabel);
      secPanel.add(answerField);
      secPanel.add(newPasswordLabel);
      secPanel.add(newPasswordField);
      secPanel.add(confirmPasswordLabel);
      secPanel.add(confirmPasswordField);
      
      secuPanel.add(getSecurity);
      secuPanel.add(submitAns);
      secuPanel.add(resetPassword);
      
      add(secPanel, BorderLayout.NORTH);
      add(secuPanel, BorderLayout.CENTER);
      
      addWindowListener(new WindowAdapter(){
         public void windowClosing(WindowEvent e)
         {
            enableButton(fp, true);
         }
      });
   }
   
   /**
    * Enables/Disables forgot password button
    * @params  JButton jb
    * @params  boolean enable
    */
   public void enableButton(JButton jb, boolean enable){
      fp = jb;
      fp.setEnabled(enable);
   }
   
   /**
    * Action Listener that provides the security question when the "Get Question" button is clicked
    * Checks if email has an account associated with it
    * If no account is associated with email, error message will be displayed
    */
   public void actionPerformed(ActionEvent ae){
      if(ae.getActionCommand().equals("Get Question")){
         String emailText = emailField.getText();
         String question = dlu.fetchQuestion(emailText);
         
         if(question.length() != 0){
            securityField.setText(question);
            emailField.setEnabled(false);
            answerField.setEnabled(true);
            answerField.requestFocus();
            submitAns.setEnabled(true);
            getSecurity.setEnabled(false);
         }
         else{
            JOptionPane.showMessageDialog(new JFrame(), "Please enter an existing email address", "Error", JOptionPane.ERROR_MESSAGE);
         } 
      }
      else if(ae.getActionCommand().equals("Submit Answer")){
         String inputAnswer = hash.MD5(answerField.getText());
         
         if(answerField.getText().length() != 0){
            if(inputAnswer.equals(dlu.getAnswer())){
               answerField.setEnabled(false);
               submitAns.setEnabled(false);
               newPasswordField.setEnabled(true);
               newPasswordField.requestFocus();
               confirmPasswordField.setEnabled(true);
               resetPassword.setEnabled(true);
            }
            else{
               JOptionPane.showMessageDialog(new JFrame(), "Your answer is incorrect", "Error", JOptionPane.ERROR_MESSAGE);
            }
         }
         else{
            JOptionPane.showMessageDialog(new JFrame(), "Please enter an answer", "Error", JOptionPane.ERROR_MESSAGE);
         }
      }
      else if(ae.getActionCommand().equals("Reset Password")){
         String newPassword  = newPasswordField.getText();
         String confirmPassword = confirmPasswordField.getText();
         
         if(newPassword.length() != 0 || confirmPassword.length() != 0){
            if(newPassword.equals(confirmPassword)){
               if(dlu.updatePassword(hash.MD5(newPassword), emailField.getText())){
                  JOptionPane.showMessageDialog (null, "Your password has been updated", "Updated!", JOptionPane.INFORMATION_MESSAGE);
                  enableButton(fp, true);
                  super.dispose();
               }
            }
            else{
               JOptionPane.showMessageDialog(new JFrame(), "Your password doesn't match", "Error", JOptionPane.ERROR_MESSAGE);
            }
         }
         else{
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a new password", "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         
      }
   }
   
   
}