from typing import List, Dict, Any

class HighlightDetector:
    def find_candidates(self, transcript_data: dict, scenes: List[tuple]) -> List[Dict[str, Any]]:
        candidates = []
        segments = transcript_data.get("segments", [])

        if not segments:
            return [
                {
                    "id": "clip_1",
                    "title": "Highlight #1 - Key Speech Segment",
                    "start_time": 0.0,
                    "end_time": 30.0,
                    "text": "Key conversational segment automatically selected by AI."
                }
            ]

        for idx, seg in enumerate(segments):
            start = seg.get("start", 0.0)
            end = seg.get("end", start + 30.0)
            if (end - start) < 10.0:
                end = start + 30.0

            candidates.append({
                "id": f"clip_{idx + 1}",
                "title": f"Viral Moment #{idx + 1}",
                "start_time": start,
                "end_time": end,
                "text": seg.get("text", "")
            })

        return candidates
