package com.aiautoshorts.editor.api;

import com.aiautoshorts.editor.models.AnalysisResult;
import com.aiautoshorts.editor.models.VideoClip;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    @Multipart
    @POST("upload")
    Call<Map<String, String>> uploadVideo(@Part MultipartBody.Part file);

    @POST("analyze")
    Call<AnalysisResult> analyzeVideo(@Body Map<String, String> body);

    @POST("generate")
    Call<Map<String, Object>> generateClips(@Body Map<String, Object> body);

    @GET("status/{job_id}")
    Call<Map<String, Object>> getStatus(@Path("job_id") String jobId);

    @GET("clips/{job_id}")
    Call<List<VideoClip>> getClips(@Path("job_id") String jobId);

    @GET("download/{clip_id}")
    Call<ResponseBody> downloadClip(@Path("clip_id") String clipId);

    @DELETE("project/{job_id}")
    Call<Map<String, String>> deleteProject(@Path("job_id") String jobId);
}
