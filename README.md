# Tailwind Compose UI

**Tailwind Compose UI** is a Jetpack Compose library that brings the power of Tailwind CSS-inspired components to Android development. It provides a collection of reusable, customizable UI components to help you build modern, responsive Android applications with ease.

## Features

- **Tailwind-inspired components**: Build UIs with familiar Tailwind CSS-like styling.
- **Jetpack Compose integration**: Fully compatible with Compose, Android's modern UI toolkit.
- **Customizable**: Easily tweak components to match your app's design.
- **Lightweight**: Minimal dependencies to keep your app lean.

## Installation

To use **Tailwind Compose UI** in your Android project, follow these steps:

### Step 1: Add the JitPack repository

Add the JitPack repository to your project's `settings.gradle` (or `build.gradle` at the project level):

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```

### Step 2: Add the dependency

Add the library dependency to your module's `build.gradle`:

```kotlin
dependencies {
    implementation "com.github.plelouch7:tailwind-prod:0.0.1"
}
```

Sync your project to fetch the library.

## Usage

Here’s an example of how to use a component from **Tailwind Compose UI** in your Jetpack Compose application:

```kotlin
import androidx.compose.runtime.Composable
import com.verimsolution.tailwind_prod.TailwindButton

@Composable
fun MyScreen() {
    TailwindButton(
        text = "Click Me",
        onClick = { /* Handle click */ },
        modifier = Modifier.padding(16.dp)
    )
}
```

### Available Components

- `TailwindButton`: A customizable button with Tailwind-inspired styles.
- `TailwindCard`: A card component for displaying content.
- *Add more components here based on your library's offerings*

For detailed documentation and additional examples, check the [API Reference](https://github.com/plelouch7/Tailwind-Compose-UI/wiki) (coming soon).

## Requirements

- **Min SDK**: 24
- **Compile SDK**: 35
- **Java Version**: 17
- **Jetpack Compose**: Compatible with Compose 1.x

## Building from Source

To build the library from source:

1. Clone the repository:
   ```bash
   git clone https://github.com/plelouch7/Tailwind-Compose-UI.git
   ```
2. Open the project in Android Studio.
3. Build the project using:
   ```bash
   ./gradlew build
   ```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes and commit (`git commit -m "Add your feature"`).
4. Push to your branch (`git push origin feature/your-feature`).
5. Open a Pull Request.

See [CONTRIBUTING.md](CONTRIBUTING.md) for more details.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.

## Contact

For questions or feedback, feel free to open an issue or contact the maintainer at [your.email@example.com](mailto\:your.email@example.com).

---

Built with ❤️ using Jetpack Compose.

