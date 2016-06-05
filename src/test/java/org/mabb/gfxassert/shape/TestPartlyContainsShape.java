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
import org.junit.Test;

import java.awt.geom.Rectangle2D;

import static org.hamcrest.core.IsNot.not;
import static org.mabb.gfxassert.GfxAssertMatchers.containsShape;
import static org.mabb.gfxassert.GfxAssertMatchers.partiallyContains;
import static org.mabb.gfxassert.geom.ShapeSubset.bottomArea;

public class TestPartlyContainsShape {
    Rectangle2D.Double containingRect = new Rectangle2D.Double(0, 0, 100, 100);

    @Test
    public void givenRectPartlyInBottomArea_whenAssertShapePartlyInBottomArea_thenTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 40, 30, 30);
        Assert.assertThat(containingRect, partiallyContains(targetRect).in(bottomArea()));
    }

    @Test
    public void givenRectInTopArea_whenAssertShapePartlyInBottomArea_thenFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 30, 30);
        Assert.assertThat(containingRect, not(partiallyContains(targetRect).in(bottomArea())));
    }

    @Test
    public void givenRectPartlyInContainer_whenAssertPartlyInContainer_thenTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 340, 355);
        Assert.assertThat(containingRect, partiallyContains(targetRect));
    }

    @Test
    public void givenRectNotInContainer_whenAssertPartlyInContainer_thenFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(400, 400, 5, 5);
        Assert.assertThat(containingRect, not(partiallyContains(targetRect)));
    }

    @Test
    public void givenRectPartlyInBottomArea_whenAssertFullyInBottomArea_thenFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 40, 30, 30);
        Assert.assertThat(containingRect, not(containsShape(targetRect).in(bottomArea())));
    }
}
