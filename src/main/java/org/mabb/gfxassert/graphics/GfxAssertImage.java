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

import org.mabb.gfxassert.geom.ShapeSubset;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

class GfxAssertImage extends BufferedImage {
    public GfxAssertImage(BufferedImage target) {
        super(target.getColorModel(), target.copyData(null),
                target.getColorModel().isAlphaPremultiplied(), null);
    }

    public boolean contains(ShapeSubset search, Color findColor) {
        StopOnColorFound strat = new StopOnColorFound(findColor);
        searchPixels(search, strat);

        return strat.found;
    }

    public List<Color> findAllColors(ShapeSubset area) {
        FindAllColors strat = new FindAllColors();
        searchPixels(area, strat);

        return strat.colors;
    }

    protected void searchPixels(ShapeSubset area, PixelSearchStrategy strat) {
        List<Rectangle2D> shapes = area.getToScale(getBounds());

        for (Rectangle2D rectOn : shapes)
            searchPixelsInRect(rectOn, strat);
    }

    protected void searchPixelsInRect(Rectangle2D rect, PixelSearchStrategy strat) {
        int xEnd = (int) rect.getMaxX();
        int xBegin = (int) rect.getX();

        int yBegin = (int) rect.getY();
        int yEnd = (int) rect.getMaxY();

        for (int x = xBegin; x < xEnd; x++) {
            for (int y = yBegin; y < yEnd; y++) {
                Color colorOn = new Color(getRGB(x, y));
                boolean shouldStop = strat.onPixel(colorOn, x, y);

                if (shouldStop)
                    return;
            }
        }
    }

    protected Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(0, 0, getWidth(), getHeight());
    }

    interface PixelSearchStrategy {
        /**
         * @return true if the image pixel search should end.
         */
        boolean onPixel(Color color, int x, int y);
    }

    class StopOnColorFound implements PixelSearchStrategy {
        public boolean found = false;
        private final Color stopOn;

        StopOnColorFound(Color color) {
            this.stopOn = color;
        }

        public boolean onPixel(Color color, int x, int y) {
            found = stopOn.equals(color);
            return found;
        }
    }

    class FindAllColors implements PixelSearchStrategy {
        final List<Color> colors = new LinkedList<Color>();

        public boolean onPixel(Color color, int x, int y) {
            if (!colors.contains(color))
                colors.add(color);

            return false;
        }
    }
}
