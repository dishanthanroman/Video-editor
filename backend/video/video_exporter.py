import os
import shutil

class VideoExporter:
    def export_final(self, processed_video_path: str, export_path: str) -> str:
        os.makedirs(os.path.dirname(export_path), exist_ok=True)
        shutil.copyfile(processed_video_path, export_path)
        return export_path
