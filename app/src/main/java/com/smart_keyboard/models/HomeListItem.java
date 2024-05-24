package com.smart_keyboard.models;

public class HomeListItem {
    private int iconResourcesId;
    private String title;
    private String description;

    public HomeListItem(int iconResourcesId, String title, String description) {
        this.iconResourcesId = iconResourcesId;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getIconResourcesId() {
        return iconResourcesId;
    }

    public void setIconResourcesId(int iconResourcesId) {
        this.iconResourcesId = iconResourcesId;
    }
}
