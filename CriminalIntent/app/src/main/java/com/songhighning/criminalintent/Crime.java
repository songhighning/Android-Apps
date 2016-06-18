package com.songhighning.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;

/**
 * Created by Alex on 2016-04-20.
 */
public class Crime {
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
    private static final String JSON_SOLVED = "solved";

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Crime(){
        //Generate unique Identifier
        mId = UUID.randomUUID();
        mDate = new Date();
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

    public JSONObject toJSON() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_ID, mId.toString());
        jsonObject.put(JSON_TITLE, mTitle);
        jsonObject.put(JSON_SOLVED,mSolved);
        jsonObject.put(JSON_DATE, getDate().getTime());
        return jsonObject;
    }

    @Override
    public String toString(){
        return mTitle;
    }
}
