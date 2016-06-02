package org.mabb.gfxassert;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class ShapeSearchArea {
    protected List<Rectangle2D> searchAreas = new ArrayList<Rectangle2D>();
    protected String description = "Area";

    private static final String TOP_AREA_DESCRIPTION = "Top Area";
    private static final String BOTTOM_AREA_DESCRIPTION = "Bottom Area";
    private static final String RIGHT_AREA_DESCRIPTION = "Right Area";
    private static final String LEFT_AREA_DESCRIPTION = "Left Area";
    private static final String ALL_AREA_DESCRIPTION = "All";

    private static final String TOP_RIGHT_DESCRIPTION = "Top Right Area";
    private static final String TOP_LEFT_DESCRIPTION = "Top Left Area";
    private static final String BOTTOM_RIGHT_DESCRIPTION = "Bottom Right Area";
    private static final String BOTTOM_LEFT_DESCRIPTION = "Bottom Left Area";

    public static ShapeSearchArea topArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(0, 0, 1, .5));
        area.description = TOP_AREA_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea bottomArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(0, .5, 1, .5));
        area.description = BOTTOM_AREA_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea leftArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(0, 0, .5, 1));
        area.description = LEFT_AREA_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea rightArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(.5, 0, .5, 1));
        area.description = RIGHT_AREA_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea topRightArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(.5, 0, .5, 1));
        area.searchAreas.add(new Rectangle2D.Double(0, 0, 1, .5));

        area.description = TOP_RIGHT_DESCRIPTION;

        return area;
    }
    public static ShapeSearchArea topLeftArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(0, 0, .5, 1));
        area.searchAreas.add(new Rectangle2D.Double(0, 0, 1, .5));
        area.description = TOP_LEFT_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea bottomRightArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(.5, 0, .5, 1));
        area.searchAreas.add(new Rectangle2D.Double(0, .5, 1, .5));
        area.description = BOTTOM_RIGHT_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea bottomLeftArea() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(0, 0, .5, 1));
        area.searchAreas.add(new Rectangle2D.Double(0, .5, 1, .5));
        area.description = BOTTOM_LEFT_DESCRIPTION;

        return area;
    }

    public static ShapeSearchArea all() {
        ShapeSearchArea area = new ShapeSearchArea();
        area.searchAreas.add(new Rectangle2D.Double(1, 1, 1, 1));
        area.description = ALL_AREA_DESCRIPTION;

        return area;
    }

    List<Rectangle2D> getScaledSearchShapes(Shape scaleToShape) {
        Rectangle2D scaleTo = scaleToShape.getBounds2D();
        List<Rectangle2D> scaledAreas = new ArrayList<Rectangle2D>(searchAreas.size());

        for (Rectangle2D matchOn : searchAreas) {
            Rectangle2D.Double scaledAreaOn = new Rectangle2D.Double();
            scaledAreaOn.height = matchOn.getHeight() * scaleTo.getHeight();
            scaledAreaOn.width = matchOn.getWidth() * scaleTo.getWidth();
            scaledAreaOn.x = matchOn.getX() * scaleTo.getHeight() + scaleTo.getX();
            scaledAreaOn.y = matchOn.getY() * scaleTo.getHeight() + scaleTo.getY();

            scaledAreas.add(scaledAreaOn);
        }

        return scaledAreas;
    }

    protected void add(Rectangle2D shape) {
        searchAreas.add(shape);
    }

    public String toString() {
        return description;
    }

    boolean contains(Shape target, Shape container) {
        Rectangle2D targetRect = target.getBounds2D();

        for (Rectangle2D searchShapeOn : getScaledSearchShapes(container)) {
            if (!searchShapeOn.contains(targetRect))
                return false;
        }

        return true;
    }
}
