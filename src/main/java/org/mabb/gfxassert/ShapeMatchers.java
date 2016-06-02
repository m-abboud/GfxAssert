package org.mabb.gfxassert;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * GfxAssert hamcrest static matcher methods. Use import static org.mabb.gfxassert.*
 */
public class ShapeMatchers {
    public static ShapeMatcher containsShape(Shape shape) {
        return ShapeMatcher.containsShape(shape);
    }

    public static ShapeMatcher containsRect(Rectangle2D rect) {
        return ShapeMatcher.containsShape(rect);
    }
}
