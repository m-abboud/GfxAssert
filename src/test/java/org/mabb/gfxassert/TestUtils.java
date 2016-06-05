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
