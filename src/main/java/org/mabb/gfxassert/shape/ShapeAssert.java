package org.mabb.gfxassert.shape;

import org.junit.Assert;
import org.mabb.gfxassert.geom.ShapeSubsetDescriptor;

import java.awt.geom.Rectangle2D;

import static org.hamcrest.number.OrderingComparison.*;
import static org.mabb.gfxassert.geom.ShapeSubsetDescriptor.*;
import static org.mabb.gfxassert.shape.ShapeMatcher.containsShape;


public class ShapeAssert {
    public static void assertInBottomArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(bottomArea()));
    }

    public static void assertInTopArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(topArea()));
    }

    public static void assertInLeftArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(bottomArea()));
    }

    public static void assertInRightArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(rightArea()));
    }

    public static void assertNotOutsideOf(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container));
    }

    public static void assertInBottomRightCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(bottomRightArea()));
    }

    public static void assertInBottomLeftCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(bottomLeftArea()));
    }

    public static void assertInTopLeftCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(topLeftArea()));
    }

    public static void assertInTopRightCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        Assert.assertThat(container, containsShape(container).in(topRightArea()));
    }
}
