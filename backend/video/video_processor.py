import os
import subprocess

class VideoProcessor:
    def crop_and_cut_vertical(self, input_path: str, output_path: str, start_time: float, end_time: float) -> bool:
        duration = end_time - start_time
        # FFmpeg filter to crop video to 9:16 vertical ratio (1080x1920)
        filter_str = "crop=ih*(9/16):ih,scale=1080:1920"
        cmd = [
            "ffmpeg", "-y",
            "-ss", str(start_time),
            "-i", input_path,
            "-t", str(duration),
            "-vf", filter_str,
            "-c:v", "libx264",
            "-c:a", "aac",
            output_path
        ]
        try:
            subprocess.run(cmd, check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
            return True
        except Exception as e:
            print(f"FFmpeg crop error: {e}")
            return False
