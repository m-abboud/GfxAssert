package org.mabb.gfxassert.graphics;

import org.mabb.gfxassert.ShapeSearchArea;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class GfxAssertImage extends BufferedImage{
    public GfxAssertImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public GfxAssertImage(BufferedImage target) {
        super(target.getColorModel(), target.copyData(null),
                target.getColorModel().isAlphaPremultiplied(), null);
    }

    public boolean contains(ShapeSearchArea searchArea, Color findColor) {
        List<Color> colors = findAllColors(searchArea);
        return colors.contains(findColor);
    }

    public List<Color> findAllColors(ShapeSearchArea area) {
        List<Rectangle2D> shapes = area.getToScale(getBounds());

        List<Color> colors = new LinkedList<Color>();
        for (Rectangle2D rectOn : shapes)
            colors.addAll(findColorsInRect(rectOn));

        return colors;
    }

    protected  List<Color> findColorsInRect(Rectangle2D rect) {
        List<Color> colors = new LinkedList<Color>();

        for (int x = (int) rect.getX(); x < (int) rect.getMaxX(); x++) {
            for (int col = (int) rect.getY(); col < (int) rect.getMaxY(); col++) {
                Color colorOn = new Color(getRGB(x, col));

                if (!colors.contains(colorOn))
                    colors.add(colorOn);
            }
        }

        return colors;
    }

    protected Rectangle2D.Double getBounds() {
        return new Rectangle2D.Double(0, 0, getWidth(), getHeight());
    }
}
