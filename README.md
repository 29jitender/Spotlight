# THIS LIBRARY IS NO LONGER MAINTAINED. I suggest you check alternatives.

# Spotlight
Spotlight is an Android library used to onboard users by showcasing specific features in the app.

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
<img src="https://img.shields.io/badge/license-Apache 2.0-green.svg?style=flat">
[![API](https://img.shields.io/badge/API-11%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=11)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Spotlight-green.svg?style=flat)](http://android-arsenal.com/details/1/3730)

# Screen
<img src="https://raw.githubusercontent.com/wooplr/Spotlight/master/art/intro.gif?token=AA5ZAHdvAspW6Zj8YyyKamkV7jWXFtMHks5XaQovwA%3D%3D"/>

# Usage
```java
new SpotlightView.Builder(this)
        .introAnimationDuration(400)
        .enableRevealAnimation(isRevealEnabled)
        .performClick(true)
        .fadeinTextDuration(400)
        .headingTvColor(Color.parseColor("#eb273f"))
        .headingTvSize(32)
        .headingTvText("Love")
        .subHeadingTvColor(Color.parseColor("#ffffff"))
        .subHeadingTvSize(16)
        .subHeadingTvText("Like the picture?\nLet others know.")
        .maskColor(Color.parseColor("#dc000000"))
        .target(view)
        .lineAnimDuration(400)
        .lineAndArcColor(Color.parseColor("#eb273f"))
        .dismissOnTouch(true)
        .dismissOnBackPress(true)
        .enableDismissAfterShown(true)
        .usageId(usageId) //UNIQUE ID
        .show();
```

## Download
### Gradle

1. Define the jitpack remote Maven repository inside the repositories block of your root `build.gradle` file

    ```javascript
    allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
    ```

2. Add the Spotlight dependency

    ```javascript
    dependencies {
        ...
        implementation 'com.github.wooplr:Spotlight:1.2.3'
    }
    ```

### Maven

1. Define the jitpack remote Maven repository in your `pom.xml` at the end of repositories

    ```xml
    <repositories>
        ...
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
    ```

2. Add the Spotlight dependency

    ```xml
    <dependency>
        <groupId>com.github.wooplr</groupId>
        <artifactId>Spotlight</artifactId>
        <version>1.2.3</version>
    </dependency>
    ```

# Builder Methods

### maskColor(int)
Overlay Color

### target(View)
View to showcase

### introAnimationDuration(long)
Intro animation duration (For Reveal and Fadein)

### enableRevealAnimation(boolean)
Enable reveal animation (Only for Lollipop and above)

### fadeinTextDuration(long)
Fade in animation duration for spotlight text (Heading and Sub-heading)

### headingTvSize(int)
Size of heading text

### headingTvColor(int)
Color of heading text

### headingTvText(CharSequence)
Text to display in heading

### subHeadingTvSize(int)
Size of sub-heading text

### subHeadingTvColor(int)
Color of sub-heading text

### subHeadingTvText(CharSequence)
Text to display in sub-heading

### setTypeface(Typeface)
Custom font for text in spotlight view

### lineAndArcColor(int)
Color of the spotlight line

### lineAnimDuration(long)
Line animation duration

### performClick(boolean)
Perform a click on target view

### usageId(String)
Unique id for each spotlight

### dismissOnTouch(boolean)
Dismiss spotlight on touch outside

### enableDismissAfterShown(boolean)
Dismiss spotlight on touch outside after spotlight is completely visible

# Configuration Method
```java
//Create global config instance to reuse it
SpotlightConfig config = new SpotlightConfig();
config.isDismissOnTouch(true);
config.setLineAndArcColor(0xFFFFFFFF);
...
.setConfiguration(config)
```

# Author

[Jitender Chaudhary](https://github.com/29jitender)

# Proguard rules

```java
-keep class com.wooplr.spotlight.** { *; }
-keep interface com.wooplr.spotlight.**
-keep enum com.wooplr.spotlight.**
```

# Credits
[MaterialIntroView](https://github.com/iammert/MaterialIntroView)

[Rahul Khanna](https://www.linkedin.com/in/rahul-khanna-01705827)

[Suraj Barthy](https://dribbble.com/thesbdesign)

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
