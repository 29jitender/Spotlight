# Spotlight
Spotlight is a android library to onboard user by showcasing specfic features in the app.

#Screen
<img src="https://raw.githubusercontent.com/wooplr/Spotlight/master/art/intro.gif?token=AA5ZAHdvAspW6Zj8YyyKamkV7jWXFtMHks5XaQovwA%3D%3D"/>

#Usage
```java
 new SpotlightView.Builder(this)
                .setRevealAnimationDuration(400)
                .enableRevalAnimation(isRevealEnabled)
                .performClick(true)
                .fadinTextDuration(400)
                .headingTvColor(Color.parseColor("#eb273f"))
                .headingTvSize(32)
                .headingTvText("Love")
                .subHeadingTvColor(Color.parseColor("#ffffff"))
                .subHeadingTvSize(16)
                .subHeadingTvText("Like the picture?\nLet others know.")
                .setMaskColor(Color.parseColor("#dc000000"))
                .setTarget(view)
                .lineAnimDuration(400)
                .lineColor(Color.parseColor("#eb273f"))
                .lineStroke(4)
                .dismissOnTouch(true)
                .setUsageId(usageId)
                .show();
```

## Download
### Gradle

1. Add it in your root `build.gradle` at the end of repositories:

    ```javascript
    allprojects {
    	repositories {
    		...
    		maven { url "https://jitpack.io" }
    	}
    }
    ```

2. Add the dependency

    ```javascript
    dependencies {
        compile 'com.github.wooplr:Spotlight:1.1.1'
    }
    ```

### Maven

1. Add it in your `pom.xml` at the end of repositories:

    ```xml
    <repositories>
        ...
    	<repository>
    	    <id>jitpack.io</id>
    	    <url>https://jitpack.io</url>
    	</repository>
    </repositories>
    ```

2. Add the dependency

    ```xml
    <dependency>
        <groupId>com.github.wooplr</groupId>
        <artifactId>Spotlight</artifactId>
        <version>1.1.1</version>
    </dependency>
    ```

# Builder Methods

### maskColor(int)
Overlay Color

### target(View)
View to showcase

### introAnimationDuration(long)
Intro animation duration (For Reveal and Fadein)

### enableRevalAnimation(boolean)
Enable reval animation (Only for Lollipop and above)

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

### lineColor(int)
Color of the spotlight line

### lineAnimDuration(long)
Line animation duration

### lineStroke(int)
Stroke of line (in Dp)

### performClick(boolean)
Perform a click on target view

### usageId(String)
Unique id for each spotlight

### dismissOnTouch(boolean)
Dismiss spotlight on touch outside


#Author

[Jitender Chaudhary](https://github.com/29jitender)

## License
[Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt)
