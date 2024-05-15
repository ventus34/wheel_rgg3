package ventus.rggwheel.controllers.color;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.model.PaletteEnum;
import ventus.rggwheel.utils.WheelUtils;

import java.util.stream.Collectors;

public class WheelColorController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelColor;
    @FXML private AnchorPane colorPane;
    @FXML private AnchorPane labelPane;
    @FXML private AnchorPane wheelPane;

    @FXML
    void initialize(){
        colorPane.setRotate(colorPane.getRotate() + WheelUtils.getAngle()/2 + 7200);
        prizesList.addAll(labelPane.getChildren().stream().map(child -> (Label) child).collect(Collectors.toList()));
    }

    @Override
    public void setRotate(Double angle) {
        colorPane.setRotate(angle);
    }

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, colorPane, WheelUtils.getRandomAngle(colorPane.getRotate(), Integer.parseInt(label)));
    }

    @Override
    public void moveToNext() {
        this.goToNext(colorPane);
    }

    @Override
    public void moveToPrevious() {
        this.goBack(colorPane);
    }

    @Override
    public Label getSpinTime() {
        return spinTime;
    }

    @Override
    public void setSpinTime(Integer spinTime) {
        this.spinTime.setText(spinTime.toString());
    }

    @Override
    public void randomizer() {
        goToRandomPrize(colorPane);
        setRandomTime();
    }

    @Override
    public void setPalette(PaletteEnum palette, boolean shuffle){
        setWheelPalette(wheelPane, labelPane, palette, shuffle);
    }

}
