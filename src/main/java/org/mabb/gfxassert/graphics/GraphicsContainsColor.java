package org.mabb.gfxassert.graphics;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.mabb.gfxassert.ShapeSearchArea;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.mabb.gfxassert.ShapeSearchArea.all;

public class GraphicsContainsColor extends TypeSafeMatcher<BufferedImage> {
    protected Color findColor;
    protected ShapeSearchArea searchArea;
    protected GfxAssertImage graphics;

    private boolean exclude = false;

    protected GraphicsContainsColor(Color color) {
        searchArea = all();
        this.findColor = color;
    }

    public boolean matchesSafely(BufferedImage item) {
        return this.search(item);
    }

    public void describeMismatchSafely(BufferedImage item, Description mismatchDescription) {
        mismatchDescription.appendText("was not inside ").appendText(searchArea.toString());
    }

    public void describeTo(Description description) {
        description.appendText("target graphics inside ").appendText(searchArea.toString()).
                appendText(" of container graphics, ").appendValue(graphics.findAllColors(searchArea));
    }

    public boolean search(BufferedImage image) {
        graphics = new GfxAssertImage(image);
        return exclude != graphics.contains(searchArea, findColor);
    }

    public GraphicsContainsColor in(ShapeSearchArea searchBox) {
        this.searchArea = searchBox;
        return this;
    }

    public GraphicsContainsColor notIn(ShapeSearchArea searchBox) {
        exclude = true;
        return in(searchBox);
    }

    @Factory
    public static GraphicsContainsColor containsColor(Color color) {
        return new GraphicsContainsColor(color);
    }
}
