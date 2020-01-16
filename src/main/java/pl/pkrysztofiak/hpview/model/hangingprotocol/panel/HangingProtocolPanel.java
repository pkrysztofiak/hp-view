package pl.pkrysztofiak.hpview.model.hangingprotocol.panel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public class HangingProtocolPanel {

    private final ObservableList<PanelModel> panels = FXCollections.observableArrayList();
    
    public HangingProtocolPanel(PanelModel... panels) {
        this.panels.setAll(panels);
    }
}