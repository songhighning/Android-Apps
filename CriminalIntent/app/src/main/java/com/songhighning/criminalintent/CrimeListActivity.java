package com.songhighning.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by Alex on 2016-04-28.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
