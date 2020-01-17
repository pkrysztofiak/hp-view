package pl.pkrysztofiak.hpview.view;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.rxjavafx.sources.Change;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.Model;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.HpPanelWrapper;
import pl.pkrysztofiak.hpview.view.panels.HpPanelView;

public class View {

    private final ObservableList<HpPanelView> panels = FXCollections.observableArrayList();
    
    public View(Model model) {
        Observable.fromIterable(model.getHpPanels()).map(HpPanelWrapper::getHpPanel).observeOn(JavaFxScheduler.platform()).subscribe(this::onHpPanelAdded);
        model.hpPanelAddedObservable().map(HpPanelWrapper::getHpPanel).observeOn(JavaFxScheduler.platform()).subscribe(this::onHpPanelAdded);
        model.hpPanelAddedObservable().flatMap(hpPanelWrapper -> hpPanelWrapper.hpPanelChangeObservable().takeUntil(model.hpPanelRemovedObservable().filter(hpPanelWrapper::equals))).observeOn(JavaFxScheduler.platform()).subscribe(this::onHpPanelUpated);
    }
    
    private void onHpPanelAdded(HpPanel hpPanel) {
        HpPanelView hpPanelView = new HpPanelView(hpPanel);
        panels.add(hpPanelView);
        hpPanelView.show();
    }
    
    private void onHpPanelUpated(Change<HpPanel> change) {
        panels.stream().filter(panel -> panel.contains(change.getOldVal())).findFirst().ifPresent(panel -> panel.setHpPanel(change.getNewVal()));
    }
}