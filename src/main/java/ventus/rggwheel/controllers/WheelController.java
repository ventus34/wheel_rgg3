package ventus.rggwheel.controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import ventus.rggwheel.model.ItemEnum;
import ventus.rggwheel.model.PaletteEnum;
import ventus.rggwheel.model.PrizeEnum;
import ventus.rggwheel.services.audio.MediaPlayerService;
import ventus.rggwheel.services.spreadsheet.GoogleFormsPostService;
import ventus.rggwheel.services.wheel.PrizesService;
import ventus.rggwheel.utils.ColorUtils;
import ventus.rggwheel.utils.WheelUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class WheelController extends FXMLController {
    private final double MUSIC_FADEOUT_TIME = 3.0;
    private final double MUSIC_FADEOUT_TIME_OFFSET = 1.0;
    private MediaPlayerService mediaPlayerService;
    private PrizesService prizesService;
    private WheelController oppositeModeController;
    static boolean isFadeoutSet = false;

    public void setMediaPlayerService(MediaPlayerService mediaPlayerService) {
        this.mediaPlayerService = mediaPlayerService;
    }

    public void setPrizesService(PrizesService prizesService) {
        this.prizesService = prizesService;
    }

    public void setOppositeModeController(WheelController oppositeModeController) {
        this.oppositeModeController = oppositeModeController;
    }
    public abstract void setRotate(Double angle);
    public abstract void spinWheel();
    public abstract void moveToNext();
    public abstract void moveToPrevious();
    public abstract void setPalette(PaletteEnum palette, boolean shuffle);
    public abstract void randomizer();
    public abstract Label getSpinTime();
    public abstract void setSpinTime(Integer spinTime);

    public List<Label> prizesList = new ArrayList<>();

    protected void setLabels(){
        List<String> labels = prizesService.labels();
        for (int i = 0; i < prizesList.size(); i++) {
            prizesList.get(i).setText(labels.get(i));
        }
    }

    protected void spin(Duration spinTime, AnchorPane wheel, Double spinAngle) {
        double spinTimeInSeconds = spinTime.toSeconds();
        isFadeoutSet = false;
        retroBoy.lockButtons();
        RotateTransition wheelAnimation = spinning(spinTime, wheel, spinAngle, spinTimeInSeconds);
        wheelAnimation.setOnFinished(e -> {
            postSpin(wheel);
        });
        wheelAnimation.play();
        mediaPlayerService.setMusicTime((int) spinTime.toSeconds());
        mediaPlayerService.play(MediaPlayerService.AudioPlayerEnum.MUSIC, null);
    }

    private RotateTransition spinning(Duration spinTime, AnchorPane wheel, Double spinAngle, double spinTimeInSeconds) {
        RotateTransition rotation = new RotateTransition(
                spinTime,
                wheel);
        double currentRotation = wheel.getRotate() % 360;
        rotation.setFromAngle(currentRotation);
        rotation.setToAngle(currentRotation + spinAngle);
        rotation.setCycleCount(1);
//        rotation.setInterpolator(Interpolator.SPLINE(0.12, 1.0, 0.22, 1));
        rotation.setInterpolator(Interpolator.SPLINE(0.06, 0.59, 0.25, 1));
        rotation.setAutoReverse(false);
        int fadeoutDuration = (int) Math.min(MUSIC_FADEOUT_TIME, spinTime.toSeconds());
        rotation.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!isFadeoutSet && newValue.toSeconds() > spinTimeInSeconds - fadeoutDuration) {
                mediaPlayerService.fadeout(MediaPlayerService.AudioPlayerEnum.MUSIC, fadeoutDuration);
                isFadeoutSet = true;
            }
        });
        return rotation;
    }

    private void postSpin(AnchorPane wheel) {
        oppositeModeController.setRotate(wheel.getRotate());
        retroBoy.unlockButtons();
        PrizeEnum currentPrize = prizesService.getPrize(WheelUtils.indicatedPrize(wheel.getRotate()));
        saveResult(currentPrize);
        if(!currentPrize.equals(PrizeEnum.DOUBLE)) {
            WheelUtils.wheelMultiplier = 1;
        }
        retroBoy.setPrizeDesc(prizesService.getPrize(WheelUtils.indicatedPrize(Math.abs(wheel.getRotate())%360)));
        retroBoy.checkPrize();
    }

    private void saveResult(PrizeEnum currentPrize) {
        retroBoy.getProgress().getPrizesHistory().add(currentPrize);
        if(currentPrize.equals(PrizeEnum.DOUBLE)) {
            WheelUtils.wheelMultiplier = WheelUtils.wheelMultiplier * 2;
        }
        System.out.println(currentPrize);
        Integer potions = getInStockAmount(ItemEnum.Potion);
        Integer hints = getInStockAmount(ItemEnum.Hints);
        if(currentPrize.equals(PrizeEnum.POTION)){
            potions = potions + WheelUtils.wheelMultiplier;
            retroBoy.getProgress().getInventory().replace(ItemEnum.Potion, potions);
            retroBoy.updateInventory();
        }
        if(currentPrize.equals(PrizeEnum.FIVE_HINTS)){
            hints = hints + 5 * WheelUtils.wheelMultiplier;
            retroBoy.getProgress().getInventory().replace(ItemEnum.Hints, hints);
            retroBoy.updateInventory();
        }
        GoogleFormsPostService.savePrizeToSpreadsheet(currentPrize.getName(), "Hints: " + hints + "; Potions: " + potions);
        retroBoy.save();
    }

    private Integer getInStockAmount(ItemEnum item) {
        return retroBoy.getProgress().getInventory().get(item);
    }

    protected void goToNext(AnchorPane wheelPane) {
        double destination = wheelPane.getRotate() + 3 * WheelUtils.getAngle()/2 - wheelPane.getRotate()%WheelUtils.getAngle();
        rotationAnimation(wheelPane, destination);
    }

    protected void goBack(AnchorPane wheelPane) {
        double destination = wheelPane.getRotate() - WheelUtils.getAngle()/2 - wheelPane.getRotate()%WheelUtils.getAngle();
        rotationAnimation(wheelPane, destination);
    }

    protected void goToRandomPrize(AnchorPane wheel){
        double destination = wheel.getRotate() + WheelUtils.getRandomAngle(wheel.getRotate(), Integer.parseInt(getSpinTime().getText()));
        rotationAnimation(wheel, destination);
    }

    protected void setRandomTime(){
        int randomTime = WheelUtils.getRandomTime();
        setSpinTime(randomTime);
        oppositeModeController.setSpinTime(randomTime);
    }

    private void rotationAnimation(AnchorPane wheelPane, double destination) {
        RotateTransition rotation = new RotateTransition(
                Duration.millis(300),
                wheelPane);
        rotation.setToAngle(destination);
        rotation.setCycleCount(1);
//        rotation.setInterpolator(Interpolator.SPLINE(0.12, 1.0, 0.22, 1));
        rotation.setInterpolator(Interpolator.SPLINE(0.06, 0.59, 0.25, 1));
        rotation.setAutoReverse(false);
        rotation.setOnFinished(e -> {
            oppositeModeController.setRotate(wheelPane.getRotate());
            retroBoy.setPrizeDesc(prizesService.getPrize(WheelUtils.indicatedPrize(Math.abs(wheelPane.getRotate())%360)));
        });
        rotation.play();
    }

    protected void setWheelPalette(AnchorPane arcsPane, AnchorPane labelsPane, PaletteEnum palette, boolean shuffle){
        List<Color> colors = ColorUtils.prepareColors(palette);
        if(shuffle) {
            Collections.shuffle(colors);
        }
        List<Shape> arcs = arcsPane.getChildren().stream().filter(node -> node instanceof Arc).map(arc -> (Arc) arc).collect(Collectors.toList());
        for (int i = 0; i < arcs.size(); i++) {
            arcs.get(i).setFill(colors.get(i % colors.size()));
        }
        labelsPane.getChildren().forEach(node -> ((Label) node).setTextFill(palette.getMode().getPrizeLabelColor()));
    }


}
