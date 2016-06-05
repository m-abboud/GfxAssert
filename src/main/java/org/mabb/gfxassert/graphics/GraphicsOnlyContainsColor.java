package org.mabb.gfxassert.graphics;

import org.hamcrest.Description;
import org.hamcrest.Factory;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphicsOnlyContainsColor extends GraphicsMatcher{
    protected Color findColor;

    protected GraphicsOnlyContainsColor(Color color) {
        super();
        this.findColor = color;
    }

    public boolean matchesSafely(BufferedImage item) {
        return this.search(item);
    }

    boolean search(BufferedImage image) {
        graphics = new GfxAssertImage(image);
        List<Color> colors = graphics.findAllColors(searchArea);
        boolean hasOnlyColor = colors.size() == 1 && colors.contains(findColor);

        return exclude != hasOnlyColor;
    }

    public void describeMismatchSafely(BufferedImage item, Description mismatchDescription) {
        mismatchDescription.appendText("was not inside ").appendText(searchArea.toString());
    }

    public void describeTo(Description description) {
        description.appendText("target graphics inside ").appendText(searchArea.toString()).
                appendText(" of container graphics, ").appendValue(graphics.findAllColors(searchArea));
    }

    @Factory
    public static GraphicsMatcher containsOnlyColor(Color color) {
        return new GraphicsOnlyContainsColor(color);
    }
}
