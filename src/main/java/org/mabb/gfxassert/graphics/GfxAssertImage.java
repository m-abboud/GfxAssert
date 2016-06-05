package org.mabb.gfxassert.graphics;

import org.mabb.gfxassert.geom.ShapeSubsetDescriptor;

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

    public boolean contains(ShapeSubsetDescriptor search, Color findColor) {
        StopOnColorFound strat = new StopOnColorFound(findColor);
        searchPixels(search, strat);

        return strat.found;
    }

    public List<Color> findAllColors(ShapeSubsetDescriptor area) {
        FindAllColors strat = new FindAllColors();
        searchPixels(area, strat);

        return strat.colors;
    }

    protected void searchPixels(ShapeSubsetDescriptor area, PixelSearchStrategy strat) {
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
