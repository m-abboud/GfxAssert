package org.mabb.gfxassert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class TestUtils {
    private static ConcurrentHashMap<String, BufferedImage> loadedImages =
            new ConcurrentHashMap<String, BufferedImage>(16, .75F, 128);

    public static InputStream loadResource(String resource) {
        if (!resource.startsWith("/"))
            resource = "/" + resource;

        return TestUtils.class.getResourceAsStream(resource);
    }

    public static BufferedImage loadImage(String resource) throws IOException {
        if (loadedImages.containsKey(resource))
            return loadedImages.get(resource);

        BufferedImage image = ImageIO.read(loadResource(resource));
        loadedImages.put(resource, image);

        return image;
    }
}
