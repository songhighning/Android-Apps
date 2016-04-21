package com.songhighning.criminalintent;

import java.util.UUID;

/**
 * Created by Alex on 2016-04-20.
 */
public class Crime {

    private UUID mId;
    private String mTitle;

    public Crime(){
        //Generate unique Identifier
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
