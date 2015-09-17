package com.example.thewizard.cjuliaol.livecam;

import android.os.Environment;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by cjuliaol on 22/07/2015.
 */
public class Photo implements Serializable {
    private UUID mId;
    private String caption;
    private User mUser;

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    private static final File sDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

    Photo() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public File getFile() {
        return new File(sDirectory, mId.toString());
    }
}
