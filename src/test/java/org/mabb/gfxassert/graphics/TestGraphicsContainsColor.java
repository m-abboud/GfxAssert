package org.mabb.gfxassert.graphics;
import org.junit.Assert;
import org.junit.Test;
import org.mabb.gfxassert.TestUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.not;
import static org.mabb.gfxassert.GfxAssertMatchers.*;
import static org.mabb.gfxassert.geom.ShapeSubsetDescriptor.*;

public class TestGraphicsContainsColor {
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
