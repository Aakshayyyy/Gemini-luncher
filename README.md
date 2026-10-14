# Gemini Custom Animation Launcher (Android / Kotlin)

This archive contains the complete Android project and all three Kotlin animation variants for launching the Google Gemini app (`com.google.android.apps.bard`) with a custom opening animation.

---

## Folder Structure

```text
GeminiCustomLauncher/
├── app/
│   ├── build.gradle.kts                  # App-level Gradle config (includes Lottie 6.4.0)
│   └── src/main/
│       ├── AndroidManifest.xml           # Includes <queries> permission for Gemini package
│       ├── java/com/example/geminilauncher/
│       │   └── MainActivity.kt           # Active MainActivity (Lottie Animation version by default)
│       └── res/
│           ├── raw/
│           │   └── intro_anim.json       # Sample 4-point Gemini sparkle Lottie animation
│           └── values/
│               └── themes.xml            # Transparent overlay & Dark screen themes
├── variants/                             # All 3 MainActivity.kt versions ready to swap in
│   ├── 1_MainActivity_IconAnimation.kt   # Pure Kotlin scale/spin animation (no external assets)
│   ├── 2_MainActivity_VideoMP4.kt        # Custom MP4 video splash screen using VideoView
│   └── 3_MainActivity_LottieJSON.kt      # Custom Lottie vector animation
├── .github/workflows/
│   └── android-build.yml                 # Cloud build script to compile APK on GitHub Actions
├── build.gradle.kts                      # Root Gradle configuration
├── settings.gradle.kts                   # Gradle repositories configuration
└── gradle.properties                     # AndroidX properties
```

---

## How to Switch Between the 3 Animation Styles

By default, `app/src/main/java/com/example/geminilauncher/MainActivity.kt` uses the **Lottie Animation** variant along with a pre-built `intro_anim.json` sparkle animation in `res/raw/`.

To switch to another variant:

### Option A: Pure Kotlin Icon Animation
1. Copy `variants/1_MainActivity_IconAnimation.kt` and overwrite `app/src/main/java/com/example/geminilauncher/MainActivity.kt`.
2. No external video or JSON files are required.

### Option B: Custom MP4 Video (`VideoView`)
1. Copy `variants/2_MainActivity_VideoMP4.kt` and overwrite `app/src/main/java/com/example/geminilauncher/MainActivity.kt`.
2. Place your custom video inside `app/src/main/res/raw/` and name it **`intro.mp4`** (all lowercase).

### Option C: Custom Lottie JSON Animation
1. Keep the default `MainActivity.kt` (or copy `variants/3_MainActivity_LottieJSON.kt`).
2. Replace `app/src/main/res/raw/intro_anim.json` with any Lottie `.json` animation downloaded from LottieFiles.

---

## How to Compile Into an APK

### Method 1: Build Online Without Installing Android Studio (GitHub Actions)
1. Create a free repository on GitHub and upload all files inside this folder.
2. Go to the **Actions** tab in your GitHub repository.
3. Select **Build Gemini Launcher APK** and click **Run workflow**.
4. Once finished (takes ~2 minutes), download **Gemini-Launcher-APK** from the Artifacts section at the bottom of the workflow summary page.

### Method 2: Build Locally in Android Studio
1. Extract this `.zip` file and open the folder in **Android Studio**.
2. Wait for Gradle Sync to finish.
3. Click **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
