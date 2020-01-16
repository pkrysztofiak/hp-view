package pl.pkrysztofiak.hpview.model.hangingprotocol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HpPanel;

public class Hp {

    private final String id;
    
    private final ObservableList<HpPanel> hpPanels = FXCollections.observableArrayList();
    private final ObservableList<HpPanel> unmodifiableHpPanels = FXCollections.unmodifiableObservableList(hpPanels);
    
    public Hp(String id, HpPanel... hpPanels) {
        this.id = id;
        this.hpPanels.setAll(hpPanels);
    }

    public String getId() {
        return id;
    }
    
    public ObservableList<HpPanel> getHpPanels() {
        return unmodifiableHpPanels;
    }
}
