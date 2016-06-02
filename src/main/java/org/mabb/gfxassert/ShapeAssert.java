package org.mabb.gfxassert;

import org.junit.Assert;

import java.awt.geom.Rectangle2D;

import static org.hamcrest.number.OrderingComparison.*;

// I wrote this for Pdf2Dom originally, todo convert to use the ShapeMatcher stuff instead
// this is a static assert method alternative to the hamcrest matchers
public class ShapeAssert {
    private static String areaAssertMessage = "Target object is not in %s area of containing rectangle";
    private static double delta = 10.0;

    public static void assertInBottomArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        String message = String.format(areaAssertMessage, "bottom");

        Assert.assertThat(message, target.y, greaterThan(container.getHeight() / 2));
        Assert.assertThat(message, target.y, lessThan(container.getHeight() + delta));
    }

    public static void assertInTopArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        String message = String.format(areaAssertMessage, "top");

        Assert.assertThat(message, target.y, lessThan(container.getHeight() / 2));
        Assert.assertThat(message, target.y, greaterThan(-delta));
    }

    public static void assertInLeftArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        String message = String.format(areaAssertMessage, "left");

        Assert.assertThat(message, target.x, lessThan(container.getWidth() / 2));
        Assert.assertThat(message, target.x, greaterThan(-delta));
    }

    public static void assertInRightArea(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        String message = String.format(areaAssertMessage, "right");

        Assert.assertThat(message, target.x, greaterThan(container.getWidth() / 2));
        Assert.assertThat(message, target.x, lessThan(container.getWidth() + delta));
    }

    public static void assertNotOutsideOf(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        String message = "Target rectangle is outside of container rectangle.";

        Assert.assertThat(message, target.x + target.width, lessThan(container.width + delta));
        Assert.assertThat(message, target.y + target.height, lessThan(container.height + delta));
    }

    public static void assertInBottomRightCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        assertNotOutsideOf(target, container);
        assertInRightArea(target, container);
        assertInBottomArea(target, container);
    }

    public static void assertInBottomLeftCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        assertNotOutsideOf(target, container);
        assertInLeftArea(target, container);
        assertInBottomArea(target, container);
    }

    public static void assertInTopLeftCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        assertNotOutsideOf(target, container);
        assertInLeftArea(target, container);
        assertInTopArea(target, container);
    }

    public static void assertInTopRightCorner(Rectangle2D.Double target, Rectangle2D.Double container)
    {
        assertNotOutsideOf(target, container);
        assertInTopArea(target, container);
        assertInRightArea(target, container);
    }
}
