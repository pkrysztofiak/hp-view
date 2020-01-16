package pl.pkrysztofiak.hpview.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import pl.pkrysztofiak.hpview.model.hangingprotocol.HangingProtocol;
import pl.pkrysztofiak.hpview.model.hangingprotocol.panel.HangingProtocolPanel;
import pl.pkrysztofiak.hpview.utils.ScreensManager;

public class Model {

    public final PublishSubject<HangingProtocol> setHp = PublishSubject.create();
    
    private final ObjectProperty<HangingProtocol> hpPropety = new SimpleObjectProperty<>();
    private final Observable<HangingProtocol> hpObservable = JavaFxObservable.valuesOf(hpPropety);
//    public final Observable<Change<HangingProtocol>> hpChangeObservable = JavaFxObservable.changesOf(hpPropety);


    {
        setHp.delay(0, TimeUnit.SECONDS, JavaFxScheduler.platform()).subscribe(hpPropety::set);
        hpObservable.subscribe(this::onHpChanged);
    }
    
    private void onHpChanged(HangingProtocol hp) {
        String userHomeDir = System.getProperty("user.home");
        String appDir = "viewer";
        Path path = Paths.get(userHomeDir, appDir, hp.getId());
        if (!Files.exists(path)) {
            ObservableList<HangingProtocolPanel> hpPanels = hp.getHpPanels();
            if (hpPanels.size() <= ScreensManager.screens.size()) {
                for (int i = 0; i < hpPanels.size(); i++) {
                    HangingProtocolPanel hpPanel = hpPanels.get(i);
                    Screen screen = ScreensManager.screens.get(i);
                    Rectangle2D visualBounds = screen.getVisualBounds();
                    hpPanel.setMinX(visualBounds.getMinX() / ScreensManager.getWidth());
                    hpPanel.setMinY(visualBounds.getMinY() / ScreensManager.getHeight());
                    hpPanel.setMaxX(visualBounds.getMaxX() / ScreensManager.getWidth());
                    hpPanel.setMaxY(visualBounds.getMaxY() / ScreensManager.getHeight());
                    
                    
                }
            } else {
                throw new RuntimeException();
            }
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                File file = new File(path.toUri());
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                objectMapper.writeValue(file, hp);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        
    }
}
