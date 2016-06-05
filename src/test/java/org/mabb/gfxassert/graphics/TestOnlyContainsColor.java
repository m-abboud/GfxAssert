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
