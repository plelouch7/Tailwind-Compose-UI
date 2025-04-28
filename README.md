# Tailwind Compose UI

**Tailwind Compose UI** is a Jetpack Compose library that brings Tailwind CSS–inspired components to Android development. It offers a collection of reusable, customizable UI elements to help you build modern, responsive Android applications with ease.

## Features

- **Tailwind-like styling**: Quickly style your components using familiar utility-based classes.
- **Jetpack Compose integration**: Seamlessly compatible with Android’s modern UI toolkit.
- **Highly customizable**: Tweak component properties to match your design requirements.
- **Lightweight**: Minimal dependencies ensure a lean app footprint.

## Installation

Add the library to your Android project by following these steps:

### 1. Add the JitPack repository
In your project-level `settings.gradle` (or `build.gradle`), include:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = "https://jitpack.io" }
    }
}
```

### 2. Add the Tailwind Compose UI dependency
In your module-level `build.gradle`:

```kotlin
dependencies {
    implementation("com.github.plelouch7:Tailwind-Compose-UI:0.0.1")
}
```

Sync the project to download the library.

## Usage

Use any Tailwind Compose UI component in your Composable functions. For example, to add a styled button:

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

- `TailwindButton` — a configurable button with utility-based styling.
- `TailwindCard` — a card layout component for grouping content.
- _More components coming soon!_

Refer to the [API Reference](https://github.com/plelouch7/Tailwind-Compose-UI/wiki) for full documentation and examples.

## Requirements

- **Minimum SDK**: 24
- **Compile SDK**: 35
- **Java**: 17 or higher
- **Jetpack Compose**: 1.x

## Building from Source

1. Clone the repository:
   ```bash
git clone https://github.com/plelouch7/Tailwind-Compose-UI.git
```
2. Open the project in Android Studio.
3. Run:
   ```bash
./gradlew build
```

## Contributing

Contributions are welcome! To contribute:

1. Fork this repository.
2. Create a new branch: `git checkout -b feature/your-feature`.
3. Commit your changes: `git commit -m "Add your feature"`.
4. Push to your branch: `git push origin feature/your-feature`.
5. Open a pull request.

See [CONTRIBUTING.md](CONTRIBUTING.md) for more details.

## License

This project is licensed under the Apache License 2.0. See [LICENSE](LICENSE) for details.

## Contact

For questions or feedback, open an issue or reach out at [your.email@example.com](mailto:your.email@example.com).

---

*Built with ❤️ using Jetpack Compose.*

