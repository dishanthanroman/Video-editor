package com.aiautoshorts.editor.models;

import java.io.Serializable;

public class VideoClip implements Serializable {
    private String id;
    private String projectId;
    private String title;
    private double startTime;
    private double endTime;
    private double score;
    private String downloadUrl;
    private String subtitleText;

    public VideoClip() {}

    public VideoClip(String id, String title, double startTime, double endTime, double score) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getStartTime() { return startTime; }
    public void setStartTime(double startTime) { this.startTime = startTime; }

    public double getEndTime() { return endTime; }
    public void setEndTime(double endTime) { this.endTime = endTime; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }

    public String getSubtitleText() { return subtitleText; }
    public void setSubtitleText(String subtitleText) { this.subtitleText = subtitleText; }
}
