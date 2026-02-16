# AdaptiveVerticalScrollLayout

A compound Custom ViewGroup that combines `ScrollView` + `LinearLayout` with **adaptive behavior** that automatically adjusts based on content size.

## Features

- **Auto-adjust gravity** - Changes content gravity based on screen size
  - Content fits screen → Uses `nonScrollableGravity` (default: `center`)
  - Content exceeds screen → Uses `scrollableGravity` (default: `top|center_horizontal`)

- **Auto-adjust overScrollMode** - Automatically manages scroll behavior
  - Content fits → `OVER_SCROLL_NEVER`
  - Content exceeds → `OVER_SCROLL_ALWAYS`

## Custom Attributes

| Attribute                          | Type    | Default                  | Description                      |
|------------------------------------|---------|--------------------------|----------------------------------|
| `app:avsl_adaptiveBehaviorEnabled` | boolean | `true`                   | Enable/disable adaptive behavior |
| `app:avsl_nonScrollableGravity`    | flag    | `center`                 | Gravity when content fits screen |
| `app:avsl_scrollableGravity`       | flag    | `top\|center_horizontal` | Gravity when scrolling is needed |
| `app:avsl_showScrollbar`           | boolean | `true`                   | Show/hide scrollbar              |

### Gravity Flags

Available flags for `avsl_nonScrollableGravity` and `avsl_scrollableGravity`:

- `top`, `bottom`
- `center_vertical`, `center_horizontal`, `center`
- `start`, `end`

## Public Methods

| Method                                                                           | Description                                      |
|----------------------------------------------------------------------------------|--------------------------------------------------|
| `getContentLayout(): LinearLayout`                                               | Access internal LinearLayout to add/remove views |
| `isScrollable(): Boolean`                                                        | Get current scrollable state                     |
| `setOnScrollableStateChangeListener(listener: OnScrollableStateChangeListener?)` | Set a listener for scrollable state changes      |

### OnScrollableStateChangeListener

A simple interface to listen for scrollable state changes.

```kotlin
adaptiveVerticalScrollLayout.setOnScrollableStateChangeListener { isScrollable ->
    // Do something with the scrollable state
}
```

## Usage

### XML

```xml
<dev.akexorcist.adaptivescrolling.view.AdaptiveVerticalScrollLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:avsl_showScrollbar="false"
    app:avsl_nonScrollableGravity="center"
    app:avsl_scrollableGravity="top|center_horizontal">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Title" />

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click" />

</dev.akexorcist.adaptivescrolling.view.AdaptiveVerticalScrollLayout>
```

## License

Licensed under the Apache License, Version 2.0. See [LICENSE](../LICENSE) for details.
