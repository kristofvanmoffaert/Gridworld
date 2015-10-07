/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.grids;

import core.states.Action;
import core.states.Direction;
import core.states.gridState;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author kristof
 */
class walkableGridLabel extends JLabel {

    gridState object;
    DecimalFormat df = new DecimalFormat("0.0##");
    boolean hasAgent = false;

    public walkableGridLabel(gridState element) {
        super();
        object = element;
        //ImageIcon icon = new ImageIcon(object.getImageUrl());
        //setIcon(icon);
        setText(null);

        setBorder(new BevelBorder(1));

    }

    public gridState getObject() {
        return object;
    }

    public void agentEntered() {
        hasAgent = true;
    }

    public void agentLeft() {
        hasAgent = false;
    }

    public void setObject(gridState o) {
        object = o;
    }

    private AlphaComposite makeComposite(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    private void paintArrow(Graphics2D g2, int x0, int y0, int x1, int y1) {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;
        double frac = 0.2;
        Stroke def = g2.getStroke();
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(x0, y0, x1, y1);



        g2.drawLine(x0 + (int) ((1 - frac) * deltaX + frac * deltaY),
                y0 + (int) ((1 - frac) * deltaY - frac * deltaX),
                x1, y1);
        g2.drawLine(x0 + (int) ((1 - frac) * deltaX - frac * deltaY),
                y0 + (int) ((1 - frac) * deltaY + frac * deltaX),
                x1, y1);
        g2.setStroke(def);
    }

    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            BufferedImage image = ImageIO.read(classLoader.getResourceAsStream(object.getImageUrl()));
            ImageIcon icon = new ImageIcon(image);

            Composite originalComposite = g2d.getComposite();
            g2d.setComposite(makeComposite(0.5f));
            g2d.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
            g2d.setComposite(originalComposite);
            if (hasAgent) {
                BufferedImage image2 = ImageIO.read(classLoader.getResourceAsStream("images/agent (copy)3.png"));
                ImageIcon icon2 = new ImageIcon(image2);
                g2d.drawImage(icon2.getImage(), 0, 0, getWidth(), getHeight(), null);
            }

            g2d.setPaint(Color.blue);
            //g.fillPolygon(uptrix, uptriy, 3);
            //for (int i=0; i < object.getActions().size(); i++) {
            if (!object.isEqualActionProbability()) {
                object.printActions();
                Action currentAction = object.getPolicy();
                g.setFont(new Font("Serif", 0, 10));
                // for (int i=0; i < object.getActions().size(); i++) {
                //     Action currentAction = object.getActions().get(i);
                if (currentAction != null) {
                    switch (currentAction.getDirection()) {
                        case RIGHT:
                            paintArrow(g2d, getWidth() / 2, getHeight() / 2, getWidth() - 3, getHeight() / 2);
                            g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2 - 15, getHeight() / 2 - 5);
                            break;
                        case LEFT:
                            paintArrow(g2d, getWidth() / 2, getHeight() / 2, 3, getHeight() / 2);
                            g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2 + 15, getHeight() / 2 - 5);
                            break;
                        case DOWN:
                            paintArrow(g2d, getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() - 3);
                            g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2, getHeight() / 3);
                            break;
                        case UP:
                            paintArrow(g2d, getWidth() / 2, getHeight() / 2, getWidth() / 2, 3);
                            g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2, (getHeight() / 3) * 2);
                            break;
                        case NONE:
                            break;
                    }
                }
                // }
            } else {
                for (int i = 0; i < object.getActions().size(); i++) {
                    Action currentAction = object.getActions().get(i);
                    if (currentAction != null) {
                        switch (currentAction.getDirection()) {
                            case RIGHT:
                                paintArrow(g2d, getWidth() / 2, getHeight() / 2, getWidth() - 3, getHeight() / 2);
                                //g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2 - 15, getHeight() / 2 - 5);
                                break;
                            case LEFT:
                                paintArrow(g2d, getWidth() / 2, getHeight() / 2, 3, getHeight() / 2);
                                //g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2 + 15, getHeight() / 2 - 5);
                                break;
                            case DOWN:
                                paintArrow(g2d, getWidth() / 2, getHeight() / 2, getWidth() / 2, getHeight() - 3);
                                //g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2, getHeight() / 3);
                                break;
                            case UP:
                                paintArrow(g2d, getWidth() / 2, getHeight() / 2, getWidth() / 2, 3);
                                //g.drawString(df.format(currentAction.getQvalue()), getWidth() / 2, (getHeight() / 3) * 2);
                                break;
                            case NONE:
                                break;
                        }
                    }
                }
                //paintArrow(g2d, getWidth() / 2, getHeight() / 2, 3, getHeight() / 2);
                //g.setColor(Color.white);
            }
        } catch (IOException ex) {
            Logger.getLogger(walkableGridLabel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //paintArrow(g2d, getWidth() / 2, getHeight() / 2, 3, getHeight() / 2);

        //g.setColor(Color.white);




    }
}
