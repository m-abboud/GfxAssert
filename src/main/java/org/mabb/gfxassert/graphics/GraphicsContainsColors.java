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
