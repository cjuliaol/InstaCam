package com.example.thewizard.cjuliaol.livecam;

import android.util.Log;

import com.facebook.model.GraphObject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cjuliaol on 8/4/2015.
 */
public class User implements Serializable {
    private static final String TAG = "UserLog";
    private String mFirstName;
    private String mLastName;
    private Date mBirthday;
    private String mPictureUrl;

    private static User sCurrentUser;

    public static User getCurrentUser() {
     return sCurrentUser;
    }

    public static void  setCurrentUser(GraphObject graphObject) {
        if (sCurrentUser == null) {
            sCurrentUser = new User(graphObject);
        }
    }

  User (GraphObject graphObject) {
       mFirstName = (String) graphObject.getProperty("first_name");
      mLastName = (String) graphObject.getProperty("last_name");
      mPictureUrl = (String) graphObject.getPropertyAs("picture",GraphObject.class)
              .getPropertyAs("data",GraphObject.class)
              .getProperty("url");

      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");


      try {
          mBirthday = sdf.parse( (String) graphObject.getProperty("birthday"));

      } catch (ParseException e) {
          e.printStackTrace();
          Log.d(TAG,"Failed parsing date");
      }
  }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        mPictureUrl = pictureUrl;
    }
}
