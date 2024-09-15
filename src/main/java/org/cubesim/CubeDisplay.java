package org.cubesim;

import org.worldcubeassociation.tnoodle.puzzle.ThreeByThreeCubePuzzle;
import org.worldcubeassociation.tnoodle.scrambles.Puzzle;

import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class CubeDisplay extends JPanel {
    private final Color[] displayedColors = new Color[27];
    private final Cube cube = new Cube();
    private final JButton scrambleButton = new JButton("Scramble Cube");
    private final JLabel timeLabel = new JLabel("Time: 0.00");
    private boolean cubeScrambled = false;
    private final DecimalFormat df = new DecimalFormat("#.##");
    private long startTime;
    private float n;
    private final Timer timer = new Timer(0, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            float n = (float) (System.currentTimeMillis() - startTime) / 1000;
            if (n < 60) {
                timeLabel.setText("Time: " + df.format(n));
            } else if (n < 600){
                timeLabel.setText("Time: "+ (int) n / 60 + ":" + df.format(n % 60));
            } else {
                timeLabel.setText("Time: TimeOut");
                timer.stop();
            }
        }
    });

    public CubeDisplay() {
        timer.setInitialDelay(0);
        startTime = System.currentTimeMillis();

        JFrame frame = new JFrame("Rubik's Cube");
        try {
            BufferedImage image;
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/cubeicon2.png")));
            frame.setIconImage(image);
        }
        catch(IOException ignored) {

        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setBackground(Color.GRAY);
        frame.setVisible(true);
        frame.setResizable(false);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setFont(new Font(timeLabel.getFont().getName(), timeLabel.getFont().getStyle(), 20));
        this.add(timeLabel);
        this.add(scrambleButton);

        frame.pack();
        updateCube();
        scrambleButton.setLayout(null);
        scrambleButton.setVisible(true);

        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_R) {
                    if (e.isControlDown() && e.isShiftDown()) {
                        cube.turn("L", "CCW");
                        cube.rotate("X", "CCW");
                    } else if (e.isControlDown()) {
                        cube.turn("L", "CW");
                        cube.rotate("X", "CW");
                    } else if (e.isShiftDown()) {
                        cube.turn("R", "CCW");
                    } else {
                        cube.turn("R", "CW");
                    } if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_U) {
                    if (e.isControlDown() && e.isShiftDown()) {
                        cube.turn("D", "CCW");
                        cube.rotate("Y", "CCW");
                    } else if (e.isControlDown()) {
                        cube.turn("D", "CW");
                        cube.rotate("Y", "CW");
                    } else if (e.isShiftDown()) {
                        cube.turn("U", "CCW");
                    } else {
                        cube.turn("U", "CW");
                    } if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_L) {
                    if (e.isControlDown() && e.isShiftDown()) {
                        cube.turn("R", "CCW");
                        cube.rotate("X", "CW");
                    } else if (e.isControlDown()) {
                        cube.turn("R", "CW");
                        cube.rotate("X", "CCW");
                    } else if (e.isShiftDown()) {
                        cube.turn("L", "CCW");
                    } else {
                        cube.turn("L", "CW");
                    } if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    if (e.isControlDown() && e.isShiftDown()) {
                        cube.turn("U", "CCW");
                        cube.rotate("Y", "CW");
                    } else if (e.isControlDown()) {
                        cube.turn("U", "CW");
                        cube.rotate("Y", "CCW");
                    } else if (e.isShiftDown()) {
                        cube.turn("D", "CCW");
                    } else {
                        cube.turn("D", "CW");
                    } if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_B) {
                    if (e.isControlDown() && e.isShiftDown()) {
                        cube.turn("F", "CCW");
                        cube.rotate("Z", "CW");
                    } else if (e.isControlDown()) {
                        cube.turn("F", "CW");
                        cube.rotate("Z", "CCW");
                    } else if (e.isShiftDown()) {
                        cube.turn("B", "CCW");
                    } else {
                        cube.turn("B", "CW");
                    } if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_F) {
                    if (e.isControlDown() && e.isShiftDown()) {
                        cube.turn("B", "CCW");
                        cube.rotate("Z", "CCW");
                    } else if (e.isControlDown()) {
                        cube.turn("B", "CW");
                        cube.rotate("Z", "CW");
                    } else if (e.isShiftDown()) {
                        cube.turn("F", "CCW");
                    } else {
                        cube.turn("F", "CW");
                    } if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_X) {
                    if (e.isShiftDown()) {
                        cube.rotate("X", "CCW");
                    } else {
                        cube.rotate("X", "CW");
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_Y) {
                    if (e.isShiftDown()) {
                        cube.rotate("Y", "CCW");
                    } else {
                        cube.rotate("Y", "CW");
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    if (e.isShiftDown()) {
                        cube.rotate("Z", "CCW");
                    } else {
                        cube.rotate("Z", "CW");
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_M) {
                    if (e.isShiftDown()) {
                        cube.turn("R", "CCW");
                        cube.turn("L", "CW");
                        cube.rotate("X", "CW");
                    } else {
                        cube.turn("R", "CW");
                        cube.turn("L", "CCW");
                        cube.rotate("X", "CCW");
                    }
                    if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_E) {
                    if (e.isShiftDown()) {
                        cube.turn("U", "CCW");
                        cube.turn("D", "CW");
                        cube.rotate("Y", "CW");
                    } else {
                        cube.turn("U", "CW");
                        cube.turn("D", "CCW");
                        cube.rotate("Y", "CCW");
                    }
                    if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                }
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    if (e.isShiftDown()) {
                        cube.turn("F", "CW");
                        cube.turn("B", "CCW");
                        cube.rotate("Z", "CCW");
                    } else {
                        cube.turn("F", "CCW");
                        cube.turn("B", "CW");
                        cube.rotate("Z", "CW");
                    }
                    if (cubeScrambled) {
                        timer.start();
                        startTime = System.currentTimeMillis();
                        cubeScrambled = false;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_T) {
                    timeLabel.setVisible(!timeLabel.isShowing());
                }
                if (cube.isSolved()) {
                    timer.stop();
                }
                updateCube();
                repaint();
            }
        };

        frame.addKeyListener(keyAdapter);
        scrambleButton.addKeyListener(keyAdapter);
        scrambleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cube.resetCube();
                //Uses tnoodle as a dependency to generate scrambles
                //https://github.com/thewca/tnoodle
                Puzzle puzzle = new ThreeByThreeCubePuzzle();
                Random r = new Random();
                String scramble = puzzle.generateWcaScramble(r);
                System.out.println(scramble);
                parseScramble(scramble);
                cubeScrambled = true;
                timer.stop();
                n = 0;
                timeLabel.setText("Time: 0.00");
                updateCube();
                repaint();
            }
        });

    }


    public void parseScramble(String scramble) {
        String[] scrambleArr = scramble.split(" ");
        for (int i = 0; i < scrambleArr.length; i++) {
            String direction = "CCW";
            String side = Character.toString(scrambleArr[i].charAt(0));
            if (scrambleArr[i].length() == 1) {
                direction = "CW";
            } else if (scrambleArr[i].charAt(1) == '2') {
                cube.turn(side, direction);
            }
            cube.turn(side, direction);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        timeLabel.setLocation(150, 0);//180, 10
        scrambleButton.setLocation(133, 320);
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 400, 400);
        g.setColor(displayedColors[0]);
        drawQuad(g, 125, 110, 125, 160, 90, 140, 90, 90); // LFU F
        g.setColor(displayedColors[1]);
        drawQuad(g, 160, 128, 160, 178, 125, 160, 125, 110);// FU F
        g.setColor(displayedColors[2]);
        drawQuad(g, 200, 150, 200, 200, 160, 178, 160, 128);// FUR F
        g.setColor(displayedColors[3]);
        drawQuad(g, 200, 200, 200, 250, 160, 228, 160, 178);// FR F
        g.setColor(displayedColors[4]);
        drawQuad(g, 200, 250, 200, 300, 160, 278, 160, 228);// FRD F
        g.setColor(displayedColors[5]);
        drawQuad(g, 160, 178, 160, 228, 125, 210, 125, 160);// CENTER F
        g.setColor(displayedColors[6]);
        drawQuad(g, 160, 228, 160, 278, 125, 260, 125, 210); // FD F
        g.setColor(displayedColors[7]);
        drawQuad(g, 125, 160, 125, 210, 90, 190, 90, 140); // FL F
        g.setColor(displayedColors[8]);
        drawQuad(g, 125, 210, 125, 260, 90, 240, 90, 190); // LFD F
        // Right Face
        g.setColor(displayedColors[9]);
        drawQuad(g, 240, 178, 240, 128, 200, 150, 200, 200); // FRU R
        g.setColor(displayedColors[10]);
        drawQuad(g, 240, 228, 240, 178, 200, 200, 200, 250); // FR R
        g.setColor(displayedColors[11]);
        drawQuad(g, 240, 278, 240, 228, 200, 250, 200, 300); // FRD R
        g.setColor(displayedColors[12]);
        drawQuad(g, 275, 158, 275, 108, 240, 130, 240, 180); // RU R
        g.setColor(displayedColors[13]);
        drawQuad(g, 275, 208, 275, 158, 240, 180, 240, 230); // Center R
        g.setColor(displayedColors[14]);
        drawQuad(g, 275, 258, 275, 208, 240, 230, 240, 280); // RD R
        g.setColor(displayedColors[15]);
        drawQuad(g, 310, 140, 310, 90, 275, 110, 275, 160); // RBU R
        g.setColor(displayedColors[16]);
        drawQuad(g, 310, 190, 310, 140, 275, 160, 275, 210); // RB R
        g.setColor(displayedColors[17]);
        drawQuad(g, 310, 240, 310, 190, 275, 210, 275, 260); // RBD R
        // Up Face
        g.setColor(displayedColors[18]);
        drawQuad(g, 200, 150, 240, 128, 200, 108, 162, 129); // FRU U
        g.setColor(displayedColors[19]);
        drawQuad(g, 202, 108, 235, 90, 200, 70, 167, 89); // Center U
        g.setColor(displayedColors[20]);
        drawQuad(g, 125, 110, 165, 88, 133, 70, 90, 90); // FUL U
        g.setColor(displayedColors[21]);
        drawQuad(g, 275, 108, 310, 90, 270, 72, 235, 90); // RUB U
        g.setColor(displayedColors[22]);
        drawQuad(g, 200, 108, 240, 128, 275, 108, 235, 90); // RU U
        g.setColor(displayedColors[23]);
        drawQuad(g, 200, 108, 167, 89, 125, 110, 162, 129); // FU U
        g.setColor(displayedColors[24]);
        drawQuad(g, 200, 70, 165, 88, 133, 70, 170, 55); // UL U
        g.setColor(displayedColors[25]);
        drawQuad(g, 270, 72, 235, 90, 200, 70, 230, 55); // UB U
        g.setColor(displayedColors[26]);
        drawQuad(g, 230, 55, 200, 70, 170, 55, 200, 40); // UBL U

        // outline
        g.setColor(Color.BLACK);
        g.drawLine(200, 150, 200, 300);
        g.drawLine(200, 150, 310, 90);
        g.drawLine(200, 150, 90, 90);
        g.drawLine(200, 300, 310, 240);
        g.drawLine(200, 300, 90, 240);
        g.drawLine(90, 240, 90, 90);
        g.drawLine(310, 240, 310, 90);
        g.drawLine(90, 90, 200, 40);
        g.drawLine(310, 90, 200, 40);

        // lines
        g.drawLine(125, 110, 125, 260);
        g.drawLine(125, 110, 230, 55);
        g.drawLine(160, 130, 160, 280);
        g.drawLine(160, 130, 270, 72);

        g.drawLine(240, 130, 240, 280);
        g.drawLine(240, 130, 135, 70);
        g.drawLine(275, 110, 275, 260);
        g.drawLine(275, 110, 170, 55);

        g.drawLine(200, 200, 310, 140);
        g.drawLine(200, 200, 90, 140);
        g.drawLine(200, 250, 310, 190);
        g.drawLine(200, 250, 90, 190);

    }

    public void drawQuad(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        g.fillPolygon(new int[] { x1, x2, x3, x4 }, new int[] { y1, y2, y3, y4 }, 4);
    }

    public void updateCube() {
        HashMap<String, Color> colors = new HashMap<String, Color>();
        colors.put("red", Color.RED);
        colors.put("orange", Color.ORANGE);
        colors.put("yellow", Color.YELLOW);
        colors.put("green", Color.GREEN);
        colors.put("blue", Color.BLUE);
        colors.put("white", Color.WHITE);

        for (int i = 0; i < cube.getCorners().length; i++) {
            HashMap<String, String> stickerFaces = new HashMap<String, String>();
            stickerFaces.put(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getColor1());
            stickerFaces.put(cube.getCorners()[i].getFace2(), cube.getCorners()[i].getColor2());
            stickerFaces.put(cube.getCorners()[i].getFace3(), cube.getCorners()[i].getColor3());
            if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getLeft(), cube.getFront(), cube.getUp())) {
                // LFU F color1
                displayedColors[0]= colors.get(stickerFaces.get(cube.getFront()));
                // LFU U displayedColors[20]
                displayedColors[20] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getUp(), cube.getBack(), cube.getLeft())) {
                // UBL U displayedColors[26]
                displayedColors[26] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getLeft(), cube.getFront(), cube.getDown())) {
                // LFD F displayedColors[8]
                displayedColors[8] = colors.get(stickerFaces.get(cube.getFront()));
            } else if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getFront(), cube.getUp(), cube.getRight())) {
                // FUR F color 3
                displayedColors[2] = colors.get(stickerFaces.get(cube.getFront()));
                // FUR R displayedColors[9]
                displayedColors[9] = colors.get(stickerFaces.get(cube.getRight()));
                // FUR U color 19
                displayedColors[18] = colors.get(stickerFaces.get(cube.getUp()));

            } else if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getRight(), cube.getBack(), cube.getUp())) {
                // RBU R displayedColors[15]
                displayedColors[15] = colors.get(stickerFaces.get(cube.getRight()));
                // RBU U displayedColors[21]
                displayedColors[21] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getRight(), cube.getBack(), cube.getDown())) {
                // RBD R displayedColors[17]
                displayedColors[17] = colors.get(stickerFaces.get(cube.getRight()));

            } else if (compareCorners(cube.getCorners()[i].getFace1(), cube.getCorners()[i].getFace2(), cube.getCorners()[i].getFace3(), cube.getRight(), cube.getFront(), cube.getDown())) {
                // FRD R displayedColors[11]
                displayedColors[11] = colors.get(stickerFaces.get(cube.getRight()));
                // FRD F displayedColors[4]
                displayedColors[4] = colors.get(stickerFaces.get(cube.getFront()));
            }

        }

        for (int i = 0; i < cube.getEdges().length; i++) {
            HashMap<String, String> stickerFaces = new HashMap<String, String>();
            stickerFaces.put(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getColor1());
            stickerFaces.put(cube.getEdges()[i].getFace2(), cube.getEdges()[i].getColor2());
            if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getFront(), cube.getUp())) {
                // FU F color2
                displayedColors[1] = colors.get(stickerFaces.get(cube.getFront()));
                // FU U displayedColors[23]
                displayedColors[23] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getRight(), cube.getUp())) {
                // RU R displayedColors[12]
                displayedColors[12] = colors.get(stickerFaces.get(cube.getRight()));
                // RU U displayedColors[22]
                displayedColors[22] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getLeft(), cube.getUp())) {
                // LU U displayedColors[24]
                displayedColors[24] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getUp(), cube.getBack())) {
                // UB U displayedColors[25]
                displayedColors[25] = colors.get(stickerFaces.get(cube.getUp()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getFront(), cube.getRight())) {
                // FR F displayedColors[3]
                displayedColors[3] = colors.get(stickerFaces.get(cube.getFront()));
                // FR R displayedColors[10]
                displayedColors[10] = colors.get(stickerFaces.get(cube.getRight()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getLeft(), cube.getFront())) {
                // FL F displayedColors[7]
                displayedColors[7] = colors.get(stickerFaces.get(cube.getFront()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getRight(), cube.getBack())) {
                // RB R displayedColors[16]
                displayedColors[16] = colors.get(stickerFaces.get(cube.getRight()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getFront(), cube.getDown())) {
                // FD F displayedColors[6]
                displayedColors[6] = colors.get(stickerFaces.get(cube.getFront()));
            } else if (compareEdges(cube.getEdges()[i].getFace1(), cube.getEdges()[i].getFace2(), cube.getRight(), cube.getDown())) {
                // RD R displayedColors[14]
                displayedColors[14] = colors.get(stickerFaces.get(cube.getRight()));
            }
        }

        HashMap<String, String> stickerFaces = new HashMap<String, String>();
        stickerFaces.put("G", "green");
        stickerFaces.put("B", "blue");
        stickerFaces.put("R", "red");
        stickerFaces.put("W", "white");
        stickerFaces.put("O", "orange");
        stickerFaces.put("Y", "yellow");
        // Center F displayedColors[5]
        displayedColors[5] = colors.get(stickerFaces.get(cube.getFront()));
        // Center R displayedColors[13]
        displayedColors[13] = colors.get(stickerFaces.get(cube.getRight()));
        // Center U displayedColors[19]
        displayedColors[19] = colors.get(stickerFaces.get(cube.getUp()));

    }

    public boolean compareCorners(String corner1Sticker1, String corner1Sticker2, String corner1Sticker3, String corner2Sticker1, String corner2Sticker2, String corner2Sticker3) {
        Set<String> corner1Stickers = new HashSet<String>();
        corner1Stickers.add(corner1Sticker1);
        corner1Stickers.add(corner1Sticker2);
        corner1Stickers.add(corner1Sticker3);
        Set<String> corner2Stickers = new HashSet<String>();
        corner2Stickers.add(corner2Sticker1);
        corner2Stickers.add(corner2Sticker2);
        corner2Stickers.add(corner2Sticker3);
        return corner1Stickers.equals(corner2Stickers);
    }

    public boolean compareEdges(String edge1Sticker1, String edge1Sticker2, String edge2Sticker1, String edge2Sticker2) {
        Set<String> edge1Stickers = new HashSet<String>();
        edge1Stickers.add(edge1Sticker1);
        edge1Stickers.add(edge1Sticker2);
        Set<String> edge2Stickers = new HashSet<String>();
        edge2Stickers.add(edge2Sticker1);
        edge2Stickers.add(edge2Sticker2);
        return edge1Stickers.equals(edge2Stickers);
    }

    public Cube getCube() {
        return cube;
    }
}
