package pl.pkrysztofiak.hpview.model.panels.grid.lines.behaviour.drag;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Point2D;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.LineModel;

public abstract class GridLineDragBehaviour {

    //THIS IS FLAW 
    public final PublishSubject<Point2D> mousePressed = PublishSubject.create();
    public final PublishSubject<Point2D> mouseDragged = PublishSubject.create();
    public final PublishSubject<Point2D> mouseReleased = PublishSubject.create();
    
    private final Observable<Point2D> dragStartedObservable = mousePressed.switchMap(pressedPoint -> mouseDragged.map(draggedPoint -> pressedPoint).take(1));
    private final Observable<Point2D> draggedObservable = dragStartedObservable.switchMap(startPoint -> mouseDragged);
    private final Observable<Point2D> dragFinishedObservable = dragStartedObservable.switchMap(startPoint -> mouseReleased.take(1));
    
    {
        dragStartedObservable.subscribe(startPoint -> onDragStart(startPoint));
        draggedObservable.subscribe(dragPoint -> onDrag(dragPoint));
        dragFinishedObservable.subscribe(finishPoint -> onDragFinish(finishPoint));
    }
    
    protected final GridLineModel gridLineModel;
    protected final SortedList<LineModel> sortedLines;
    protected final ObservableList<GridLineModel> gridLines;
    
    protected final List<LineModel> dragLines = new ArrayList<>();
    
    public GridLineDragBehaviour(GridLineModel gridLine, ObservableList<GridLineModel> gridLines) {
        this.gridLineModel = gridLine;
        sortedLines = new SortedList<>(gridLine.getLines(), (hLine1, hLine2) -> hLine1.compareTo(hLine2));
        this.gridLines = gridLines;
    
    }
    
    protected abstract void onDragStart(Point2D point);
    protected abstract void onDrag(Point2D point);
    protected abstract void onDragFinish(Point2D point);
}