package ventus.rggwheel.controllers;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import ventus.rggwheel.RetroBoyModesEnum;

public class BackgroundController {
    private final Rectangle screen;
    private final Label retroboyName;

    public BackgroundController(Rectangle screen, Label retroboyName) {
        this.screen = screen;
        this.retroboyName = retroboyName;
    }

    private RetroBoyModesEnum backgroundMode = RetroBoyModesEnum.GBC;

    public void switchBackground(){
        screen.setFill(backgroundMode.getBackground());
        retroboyName.setText(backgroundMode.getRetroboyName());
        if(backgroundMode == RetroBoyModesEnum.GBC){
            backgroundMode = RetroBoyModesEnum.GB;
        } else {
            backgroundMode = RetroBoyModesEnum.GBC;
        }
    }

}
