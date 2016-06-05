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

package org.mabb.gfxassert.shape;

import org.junit.Assert;

import java.awt.geom.Rectangle2D;

import static org.mabb.gfxassert.geom.ShapeSubset.*;
import static org.mabb.gfxassert.shape.ContainsShape.containsShape;


public class ShapeAssert {
    public static void assertInBottomArea(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(bottomArea()));
    }

    public static void assertInTopArea(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(topArea()));
    }

    public static void assertInLeftArea(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(bottomArea()));
    }

    public static void assertInRightArea(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(rightArea()));
    }

    public static void assertNotOutsideOf(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container));
    }

    public static void assertInBottomRightCorner(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(bottomRightArea()));
    }

    public static void assertInBottomLeftCorner(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(bottomLeftArea()));
    }

    public static void assertInTopLeftCorner(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(topLeftArea()));
    }

    public static void assertInTopRightCorner(Rectangle2D.Double target, Rectangle2D.Double container) {
        Assert.assertThat(container, containsShape(container).in(topRightArea()));
    }
}
