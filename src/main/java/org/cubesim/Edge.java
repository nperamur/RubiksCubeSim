package org.cubesim;

public class Edge {
    private final String color1;
    private final String color2;
    private String face1;
    private String face2;

    public Edge(String color1, String face1, String color2, String face2 ) {
        this.color1 = color1;
        this.face1 = face1;
        this.color2 = color2;
        this.face2 = face2;

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
    public void setFace1(String face1) {
        this.face1 = face1;
    }

    public void setFace2(String face2) {
        this.face2 = face2;
    }

    public String toString() {
        return "Edge has colors: " + color1 + ", " + color2 + " which are on faces: " + face1 + ", " + face2 + " respectively";
    }

}