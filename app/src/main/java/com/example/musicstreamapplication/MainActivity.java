package com.example.musicstreamapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SongCollection songCollection = new SongCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleSelection(View myView){
        String resourceId = com.example.musicstreamapplication.util.AppUtil.getResourceId(this, myView);

        Song selectedSong = songCollection.searchById(resourceId);

        com.example.musicstreamapplication.util.AppUtil.popMessage(this, "Streaming song: " + selectedSong.getTitle());

        sendDataToActivity(selectedSong);
    }

    public void isReallyOnline(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            com.example.musicstreamapplication.util.AppUtil.popMessage(getApplicationContext(), "Online");
        } else {
            com.example.musicstreamapplication.util.AppUtil.popMessage(getApplicationContext(), "Offline");
        }
    }

    public void sendDataToActivity(Song song) {
        Intent intent = new Intent(this, PlaySongActivity.class);
        intent.putExtra("id", song.getId());
        intent.putExtra("title", song.getTitle());
        intent.putExtra("artiste", song.getArtiste());
        intent.putExtra("fileLink", song.getFileLink());
        intent.putExtra("coverArt", song.getCoverArt());

        startActivity(intent);
    }
}
