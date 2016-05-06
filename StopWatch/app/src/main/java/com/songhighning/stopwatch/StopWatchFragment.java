package com.songhighning.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

/**
 * Created by Alex on 2016-04-28.
 */
public class StopWatchFragment extends Fragment {
    private Button mStartStop;
    private Button mReset;
    private Chronometer mChronometer;
    private boolean isRunning = false;
    private long savedElapsedTime = 0;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(StopWatchActivity.TAG,"StopWatchFragment OnCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.i(StopWatchActivity.TAG,"StopWatchFragment OnCreateView");
        View v = inflater.inflate(R.layout.fragment_stop_watch,parent,false);
        mChronometer = (Chronometer)v.findViewById(R.id.WatchView);
        mStartStop = (Button)v.findViewById(R.id.StartButton);
        mReset = (Button)v.findViewById(R.id.StopButton);

        mStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StopWatchActivity.TAG, "StopWatchFragment StartButton OnClick");
                //something happens

                if(!isRunning) {
                    mChronometer.setBase(SystemClock.elapsedRealtime() - savedElapsedTime);
                    mChronometer.start();
                    isRunning = true;
                } else {
                    mChronometer.stop();
                    isRunning = false;
                    savedElapsedTime = SystemClock.elapsedRealtime() - mChronometer.getBase();
                }
            }
        });

        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StopWatchActivity.TAG, "StopWatchFragment StopButton OnClick");
                //something happens
                savedElapsedTime = 0;
                mChronometer.setBase(SystemClock.elapsedRealtime());
            }
        });

        return v;
    }
}
