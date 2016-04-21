package com.songhighning.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
public class CrimeActivity extends FragmentActivity {
    protected static final String TAG = "AlexMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        Log.i(TAG, "CrimeActivity onCreate");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null){
            //when an activity is destroyed, fragmentManager saes out its list of fragments
            fragment = new CrimeFragment();
            fm.beginTransaction() //fluent interface, creates and returns an instance of FragmentTransaction
                    .add(R.id.fragmentContainer,fragment) //creates a fragment,
                    // R.id.fragmentContainer is used as a unique identifier for this fragment in the
                    //FragmentManager's list
                    .commit();  //commits the fragment

        }
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
