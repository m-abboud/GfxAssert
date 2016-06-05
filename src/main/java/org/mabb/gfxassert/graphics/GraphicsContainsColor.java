package org.mabb.gfxassert.graphics;

import org.hamcrest.Description;
import org.hamcrest.Factory;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GraphicsContainsColor extends GraphicsMatcher {
    protected Color findColor;

    protected GraphicsContainsColor(Color color) {
        super();
        this.findColor = color;
    }

    public boolean matchesSafely(BufferedImage item) {
        return this.search(item);
    }

    boolean search(BufferedImage image) {
        graphics = new GfxAssertImage(image);
        return exclude != graphics.contains(searchArea, findColor);
    }

    public void describeMismatchSafely(BufferedImage item, Description mismatchDescription) {
        mismatchDescription.appendText("was not inside ").appendText(searchArea.toString());
    }

    public void describeTo(Description description) {
        description.appendText("target graphics inside ").appendText(searchArea.toString()).
                appendText(" of container graphics, ").appendValue(graphics.findAllColors(searchArea));
    }

    @Factory
    public static GraphicsMatcher containsColor(Color color) {
        return new GraphicsContainsColor(color);
    }
}
