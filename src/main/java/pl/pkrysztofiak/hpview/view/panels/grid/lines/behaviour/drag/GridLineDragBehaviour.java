package pl.pkrysztofiak.hpview.view.panels.grid.lines.behaviour.drag;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import pl.pkrysztofiak.hpview.model.panels.grid.lines.GridLineModel;
import pl.pkrysztofiak.hpview.utils.MouseEventUtil;
import pl.pkrysztofiak.hpview.utils.RatioUtil;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;
import pl.pkrysztofiak.hpview.view.panels.grid.lines.GridLineView;

public class GridLineDragBehaviour {

    public GridLineDragBehaviour(GridLineView gridLineView, GridLineModel gridLineModel, GridPanelsView gridPanelsView) {
        Observable<Point2D> pressedObservable = JavaFxObservable.eventsOf(gridLineView, MouseEvent.MOUSE_PRESSED)
        .map(MouseEventUtil::screenToPoint2D)
        .map(gridPanelsView::screenToLocal);
        
        Observable<Point2D> draggedObservable = JavaFxObservable.eventsOf(gridLineView, MouseEvent.MOUSE_DRAGGED)
        .map(MouseEventUtil::screenToPoint2D)
        .map(gridPanelsView::screenToLocal);
        
        Observable<Point2D> releasedObservable = JavaFxObservable.eventsOf(gridLineView, MouseEvent.MOUSE_RELEASED)
        .map(MouseEventUtil::screenToPoint2D)
        .map(gridPanelsView::screenToLocal);

        Observable.combineLatest(pressedObservable, gridPanelsView.widthObservable(), gridPanelsView.heightObservable(), RatioUtil::layoutPointToRatio)
        .subscribe(gridLineModel.dragBehaviour.mousePressed::onNext);
        
        Observable.combineLatest(draggedObservable, gridPanelsView.widthObservable(), gridPanelsView.heightObservable(), RatioUtil::layoutPointToRatio)
        .subscribe(gridLineModel.dragBehaviour.mouseDragged::onNext);
        
        Observable.combineLatest(releasedObservable, gridPanelsView.widthObservable(), gridPanelsView.heightObservable(), RatioUtil::layoutPointToRatio)
        .subscribe(gridLineModel.dragBehaviour.mouseReleased::onNext);
    }
}