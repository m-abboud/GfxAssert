package org.mabb.gfxassert;

import org.mabb.gfxassert.graphics.*;
import org.mabb.gfxassert.shape.ContainsShape;
import org.mabb.gfxassert.shape.PartiallyContainsShape;
import org.mabb.gfxassert.shape.ShapeMatcher;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * GfxAssert hamcrest static matcher methods. Use import static org.mabb.gfxassert.*
 */
public class GfxAssertMatchers {
    public static ShapeMatcher containsShape(Shape shape) {
        return ContainsShape.containsShape(shape);
    }

    public static ShapeMatcher containsRect(Rectangle2D rect) {
        return ContainsShape.containsShape(rect);
    }

    public static ShapeMatcher partiallyContains(Shape shape) {
        return PartiallyContainsShape.partiallyContainsShape(shape);
    }

    public static GraphicsMatcher containsColor(Color color) {
        return GraphicsContainsColor.containsColor(color);
    }

    public static GraphicsMatcher containsColors(Color... color) {
        return GraphicsContainsColors.containsColors(color);
    }

    public static GraphicsMatcher containsOnlyColor(Color color) {
        return GraphicsOnlyContainsColor.containsOnlyColor(color);
    }

    public static GraphicsMatcher containsOnlyColors(Color... colors) {
        return GraphicsOnlyContainsColors.containsOnlyColors(colors);
    }

}
