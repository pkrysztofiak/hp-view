package pl.pkrysztofiak.hpview.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.stage.Screen;

public class ScreensManager {
    
    public static final ObservableList<Screen> screens = Screen.getScreens();
    public static final Observable<ObservableList<Screen>> screensObservable = JavaFxObservable.emitOnChanged(screens);
    public static final Observable<Screen> screenAddedObservable = JavaFxObservable.additionsOf(screens);
    public static final Observable<Screen> screenRemovedObservable = JavaFxObservable.removalsOf(screens);

    private static final ObjectProperty<Double> widthProperty = new SimpleObjectProperty<>(3840.);
    public static final Observable<Double> widthObservable = JavaFxObservable.valuesOf(widthProperty);
    private static final ObjectProperty<Double> heightProperty = new SimpleObjectProperty<>(1200.);
    public static final Observable<Double> heightObservable = JavaFxObservable.valuesOf(widthProperty);
    
    static {
        screensObservable.delay(0, TimeUnit.SECONDS, JavaFxScheduler.platform()).subscribe(ScreensManager::onScreensChanged);
    }
    
    public static Double getWidth() {
        return widthProperty.get();
    }
    
    public static Double getHeight() {
        return heightProperty.get();
    }
    
    private static void onScreensChanged(ObservableList<Screen> screens) {
//        widthProperty.set(screens.stream().map(Screen::getVisualBounds).mapToDouble(Rectangle2D::getWidth).sum());
//        heightProperty.set(screens.stream().map(Screen::getVisualBounds).mapToDouble(Rectangle2D::getHeight).sum());
//        System.out.println("width=" + widthProperty.get() + ", height=" + heightProperty.get());
    }
    
    public static Double toPixelX(double x) {
        return x * widthProperty.get();
    }
    
    public static Double toPixelY(double y) {
        return y * heightProperty.get();
    }
}