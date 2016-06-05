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
import static org.mabb.gfxassert.GfxAssertMatchers.*;
import static org.mabb.gfxassert.geom.ShapeSubset.*;

public class TestContainsColor {
    @Test
    public void givenImageWithBlueInTopHalf_whenAssertBlueInTopHalf_assertIsTrue() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");

        Assert.assertThat(image, containsColor(Color.blue).in(topArea()));
    }

    @Test
    public void givenImageWithBlueInTopHalf_whenAssertBlueInBottomHalf_assertIsFalse() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");

        Assert.assertThat(image, not(containsColor(Color.blue).in(bottom(50).percent())));
    }

    @Test
    public void givenImageWithRedInBottomHalf_whenAssertRedInImage_assertIsTrue() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");

        Assert.assertThat(image, containsColor(Color.red));
    }

    @Test
    public void givenImageWithRedInBottomHalf_whenAssertRedInBottomHalf_assertIsTrue() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");
        Assert.assertThat(image, containsColor(Color.red).in(bottom(50).percent()));
    }

    @Test
    public void givenImageWithRedAndBlueAndBlack_whenAssertHasColors_assertIsTrue() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");
        Assert.assertThat(image, containsColors(Color.red, Color.black, Color.blue));
    }

    @Test
    public void givenImageWithRedBlueBlack_whenAssertHasColorsAndNonExistantPink_assertIsFalse() throws IOException {
        BufferedImage image = TestUtils.loadImage("test-image-contains-colors.png");
        Assert.assertThat(image, not(containsColors(Color.red, Color.black, Color.blue, Color.pink)));
    }
}
