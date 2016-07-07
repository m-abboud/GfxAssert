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

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public class GraphicsOnlyContainsColor extends GraphicsMatcher {
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
        description.appendText("only color ").appendValue(formatColor(findColor)).
                appendText(" inside ").appendValue(searchArea.toString()).
                appendText(" of target image.");
    }

    @Factory
    public static GraphicsMatcher containsOnlyColor(Color color) {
        return new GraphicsOnlyContainsColor(color);
    }
}
