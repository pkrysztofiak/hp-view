package pl.pkrysztofiak.hpview.view.panels;

import java.util.Optional;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.grid.GridPanelsModel;
import pl.pkrysztofiak.hpview.view.panels.grid.GridPanelsView;

public class HpPanelView extends Stage {

    private final GridPanelsModel gridPanelsModel = new GridPanelsModel();
    
    private final StackPane root = new StackPane(new GridPanelsView(gridPanelsModel));
    private final Scene scene = new Scene(root);

    {
        setScene(scene);
    }
    
    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);
    
    {
        hpPanelObservable.switchMap(HpPanel::minXObservable).observeOn(JavaFxScheduler.platform()).subscribe(this::setX);
        hpPanelObservable.switchMap(HpPanel::minYObservable).observeOn(JavaFxScheduler.platform()).subscribe(this::setY);
        Observable.combineLatest(hpPanelObservable.switchMap(HpPanel::minXObservable), hpPanelObservable.switchMap(HpPanel::maxXObservable), (pxMinX, pxMaxX) -> pxMaxX - pxMinX).observeOn(JavaFxScheduler.platform()).subscribe(this::setWidth);
        Observable.combineLatest(hpPanelObservable.switchMap(HpPanel::minYObservable), hpPanelObservable.switchMap(HpPanel::maxYObservable), (pxMinX, pxMaxX) -> pxMaxX - pxMinX).observeOn(JavaFxScheduler.platform()).subscribe(this::setHeight);
    }
    
    public HpPanelView(HpPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
 
    public void setHpPanel(HpPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
    
    public boolean contains(HpPanel hpPanel) {
        return Optional.ofNullable(hpPanelProperty.get()).map(hpPanel::equals).orElse(false);
    }
}