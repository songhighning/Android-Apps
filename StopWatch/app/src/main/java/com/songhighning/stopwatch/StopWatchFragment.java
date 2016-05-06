package com.songhighning.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
    private Button mStartStopButton, mResetButton;
    private TextView mWatchView;

    private Handler mHandler = new Handler();
    private long mStartTime = 0L;
    private boolean mIsWatchStopped = false;

    long mTimeInMilliSeconds = 0L;
    long mPreviousTimeElapsed = 0L;
    long mUpdatedTime = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(StopWatchActivity.TAG, "StopWatchFragment OnCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        Log.i(StopWatchActivity.TAG,"StopWatchFragment OnCreateView");
        View v = inflater.inflate(R.layout.fragment_stop_watch,parent,false);
        mWatchView = (TextView)v.findViewById(R.id.WatchView);
        mWatchView.setText(R.string.timer_Val);

        mStartStopButton = (Button)v.findViewById(R.id.StartButton);
        mStartStopButton.setText(R.string.Start);
        final float textSize = mStartStopButton.getTextSize();
        mStartStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StopWatchActivity.TAG, "StopWatchFragment StartButton OnClick");
                //something happens

                if (!mIsWatchStopped) {
                    mStartStopButton.setText(R.string.Stop);
                    mStartTime = SystemClock.uptimeMillis();
                    mHandler.postDelayed(updateTimerThread, 0);
                    mIsWatchStopped = !mIsWatchStopped;
                    mStartStopButton.setTextSize(textSize * 2);
                } else {
                    mStartStopButton.setText(R.string.Start);
                    mStartStopButton.setTextSize(textSize / 2.0F);
                    mIsWatchStopped = !mIsWatchStopped;
                    mPreviousTimeElapsed += mTimeInMilliSeconds;
                    mHandler.removeCallbacks(updateTimerThread);
                }
            }
        });

        mResetButton = (Button)v.findViewById(R.id.StopButton);
        mResetButton.setText(R.string.Reset);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(StopWatchActivity.TAG, "StopWatchFragment StopButton OnClick");
                //something happens

                mHandler.removeCallbacks(updateTimerThread);
                mWatchView.setText(R.string.timer_Val);
                mStartStopButton.setText(R.string.Start);
                mIsWatchStopped = false;
                mPreviousTimeElapsed = mUpdatedTime = mTimeInMilliSeconds = 0;

            }
        });


        return v;

    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            mTimeInMilliSeconds = SystemClock.uptimeMillis() - mStartTime;

            mUpdatedTime = mPreviousTimeElapsed + mTimeInMilliSeconds;

            int secs = (int)(mUpdatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int)(mUpdatedTime % 1000);
            mWatchView.setText("" + mins + ":"
                + String.format("%02d",secs) + ":"
                + String.format("%03d",milliseconds));
            mHandler.postDelayed(this,0);
        }
    };
}
