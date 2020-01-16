package pl.pkrysztofiak.hpview.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pl.pkrysztofiak.hpview.model.hangingprotocol.HangingProtocol;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;

public class Model {

    private final ObjectProperty<HangingProtocol> hpPropety = new SimpleObjectProperty<>();
    
    {
        HangingProtocolPanel hpPanel1 = new HangingProtocolPanel(
                new PanelModel(0, 0, 0.5, 0.25),
                new PanelModel(0.5, 0, 1, 0.25),
                new PanelModel(0, 0.25, 1, 0.75),
                new PanelModel(0, 0.75, 0.5, 1),
                new PanelModel(0.5, 0.75, 1, 1)
                );
        
        HangingProtocolPanel hpPanel2 = new HangingProtocolPanel(
                new PanelModel(0, 0, 0.5, 0.5),
                new PanelModel(0.5, 0, 1, 0.5),
                new PanelModel(0, 0.5, 1, 1),
                new PanelModel(0, 0.5, 1, 1)
                );

        HangingProtocol hp = new HangingProtocol(hpPanel1, hpPanel2);
    }
}
