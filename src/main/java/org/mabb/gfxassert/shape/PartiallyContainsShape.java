package org.mabb.gfxassert.shape;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.mabb.gfxassert.geom.ShapeSubset;

import java.awt.*;

public class PartiallyContainsShape extends ShapeMatcher {
    protected PartiallyContainsShape(Shape target, ShapeSubset searchBox) {
        super(target, searchBox);
    }

    protected PartiallyContainsShape(Shape target) {
        super(target);
    }

    public boolean matchesSafely(Shape item) {
        return this.search(item);
    }

    public void describeMismatchSafely(Shape item, Description mismatchDescription) {
        mismatchDescription.appendText("was not inside ").appendText(searchArea.toString());
    }

    public void describeTo(Description description) {
        description.appendText("target shape inside ").appendText(searchArea.toString()).appendText(" of container shape, ").appendValue(target);
    }

    public boolean search(Shape container) {
        return exclude != searchArea.partiallyContains(target, container);
    }

    @Factory
    public static ShapeMatcher partiallyContainsShape(Shape shape) {
        return new PartiallyContainsShape(shape);
    }
}
