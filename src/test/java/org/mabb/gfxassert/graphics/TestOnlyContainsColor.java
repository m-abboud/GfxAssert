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

import org.junit.Assert;
import org.junit.Test;
import org.mabb.gfxassert.TestUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.not;
import static org.mabb.gfxassert.GfxAssertMatchers.containsOnlyColor;
import static org.mabb.gfxassert.GfxAssertMatchers.containsOnlyColors;
import static org.mabb.gfxassert.geom.ShapeSubset.top;

public class TestOnlyContainsColor {
    @Test
    public void givenImageBlueInTop_assertOnlyContainsBlueInTop_assertIsTrue() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");
        Assert.assertThat(image, containsOnlyColors(Color.blue, Color.WHITE).in(top(10).percent()));
    }

    @Test
    public void givenImageManyColors_assertOnlyContainsBlue_assertFalse() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");
        Assert.assertThat(image, not(containsOnlyColors(Color.blue, Color.WHITE)));
    }

    @Test
    public void givenImageWithManyColors_assertOnlyContainsBlue_assertIsfalse() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");
        Assert.assertThat(image, not(containsOnlyColor(Color.blue)));
    }
}
