# GfxAssert
A set of possibley useful Java (unit)test tools for geometric shapes and images.
Uses hamcrest matchers and alternate static assert methods.

# Basic Usage
##### Static import all shape matchers
```import static org.mabb.gfxassert.ShapeMatchers.*;
import static org.mabb.gfxassert.ShapeSearchArea.*;```

#### Asserting that a target shape is in a particular area of a containing shape
```Rectangle2D.Double containingRect = new Rectangle2D.Double(0, 0, 100, 100);
Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 55, 30, 30);

Assert.assertThat(containingRect, containsShape(targetRect).in(bottomArea()));```