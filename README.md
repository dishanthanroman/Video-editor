# AI AUTO SHORTS – Long Video to Shorts AI Editor

**AI AUTO SHORTS** is a full-featured Android application and backend service that automatically converts long-form videos (YouTube, Google Drive, or Local Storage) into high-engaging vertical 9:16 Shorts, Reels, and TikTok clips.

---

## 📱 Features

- **Video Import:** Upload local MP4/MOV videos, paste YouTube URLs, or import Google Drive links.
- **AI-Powered Analysis:**
  - Whisper transcription & speech recognition.
  - Scene detection with PySceneDetect & OpenCV.
  - Smart viral moment detection and AI clip scoring.
- **Automatic Vertical Generator:**
  - Auto-crop videos to 9:16 (1080x1920 HD).
  - Audio normalization and precise lip-sync preservation.
  - Auto subtitle generation and styling.
- **Video Editor & Export:**
  - Custom subtitle editor.
  - Fast rendering and direct download to device storage.

---

## 🏗 Project Architecture

```
AI-Auto-Shorts/
├── .github/workflows/
│   └── build-apk.yml           # Automatic GitHub Actions compilation
├── app/                        # Native Android Application (Java, API 27+)
│   ├── src/main/java/com/aiautoshorts/editor/
│   └── src/main/res/           # Layouts, Dark Theme, Resources
├── backend/                    # Python FastAPI Backend
│   ├── main.py
│   ├── requirements.txt
│   ├── ai/                     # Whisper, PySceneDetect, Scoring, Subtitles
│   ├── video/                  # FFmpeg processing, Downloader, Exporter
│   └── storage/                # Google Drive & Local Storage
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🚀 How to Run the Backend (FastAPI)

1. **Install Dependencies:**
   ```bash
   pip install -r backend/requirements.txt
   ```
2. **Start Server:**
   ```bash
   uvicorn backend.main:app --host 0.0.0.0 --port 8000
   ```

---

## 📲 How to Upload & Build APK from Mobile Browser

1. Go to [GitHub.com](https://github.com) on your Android mobile browser and request **Desktop Site**.
2. Click **New Repository** and name it `AI-Auto-Shorts`.
3. Upload the repository files directly to GitHub.
4. GitHub Actions will automatically start building the APK under the **Actions** tab!
5. Once completed, download `AI-Auto-Shorts-Debug-APK.zip` or `AI-Auto-Shorts-Release-APK.zip` under **Artifacts** and install `app-debug.apk` or `app-release.apk` directly on your Android phone!
