![Android Kotlin](https://img.shields.io/badge/Android-Kotlin-6C3FD1.svg?style=flat&logo=android)
![Minimum SDK Version](https://img.shields.io/badge/MinSdk-21-blue)
[![Maven Central](https://img.shields.io/maven-central/v/com.akexorcist.adaptivescrolling/compose?color=blue&label=Maven%20Central)](https://central.sonatype.com/namespace/com.akexorcist.adaptivescrolling)
![Apache 2.0](https://img.shields.io/badge/License-Apache%202-blue)

# Adaptive Scrolling UI

Adaptive scrolling behavior that automatically adjusts based on content size. The layout will not be scrollable if the content's height is less than or equal to the container's height.

![Demo](images/image_001.gif)

## Usage

### Compose

Adaptive scrolling container for Jetpack Compose.

```kotlin
implementation("com.akexorcist.adaptivescrolling:compose:1.0.0")
```

```kotlin
@Composable
fun AdaptiveScrollContainerExample() {
    AdaptiveScrollContainer { isScrollable ->
        // Your content here
    }
}
```

See more detail in [Adaptive Scrolling - Compose](compose/README.md)

### View

Adaptive scrolling layout for Android Views.

```kotlin
implementation("com.akexorcist.adaptivescrolling:view:1.0.0")
```

```xml
<com.akexorcist.adaptivescrolling.view.AdaptiveVerticalScrollLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:avsl_showScrollbar="false"
    app:avsl_nonScrollableGravity="center"
    app:avsl_scrollableGravity="top|center_horizontal">

    <!-- Your content here -->

</com.akexorcist.adaptivescrolling.view.AdaptiveVerticalScrollLayout>
```

See more detail in [Adaptive Scrolling - View](view/README.md)

## License

This library is licensed under the [Apache License, Version 2.0](LICENSE).
