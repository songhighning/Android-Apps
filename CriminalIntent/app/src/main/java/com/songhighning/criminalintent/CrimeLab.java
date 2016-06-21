package com.songhighning.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Alex on 2016-04-27.
 */
public class CrimeLab {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Crime> mCrimes;
    private  CriminalIntentJSONSerializer mSerializer;

    private static CrimeLab sCrimelab;
    private Context mAppContext;


    //having a Context parameter allows the singleton to start activities, access project resources,
    //find your application's private storage and more
    private CrimeLab(Context appContext){
        mAppContext = appContext;
        //mCrimes = new ArrayList<Crime>();
        mSerializer = new CriminalIntentJSONSerializer(appContext,FILENAME);

        try{
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e){
            mCrimes = new ArrayList<>();
            Log.e(TAG, " Error loading crimes: ", e);
        }

        //generate 100 boring crimes
        /*for (int i = 0; i < 10;i++){
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i%2 ==0);
            mCrimes.add(c);
        }*/

    }

    public static CrimeLab get(Context c){
        if (sCrimelab == null){
            sCrimelab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimelab;
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c:mCrimes){
            //when comparing UUID
            // a == b returns True if a and b are the same objects
            // equals return True if a and b have the same UUID values
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public boolean saveCrimes(){
        try{
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG,"crimes saved to file" );
            return true;
        }catch (Exception e){
            Log.e(TAG, "Error saving crimes: ",e);
            return false;
        }
    }

    public void deleteCrime(Crime c){
        mCrimes.remove(c);
    }
}
