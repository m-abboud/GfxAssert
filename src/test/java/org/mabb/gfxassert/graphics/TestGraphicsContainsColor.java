package org.mabb.gfxassert.graphics;
import org.junit.Assert;
import org.junit.Test;
import org.mabb.gfxassert.ShapeSearchArea;
import org.mabb.gfxassert.TestUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.not;
import static org.mabb.gfxassert.GfxAssertMatchers.*;
import static org.mabb.gfxassert.ShapeSearchArea.*;

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
}
