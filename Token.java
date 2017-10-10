/**
* @author Team Blue
* @version November 13, 2014
* Description: A class that creates a token for the user, it contains and access level and expiring time.
*/

import java.text.*;
import java.util.*;
import java.security.*;

public class Token{
   
   //Global Attributes
   private Hasher hash;
   private String tokenName;
   private String accessLevel;
   private long currentTime;
   private long expiringTime;
   
   /**
    * Parameterized constructor that passes in the access level for token
    * @param accesslvl Users access level
    **/
   public Token(String accessLevel){
      hash = new Hasher();
      double random = Math.random() * 1000000; //Random number
      this.tokenName = hash.MD5(String.valueOf(random));
      this.accessLevel = accessLevel; 
      this.currentTime = System.currentTimeMillis(); //Current Time in Milliseconds when the Token is created
      this.expiringTime = currentTime + 600000; //10 Minutes Expiring Time
   }
   
   /**
    * Returns a long which is the expiring time of the token
    * @return expiringTime
    **/
   public long getExpiringTime(){
      return expiringTime;
   }
   
   /**
    * Returns a boolean showing whether this token has expired or not
    * @return check
    **/
   public boolean checkTime(){
      boolean check = false;
      long time = System.currentTimeMillis(); //The current time when the method is used
      
      if(time > getExpiringTime()){
         check = false;
      }      
      else{
         check = true;
      }
      return check;
   }
}