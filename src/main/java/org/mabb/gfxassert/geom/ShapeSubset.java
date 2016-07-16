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

package org.mabb.gfxassert.geom;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mabb.gfxassert.geom.ShapeSubset.AreaDescriptor.SearchType.*;

public class ShapeSubset {
    protected List<AreaDescriptor> searchAreas = new ArrayList<AreaDescriptor>();

    public ShapeSubset(AreaDescriptor... shapes) {
        searchAreas.addAll(Arrays.asList(shapes));
    }

    public static ShapeSubset topArea() {
        return top(50).percent();
    }

    public static ShapeSubset bottomArea() {
        return bottom(50).percent();
    }

    public static ShapeSubset leftArea() {
        return left(50).percent();
    }

    public static ShapeSubset rightArea() {
        return right(50).percent();
    }

    public static ShapeSubset topRightArea() {
        ShapeSubset area = new ShapeSubset();
        area.add(new PercentArea(50, TOP));
        area.add(new PercentArea(50, RIGHT));

        return area;
    }

    public static ShapeSubset topLeftArea() {
        ShapeSubset area = new ShapeSubset();
        area.add(new PercentArea(50, TOP));
        area.add(new PercentArea(50, LEFT));

        return area;
    }

    public static ShapeSubset bottomRightArea() {
        ShapeSubset area = new ShapeSubset();
        area.add(new PercentArea(50, BOTTOM));
        area.add(new PercentArea(50, RIGHT));

        return area;
    }

    public static ShapeSubset bottomLeftArea() {
        ShapeSubset area = new ShapeSubset();
        area.add(new PercentArea(50, BOTTOM));
        area.add(new PercentArea(50, LEFT));

        return area;
    }

    public static ShapeSubset all() {
        ShapeSubset area = new ShapeSubset();
        area.add(new PercentArea(100, ALL));

        return area;
    }

    public List<Rectangle2D> getToScale(Shape container) {
        List<Rectangle2D> scaledAreas = new ArrayList<Rectangle2D>(searchAreas.size());

        for (AreaDescriptor area : searchAreas)
            scaledAreas.add(area.applyForContainer(container).getBounds2D());

        return scaledAreas;
    }

    protected void add(AreaDescriptor shape) {
        searchAreas.add(shape);
    }

    public String toString() {
        String formattedAreas = "";

        for (AreaDescriptor area : searchAreas) {
            if (!formattedAreas.isEmpty())
                formattedAreas += " and ";

            formattedAreas += area.toString();
        }

        return formattedAreas;
    }

    public boolean contains(Shape target, Shape container) {
        Rectangle2D targetRect = target.getBounds2D();

        for (Rectangle2D searchShapeOn : getToScale(container)) {
            if (!searchShapeOn.contains(targetRect))
                return false;
        }

        return true;
    }

    public boolean partiallyContains(Shape target, Shape container) {
        Rectangle2D targetRect = target.getBounds2D();

        for (Rectangle2D searchShapeOn : getToScale(container)) {
            if (!searchShapeOn.contains(targetRect) && !searchShapeOn.intersects(targetRect))
                return false;
        }

        return true;
    }

    public static AreaDescriptor center(int num) {
        return new PercentArea(num, CENTER);
    }

    public static AreaDescriptor right(int num) {
        return new PercentArea(num, RIGHT);
    }

    public static AreaDescriptor left(int num) {
        return new PercentArea(num, LEFT);
    }

    public static AreaDescriptor top(int num) {
        return new PercentArea(num, TOP);
    }

    public static AreaDescriptor bottom(int num) {
        return new PercentArea(num, BOTTOM);
    }

    /**
     * Seperate class in order to force .percent() or .pixels() syntax after a bottom(20)
     */
    public abstract static class AreaDescriptor {
        protected enum SearchType {
            TOP, BOTTOM, RIGHT, LEFT, CENTER, ALL
        }

        protected double number;
        protected final SearchType searchArea;

        public AreaDescriptor(double num, SearchType bottom) {
            this.number = num;
            this.searchArea = bottom;
        }

        public ShapeSubset percent() {
            return new ShapeSubset(new PercentArea(number, searchArea));
        }

        public ShapeSubset pixels() {
            return new ShapeSubset(new PixelArea(number, searchArea));
        }

        public abstract Shape applyForContainer(Shape container);

        protected abstract String getUnit();

        public String toString() {
            String area = searchArea.toString();
            area = capitialize(area);

            if (searchArea == ALL)
                return area;

            return area + " " + number + getUnit();
        }

        private static String capitialize(String str) {
            if (str == null || str.isEmpty())
                return str;

            str = str.toLowerCase();
            char[] chars = str.toCharArray();
            chars[0] = new String(new char[]{chars[0]}).toUpperCase().toCharArray()[0];

            return new String(chars);
        }
    }

    public static class PercentArea extends AreaDescriptor {
        public PercentArea(double num, SearchType type) {
            super(num, type);
        }

        public Shape applyForContainer(Shape container) {
            Rectangle2D.Double rect;

            double pct = number / 100.0;
            switch (searchArea) {
                case TOP:
                    rect = new Rectangle2D.Double(0, 0, 1, pct);
                    break;
                case BOTTOM:
                    rect = new Rectangle2D.Double(0, 1 - pct, 1, pct);
                    break;
                case RIGHT:
                    rect = new Rectangle2D.Double(1 - pct, 0, pct, 1);
                    break;
                case LEFT:
                    rect = new Rectangle2D.Double(0, 0, pct, 1);
                    break;
                case CENTER:
                    rect = new Rectangle2D.Double(0.5 - (pct / 2), 0.5 - (pct / 2), pct, pct);
                    break;
                default:
                case ALL:
                    rect = new Rectangle2D.Double(0, 0, 1, 1);
                    break;
            }

            return scaleTo(rect, container);
        }

        private Shape scaleTo(Shape search, Shape scaleToArea) {
            Rectangle2D scaleTo = scaleToArea.getBounds2D();
            Rectangle2D searchBounds = search.getBounds2D();

            Rectangle2D.Double scaledAreaOn = new Rectangle2D.Double();
            scaledAreaOn.height = searchBounds.getHeight() * scaleTo.getHeight();
            scaledAreaOn.width = searchBounds.getWidth() * scaleTo.getWidth();
            scaledAreaOn.x = searchBounds.getX() * scaleTo.getWidth() + scaleTo.getX();
            scaledAreaOn.y = searchBounds.getY() * scaleTo.getHeight() + scaleTo.getY();

            return scaledAreaOn;
        }

        public String getUnit() {
            return "%";
        }
    }

    public static class PixelArea extends AreaDescriptor {
        public PixelArea(double num, SearchType type) {
            super(num, type);
        }

        public Shape applyForContainer(Shape container) {
            Rectangle2D rect;
            Rectangle2D scaleTo = container.getBounds2D();

            double pixels = number;
            switch (searchArea) {
                case TOP:
                    rect = new Rectangle2D.Double(0, 0, scaleTo.getWidth(), pixels);
                    break;
                case BOTTOM:
                    rect = new Rectangle2D.Double(0, scaleTo.getHeight() + scaleTo.getY() - pixels, scaleTo.getWidth(), pixels);
                    break;
                case RIGHT:
                    rect = new Rectangle2D.Double(scaleTo.getWidth() + scaleTo.getX() - pixels, 0, pixels, 1);
                    break;
                case LEFT:
                    rect = new Rectangle2D.Double(0, 0, pixels, scaleTo.getHeight());
                    break;
                case CENTER:
                    rect = new Rectangle2D.Double(
                            scaleTo.getHeight() + scaleTo.getY() - (pixels / 2),
                            scaleTo.getWidth() + scaleTo.getX() - (pixels / 2),
                            pixels, pixels);
                    break;
                default:
                case ALL:
                    rect = scaleTo;
                    break;
            }

            return rect;
        }

        public String getUnit() {
            return "px";
        }
    }
}
