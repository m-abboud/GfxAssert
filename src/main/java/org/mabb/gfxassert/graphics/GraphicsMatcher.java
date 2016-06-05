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

import org.mabb.gfxassert.MultiTypeSafeMatcher;
import org.mabb.gfxassert.geom.ShapeSubset;

import java.awt.image.BufferedImage;

import static org.mabb.gfxassert.geom.ShapeSubset.all;

public abstract class GraphicsMatcher extends MultiTypeSafeMatcher<BufferedImage> {
    protected ShapeSubset searchArea;
    protected GfxAssertImage graphics;

    protected boolean exclude = false;

    protected GraphicsMatcher() {
        searchArea = all();
    }

    public GraphicsMatcher in(ShapeSubset searchBox) {
        this.searchArea = searchBox;
        return this;
    }

    public GraphicsMatcher notIn(ShapeSubset searchBox) {
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
