package org.mabb.gfxassert.graphics;

import org.mabb.gfxassert.MultiTypeSafeMatcher;
import org.mabb.gfxassert.geom.ShapeSubsetDescriptor;

import java.awt.image.BufferedImage;

import static org.mabb.gfxassert.geom.ShapeSubsetDescriptor.all;

public abstract class GraphicsMatcher extends MultiTypeSafeMatcher<BufferedImage> {
    protected ShapeSubsetDescriptor searchArea;
    protected GfxAssertImage graphics;

    protected boolean exclude = false;

    protected GraphicsMatcher() {
        searchArea = all();
    }

    public GraphicsMatcher in(ShapeSubsetDescriptor searchBox) {
        this.searchArea = searchBox;
        return this;
    }

    public GraphicsMatcher notIn(ShapeSubsetDescriptor searchBox) {
        exclude = true;
        return in(searchBox);
    }

    protected BufferedImage convertToMainType(Object item) {
        throw new RuntimeException("Could not convert type");
    }

    protected Class[] expectedTypes() {
        return new Class[0];
    }
}
