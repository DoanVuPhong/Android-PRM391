package com.fpt.edu.tiengviet123;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.fpt.edu.tiengviet123.bean.VCharacter;

import java.io.IOException;
import java.util.List;

public class Utils {
    static MediaPlayer audio;

    static void playAudio(String text, List<VCharacter> list, Context context) {
        stop();
        int audioId = 0;
        for (int i = 0; i < list.size(); i++) {
            if (text.equalsIgnoreCase(list.get(i).getFace())) {
                audioId = list.get(i).getBaseAudioId();
            }else if(text.equalsIgnoreCase(list.get(i).getContent())){
                audioId = list.get(i).getExtentionAudioID();
            }
        }
        if (audioId != 0) {
            audio = MediaPlayer.create(context, audioId);
            if (audio.isPlaying()) {
                stop();
            }
            audio.start();
            audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer audio) {
                    stop();
                }
            });
        }
    }


    static void stop() {
        if (audio != null) {
            audio.reset();
            audio.release();
            audio = null;
        }
    }


}
