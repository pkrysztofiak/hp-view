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

public class HpPanelView extends Stage {

    private final StackPane root = new StackPane();
    private final Scene scene = new Scene(root);
    
    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    private final Observable<HpPanel> hpPanelObservable = JavaFxObservable.valuesOf(hpPanelProperty);
    private final Observable<Double> hpPanelMinXObservable = hpPanelObservable.switchMap(HpPanel::minXObservable);
    private final Observable<Double> hpPanelMinYObservable = hpPanelObservable.switchMap(HpPanel::minYObservable);
    private final Observable<Double> hpPanelMaxXObservable = hpPanelObservable.switchMap(HpPanel::maxXObservable);
    private final Observable<Double> hpPanelMaxYObservable = hpPanelObservable.switchMap(HpPanel::maxYObservable);
    
    {
        setScene(scene);
        hpPanelMinXObservable.observeOn(JavaFxScheduler.platform()).subscribe(this::setX);
        hpPanelMinYObservable.observeOn(JavaFxScheduler.platform()).subscribe(this::setY);
        Observable.combineLatest(hpPanelMinXObservable, hpPanelMaxXObservable, (pxMinX, pxMaxX) -> pxMaxX - pxMinX).subscribe(this::setWidth);
        Observable.combineLatest(hpPanelMinYObservable, hpPanelMaxYObservable, (pxMinX, pxMaxX) -> pxMaxX - pxMinX).subscribe(this::setHeight);
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