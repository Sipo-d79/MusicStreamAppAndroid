package com.example.musicstreamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class PlaySongActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://p.scdn.co/mp3-preview/";

    private String songId = "";
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private String coverArt = "";
    private String url = "";

    private MediaPlayer player = null;
    private int musicPosition = 0;
    private Button btnPlayPause = null;

    private SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        retrieveData();
        displaySong(title, artiste, coverArt);
    }

    public void playPrevious(View view){
        Song prevSong = songCollection.getPrevSong(songId);
        if(prevSong != null){
            songId = prevSong.getId();
            title = prevSong.getTitle();
            artiste = prevSong.getArtiste();
            fileLink = prevSong.getFileLink();
            coverArt = prevSong.getCoverArt();

            url = BASE_URL + fileLink;
            displaySong(title, artiste, coverArt);
            stopActivities();
            playOrPauseMusic(view);
        }
    }

    public void playNext(View view){
        Song nextSong = songCollection.getNextSong(songId);
        if(nextSong != null){
            songId = nextSong.getId();
            title = nextSong.getTitle();
            artiste = nextSong.getArtiste();
            fileLink = nextSong.getFileLink();
            coverArt = nextSong.getCoverArt();

            url = BASE_URL + fileLink;
            displaySong(title, artiste, coverArt);
            stopActivities();
            playOrPauseMusic(view);
        }
    }

    private void stopActivities(){
        if(player == null)
        {
            preparePlayer();
        }
        player.stop();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        if(player == null) {

        }else {
            player.stop();
            player.release();
            player = null;
        }
        startActivity(intent);
    }

    public void playOrPauseMusic(View view){
        if(player == null){
            preparePlayer();
        }

        if(!player.isPlaying()){
            if(musicPosition > 0){
                player.seekTo(musicPosition);
            }
            player.start();
            btnPlayPause.setText("PAUSE");

            setTitle("Now playing: " + title + " - " + artiste);
            gracefullyStopWhenMusicEnds();
        }
        else {
            pauseMusic();
        }
    }

    private void gracefullyStopWhenMusicEnds(){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(player != null) {
                    btnPlayPause.setText("PLAY");
                    musicPosition = 0;
                    setTitle("");
                    player.stop();
                    player.release();
                    player = null;
                }
            }
        });
    }

    private void pauseMusic(){
        player.pause();
        musicPosition = player.getCurrentPosition();
        btnPlayPause.setText("PLAY");
    }

    private void preparePlayer(){
        player = new MediaPlayer();

        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            player.prepare();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void displaySong(String title, String artiste, String coverArt){
        TextView txtTitle = findViewById(R.id.txtSongTitle);
        txtTitle.setText(title);

        TextView txtArtiste = findViewById(R.id.txtArtiste);
        txtArtiste.setText(artiste);

        int imageId = com.example.musicstreamapplication.util.AppUtil.getImageIdFromDrawable(this, coverArt);
        ImageView ivCoverArt = findViewById(R.id.imgCoverArt);
        ivCoverArt.setImageResource(imageId);
    }

    private void retrieveData(){
        Bundle songData = this.getIntent().getExtras();
        songId = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        url = BASE_URL + fileLink;
    }

}
