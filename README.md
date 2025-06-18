# CrossyRoad

A fun and engaging Android game inspired by the classic Crossy Road, where players navigate a character through traffic and water obstacles to reach the goal.

## 📖 Description

CrossyRoad is an Android-based mobile game that challenges players to safely guide their character across busy roads and rivers. Players must avoid oncoming vehicles, use floating logs to cross water, and reach the finish line while collecting points and maintaining their health.

**Who it's for:** Mobile game enthusiasts, Android developers, and anyone who enjoys casual arcade-style games.

## ✨ Features

- **🎮 Intuitive Touch Controls** - Tap above, below, left, or right of your character to move in that direction
- **🚗 Dynamic Obstacles** - Multiple vehicles with different speeds and patterns
- **❤️ Health System** - Three lives with visual health indicators
- **🏆 Scoring System** - Points awarded for progress and survival
- **🎯 Win Condition** - Reach the goal area to complete the level
- **📱 Responsive Design** - Optimized for various Android screen sizes
- **🎨 Smooth Animations** - Animated logo and character sprites
- **📊 Score Tracking** - Tracks both current and highest scores

## 🛠️ Tech Stack

- **Language:** Java 8
- **Platform:** Android (API 30+)
- **Framework:** Android SDK
- **UI Components:** 
  - SurfaceView for game rendering
  - AnimationDrawable for sprite animations
  - ConstraintLayout for UI layouts
- **Testing:** JUnit 4, Espresso, AndroidJUnitRunner
- **Build System:** Gradle
- **Minimum SDK:** API 30 (Android 11)
- **Target SDK:** API 33 (Android 13)

## 📱 Installation

### Prerequisites
- Android Studio (latest version recommended)
- Android SDK API 30 or higher
- Java Development Kit (JDK) 8 or higher

### Setup Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/CrossyRoad.git
   cd CrossyRoad
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the CrossyRoad folder and select it

3. **Sync Gradle**
   - Wait for Android Studio to sync the project
   - If prompted, update Gradle wrapper version

4. **Build and Run**
   - Connect an Android device or start an emulator (API 30+)
   - Click the "Run" button (green play icon) or press `Shift + F10`
   - Select your target device and wait for the app to install

### Alternative: Command Line Build
```bash
# Navigate to project directory
cd CrossyRoad

# Build the project
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```


### Dependencies
Key dependencies are managed in `app/build.gradle`:
- AndroidX AppCompat for UI compatibility
- Material Design components
- ConstraintLayout for responsive layouts
- Testing frameworks (JUnit, Espresso)

### Customization
- **Character Sprites**: Replace images in `res/drawable/` folder
- **Game Speed**: Modify velocity values in `GameView.java`
- **Difficulty**: Adjust obstacle patterns and speeds
- **Scoring**: Modify point values in `Player.java`

## 📁 Folder Structure

```
CrossyRoad/
├── app/                          # Main application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/crossyroad/
│   │   │   │   ├── MainActivity.java      # App entry point & start screen
│   │   │   │   ├── GameView.java          # Main game rendering & logic
│   │   │   │   ├── Player.java            # Player character & movement
│   │   │   │   ├── Obstacle.java          # Vehicle & log obstacles
│   │   │   │   ├── GameLoop.java          # Game loop & timing
│   │   │   │   ├── Screen2.java           # Character selection screen
│   │   │   │   ├── Screen3.java           # Difficulty selection
│   │   │   │   ├── GameOverScreen.java    # Game over handling
│   │   │   │   └── WinScreen.java         # Victory screen
│   │   │   ├── res/
│   │   │   │   ├── drawable/              # Game sprites & animations
│   │   │   │   ├── layout/                # UI layout files
│   │   │   │   ├── values/                # App strings & themes
│   │   │   │   └── xml/                   # Configuration files
│   │   │   └── AndroidManifest.xml        # App manifest
│   │   ├── test/                          # Unit tests
│   │   │   └── java/com/example/crossyroad/
│   │   │       ├── Sprint2Tests.java      # Sprint 2 test suite
│   │   │       ├── Sprint3Tests.java      # Sprint 3 test suite
│   │   │       ├── Sprint4Tests.java      # Sprint 4 test suite
│   │   │       └── Sprint5Tests.java      # Sprint 5 test suite
│   │   └── androidTest/                   # Instrumented tests
│   └── build.gradle                       # App-level build config
├── build.gradle                          # Project-level build config
├── gradle/                               # Gradle wrapper files
├── gradlew                               # Gradle wrapper script (Unix)
├── gradlew.bat                           # Gradle wrapper script (Windows)
└── settings.gradle                       # Project settings
```

### Key Files Explained
- **MainActivity.java**: Handles the animated start screen and navigation
- **GameView.java**: Core game engine with collision detection and rendering
- **Player.java**: Player character logic, movement, and scoring
- **Obstacle.java**: Vehicle and log obstacle behavior
- **GameLoop.java**: Manages game timing and frame updates

## 🧪 Testing

The project includes comprehensive test suites for each development sprint:

```bash
# Run all unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew test --tests "com.example.crossyroad.Sprint5Tests"
```
