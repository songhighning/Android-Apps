package com.songhighning.criminalintent;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.preference.DialogPreference;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * Created by Alex on 2016-04-20.
 */
public class CrimeFragment extends Fragment {

    public static final String EXTRA_CRIME_ID =
            "com.songhighning.android.criminalintent.crime_id";

    private static final String TAG = "AlexMessage";
    private static final String DIALOG_DATE = "date";

    private static final String DIALOG_TIME = "time";
    private static final int REQUEST_TIME = 0;
    private static final int REQUEST_DATE = 0;

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    //private Button mTimeButton;


    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID,crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

        UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

        setHasOptionsMenu(true);
    }


    private void updateDate(){
        mDateButton.setText(new SimpleDateFormat("EEEE, MMMM dd, yyyy. hh:mm a")
                .format(mCrime.getDate()));
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        //to inflate the fragment views
        View v = inflater.inflate(R.layout.fragment_crime,parent,false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            if(((AppCompatActivity)getActivity()).getSupportActionBar() != null){
                if(NavUtils.getParentActivityName(getActivity())!=null) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        updateDate();


        //mTimeButton = (Button)v.findViewById(R.id.time_button);


        // Dialog for user to pick to change Time or Date
        final String[] date_or_time = getResources().getStringArray(R.array.pick_date_time);
        mDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //FragmentManager fmMaster = getActivity().getSupportFragmentManager();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.date_time_Picker)
                        .setItems(R.array.pick_date_time, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "the which is " + which);
                                Log.i(TAG, "the Date_or_time 0 is " + date_or_time[0] +"&");
                                Log.i(TAG, "the Date_or_time 1 is " + date_or_time[1]+"&");
                                Log.i(TAG, "the picked item is " + date_or_time[which]);

                                if(date_or_time[which].equals("Date")){
                                    Log.i(TAG, " Date selected");
                                    FragmentManager fm = getActivity()
                                            .getSupportFragmentManager();

                                    DatePickerFragment dialogDate = DatePickerFragment.newInstance(mCrime.getDate());
                                    dialogDate.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                                    dialogDate.show(fm, DIALOG_DATE);
                                }

                                else{
                                    Log.i(TAG," Time selected");
                                    FragmentManager fm = getActivity()
                                            .getSupportFragmentManager();
                                    TimePickerFragment dialogTime = TimePickerFragment.newInstance(mCrime.getDate());
                                    dialogTime.setTargetFragment(CrimeFragment.this, REQUEST_TIME);
                                    dialogTime.show(fm, DIALOG_TIME);
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });





        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());



        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK) return;
        if(requestCode == REQUEST_DATE){
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        }

        else if (requestCode == REQUEST_TIME){
            Log.i(TAG," CrimeFragment onActivityResult Time_RE");
            Date date = (Date)data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mCrime.setDate(date);
            updateDate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                if(NavUtils.getParentActivityName(getActivity())!=null){
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}