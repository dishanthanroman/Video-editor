import os

class SubtitleEngine:
    def generate_ass_subtitles(self, transcript_segments: list, output_ass_path: str) -> str:
        header = """[Script Info]
Title: AI Auto Shorts Subtitles
ScriptType: v4.00+
WrapStyle: 0
ScaledBorderAndShadow: yes
YCbCr Matrix: None

[V4+ Styles]
Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding
Style: Default,Arial,20,&H00FFFFFF,&H000000FF,&H00000000,&H80000000,-1,0,0,0,100,100,0,0,1,2,0,2,10,10,30,1

[Events]
Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
"""
        events = []
        for seg in transcript_segments:
            start_formatted = self._format_time(seg.get("start", 0))
            end_formatted = self._format_time(seg.get("end", 5))
            text = seg.get("text", "")
            events.append(f"Dialogue: 0,{start_formatted},{end_formatted},Default,,0,0,0,,{text}")

        content = header + "\n".join(events)
        with open(output_ass_path, "w", encoding="utf-8") as f:
            f.write(content)

        return output_ass_path

    def _format_time(self, seconds: float) -> str:
        hrs = int(seconds // 3600)
        mins = int((seconds % 3600) // 60)
        secs = int(seconds % 60)
        csecs = int((seconds % 1) * 100)
        return f"{hrs}:{mins:02d}:{secs:02d}.{csecs:02d}"
