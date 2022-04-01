package com.example.my_java_app_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

        TextView txtview2 = findViewById(R.id.textView2);
        txtview2.setText(android.os.Build.MODEL + " can upgrade");
        AudioManager = (AudioManager)getSystemService(this.AUDIO_SERVICE);


        AudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)-5, 0);
        player = MediaPlayer.create(this , R.raw.audio);
        player.start();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("notify" , "notify" , NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this , "notify")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("rickrolled")
                .setContentText("shhh don't spoil it to others :)")
                .setSmallIcon(R.raw.icon);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1 , builder.build());


        //Toast.makeText(this , "Volume : " + AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC) , Toast.LENGTH_SHORT);



        //player = MediaPlayer.create(this , R.raw.audio);
        //player.start();

    }
    public void onclick (View view){
        player.stop();

        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
        httpIntent.setData(Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));

        startActivity(httpIntent);


    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                AudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)-5, 0);
            default:
                return super.dispatchKeyEvent(event);
        }
    }
}

