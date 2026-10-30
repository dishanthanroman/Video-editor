package com.aiautoshorts.editor.models;

import java.io.Serializable;
import java.util.List;

public class AnalysisResult implements Serializable {
    private String jobId;
    private String status;
    private String transcript;
    private List<VideoClip> candidateClips;

    public AnalysisResult() {}

    public String getJobId() { return jobId; }
    public void setJobId(String jobId) { this.jobId = jobId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTranscript() { return transcript; }
    public void setTranscript(String transcript) { this.transcript = transcript; }

    public List<VideoClip> getCandidateClips() { return candidateClips; }
    public void setCandidateClips(List<VideoClip> candidateClips) { this.candidateClips = candidateClips; }
}
