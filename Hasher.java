/**
* @author Team Blue
* @version November 13, 2014
* Description: A class that hashes
* Reference: http://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash
*/ 

import java.text.*;
import java.util.*;
import java.security.*;

public class Hasher{
   
   /**
    * Default Constructor
    */
   public Hasher(){
   }
   
   /**
    * Returns a String that is the hashed using MD5 algorithm
    * @param input String that will be hashed
    * @return hash
    **/
   public String MD5(String md5) {
      String hash = "";
      try {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] array = md.digest(md5.getBytes());
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < array.length; ++i) {
            sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
         }
         hash = sb.toString();
      } 
      catch (NoSuchAlgorithmException e) {
         System.exit(0);
      }
      return hash;
   }
}