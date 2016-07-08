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

    public void describeMismatchSafely(BufferedImage item, Description description) {
        description.appendText("was not inside ").appendText(searchArea.toString());
    }

    public void describeTo(Description description) {
        List<Color> colors = graphics.findAllColors(searchArea);

        description.appendText("only colors ").appendValue(formatColors(colors)).
                appendText(" inside ").appendValue(searchArea.toString()).
                appendText(" of target image.");
    }

    @Factory
    public static GraphicsMatcher containsOnlyColors(Color... colors) {
        return new GraphicsOnlyContainsColors(colors);
    }
}
