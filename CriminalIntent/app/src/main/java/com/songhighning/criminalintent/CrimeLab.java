package com.songhighning.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Alex on 2016-04-27.
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimelab;
    private Context mAppContext;


    //having a Context parameter allows the singleton to start activities, access project resources,
    //find your application's private storage and more
    private CrimeLab(Context appContext){
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        //generate 100 boring crimes
        for (int i = 0; i < 10;i++){
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i%2 ==0);
            mCrimes.add(c);
        }

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
}
