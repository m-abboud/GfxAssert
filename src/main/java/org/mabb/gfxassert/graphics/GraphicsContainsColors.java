/*
 * Copyright (C) Matthew Abboud 2016
 *
 * GfxAssert is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GfxAssert is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GfxAssert. If not, see <http://www.gnu.org/licenses/>.
 */

package org.mabb.gfxassert.graphics;

import org.hamcrest.Description;
import org.mabb.gfxassert.MultiTypeSafeMatcher;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.mabb.gfxassert.graphics.GraphicsContainsColor.containsColor;

public class GraphicsContainsColors extends GraphicsMatcher {
    private final Color[] findColors;

    GraphicsContainsColors(Color[] colors) {
        super();
        this.findColors = colors;
    }

    boolean search(BufferedImage image) {
        graphics = new GfxAssertImage(image);
        List<Color> colors = graphics.findAllColors(searchArea);

        boolean hasOnlyColors = true;
        for (Color colorOn : findColors)
            hasOnlyColors = hasOnlyColors && colors.contains(colorOn);

        return exclude != hasOnlyColors;
    }

    protected boolean matchesSafely(BufferedImage item) {
        return this.search(item);
    }

    public void describeTo(Description description) {
        List<Color> colors = graphics.findAllColors(searchArea);

        description.appendText("colors ").appendValue(formatColors(colors)).
                appendText(" inside ").appendValue(searchArea.toString()).
                appendText(" of target image.");
    }

    protected void describeItemMismatch(Object item, Description description) {
        description.appendText("was not inside ").appendText(searchArea.toString());
    }

    public static GraphicsMatcher containsColors(Color[] colors) {
        return new GraphicsContainsColors(colors);
    }
}
