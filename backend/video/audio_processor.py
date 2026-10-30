import subprocess

class AudioProcessor:
    def extract_audio(self, video_path: str, output_audio_path: str) -> bool:
        cmd = [
            "ffmpeg", "-y",
            "-i", video_path,
            "-vn",
            "-acodec", "pcm_s16le",
            "-ar", "16000",
            "-ac", "1",
            output_audio_path
        ]
        try:
            subprocess.run(cmd, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            return True
        except Exception as e:
            print(f"Audio extraction error: {e}")
            return False

    def normalize_audio(self, video_path: str, output_path: str) -> bool:
        cmd = [
            "ffmpeg", "-y",
            "-i", video_path,
            "-af", "loudnorm=I=-16:TP=-1.5:LRA=11",
            "-c:v", "copy",
            output_path
        ]
        try:
            subprocess.run(cmd, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            return True
        except Exception as e:
            print(f"Audio normalization error: {e}")
            return False
