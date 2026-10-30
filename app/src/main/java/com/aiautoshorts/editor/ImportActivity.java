package com.aiautoshorts.editor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.aiautoshorts.editor.utils.FileUtils;
import com.aiautoshorts.editor.utils.VideoUtils;

import java.io.File;

public class ImportActivity extends AppCompatActivity {

    private TextView tvFileInfo;
    private Button btnStartAnalysis;
    private Uri selectedVideoUri;

    private final ActivityResultLauncher<Intent> videoPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedVideoUri = result.getData().getData();
                    if (selectedVideoUri != null) {
                        displayVideoDetails(selectedVideoUri);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);

        tvFileInfo = findViewById(R.id.tv_selected_file_info);
        btnStartAnalysis = findViewById(R.id.btn_start_analysis);
        Button btnSelectFile = findViewById(R.id.btn_select_file);

        btnSelectFile.setOnClickListener(v -> openFilePicker());

        btnStartAnalysis.setOnClickListener(v -> {
            if (selectedVideoUri != null) {
                File file = FileUtils.getFileFromUri(this, selectedVideoUri);
                if (file != null) {
                    Intent intent = new Intent(ImportActivity.this, AnalysisActivity.class);
                    intent.putExtra("FILE_PATH", file.getAbsolutePath());
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Failed to load video file", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        videoPickerLauncher.launch(Intent.createChooser(intent, "Select Video"));
    }

    private void displayVideoDetails(Uri uri) {
        String fileName = FileUtils.getFileName(this, uri);
        long duration = VideoUtils.getVideoDuration(this, uri);
        String formattedDuration = VideoUtils.formatDuration(duration);

        tvFileInfo.setText(String.format("File: %s\nDuration: %s", fileName, formattedDuration));
        tvFileInfo.setVisibility(View.VISIBLE);
        btnStartAnalysis.setVisibility(View.VISIBLE);
    }
}
