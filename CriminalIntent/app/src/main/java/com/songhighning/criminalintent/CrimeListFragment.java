package com.songhighning.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Alex on 2016-04-27.
 */
public class CrimeListFragment extends ListFragment {
    private static final String TAG = "AlexMessage";
    private ArrayList<Crime> mCrimes;
    private static final int REQUEST_CRIME = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.crime_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        /*ArrayAdapter<Crime> adapter =
                new ArrayAdapter<Crime>(getActivity(),
                                        android.R.layout.simple_list_item_1,
                                        mCrimes);*/
        //setListAdapter method is a ListFragment convenience method
        // used to set the adapter of the implicit ListView managed by CrimeListFragment
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){

        //Crime c = (Crime)(getListAdapter()).getItem(position);
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getTitle() + "was clicked");

        //Start CrimePagerActivity
        Intent i = new Intent (getActivity(),CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivityForResult(i, REQUEST_CRIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CRIME){
            //HANDLE result
        }
    }

    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK,null);
    }


    private class CrimeAdapter extends ArrayAdapter<Crime>{

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //if no view, inflate one
            if (convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }

            // Configure the view for this Crime
            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(new SimpleDateFormat("EEEE, MMMM dd, yyyy. hh:mm a")
                    .format(c.getDate()));

            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater){
        //Code not required but as a matter of convention
        Log.i(TAG, " onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent i = new Intent (getActivity(),CrimePagerActivity.class);
                i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
                startActivityForResult(i, REQUEST_CRIME);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
