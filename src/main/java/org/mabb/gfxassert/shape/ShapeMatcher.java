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

package org.mabb.gfxassert.shape;

import org.hamcrest.TypeSafeMatcher;
import org.mabb.gfxassert.geom.ShapeSubset;

import java.awt.*;

import static org.mabb.gfxassert.geom.ShapeSubset.all;

public abstract class ShapeMatcher extends TypeSafeMatcher<Shape> {
    protected boolean exclude = false;
    protected final Shape target;
    protected ShapeSubset searchArea;

    protected ShapeMatcher(Shape target, ShapeSubset searchBox) {
        this.target = target;
        this.searchArea = searchBox;
    }

    protected ShapeMatcher(Shape target) {
        this.target = target;
        this.searchArea = all();
    }

    public ShapeMatcher in(ShapeSubset searchBox) {
        this.searchArea = searchBox;
        return this;
    }

    public ShapeMatcher notIn(ShapeSubset searchBox) {
        exclude = true;
        return in(searchBox);
    }
}
