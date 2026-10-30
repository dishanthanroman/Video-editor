package com.aiautoshorts.editor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aiautoshorts.editor.api.ApiClient;

public class SettingsActivity extends AppCompatActivity {

    private EditText etServerUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        etServerUrl = findViewById(R.id.et_server_url);
        Button btnSave = findViewById(R.id.btn_save_settings);
        Button btnAbout = findViewById(R.id.btn_about_app);

        etServerUrl.setText(ApiClient.getBaseUrl());

        btnSave.setOnClickListener(v -> {
            String newUrl = etServerUrl.getText().toString().trim();
            if (!newUrl.isEmpty()) {
                ApiClient.setBaseUrl(newUrl);
                Toast.makeText(SettingsActivity.this, "Server URL updated!", Toast.LENGTH_SHORT).show();
            }
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}
