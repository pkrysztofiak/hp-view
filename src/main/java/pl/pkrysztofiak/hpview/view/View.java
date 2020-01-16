package pl.pkrysztofiak.hpview.view;

import io.reactivex.rxjavafx.sources.Change;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.Model;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.HpPanelWrapper;
import pl.pkrysztofiak.hpview.view.panels.HpPanelView;

public class View {

    private final ObservableList<HpPanelView> panels = FXCollections.observableArrayList();
    
    public View(Model model) {
        model.getHpPanels().stream().map(HpPanelWrapper::getHpPanel).forEach(this::onHpPanelAdded);
        model.hpPanelAddedObservable().map(HpPanelWrapper::getHpPanel).subscribe(this::onHpPanelAdded);
        model.hpPanelAddedObservable().flatMap(hpPanelWrapper -> hpPanelWrapper.hpPanelChangeObservable().takeUntil(model.hpPanelRemovedObservable().filter(hpPanelWrapper::equals))).subscribe(this::onHpPanelUpated);
    }
    
    private void onHpPanelAdded(HpPanel hpPanel) {
        HpPanelView hpPanelView = new HpPanelView(hpPanel);
        panels.add(hpPanelView);
        Platform.runLater(() -> {
            hpPanelView.show();
        });
    }
    
    private void onHpPanelUpated(Change<HpPanel> change) {
        panels.stream().filter(panel -> panel.contains(change.getOldVal())).findFirst().ifPresent(panel -> panel.setHpPanel(change.getNewVal()));
    }
}