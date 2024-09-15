package org.cubesim;

public class Corner {
    private final String color1;
    private final String color2;
    private final String color3;
    private String face1;
    private String face2;
    private String face3;

    public Corner(String color1, String face1, String color2, String face2, String color3, String face3 ) {
        this.color1 = color1;
        this.face1 = face1;
        this.color2 = color2;
        this.face2 = face2;
        this.color3 = color3;
        this.face3 = face3;
    }
    public String getColor1() {
        return color1;
    }
    public String getFace1() {
        return face1;
    }
    public String getColor2() {
        return color2;
    }
    public String getFace2() {
        return face2;
    }
    public String getColor3() {
        return color3;
    }
    public String getFace3() {
        return face3;
    }
    public void setFace1(String face1) {
        this.face1 = face1;
    }

    public void setFace2(String face2) {
        this.face2 = face2;
    }

    public void setFace3(String face3) {
        this.face3 = face3;
    }
    public String toString() {
        return "Corner has colors: " + color1 + ", " + color2 + ", " + color3 + " which are on faces: " + face1 + ", " + face2 + ", " + face3 + " respectively";
    }

}