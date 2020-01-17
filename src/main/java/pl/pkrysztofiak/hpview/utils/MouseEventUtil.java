package pl.pkrysztofiak.hpview.utils;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class MouseEventUtil {

    public static Point2D screenToPoint2D(MouseEvent mouseEvent) {
        return new Point2D(mouseEvent.getScreenX(), mouseEvent.getScreenY());
    }
}
