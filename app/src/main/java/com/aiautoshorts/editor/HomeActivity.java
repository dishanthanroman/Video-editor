package com.aiautoshorts.editor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiautoshorts.editor.adapters.ProjectAdapter;
import com.aiautoshorts.editor.models.Project;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.card_import_local).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ImportActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.card_import_youtube).setOnClickListener(v -> showUrlImportDialog("YouTube"));

        findViewById(R.id.card_import_drive).setOnClickListener(v -> showUrlImportDialog("Google Drive"));

        findViewById(R.id.card_auto_generate).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ImportActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_settings).setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Initialize Recent Projects RecyclerView
        RecyclerView rvRecentProjects = findViewById(R.id.rv_recent_projects);
        rvRecentProjects.setLayoutManager(new LinearLayoutManager(this));

        List<Project> sampleProjects = new ArrayList<>();
        sampleProjects.add(new Project("p1", "Sample Long Video Podcast.mp4", ""));
        sampleProjects.add(new Project("p2", "Product Review 4K.mp4", ""));

        ProjectAdapter adapter = new ProjectAdapter(sampleProjects);
        rvRecentProjects.setAdapter(adapter);
    }

    private void showUrlImportDialog(String source) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_import_url, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        EditText etUrl = view.findViewById(R.id.et_url_input);
        Button btnImport = view.findViewById(R.id.btn_import_url);
        Button btnCancel = view.findViewById(R.id.btn_cancel_dialog);

        etUrl.setHint("Enter " + source + " video URL");

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnImport.setOnClickListener(v -> {
            String url = etUrl.getText().toString().trim();
            if (url.isEmpty()) {
                Toast.makeText(HomeActivity.this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
            } else {
                dialog.dismiss();
                Intent intent = new Intent(HomeActivity.this, AnalysisActivity.class);
                intent.putExtra("VIDEO_URL", url);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}
