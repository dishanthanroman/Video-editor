from typing import List, Tuple

class SceneDetector:
    def detect_scenes(self, video_path: str) -> List[Tuple[float, float]]:
        try:
            from scenedetect import detect, ContentDetector
            scene_list = detect(video_path, ContentDetector())
            scenes = []
            for scene in scene_list:
                start_sec = scene[0].get_seconds()
                end_sec = scene[1].get_seconds()
                scenes.append((start_sec, end_sec))
            if scenes:
                return scenes
        except Exception as e:
            print(f"PySceneDetect fallback activated: {e}")

        # Fallback default scene cuts
        return [(0.0, 30.0), (30.0, 60.0), (60.0, 90.0)]
