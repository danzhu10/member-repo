package com.member.test.item;

import java.io.Serializable;

public class AwardItem implements Serializable {

    private String type;
    private int point;
    private String title;
    private String image;

    public AwardItem(String type, int point, String title, String image) {
        this.type = type;
        this.point = point;
        this.title = title;
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public int getPoint() {
        return point;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
