package com.example.my_java_app_2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    AudioManager AudioManager;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AudioManager = (AudioManager)getSystemService(this.AUDIO_SERVICE);


        AudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        player = MediaPlayer.create(this , R.raw.audio);
        player.start();


        //Toast.makeText(this , "Volume : " + AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) , Toast.LENGTH_SHORT);



        //player = MediaPlayer.create(this , R.raw.audio);
        //player.start();

    }
    public void onclick (View view){
        TextView txt = findViewById(R.id.test);
        txt.setText("Volume " + AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    }

    public void onclick2 (View view){
        AudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        player = MediaPlayer.create(this , R.raw.audio);
        player.start();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                AudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}