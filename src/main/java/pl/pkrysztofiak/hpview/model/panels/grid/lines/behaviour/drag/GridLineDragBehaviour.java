package pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;

public class GridLineDragBehaviour {

    public final PublishSubject<Point2D> mousePressed = PublishSubject.create();
    public final PublishSubject<Point2D> mouseDragged = PublishSubject.create();
    public final PublishSubject<Point2D> mouseReleased = PublishSubject.create();
    
    {
        mousePressed.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onMousePressed);
        mouseDragged.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onMouseDragged);
        mouseReleased.delay(0, TimeUnit.SECONDS, Schedulers.single()).subscribe(this::onMouseReleased);
    }
    
    public GridLineDragBehaviour(GridLineModel gridLineModel, ObservableList<? extends GridLineModel> gridLines) {
        
    }
    
    private void onMousePressed(Point2D point) {
        System.out.println("pressed x=" + point.getX() + ", y=" + point.getY());
    }
    
    private void onMouseDragged(Point2D point) {
        System.out.println("dragged x=" + point.getX() + ", y=" + point.getY());
    }
    
    private void onMouseReleased(Point2D point) {
        System.out.println("released x=" + point.getX() + ", y=" + point.getY());
    }
    
}
