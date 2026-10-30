package com.aiautoshorts.editor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiautoshorts.editor.adapters.ProjectAdapter;
import com.aiautoshorts.editor.models.Project;

import java.util.ArrayList;
import java.util.List;

public class GeneratedShortsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generated_shorts);

        RecyclerView rvShorts = findViewById(R.id.rv_shorts_list);
        rvShorts.setLayoutManager(new LinearLayoutManager(this));

        List<Project> sampleShorts = new ArrayList<>();
        sampleShorts.add(new Project("1", "Viral Podcast Clip #1", ""));
        sampleShorts.add(new Project("2", "Tech Review Highlight", ""));

        ProjectAdapter adapter = new ProjectAdapter(sampleShorts);
        rvShorts.setAdapter(adapter);
    }
}
