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
