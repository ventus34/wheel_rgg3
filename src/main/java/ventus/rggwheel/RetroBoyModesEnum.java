package ventus.rggwheel;

import javafx.scene.paint.Color;

public enum RetroBoyModesEnum {
    GBC(Color.web("#000000"), Color.WHITE,"Retro Boy Color"),
    GB(Color.web("#9dbb7f"), Color.web("#9dbb7f"),"Retro Boy");

    private Color background;
    private Color prizeLabelColor;
    private String retroboyName;

    public Color getBackground() {
        return background;
    }

    public String getRetroboyName() {
        return retroboyName;
    }

    public Color getPrizeLabelColor() {
        return prizeLabelColor;
    }

    RetroBoyModesEnum(Color background, Color prizeLabelColor, String retroboyName) {
        this.background = background;
        this.prizeLabelColor = prizeLabelColor;
        this.retroboyName = retroboyName;
    }
}
