package com.aiautoshorts.editor.models;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {
    private String id;
    private String title;
    private String originalVideoUrl;
    private String localFilePath;
    private long durationMs;
    private long sizeBytes;
    private List<VideoClip> generatedClips;

    public Project() {}

    public Project(String id, String title, String originalVideoUrl) {
        this.id = id;
        this.title = title;
        this.originalVideoUrl = originalVideoUrl;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getOriginalVideoUrl() { return originalVideoUrl; }
    public void setOriginalVideoUrl(String originalVideoUrl) { this.originalVideoUrl = originalVideoUrl; }

    public String getLocalFilePath() { return localFilePath; }
    public void setLocalFilePath(String localFilePath) { this.localFilePath = localFilePath; }

    public long getDurationMs() { return durationMs; }
    public void setDurationMs(long durationMs) { this.durationMs = durationMs; }

    public long getSizeBytes() { return sizeBytes; }
    public void setSizeBytes(long sizeBytes) { this.sizeBytes = sizeBytes; }

    public List<VideoClip> getGeneratedClips() { return generatedClips; }
    public void setGeneratedClips(List<VideoClip> generatedClips) { this.generatedClips = generatedClips; }
}
