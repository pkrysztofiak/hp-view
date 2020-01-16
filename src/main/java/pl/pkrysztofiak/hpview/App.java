package pl.pkrysztofiak.hpview;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.pkrysztofiak.hpview.model.Model;
import pl.pkrysztofiak.hpview.model.hangingprotocol.HangingProtocol;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;
import pl.pkrysztofiak.hpview.model.panels.panel.PanelModel;
import pl.pkrysztofiak.hpview.view.View;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        new View(model);
        
        HangingProtocolPanel hpPanel1 = new HangingProtocolPanel(
                "2dd611b7-224e-435b-8fe6-298a4ac95e5b",
                new PanelModel(0, 0, 0.5, 0.25),
                new PanelModel(0.5, 0, 1, 0.25),
                new PanelModel(0, 0.25, 1, 0.75),
                new PanelModel(0, 0.75, 0.5, 1),
                new PanelModel(0.5, 0.75, 1, 1)
                );
        
        // |#1|#2|
        // |#3|#4|
        HangingProtocolPanel hpPanel2 = new HangingProtocolPanel(
                "77c9d42a-f3e3-428b-ad4e-8f7961aafa67",
                new PanelModel(0, 0, 0.5, 0.5), //#1
                new PanelModel(0.5, 0, 1, 0.5), //#2
                new PanelModel(0, 0.5, 0.5, 1), //#3
                new PanelModel(0.5, 0.5, 1, 1)  //#4
                );

        HangingProtocol hp = new HangingProtocol(
                "d02b16da-dab1-4187-b2e3-6b2c92f2404d",
                hpPanel1, 
                hpPanel2);
        
        model.setHp.onNext(hp);
    }
}
