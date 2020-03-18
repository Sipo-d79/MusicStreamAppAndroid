package com.example.musicstreamapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.musicstreamapplication.util.AppUtil;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Song[] songs;

    DataAdapter(Context context, Song[] songs) {
        this.songs = songs;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);

        ImageButton button = (ImageButton)parent.findViewById(R.id.image);

        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buttonPosition = (int)v.getTag();

                String resourceId = com.example.musicstreamapplication.util.AppUtil.getResourceId(this, v);

                //String resourceIdButton = AppUtil.getResourceId(this, myView);
                //String resourceId = AppUtil.getResourceNameById(this, resourceIdButton);

                Song selectedSong = songCollection.searchByCoverArt(resourceId);

                com.example.musicstreamapplication.util.AppUtil.popMessage(this, "Streaming song: " + selectedSong.getTitle());

                sendDataToActivity(selectedSong);
            }
        });
        */
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Song song = songs[position];
        try {
            holder.imageButton.setImageResource(R.drawable.class.getField(song.getCoverArt()).getInt(null));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //holder.imageView.setImageResource(song.getCoverArt());
        holder.songName.setText(song.getTitle());
        holder.artiste.setText(song.getArtiste());
    }

    @Override
    public int getItemCount() {
        return songs.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageButton imageButton;
        final TextView songName, artiste;

        ViewHolder(View view) {
            super(view);
            imageButton = (ImageButton) view.findViewById(R.id.image);
            songName = (TextView) view.findViewById(R.id.txtTitle);
            artiste = (TextView) view.findViewById(R.id.txtArtiste);
        }
    }
}
