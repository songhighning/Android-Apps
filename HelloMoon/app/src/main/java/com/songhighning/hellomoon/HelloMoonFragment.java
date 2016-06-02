package com.songhighning.hellomoon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import android.os.Handler;

/**
 * Created by Alex on 2016-05-24.
 */
public class HelloMoonFragment extends Fragment {
    private AudioPlayer mPlayer = new AudioPlayer();
    private Button mPlayButton;
    private Button mStopButton;
    private boolean mplaying = false;
    private SeekBar mSeekBar;
    Handler seekHandler= new Handler();

    Runnable run= new Runnable() {
        @Override
        public void run() {
            audioFinishedUpdate();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState){
        Log.i(HelloMoonActivity.TAG, " HelloMoonFragment onCreateView");
        View v = inflater.inflate(R.layout.fragment_hello_moon,parent,false);

        mSeekBar = (SeekBar)v.findViewById(R.id.hellomoon_seekbar);
        mSeekBar.setMax(mPlayer.getDuration(getActivity()));

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    Log.i(HelloMoonActivity.TAG," seekTo " + progress);
                    mPlayer.seekTo(getActivity(),progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mPlayButton = (Button)v.findViewById(R.id.hellomoon_playButton);
        updatePlayButton();
        mPlayButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mPlayer.play(getActivity());
                mplaying = !mplaying;
                updatePlayButton();


            }
        });

        mStopButton = (Button)v.findViewById(R.id.hellomoon_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mPlayer.stop();
            }
        });
        audioFinishedUpdate();
        return v;
    }



    public void seekBarUpdate(){
        //Log.i(HelloMoonActivity.TAG," seekBarUpdate" );
        mSeekBar.setProgress(mPlayer.getCurrentPosition());
        //seekHandler.postDelayed(run,1000);
    }

    public void audioFinishedUpdate(){
        //Log.i(HelloMoonActivity.TAG," aduioFinishedUpdate" );
        if(mPlayer.isPlayerStopped()){
                //Log.i(HelloMoonActivity.TAG, " changiing playButton to "+R.string.hellomoon_play);
                mPlayButton.setText(R.string.hellomoon_play);
                mplaying = false;
         }
        seekBarUpdate();
        seekHandler.postDelayed(run, 1000);
    }

    private void updatePlayButton(){
        if(!mplaying){
            Log.i(HelloMoonActivity.TAG, " changiing playButton to " + R.string.hellomoon_play);
            mPlayButton.setText(R.string.hellomoon_play);
        }

        else{
            Log.i(HelloMoonActivity.TAG, " changiing playButton to " + R.string.hellomoon_pause);
            mPlayButton.setText(R.string.hellomoon_pause);
        }
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        mPlayer.stop();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
