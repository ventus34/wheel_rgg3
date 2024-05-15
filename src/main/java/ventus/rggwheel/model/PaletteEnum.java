package ventus.rggwheel.model;

import ventus.rggwheel.RetroBoyModesEnum;

public enum PaletteEnum {
    GB_1(RetroBoyModesEnum.GB, new String[]{"#d0d058", "#a0a840", "#708028", "#405010"}),
    GB_2(RetroBoyModesEnum.GB, new String[]{"#332c50", "#46878f", "#94e344", "#e2f3e4"}),
    GB_3(RetroBoyModesEnum.GB, new String[]{"#2c2137", "#446176", "#3fac95", "#a1ef8c"}),
    GB_4(RetroBoyModesEnum.GB, new String[]{"#547C3E", "#83BB62", "#A3CE8D", "#B3D6A0", "#50793C", "#466B34", "#55873E", "#819F71", "#ABC0A0", "#C5E0B7"}),
    GBC_1(RetroBoyModesEnum.GBC, new String[]{"#ff2121", "#ff9121", "#ffcf21", "#faff1f", "#c4ff1f", "#73ff21", "#21ff64", "#1fffd2", "#1fedff", "#1fbcff", "#1f90ff", "#1f69ff", "#1f51ff", "#1f25ff", "#5c1fff", "#7e1fff", "#af1fff", "#ff1ff8", "#ff1f92", "#ff1f58"});

    private final RetroBoyModesEnum mode;
    private final String[] colors;

    PaletteEnum(RetroBoyModesEnum mode, String[] colors) {
        this.colors = colors;
        this.mode = mode;
    }

    public String[] getColors() {
        return colors;
    }

    public RetroBoyModesEnum getMode() {
        return mode;
    }
}
