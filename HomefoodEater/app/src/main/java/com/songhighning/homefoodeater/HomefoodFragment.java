package com.songhighning.homefoodeater;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import homefood.HomefoodMaker;

/**
 * Created by Alex on 2016-04-11.
 */
public class HomefoodFragment extends Fragment {
    private HomefoodMaker mHomefoodMaker;
    private EditText mTitleField;
    private Toast mToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomefoodMaker = new HomefoodMaker();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_homefood, parent, false);

        mTitleField = (EditText)v.findViewById(R.id.homefood_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //leave this blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHomefoodMaker.setShopName(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                //leave blank
                        if (mToast == null){
                    mToast = Toast.makeText(getActivity(),mHomefoodMaker.getShopName(), Toast.LENGTH_LONG);
                }
                //mToast.setText("Shop name: " + mHomefoodMaker.getShopName());
                //mToast.show();
            }
        });

        return v;
    }
}
