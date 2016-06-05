package org.mabb.gfxassert.graphics;

import org.hamcrest.Description;
import org.mabb.gfxassert.MultiTypeSafeMatcher;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.mabb.gfxassert.graphics.GraphicsContainsColor.containsColor;

public class GraphicsContainsColors extends GraphicsMatcher {
    List<GraphicsContainsColor> matchers = new ArrayList<GraphicsContainsColor>();

    GraphicsContainsColors(Color[] colors) {
        super();

        for (Color colorOn : colors)
            matchers.add((GraphicsContainsColor) containsColor(colorOn));
    }

    public void describeTo(Description description) {
        for (GraphicsContainsColor matcherOn : matchers)
            matcherOn.describeTo(description);
    }

    protected boolean matchesSafely(BufferedImage item) {
        boolean result = true;
        for (GraphicsContainsColor matcherOn : matchers)
            result = result && matcherOn.matchesSafely(item);

        return result;
    }

    public static GraphicsMatcher containsColors(Color[] colors) {
        return new GraphicsContainsColors(colors);
    }
}
