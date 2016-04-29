package com.songhighning.homefoodeater;

import android.support.v4.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

import homefood.HomefoodMaker;
import homefood.HomefoodSystem;

/**
 * Created by Alex on 2016-04-14.
 */
public class MakersListFragment extends ListFragment {
    ArrayList<HomefoodMaker> mMakers;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.homefood_title);
        mMakers = HomefoodSystem.get(getActivity()).getMakers();
    }
}
