package com.aiautoshorts.editor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aiautoshorts.editor.api.ApiClient;
import com.aiautoshorts.editor.models.AnalysisResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnalysisActivity extends AppCompatActivity {

    private TextView tvStatusTitle;
    private TextView tvStatusDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        tvStatusTitle = findViewById(R.id.tv_status_title);
        tvStatusDesc = findViewById(R.id.tv_status_desc);

        String filePath = getIntent().getStringExtra("FILE_PATH");
        String videoUrl = getIntent().getStringExtra("VIDEO_URL");

        if (filePath != null) {
            uploadAndAnalyzeFile(new File(filePath));
        } else if (videoUrl != null) {
            analyzeUrl(videoUrl);
        } else {
            Toast.makeText(this, "No video source provided", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void uploadAndAnalyzeFile(File file) {
        tvStatusTitle.setText("Uploading Video...");
        tvStatusDesc.setText("Sending file to AI Backend Server...");

        RequestBody requestFile = RequestBody.create(MediaType.parse("video/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        ApiClient.getService().uploadVideo(body).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String serverPath = response.body().get("file_path");
                    requestAnalysis(serverPath);
                } else {
                    handleError("Upload failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                handleError("Network error: " + t.getMessage());
            }
        });
    }

    private void analyzeUrl(String url) {
        requestAnalysis(url);
    }

    private void requestAnalysis(String videoSource) {
        tvStatusTitle.setText("Analyzing Video with AI...");
        tvStatusDesc.setText("Running Whisper transcription & Scene Detection...");

        Map<String, String> request = new HashMap<>();
        request.put("video_source", videoSource);

        ApiClient.getService().analyzeVideo(request).enqueue(new Callback<AnalysisResult>() {
            @Override
            public void onResponse(Call<AnalysisResult> call, Response<AnalysisResult> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AnalysisResult result = response.body();
                    Intent intent = new Intent(AnalysisActivity.this, EditorActivity.class);
                    intent.putExtra("ANALYSIS_RESULT", result);
                    startActivity(intent);
                    finish();
                } else {
                    handleError("Analysis failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AnalysisResult> call, Throwable t) {
                handleError("Analysis network error: " + t.getMessage());
            }
        });
    }

    private void handleError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        finish();
    }
}
