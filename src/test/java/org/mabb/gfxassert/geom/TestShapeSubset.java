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

package org.mabb.gfxassert.geom;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class TestShapeSubset {
    @Test
    public void givenTopAreaShapeSubset_whenToStringCalled_thenSearchAreaCapitialized() throws Exception {
        String shapeDesc = ShapeSubset.topArea().toString();

        Assert.assertEquals("Top", shapeDesc.substring(0,3));
    }

    @Test
    public void givenTop20Percent_whenToString_then20PercentSignIncluded() throws Exception {
        String shapeDesc = ShapeSubset.top(20).percent().toString();

        Assert.assertThat(shapeDesc, containsString("20.0%"));
    }

    @Test
    public void given_top_42_Pixels_when_to_string_then_20_and_Px_Unit_Included() throws Exception {
        String shapeDesc = ShapeSubset.top(20).pixels().toString();

        Assert.assertThat(shapeDesc, containsString("20.0px"));
    }
}
