package org.mabb.gfxassert;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import java.awt.*;
import static org.mabb.gfxassert.ShapeSearchArea.all;

public class ShapeMatcher extends TypeSafeMatcher<Shape> {
    protected final Shape target;
    protected ShapeSearchArea searchArea;
    boolean exclude = false;

    protected ShapeMatcher(Shape target, ShapeSearchArea searchBox) {
        this.target = target;
        this.searchArea = searchBox;
    }

    protected ShapeMatcher(Shape target) {
        this.target = target;
        this.searchArea = all();
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

    public ShapeMatcher in(ShapeSearchArea searchBox) {
        this.searchArea = searchBox;
        return this;
    }

    public ShapeMatcher notIn(ShapeSearchArea searchBox) {
        exclude = true;
        return in(searchBox);
    }

    public boolean search(Shape container) {
        return exclude != searchArea.contains(target, container);
    }

    @Factory
    public static ShapeMatcher containsShape(Shape shape) {
        return new ShapeMatcher(shape);
    }
}
