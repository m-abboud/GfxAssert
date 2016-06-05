package org.mabb.gfxassert.graphics;

import org.hamcrest.Description;
import org.hamcrest.Factory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphicsOnlyContainsColors extends GraphicsMatcher {
    protected Color[] findColors;

    protected GraphicsOnlyContainsColors(Color[] color) {
        super();
        this.findColors = color;
    }

    public boolean matchesSafely(BufferedImage item) {
        return this.search(item);
    }

    boolean search(BufferedImage image) {
        graphics = new GfxAssertImage(image);
        List<Color> colors = graphics.findAllColors(searchArea);

        boolean hasOnlyColor = colors.size() == findColors.length;
        for (Color colorOn : findColors)
            hasOnlyColor = hasOnlyColor && colors.contains(colorOn);

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
    public static GraphicsMatcher containsOnlyColors(Color... colors) {
        return new GraphicsOnlyContainsColors(colors);
    }
}
