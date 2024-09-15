package org.cubesim;


import java.util.HashMap;

public class Cube {
    private final Edge[] edges;
    private final Corner[] corners;
    private String up;
    private String down;
    private String left;
    private String right;
    private String front;
    private String back;
    private final HashMap<String, String> opposites;
    public Cube() {
        edges = new Edge[] {
                new Edge("blue", "B", "white", "W"),
                new Edge("red", "R", "white", "W"),
                new Edge("orange", "O", "white", "W"),
                new Edge("green", "G", "white", "W"),
                new Edge("yellow", "Y", "blue", "B"),
                new Edge("yellow", "Y", "red", "R"),
                new Edge("yellow", "Y", "orange", "O"),
                new Edge("yellow", "Y", "green", "G"),
                new Edge("red", "R", "blue", "B"),
                new Edge("orange", "O", "blue", "B"),
                new Edge("green", "G", "orange", "O"),
                new Edge("red", "R", "green", "G")
        };

        corners = new Corner[] {
                new Corner("white", "W", "blue", "B", "red", "R"),
                new Corner("white", "W", "red", "R", "green", "G"),
                new Corner("white", "W", "orange", "O", "green", "G"),
                new Corner("white", "W", "orange", "O", "blue", "B"),
                new Corner("yellow", "Y", "blue", "B", "red", "R"),
                new Corner("yellow", "Y", "green", "G", "orange", "O"),
                new Corner("yellow", "Y", "orange", "O", "blue", "B"),
                new Corner("yellow", "Y", "red", "R", "green", "G")
        };
        up = "W";
        front = "G";
        down = "Y";
        back = "B";
        left = "O";
        right = "R";

        opposites = new HashMap<String, String>();
        opposites.put(front, back);
        opposites.put(left, right);
        opposites.put(up, down);
        opposites.put(back, front);
        opposites.put(right, left);
        opposites.put(down, up);
    }

    public void turn(String face, String direction) {
        String side = switch (face) {
            case "U" -> up;
            case "D" -> down;
            case "L" -> left;
            case "R" -> right;
            case "F" -> front;
            default -> back;
        };

        for (int i = 0; i < corners.length; i++) {
            // Checks weather piece is on face we turn and turns it
            if (corners[i].getFace1().equals(side)) {
                corners[i].setFace2(next(face, corners[i].getFace2(), direction));
                corners[i].setFace3(next(face, corners[i].getFace3(), direction));
            } else if (corners[i].getFace2().equals(side)) {
                corners[i].setFace1(next(face, corners[i].getFace1(), direction));
                corners[i].setFace3(next(face, corners[i].getFace3(), direction));
            } else if (corners[i].getFace3().equals(side)) {
                corners[i].setFace1(next(face, corners[i].getFace1(), direction));
                corners[i].setFace2(next(face, corners[i].getFace2(), direction));
            }
        }

        for (int i = 0; i < edges.length; i++) {
            if (edges[i].getFace1().equals(side)) {
                edges[i].setFace2(next(face, edges[i].getFace2(), direction));
            } else if (edges[i].getFace2().equals(side)) {
                edges[i].setFace1(next(face, edges[i].getFace1(), direction));
            }
        }
    }

    public void rotate(String type, String direction) {
        if (type.equals("X") && direction.equals("CW")) {
            String temp = up;
            up = front;
            front = down;
            down = back;
            back = temp;
        } else if (type.equals("X") && direction.equals("CCW")) {
            String temp = up;
            up = back;
            back = down;
            down = front;
            front = temp;
        } else if (type.equals("Y") && direction.equals("CCW")) {
            String temp = left;
            left = back;
            back = right;
            right = front;
            front = temp;
        } else if (type.equals("Y") && direction.equals("CW")) {
            String temp = left;
            left = front;
            front = right;
            right = back;
            back = temp;
        } else if (type.equals("Z") && direction.equals("CW")) {
            String temp = up;
            up = left;
            left = down;
            down = right;
            right = temp;
        } else if (type.equals("Z") && direction.equals("CCW")) {
            String temp = up;
            up = right;
            right = down;
            down = left;
            left = temp;
        }

    }


    public String next(String face, String side, String direction) {
        HashMap<String, String> cubeMap1 = new HashMap<String, String>(); //UD
        cubeMap1.put(front, left);
        cubeMap1.put(right, front);
        cubeMap1.put(left, back);
        cubeMap1.put(back, right);
        HashMap<String, String> cubeMap2 = new HashMap<String, String>(); //RL
        cubeMap2.put(down, front);
        cubeMap2.put(front, up);
        cubeMap2.put(up, back);
        cubeMap2.put(back, down);
        HashMap<String, String> cubeMap3 = new HashMap<String, String>(); //FB
        cubeMap3.put(up, right);
        cubeMap3.put(right, down);
        cubeMap3.put(down, left);
        cubeMap3.put(left, up);

        if (direction.equals("CW")) {
            return switch(face) {
                case "U" -> cubeMap1.get(side);
                case "R" -> cubeMap2.get(side);
                case "F" -> cubeMap3.get(side);
                case "D" -> cubeMap1.get(opposites.get(side));
                case "L" -> cubeMap2.get(opposites.get(side));
                case "B" -> cubeMap3.get(opposites.get(side));
                default -> null;
            };
        }

        return switch(face) {
            case "U" -> cubeMap1.get(opposites.get(side));
            case "R" -> cubeMap2.get(opposites.get(side));
            case "F" -> cubeMap3.get(opposites.get(side));
            case "D" -> cubeMap1.get(side);
            case "L" -> cubeMap2.get(side);
            case "B" -> cubeMap3.get(side);
            default -> null;
        };
    }

    public void printCube() {
        for (int i = 0; i < corners.length; i++) {
            System.out.println("Corner " + i + ": " + corners[i]);
            System.out.println();
        }

        for (int i = 0; i < edges.length; i++) {
            System.out.println("Edge " + i + ": " + edges[i]);
            System.out.println();
        }
    }

    public boolean isSolved() {
        HashMap<String, String> stickerFaces = new HashMap<String, String>();
        stickerFaces.put("G", "green");
        stickerFaces.put("B", "blue");
        stickerFaces.put("R", "red");
        stickerFaces.put("W", "white");
        stickerFaces.put("O", "orange");
        stickerFaces.put("Y", "yellow");
        for (int i = 0; i < corners.length; i++) {
            if (!stickerFaces.get(corners[i].getFace1()).equals(corners[i].getColor1()) || !stickerFaces.get(corners[i].getFace2()).equals(corners[i].getColor2()) || !stickerFaces.get(corners[i].getFace3()).equals(corners[i].getColor3())) {
                return false;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            if (!stickerFaces.get(edges[i].getFace1()).equals(edges[i].getColor1()) || !stickerFaces.get(edges[i].getFace2()).equals(edges[i].getColor2())) {
                return false;
            }
        }
        return true;
    }

    public void resetCube() {
        HashMap<String, String> stickerFaces = new HashMap<String, String>();
        stickerFaces.put("green", "G");
        stickerFaces.put("blue", "B");
        stickerFaces.put("red", "R");
        stickerFaces.put("white", "W");
        stickerFaces.put("orange", "O");
        stickerFaces.put("yellow", "Y");
        for (int i = 0; i < corners.length; i++) {
            corners[i].setFace1(stickerFaces.get(corners[i].getColor1()));
            corners[i].setFace2(stickerFaces.get(corners[i].getColor2()));
            corners[i].setFace3(stickerFaces.get(corners[i].getColor3()));
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i].setFace1(stickerFaces.get(edges[i].getColor1()));
            edges[i].setFace2(stickerFaces.get(edges[i].getColor2()));
        }
    }

    public Edge[] getEdges() {
        return edges;
    }

    public Corner[] getCorners() {
        return corners;
    }

    public String getUp() {
        return up;
    }

    public String getDown() {
        return down;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }
}