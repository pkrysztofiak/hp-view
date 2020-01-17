package pl.pkrysztofiak.hpview.model;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.subjects.PublishSubject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import pl.pkrysztofiak.hpview.model.hp.Hp;
import pl.pkrysztofiak.hpview.model.hp.panel.HpPanel;
import pl.pkrysztofiak.hpview.model.panels.HpPanelWrapper;
import pl.pkrysztofiak.hpview.utils.ScreensManager;

public class Model {

    public final PublishSubject<Hp> setHp = PublishSubject.create();
    
    private final ObjectProperty<Hp> hpPropety = new SimpleObjectProperty<>();
    private final Observable<Hp> hpObservable = JavaFxObservable.valuesOf(hpPropety);
    
    private final ObservableList<HpPanelWrapper> hpPanelsWrappers = FXCollections.observableArrayList();
    private final ObservableList<HpPanelWrapper> unmodifiableHpPanelsWrappers = FXCollections.unmodifiableObservableList(hpPanelsWrappers);
    private final Observable<HpPanelWrapper> hpPanelWrapperAddedObservable = JavaFxObservable.additionsOf(hpPanelsWrappers);
    private final Observable<HpPanelWrapper> hpPanelWrapperRemovedObservable = JavaFxObservable.removalsOf(hpPanelsWrappers);

    {
        setHp.delay(0, TimeUnit.SECONDS, JavaFxScheduler.platform()).subscribe(hpPropety::set);
        hpObservable.subscribe(this::onHpChanged);
    }
    
    private void onHpChanged(Hp hp) {
        ObservableList<HpPanel> hpPanels = hp.getHpPanels();
        if (hpPanels.size() <= ScreensManager.screens.size()) {
            for (int i = 0; i < hpPanels.size(); i++) {
                HpPanel hpPanel = hpPanels.get(i);
                Screen screen = ScreensManager.screens.get(i);
                Rectangle2D visualBounds = screen.getVisualBounds();
                System.out.println(visualBounds);
                
                hpPanel.setMinX(visualBounds.getMinX());
                hpPanel.setMinY(visualBounds.getMinY());
                hpPanel.setMaxX(visualBounds.getMaxX());
                hpPanel.setMaxY(visualBounds.getMaxY());
                
                System.out.println(hpPanel);
                
                if (i < hpPanelsWrappers.size()) {
                    hpPanelsWrappers.get(i).setHpPanel(hpPanel);
                } else {
                    hpPanelsWrappers.add(new HpPanelWrapper(hpPanel));
                }
            }
        } else {
            throw new RuntimeException();
        }
        
//        String userHomeDir = System.getProperty("user.home");
//        String appDir = "viewer";
//        Path path = Paths.get(userHomeDir, appDir, hp.getId());
//        if (!Files.exists(path)) {
//            //TODO dopisac usuwanie
//        } else {
//            try {
//                ObjectMapper objectMapper = new ObjectMapper();
//                File file = new File(path.toUri());
//                if (!file.exists()) {
//                    file.getParentFile().mkdirs();
//                }
//                objectMapper.writeValue(file, hp);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
    
    public ObservableList<HpPanelWrapper> getHpPanels() {
        return unmodifiableHpPanelsWrappers;
    }
    
    public Observable<HpPanelWrapper> hpPanelAddedObservable() {
        return hpPanelWrapperAddedObservable;
    }
    
    public Observable<HpPanelWrapper> hpPanelRemovedObservable() {
        return hpPanelWrapperRemovedObservable;
    }
}