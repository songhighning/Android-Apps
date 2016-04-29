package com.songhighning.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alex on 2016-04-28.
 */
public class StopWatchFragment extends Fragment {
    private Button mStartButton, mStopButton;
    private int mFakeTime = 0;
    private TextView mWatchView;
    private Boolean mStopButtonPressed = false;
    private Handler mHandler;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mStopButtonPressed) {
                long seconds = (System.currentTimeMillis()) / 1000;
                mWatchView.setText(String.format("%02d:%02d", seconds / 60, seconds % 60));
                mHandler.postDelayed(mRunnable, 1000L);
            }
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(StopWatchActivity.TAG,"StopWatchFragment OnCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.i(StopWatchActivity.TAG,"StopWatchFragment OnCreateView");
        View v = inflater.inflate(R.layout.fragment_stop_watch,parent,false);
        mWatchView = (TextView)v.findViewById(R.id.WatchView);

        mStartButton = (Button)v.findViewById(R.id.StartButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StopWatchActivity.TAG, "StopWatchFragment StartButton OnClick");
                //something happens
                mStopButtonPressed = false;
                mHandler.postDelayed(mRunnable, 1000L);
            }
        });

        mStopButton = (Button)v.findViewById(R.id.StopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StopWatchActivity.TAG,"StopWatchFragment StopButton OnClick");
                //something happens
                mStopButtonPressed = true;
            }
        });




        return v;

    }
}
