package com.aiautoshorts.editor.models;

import java.io.Serializable;

public class SubtitleStyle implements Serializable {
    private String fontName;
    private int fontSize;
    private String fontColor;
    private String highlightColor;

    public SubtitleStyle() {
        this.fontName = "Arial";
        this.fontSize = 24;
        this.fontColor = "#FFFFFF";
        this.highlightColor = "#00E676";
    }

    public String getFontName() { return fontName; }
    public void setFontName(String fontName) { this.fontName = fontName; }

    public int getFontSize() { return fontSize; }
    public void setFontSize(int fontSize) { this.fontSize = fontSize; }

    public String getFontColor() { return fontColor; }
    public void setFontColor(String fontColor) { this.fontColor = fontColor; }

    public String getHighlightColor() { return highlightColor; }
    public void setHighlightColor(String highlightColor) { this.highlightColor = highlightColor; }
}
