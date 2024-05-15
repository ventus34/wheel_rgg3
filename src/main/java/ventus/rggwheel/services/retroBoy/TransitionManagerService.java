package ventus.rggwheel.services.retroBoy;

import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import ventus.rggwheel.RetroBoyModesEnum;
import ventus.rggwheel.controllers.BackgroundController;
import ventus.rggwheel.controllers.FXMLController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransitionManagerService {
    private final Map<RetroBoyModesEnum, ArrayList<FXMLController>> availableScenes;
    private final BackgroundController backgroundController;
    private RetroBoyModesEnum currentMode;
    private int currentScene;
    private final int numberOfScenes;

    public enum SceneEnum {
        SPLASH, WHEEL, PRIZE, INVENTORY
    }

    public TransitionManagerService(ArrayList<FXMLController> monoScenes, ArrayList<FXMLController> colorScenes, BackgroundController backgroundController) {
        Map<RetroBoyModesEnum, ArrayList<FXMLController>> scenes = new HashMap<>();
        scenes.put(RetroBoyModesEnum.GBC, monoScenes);
        scenes.put(RetroBoyModesEnum.GB, colorScenes);
        this.availableScenes = scenes;
        this.currentMode = RetroBoyModesEnum.GBC;
        this.currentScene = 0;
        this.backgroundController = backgroundController;
        if (monoScenes.size() == colorScenes.size()) {
            this.numberOfScenes = monoScenes.size();
        } else {
            throw new IllegalStateException("Number of scenes for each RetroBoy version have to be the same");
        }
    }

    public void switchScene() {
        if (this.currentScene == SceneEnum.SPLASH.ordinal()) {
            availableScenes.get(currentMode).get(this.currentScene).hide();
            this.currentScene = SceneEnum.WHEEL.ordinal();
        }
        availableScenes.get(currentMode).get(this.currentScene).hide();
        if (this.currentScene < numberOfScenes - SceneEnum.WHEEL.ordinal()) {
            availableScenes.get(currentMode).get(++this.currentScene).show();
        } else if (this.currentScene == numberOfScenes - SceneEnum.WHEEL.ordinal()) {
            this.currentScene = SceneEnum.PRIZE.ordinal();
            availableScenes.get(currentMode).get(this.currentScene).show();
        } else {
            throw new IllegalStateException("Scene doesnt exist");
        }


    }

    public void switchMode() {
        backgroundController.switchBackground();
        if (currentMode == RetroBoyModesEnum.GBC) {
            currentMode = RetroBoyModesEnum.GB;
            availableScenes.get(RetroBoyModesEnum.GBC).get(currentScene).hide();
            availableScenes.get(RetroBoyModesEnum.GB).get(currentScene).show();
        } else {
            currentMode = RetroBoyModesEnum.GBC;
            availableScenes.get(RetroBoyModesEnum.GB).get(currentScene).hide();
            availableScenes.get(RetroBoyModesEnum.GBC).get(currentScene).show();
        }
    }

    public boolean checkAndBack() {
        boolean isCheckScene;
        if (currentScene == SceneEnum.PRIZE.ordinal()) {
            availableScenes.get(currentMode).get(currentScene).hide();
            currentScene = SceneEnum.WHEEL.ordinal();
            availableScenes.get(currentMode).get(currentScene).show();
            isCheckScene = true;
        } else {
            availableScenes.get(currentMode).get(currentScene).hide();
            currentScene = SceneEnum.PRIZE.ordinal();
            availableScenes.get(currentMode).get(currentScene).show();
            isCheckScene = false;
        }
        return isCheckScene;
    }

    public boolean isInventory(){
        return currentScene == SceneEnum.INVENTORY.ordinal();
    }


    public RetroBoyModesEnum getCurrentMode() {
        return currentMode;
    }

    public SceneEnum getCurrentScene(){
        return SceneEnum.values()[currentScene-1];
    }
}
