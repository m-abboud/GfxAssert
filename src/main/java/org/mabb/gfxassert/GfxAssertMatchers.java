/*
 * Copyright (C) Matthew Abboud 2016
 *
 * GfxAssert is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GfxAssert is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GfxAssert. If not, see <http://www.gnu.org/licenses/>.
 */

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
