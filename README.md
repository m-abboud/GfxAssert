# GfxAssert
[![Build Status](https://travis-ci.org/m-abboud/GfxAssert.svg?branch=master)](https://travis-ci.org/m-abboud/GfxAssert)

GfxAssert is an experimental way of using graphical and gemoetric asserts in test code. If your program outputs an image or something that can be converted to an image you could use this library to assert that certain colors appear in certain areas to fuzzy assert a feature is working. 

A simple example of what could use this is a program that exports images with rectangles of various colors, to test that a green shape is indeed in the final image output in only the top half you can call 
```java
    assertThat(image, containsOnlyColor(Color.green).in(top(50).percent()));
```

This gives an advantage over something like comparing the image files and failing if they are different. If f we need to change our simple program to anti alias our green rectangles the compare files test will now be broken and must be changed. The containsColor test is more decoupled from the code so it needs to change less which is good. Good tests should not need to be changed when the feature they test is unchanged or implementation is changed.

A more real example would be document proccessing programs such as a PDF to HTML converter where the final output can change a lot while it's under development but certain finished features can be tested in the output by eeking them out with this. Consider a test that converts a PDF with a filled rectangle to HTML and is testing the filled rectangle color conversion feature. When a bug effecting rect alignment by a couple pixels is fixed a serialized compare test would break but a leanent graphical assert would still work saving time and effort, yay.

## Maven (On Maven Central)
    <dependencies>
		<dependency>
			<groupId>net.mabboud.gfxassert</groupId>
			<artifactId>GfxAssert</artifactId>
            <version>1.0.4</version>
		</dependency>
    </dependencies>


## Basic Usage
##### Static import all shape matchers
```java
    import static org.mabb.gfxassert.geom.ShapeSubsetDescriptor.*;
    import static org.mabb.gfxassert.GfxAssertMatchers.*;
```

#### Asserting that a color is in a target area of an image
```java
    BufferedImage image = ImageIO.read(new File("test-image-contains-colors.png"));

    Assert.assertThat(image, containsColor(Color.red).in(bottom(50).percent()));
```

#### Asserting that a target shape is in a particular area of a containing shape
```java
    Rectangle2D.Double containingRect = new Rectangle2D.Double(0, 0, 100, 100);
    Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 55, 30, 30);

    Assert.assertThat(containingRect, containsShape(targetRect).in(bottomArea()));
```
