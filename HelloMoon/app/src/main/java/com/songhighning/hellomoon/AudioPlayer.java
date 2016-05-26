package com.songhighning.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Alex on 2016-05-26.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;

    public void stop(){
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play (Context c){
        //prevents multiple instances of MediaPlayer
        stop();

        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mp){
                stop();
            }
        });
        mPlayer.start();
    }

}
