# Angry Birds Game Clone

## Overview

This project is a simplified clone of the popular **Angry Birds** game, developed using the **LibGDX** framework. It features a slingshot mechanic where players launch birds to destroy structures and defeat pigs across multiple levels. The game combines physics-based challenges with extendable mechanics, making it both entertaining and a great foundation for game development learning.

## Features

- **Bird Mechanics**:
    - Includes different bird types, each with unique abilities, such as splitting into multiple birds or causing explosive damage.
    - Bird abilities can be activated mid-flight by interacting with the screen.

- **Dynamic Levels**:
    - Each level is defined by specific arrangements of pigs and materials.
    - Materials like wood, glass, and iron have varying durability and behavior on collision.

- **State Management**:
    - Includes game states such as gameplay, winning, losing, and pause screens.
    - Allows seamless transitions between states.

- **Game Progression**:
    - Levels are cleared when all pigs are eliminated.
    - Players progress to subsequent levels or restart upon failure.

- **Serialization**:
    - Game progress is automatically saved and can be reloaded on reopening.
    - Serialization ensures the game state persists across sessions.

- **Customizable Framework**:
    - Easily extendable to add new bird types, materials, levels, or mechanics.

## Installation

### Prerequisites
- **Java Development Kit (JDK)**: Version 11 or higher.
- **Gradle**: Pre-installed or bundled with the project.
- **LibGDX Framework**: Automatically managed via Gradle dependencies.

### Steps
1. Clone or download the repository:
   ```bash
   git clone <repository_url>
   cd AngryBirds
   ```
2. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

## How to Run

### Running the Game
1. Use the Gradle `run` task:
   ```bash
   ./gradlew run
   ```

### Controls
- **Bird Launch**: Use the mouse to drag and release a bird from the slingshot.
- **Special Abilities**: Click during the bird’s flight to activate its ability (if applicable).
- **Pause/Resume**: Click the pause button to stop gameplay temporarily and resume later.

### Saving and Resuming
- The game automatically saves the current state, including progress and configurations.
- Reopening the game resumes from the last saved state.

## Directory Structure

- **`core/`**: Contains the main game logic and assets.
    - **`Birds/`**: Classes defining bird behaviors and abilities.
    - **`Materials/`**: Classes for game materials (e.g., wood, iron).
    - **`Pigs/`**: Classes representing pig entities.
    - **`Screens/`**: Game state and screen management (e.g., GameScreen, WinningScreen).

- **`lwjgl3/`**: The desktop launcher subproject.
    - Runs the game using LibGDX's LWJGL3 backend.

- **`assets/`**: Game resources, including textures, backgrounds, and sound effects.

- **`build.gradle`**: Gradle build configuration file for dependency and task management.

## Extending the Game

### Adding New Bird Types
1. Create a new class in the `Birds` directory extending the `Birds` base class.
2. Implement the `activateAbility` method to _modify_ the bird’s special behavior.
3. Add the new bird to the desired level in the `initializeBirds` method.

### Adding New Materials
1. Create a class in the `Materials` directory, extending the `Materials` base class.
2. Define properties such as texture, durability, and behavior upon collision.
3. Add the material to a level in the `initializeMaterials` method.

### Creating New Levels
1. Create a new class extending the `State` class (e.g., `Level3`).
2. Override methods such as `initializeBirds`, `initializePigs`, and `initializeMaterials` to design the level.
3. Add transitions to the new level in the game flow (e.g., after winning a previous level).

### Customizing Game Mechanics
- Modify gravity, collision handling, or other game physics in the `GameScreen` class.
- Extend or alter existing entities for unique gameplay experiences.

## Troubleshooting

### Common Issues
- **Gradle Build Errors**: Ensure you’re using Gradle version compatible with the project (e.g., Gradle 7+).
- **Texture Loading Errors**: Verify that asset paths are correct and assets exist in the `assets` folder.
- **Serialization Issues**: Ensure all transient fields are reinitialized after deserialization.

## Contributing

Feel free to contribute by reporting bugs, suggesting features, or submitting pull requests. Ensure code follows the existing style and structure.
