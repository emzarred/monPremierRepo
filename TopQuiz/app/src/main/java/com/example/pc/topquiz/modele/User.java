package com.example.pc.topquiz.modele;

/**
 * Created by pc on 02/03/2018.
 */

public class User {
   private String mFirstname;

   public String getFirstname() {
      return mFirstname;
   }

   public void setFirstname(String firstname) {
      mFirstname = firstname;
   }

   @Override
   public String toString() {
      return "User{" +
              "mFirstname='" + mFirstname + '\'' +
              '}';
   }
}
