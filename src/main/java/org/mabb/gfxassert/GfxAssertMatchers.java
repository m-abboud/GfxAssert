package org.mabb.gfxassert;

import org.mabb.gfxassert.graphics.GraphicsContainsColor;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * GfxAssert hamcrest static matcher methods. Use import static org.mabb.gfxassert.*
 */
public class GfxAssertMatchers {
    public static ShapeMatcher containsShape(Shape shape) {
        return ShapeMatcher.containsShape(shape);
    }

    public static ShapeMatcher containsRect(Rectangle2D rect) {
        return ShapeMatcher.containsShape(rect);
    }
    public static GraphicsContainsColor containsColor(Color color) {
        return GraphicsContainsColor.containsColor(color);
    }
}
