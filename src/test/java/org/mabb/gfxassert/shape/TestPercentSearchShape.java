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

import static org.mabb.gfxassert.GfxAssertMatchers.containsShape;
import static org.mabb.gfxassert.geom.ShapeSubset.*;

public class TestPercentSearchShape {
    Rectangle2D.Double containingRect = new Rectangle2D.Double(0, 0, 100, 100);

    @Test
    public void givenRectInBottom10Percent_whenAssertInBottom10Percent_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 90, 10, 10);
        Assert.assertThat(containingRect, containsShape(targetRect).in(bottom(10).percent()));
    }
    
    @Test
    public void givenRectInBottom10Percent_whenAssertInBottom5Percent_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 90, 10, 10);
        Assert.assertThat(containingRect, containsShape(targetRect).notIn(bottom(5).percent()));
    }

    @Test
    public void givenRectInTop10Percent_whenAssertInTop10Percent_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 10, 10);
        Assert.assertThat(containingRect, containsShape(targetRect).in(top(10).percent()));
    }
    
    @Test
    public void givenRectInTop10Percent_whenAssertInTop5Percent_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 10, 10);
        Assert.assertThat(containingRect, containsShape(targetRect).notIn(top(5).percent()));
    }
}
