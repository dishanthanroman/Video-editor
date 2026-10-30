package com.aiautoshorts.editor.utils;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;

public class VideoUtils {

    public static long getVideoDuration(Context context, Uri uri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(context, uri);
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            retriever.release();
            return time != null ? Long.parseLong(time) : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String formatDuration(long durationMs) {
        long seconds = (durationMs / 1000) % 60;
        long minutes = (durationMs / (1000 * 60)) % 60;
        long hours = (durationMs / (1000 * 60 * 60));
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }
}
