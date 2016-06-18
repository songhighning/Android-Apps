package com.songhighning.criminalintent;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Alex on 2016-06-18.
 */
public class CriminalIntentJSONSerializer {
    private Context mContext;
    private String mFilename;

    public CriminalIntentJSONSerializer(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException{
        //Build an array in JSON
        JSONArray jsonArray = new JSONArray();
        for (Crime c : crimes){
            jsonArray.put(c.toJSON());
        }

        //Write the file to disk
        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFilename,Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(jsonArray.toString());
        }finally {
            if (writer != null){
                writer.close();
            }
        }

    }
}
