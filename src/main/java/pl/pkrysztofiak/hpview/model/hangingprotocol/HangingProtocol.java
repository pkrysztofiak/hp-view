package pl.pkrysztofiak.hpview.model.hangingprotocol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;

public class HangingProtocol {

    private final String id;
    
    private final ObservableList<HangingProtocolPanel> hpPanels = FXCollections.observableArrayList();
    private final ObservableList<HangingProtocolPanel> unmodifiableHpPanels = FXCollections.unmodifiableObservableList(hpPanels);
    
    public HangingProtocol(String id, HangingProtocolPanel... hpPanels) {
        this.id = id;
        this.hpPanels.setAll(hpPanels);
    }

    public String getId() {
        return id;
    }
    
    public ObservableList<HangingProtocolPanel> getHpPanels() {
        return unmodifiableHpPanels;
    }
}
