package com.aiautoshorts.editor;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aiautoshorts.editor.api.ApiClient;
import com.aiautoshorts.editor.models.VideoClip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExportActivity extends AppCompatActivity {

    private TextView tvStatus;
    private ProgressBar progressBar;
    private Button btnDownload;
    private VideoClip clip;
    private String serverVideoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);

        tvStatus = findViewById(R.id.tv_export_status);
        progressBar = findViewById(R.id.pb_export);
        btnDownload = findViewById(R.id.btn_download_export);

        clip = (VideoClip) getIntent().getSerializableExtra("CLIP_DATA");
        serverVideoPath = getIntent().getStringExtra("SERVER_VIDEO_PATH");

        startExportProcess();

        btnDownload.setOnClickListener(v -> finish());
    }

    private void startExportProcess() {
        tvStatus.setText("Rendering Short in 1080x1920 (9:16)...");

        Map<String, Object> requestParams = new HashMap<>();
        if (clip != null) {
            requestParams.put("clip_id", clip.getId());
            requestParams.put("start_time", clip.getStartTime());
            requestParams.put("end_time", clip.getEndTime());
        } else {
            requestParams.put("clip_id", "clip_default");
            requestParams.put("start_time", 0.0);
            requestParams.put("end_time", 30.0);
        }
        if (serverVideoPath != null) {
            requestParams.put("video_path", serverVideoPath);
        }

        ApiClient.getService().generateClips(requestParams).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String downloadClipId = clip != null && clip.getId() != null ? clip.getId() : "clip_default";
                    downloadRenderedFile(downloadClipId);
                } else {
                    tvStatus.setText("Export failed on server.");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                tvStatus.setText("Network error during rendering: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void downloadRenderedFile(String clipId) {
        tvStatus.setText("Downloading rendered Short...");

        ApiClient.getService().downloadClip(clipId).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean success = saveToDisk(response.body(), clipId + ".mp4");
                    if (success) {
                        tvStatus.setText("Export completed successfully! Saved to Movies.");
                        progressBar.setVisibility(View.GONE);
                        btnDownload.setVisibility(View.VISIBLE);
                    } else {
                        tvStatus.setText("Failed to save file to device storage.");
                        progressBar.setVisibility(View.GONE);
                    }
                } else {
                    tvStatus.setText("Download failed.");
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                tvStatus.setText("Download failed: " + t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private boolean saveToDisk(ResponseBody body, String filename) {
        try {
            File destination = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), filename);
            InputStream inputStream = body.byteStream();
            FileOutputStream outputStream = new FileOutputStream(destination);

            byte[] fileReader = new byte[4096];
            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
