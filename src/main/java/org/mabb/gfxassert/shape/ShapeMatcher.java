package org.mabb.gfxassert.shape;

import org.hamcrest.TypeSafeMatcher;
import org.mabb.gfxassert.geom.ShapeSubset;

import java.awt.*;

import static org.mabb.gfxassert.geom.ShapeSubset.all;

public abstract class ShapeMatcher extends TypeSafeMatcher<Shape> {
    protected boolean exclude = false;
    protected final Shape target;
    protected ShapeSubset searchArea;

    protected ShapeMatcher(Shape target, ShapeSubset searchBox) {
        this.target = target;
        this.searchArea = searchBox;
    }

    protected ShapeMatcher(Shape target) {
        this.target = target;
        this.searchArea = all();
    }

    public ShapeMatcher in(ShapeSubset searchBox) {
        this.searchArea = searchBox;
        return this;
    }

    public ShapeMatcher notIn(ShapeSubset searchBox) {
        exclude = true;
        return in(searchBox);
    }
}
