package ventus.rggwheel.controllers.mono;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ventus.rggwheel.controllers.WheelController;
import ventus.rggwheel.model.PaletteEnum;
import ventus.rggwheel.utils.WheelUtils;

import java.util.stream.Collectors;


public class WheelMonoController extends WheelController {
    @FXML private Label spinTime;
    @FXML private ImageView wheelMono;
    @FXML private AnchorPane monoPane;
    @FXML private AnchorPane labelPane;
    @FXML private AnchorPane wheelPane;

    @FXML
    void initialize(){
        monoPane.setRotate(monoPane.getRotate() + WheelUtils.getAngle()/2 + 7200);
        prizesList.addAll(labelPane.getChildren().stream().map(child -> (Label) child).collect(Collectors.toList()));
    }

    @Override
    public void spinWheel(){
        String label = spinTime.getText();
        Duration duration = Duration.seconds(Integer.parseInt(label));
        spin(duration, monoPane, WheelUtils.getRandomAngle(monoPane.getRotate(), Integer.parseInt(label)));
    }

    @Override
    public void setRotate(Double angle) {
        monoPane.setRotate(angle);
    }

    @Override
    public void moveToNext() {
        this.goToNext(monoPane);
    }

    @Override
    public void moveToPrevious() {
        this.goBack(monoPane);
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
        setPalette(PaletteEnum.GB_4, true);
        goToRandomPrize(monoPane);
        setRandomTime();
    }

    @Override
    public void setPalette(PaletteEnum palette, boolean shuffle){
        setWheelPalette(wheelPane, labelPane, palette, shuffle);
    }
}
