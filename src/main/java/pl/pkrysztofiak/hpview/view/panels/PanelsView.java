package pl.pkrysztofiak.hpview.view.panels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HpPanel;

public class PanelsView {

    private final ObjectProperty<HpPanel> hpPanelProperty = new SimpleObjectProperty<>();
    
    public PanelsView() {
        
    }
    
    public void setHpPanel(HpPanel hpPanel) {
        hpPanelProperty.set(hpPanel);
    }
}
