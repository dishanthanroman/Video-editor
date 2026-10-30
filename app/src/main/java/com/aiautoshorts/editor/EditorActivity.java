package com.aiautoshorts.editor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.aiautoshorts.editor.models.AnalysisResult;
import com.aiautoshorts.editor.models.VideoClip;

public class EditorActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView tvClipTitle;
    private AnalysisResult analysisResult;
    private VideoClip currentClip;
    private String localVideoPath;
    private String serverVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        videoView = findViewById(R.id.video_preview);
        tvClipTitle = findViewById(R.id.tv_clip_title);
        Button btnEditSubtitles = findViewById(R.id.btn_edit_subtitles);
        Button btnExportClip = findViewById(R.id.btn_export_clip);

        analysisResult = (AnalysisResult) getIntent().getSerializableExtra("ANALYSIS_RESULT");
        localVideoPath = getIntent().getStringExtra("VIDEO_PATH");
        serverVideoPath = getIntent().getStringExtra("SERVER_VIDEO_PATH");

        if (analysisResult != null && analysisResult.getCandidateClips() != null && !analysisResult.getCandidateClips().isEmpty()) {
            currentClip = analysisResult.getCandidateClips().get(0);
            tvClipTitle.setText(currentClip.getTitle());
        } else {
            tvClipTitle.setText("AI Generated Short #1");
        }

        // Configure VideoView preview playback
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        if (currentClip != null && currentClip.getDownloadUrl() != null) {
            videoView.setVideoURI(Uri.parse(currentClip.getDownloadUrl()));
            videoView.start();
        } else if (localVideoPath != null) {
            videoView.setVideoPath(localVideoPath);
            videoView.start();
        }

        btnEditSubtitles.setOnClickListener(v -> {
            Intent intent = new Intent(EditorActivity.this, SubtitleActivity.class);
            if (currentClip != null) {
                intent.putExtra("CLIP_DATA", currentClip);
            }
            startActivity(intent);
        });

        btnExportClip.setOnClickListener(v -> {
            Intent intent = new Intent(EditorActivity.this, ExportActivity.class);
            if (currentClip != null) {
                intent.putExtra("CLIP_DATA", currentClip);
            }
            if (serverVideoPath != null) {
                intent.putExtra("SERVER_VIDEO_PATH", serverVideoPath);
            }
            startActivity(intent);
        });
    }
}
