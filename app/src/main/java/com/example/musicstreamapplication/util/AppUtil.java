package com.example.musicstreamapplication.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.musicstreamapplication.R;

public final class AppUtil
{
    public static void popMessage(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static String getResourceId(Context context, View view)
    {
        String id = context.getResources().getResourceEntryName(view.getId());

        return id;
    }

    public static String getResourceNameById(Context context, String id){
        String name = context.getResources().getResourceEntryName(Integer.parseInt(id));

        return name;
    }

    public static int getImageIdFromDrawable(Context context, String imageName)
    {
        int imageID = context.getResources().getIdentifier(imageName,"drawable", context.getPackageName());

        return imageID;
    }
}