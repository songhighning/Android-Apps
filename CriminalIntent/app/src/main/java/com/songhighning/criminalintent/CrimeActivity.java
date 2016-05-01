package com.songhighning.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {
    protected static final String TAG = "AlexMessage";

    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);

        return CrimeFragment.newInstance(crimeId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "CrimeActivity onPause");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "CrimeActivity onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "CrimeActivity onRestart");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "CrimeActivity onDestroy");
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "CrimeActivity onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"CrimeActivity onRestoreInstanceState");
    }
}
