package pl.pkrysztofiak.hpview.utils;

import javafx.geometry.Point2D;

public class RatioUtil {

    public static Point2D layoutPointToRatio(Point2D point, double width, double height) {
        return new Point2D(point.getX() / width, point.getY() / height);
    }
}
