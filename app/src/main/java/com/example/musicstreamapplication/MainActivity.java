package com.example.musicstreamapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.musicstreamapplication.util.AppUtil;

public class MainActivity extends AppCompatActivity {

    private SongCollection songCollection = new SongCollection();
    private Song[] songs = new Song[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(this, songs);
        recyclerView.setAdapter(adapter);
    }

    public void handleSelection(View myView){
        String resourceId = com.example.musicstreamapplication.util.AppUtil.getResourceId(this, myView);

        //String resourceIdButton = AppUtil.getResourceId(this, myView);
        //String resourceId = AppUtil.getResourceNameById(this, resourceIdButton);

        Song selectedSong = songCollection.searchByCoverArt(resourceId);

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

    private void setInitialData()
    {
        songs[0] = new Song("s1001", "The Way You Look Tonight", "Michael Buble",
                "a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66, "michael_buble_collection");
        songs[1] = new Song("s1002", "Billie Jean", "Michael Jackson",
            "f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965",
            4.9, "billie_jean");
        songs[2] = new Song("s1003", "Смотри", "Полина Гагарина",
                "11bee4c2fdaeb87acf561a5d504a6cd1fdee45b8?cid=2afe87a64b0042dabf51f37318616965",
                3.51, "smotri_gagarina");
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
