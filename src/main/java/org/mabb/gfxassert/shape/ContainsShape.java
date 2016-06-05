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

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.mabb.gfxassert.geom.ShapeSubset;

import java.awt.*;

public class ContainsShape extends ShapeMatcher {
    protected ContainsShape(Shape target, ShapeSubset searchBox) {
        super(target, searchBox);
    }

    protected ContainsShape(Shape target) {
        super(target);
    }

    public boolean matchesSafely(Shape item) {
        return this.search(item);
    }

    public void describeMismatchSafely(Shape item, Description mismatchDescription) {
        mismatchDescription.appendText("was not inside ").appendText(searchArea.toString());
    }

    public void describeTo(Description description) {
        description.appendText("target shape inside ").appendText(searchArea.toString()).appendText(" of container shape, ").appendValue(target);
    }

    public boolean search(Shape container) {
        return exclude != searchArea.contains(target, container);
    }

    @Factory
    public static ContainsShape containsShape(Shape shape) {
        return new ContainsShape(shape);
    }
}
