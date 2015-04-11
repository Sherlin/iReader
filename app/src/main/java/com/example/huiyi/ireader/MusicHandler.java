package com.example.huiyi.ireader;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by huiyi on 8/4/15.
 */
public class MusicHandler implements Parcelable
{
    private MediaPlayer mediaPlayer;
    private Context context;
    private int iVolume;

    private final static int INT_VOLUME_MAX = 100;
    private final static int INT_VOLUME_MIN = 0;
    private final static float FLOAT_VOLUME_MAX = 1;
    private final static float FLOAT_VOLUME_MIN = 0;

    public MusicHandler(Context context)
    {
        this.context = context;
    }

    public void load(String path, boolean looping)
    {
        mediaPlayer = MediaPlayer.create(context, Uri.fromFile(new File(path)));
        mediaPlayer.setLooping(looping);
    }

    public void load(int address, boolean looping)
    {
        mediaPlayer = MediaPlayer.create(context, address);
        mediaPlayer.setLooping(looping);
    }

    public void fadeIn(int fadeDuration)
    {
        //Set current volume, depending on fade or not
        if (fadeDuration > 0)
            iVolume = INT_VOLUME_MIN;
        else
            iVolume = INT_VOLUME_MAX;

        increaseVolume(0);

        //Play music
        if(!mediaPlayer.isPlaying()) mediaPlayer.start();

        //Start increasing volume in increments
        if(fadeDuration > 0)
        {
            final Timer timer = new Timer(true);
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    increaseVolume(1);
                    System.out.println("looping");
                    if (iVolume == INT_VOLUME_MAX)
                    {
                        timer.cancel();
                        timer.purge();
                    }
                }
            };

            // calculate delay, cannot be zero, set to 1 if zero
            int delay = fadeDuration/INT_VOLUME_MAX;
            if (delay == 0) delay = 1;

            timer.schedule(timerTask, delay, delay);
        }

        
    }



    public void fadeOut(int fadeDuration)
    {
        //Set current volume, depending on fade or not
        if (fadeDuration < 0)
            iVolume = INT_VOLUME_MIN;
        else
            iVolume = INT_VOLUME_MAX;

        decreaseVolume(0);

        //Play music
        if(!mediaPlayer.isPlaying()) mediaPlayer.start();

        //Start increasing volume in increments
        if(fadeDuration > 0)
        {
            final Timer timer = new Timer(true);
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    decreaseVolume(1);
                    System.out.println("looping");
                    if (iVolume == INT_VOLUME_MIN)
                    {
                        timer.cancel();
                        timer.purge();
                    }
                }
            };

            // calculate delay, cannot be zero, set to 1 if zero
            int delay = fadeDuration/INT_VOLUME_MAX;
            if (delay == 0) delay = 1;

            timer.schedule(timerTask, delay, delay);
        }
    }

    public void pause(int fadeDuration)
    {
        //Set current volume, depending on fade or not
        if (fadeDuration > 0)
            iVolume = INT_VOLUME_MAX;
        else
            iVolume = INT_VOLUME_MIN;

        increaseVolume(0);

        //Start increasing volume in increments
        if(fadeDuration > 0)
        {
            final Timer timer = new Timer(true);
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    increaseVolume(-1);
                    if (iVolume == INT_VOLUME_MIN)
                    {
                        //Pause music

                        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
                        timer.cancel();
                        timer.purge();
                    }
                }
            };

            // calculate delay, cannot be zero, set to 1 if zero
            int delay = fadeDuration/INT_VOLUME_MAX;
            if (delay == 0) delay = 1;

            timer.schedule(timerTask, delay, delay);
        }
    }

    private void decreaseVolume(int change)
    {
        //increment or decrement depending on type of fade
        iVolume = iVolume - change;

        //ensure iVolume within boundaries
        if (iVolume < INT_VOLUME_MIN)
            iVolume = INT_VOLUME_MIN;
        else if (iVolume > INT_VOLUME_MAX)
            iVolume = INT_VOLUME_MAX;

        //convert to float value
        float fVolume = 1 - ((float) Math.log(INT_VOLUME_MAX - iVolume) / (float) Math.log(INT_VOLUME_MAX));

        //ensure fVolume within boundaries
        if (fVolume < FLOAT_VOLUME_MIN)
            fVolume = FLOAT_VOLUME_MIN;
        else if (fVolume > FLOAT_VOLUME_MAX)
            fVolume = FLOAT_VOLUME_MAX;

        mediaPlayer.setVolume(fVolume, fVolume);

    }

    private void increaseVolume(int change)
    {
        //increment or decrement depending on type of fade
        iVolume = iVolume + change;

        //ensure iVolume within boundaries
        if (iVolume < INT_VOLUME_MIN)
            iVolume = INT_VOLUME_MIN;
        else if (iVolume > INT_VOLUME_MAX)
            iVolume = INT_VOLUME_MAX;

        //convert to float value
        float fVolume = 1 - ((float) Math.log(INT_VOLUME_MAX - iVolume) / (float) Math.log(INT_VOLUME_MAX));

        //ensure fVolume within boundaries
        if (fVolume < FLOAT_VOLUME_MIN)
            fVolume = FLOAT_VOLUME_MIN;
        else if (fVolume > FLOAT_VOLUME_MAX)
            fVolume = FLOAT_VOLUME_MAX;

        mediaPlayer.setVolume(fVolume, fVolume);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


    }

    public int getCurrentPosition()
    {
        return mediaPlayer.getCurrentPosition();
    }

    public void seekTo(int seek)
    {
        mediaPlayer.seekTo(seek);
    }

}
