# Car Racing Game - JavaFX

## Overview
This is a simple 2D **Car Racing Game** developed in Java using JavaFX. The objective is to avoid obstacles for as long as possible while keeping track of survival time. The game includes a leaderboard system that stores the top 5 longest survival times.

## Requirements
- Java Development Kit (JDK) 11 or later
- JavaFX SDK
- An IDE that supports JavaFX (IntelliJ IDEA, Eclipse, or VS Code)
- The following assets (included in the project directory under `/images/`):
  - `playercar.png`
  - `obstaclecar.png`
  - `highway.png`
  - `gameicon.png`

## How to Run

### 1. Setup JavaFX
Make sure JavaFX is properly configured in your development environment:
- IntelliJ IDEA**: Add JavaFX SDK to Project Structure → Libraries.
- Eclipse: Install the e(fx)clipse plugin and configure JavaFX in the build path.
- VS Code: Use the Java Extension Pack and configure `launch.json` with JavaFX runtime.

### 2. Compile and Run
1. Open the project in your preferred IDE.
2. Ensure the images are in the `/images/` folder inside the `resources` directory.
3. Compile and run `Main.java`.
4. The game will launch with a **loading screen**, followed by the main gameplay.

## Controls
- Left Arrow (`←`): Move car left
- Right Arrow (`→`): Move car right

## Features
- Randomly spawning obstacles**
- Leaderboard** that saves the top 5 times
- Restart button** after crashing
- Simple animations** using JavaFX

## Troubleshooting
- If images are not loading, ensure the `/images/` folder is inside `resources`.
- If JavaFX errors occur, verify that the SDK is correctly set up in your IDE.

## Future Improvements
- Adding sound effects and background music.
- Increasing difficulty as time progresses.
- Power-ups or additional mechanics to enhance gameplay.



