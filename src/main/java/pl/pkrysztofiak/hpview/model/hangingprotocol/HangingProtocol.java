package pl.pkrysztofiak.hpview.model.hangingprotocol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;

public class HangingProtocol {

    private final ObservableList<HangingProtocolPanel> hpPanels = FXCollections.observableArrayList();
    
    public HangingProtocol(HangingProtocolPanel... hpPanels) {
        this.hpPanels.setAll(hpPanels);
    }
}
