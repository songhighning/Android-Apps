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

    public int getDuration(Context c){
        return MediaPlayer.create(c, R.raw.one_small_step).getDuration();
    }

    public int getCurrentPosition(){
        if(mPlayer == null){
            return 0;}
        else{
                return mPlayer.getCurrentPosition();
            }
    }

    public boolean isPlayerStopped(){
        return mPlayer == null;
    }

    public void seekTo(Context c, int adjustedPosition){
        if(mPlayer == null){
            mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
        }

        mPlayer.seekTo(adjustedPosition);
    }



    public void play (Context c){
        //prevents multiple instances of MediaPlayer
        if(mPlayer == null){
             mPlayer = MediaPlayer.create(c, R.raw.one_small_step);

             mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                 public void onCompletion(MediaPlayer mp) {
                     stop();
                 }
             });
            mPlayer.start();
        }

        else{
            if(mPlayer.isPlaying()){
                mPlayer.pause();
            }

            else{
                mPlayer.start();
            }

        }
    }

}
