# AdaptiveVerticalScrollContainer

A Jetpack Compose container that conditionally applies vertical scrolling to its content only when the content's height exceeds the container's available height. It also provides the scrollable state to its content, allowing for adaptive UI adjustments.

## Features

- **Conditional Scrolling** - Automatically enables or disables vertical scrolling based on content size.
- **Adaptive Content** - Provides a `isScrollable` boolean state to the content lambda, allowing you to dynamically adjust your UI. For example, you can change the `Arrangement` or `Alignment` of items when the content becomes scrollable.

## Public Methods

| Method                                                                  | Description                                             |
|-------------------------------------------------------------------------|---------------------------------------------------------|
| `AdaptiveVerticalScrollContainer`                                               | The main composable function for the container.         |

### Parameters

| Parameter             | Type                                                      | Description                                                                  |
|-----------------------|-----------------------------------------------------------|------------------------------------------------------------------------------|
| `modifier`            | `Modifier`                                                | The modifier to be applied to the layout.                                    |
| `scrollState`         | `ScrollState`                                             | State for observing and controlling the scroll position.                     |
| `scrollEnabled`       | `Boolean`                                                 | Enable or disable scrolling.                                                 |
| `flingBehavior`       | `FlingBehavior?`                                          | Fling behavior for scrolling.                                                |
| `reverseScrolling`    | `Boolean`                                                 | Reverse the direction of scrolling.                                          |
| `verticalArrangement` | `Arrangement.Vertical`                                    | Vertical arrangement of the content.                                         |
| `horizontalAlignment` | `Alignment.Horizontal`                                    | Horizontal alignment of the content.                                         |
| `content`             | `@Composable ColumnScope.(isScrollable: Boolean) -> Unit` | The content to be displayed inside the container, with the scrollable state. |

## Usage

### Composable

```kotlin
@Composable
fun MyScreen() {
    AdaptiveVerticalScrollContainer(
        modifier = Modifier.fillMaxSize(),
    ) { isScrollable ->
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = if (isScrollable) Arrangement.Top else Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Your content here
        }
    }
}
```

## License

Licensed under the Apache License, Version 2.0. See [LICENSE](../LICENSE) for details.
