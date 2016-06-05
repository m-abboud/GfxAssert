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

import static org.hamcrest.CoreMatchers.not;
import static org.mabb.gfxassert.GfxAssertMatchers.*;
import static org.mabb.gfxassert.geom.ShapeSubset.*;

public class TestContainsShape {
    Rectangle2D.Double containingRect = new Rectangle2D.Double(0, 0, 100, 100);
    
    @Test
    public void givenRectInBottomAreaOfContainingRect_whenAssertShapeInBottomArea_thenAssertIsTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 55, 30, 30);

        Assert.assertThat(containingRect, containsShape(targetRect).in(bottomArea()));
    }

    @Test
    public void givenRectInTopAreaOfContainingRect_whenAssertShapeInBottomArea_thenAssertIsFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 10, 10);

        Assert.assertThat(containingRect, not(containsShape(targetRect).in(bottomArea())));
    }

    @Test
    public void givenRectInTopAreaOfContainingRect_whenAssertShapeInTopArea_withNotInMethod_thenAssertIsFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 10, 10);

        Assert.assertThat(containingRect, containsShape(targetRect).notIn(bottomArea()));
    }

    @Test
    public void givenRectInTopAreaOfContainingRect_whenAssertShapeInTopArea_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 10, 10);

        Assert.assertThat(containingRect, containsShape(targetRect).in(topArea()));
    }

    @Test
    public void givenRectInBottomArea_whenAssertShapeInTopArea_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 55, 30, 30);

        Assert.assertThat(containingRect, containsShape(targetRect).notIn(topArea()));
    }

    @Test
    public void givenRectInLeftArea_whenAssertShapeInLeftArea_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 5, 5);

        Assert.assertThat(containingRect, containsShape(targetRect).in(leftArea()));
    }

    @Test
    public void givenRectInLeftArea_whenAssertShapeInRightArea_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 5, 5);

        Assert.assertThat(containingRect, containsShape(targetRect).notIn(rightArea()));
    }

    @Test
    public void givenRectInRightArea_whenAssertShapeInRightArea_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(55, 0, 5, 5);

        Assert.assertThat(containingRect, containsShape(targetRect).in(rightArea()));
    }

    @Test
    public void givenRectInRightArea_whenAssertShapeInLeftArea_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(55, 0, 5, 5);

        Assert.assertThat(containingRect, containsShape(targetRect).notIn(leftArea()));
    }

    @Test
    public void givenRectInTopRightArea_whenAssertShapeInTopRightArea_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(55, 0, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).in(topRightArea()));
    }

    @Test
    public void givenRectInTopRightArea_whenAssertShapeInBottomLeftArea_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(55, 0, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).notIn(bottomLeftArea()));
    }
    
    @Test
    public void givenRectInTopLeftArea_whenAssertShapeInTopLeftArea_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).in(topLeftArea()));
    }

    @Test
    public void givenRectInTopLeftArea_whenAssertShapeInBottomLeftArea_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 0, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).notIn(bottomRightArea()));
    }

    @Test
    public void givenRectInBottomRight_whenAssertShapeInBottomRightArea_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(55, 55, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).in(bottomRightArea()));
    }

    @Test
    public void givenRectInBottomRight_whenAssertShapeInTopLeft_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(55, 55, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).notIn(topLeftArea()));
    }
    
    @Test
    public void givenRectInBottomLeft_whenAssertShapeInBottomLeft_thenAssertTrue() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 55, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).in(bottomLeftArea()));
    }

    @Test
    public void givenRectInBottomLeft_whenAssertShapeInTopRight_thenAssertFalse() {
        Rectangle2D.Double targetRect = new Rectangle2D.Double(0, 55, 5, 5);
        Assert.assertThat(containingRect, containsShape(targetRect).notIn(topRightArea()));
    }

}
