package com.example.thewizard.cjuliaol.instacam;

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
