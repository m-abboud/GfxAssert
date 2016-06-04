package org.mabb.gfxassert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TestUtils {
    public static InputStream loadResource(String resource) {
        if (!resource.startsWith("/"))
            resource = "/" + resource;

        return TestUtils.class.getResourceAsStream(resource);
    }

    public static BufferedImage loadImage(String resource) throws IOException {
        return ImageIO.read(loadResource(resource));
    }
}
