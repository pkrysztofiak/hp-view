package pl.pkrysztofiak.hpview.view.panels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;

public class PanelsView {

    private final ObjectProperty<HangingProtocolPanel> hpPanelProperty = new SimpleObjectProperty<>();
    
    public PanelsView() {
        
    }
    
    public void setHpPanel(HangingProtocolPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
}
