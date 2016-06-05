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
