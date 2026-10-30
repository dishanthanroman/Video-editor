package com.aiautoshorts.editor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aiautoshorts.editor.models.VideoClip;

public class SubtitleActivity extends AppCompatActivity {

    private EditText etSubtitleText;
    private VideoClip clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtitle_customization);

        etSubtitleText = findViewById(R.id.et_subtitle_text);
        Button btnSave = findViewById(R.id.btn_save_subtitle);

        clip = (VideoClip) getIntent().getSerializableExtra("CLIP_DATA");
        if (clip != null && clip.getSubtitleText() != null) {
            etSubtitleText.setText(clip.getSubtitleText());
        }

        btnSave.setOnClickListener(v -> {
            String updatedSubtitles = etSubtitleText.getText().toString();
            if (clip != null) {
                clip.setSubtitleText(updatedSubtitles);
            }
            Toast.makeText(SubtitleActivity.this, "Subtitles saved successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
