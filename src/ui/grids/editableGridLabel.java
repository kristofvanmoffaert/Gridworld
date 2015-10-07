/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.grids;

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
class editableGridLabel extends JLabel {

    gridState object;
    gridMenu menu;
    editableGridLabel copy;

    public editableGridLabel(gridState element, gridMenu m) {
        super();
        object = element;
        //ImageIcon icon = new ImageIcon(object.getImageUrl());
        //setIcon(icon);
        setText(null);
        menu = m;

        setBorder(new BevelBorder(1));
        addMouseListener(new PopupTriggerListener());
        copy = this;

    }

    public gridState getObject() {
        return object;
    }

    public void setObject(gridState o) {
        object = o;
    }
/*
  private AlphaComposite makeComposite(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return(AlphaComposite.getInstance(type, alpha));
  }


      private void paintArrow(Graphics2D g2, int x0, int y0, int x1,int y1){
	int deltaX = x1 - x0;
	int deltaY = y1 - y0;
	double frac = 0.2;
        Stroke def = g2.getStroke();
        g2.setStroke(new BasicStroke(3));
	g2.drawLine(x0,y0,x1,y1);

        
        
	g2.drawLine(x0 + (int)((1-frac)*deltaX + frac*deltaY),
		   y0 + (int)((1-frac)*deltaY - frac*deltaX),
		   x1, y1);
	g2.drawLine(x0 + (int)((1-frac)*deltaX - frac*deltaY),
		   y0 + (int)((1-frac)*deltaY + frac*deltaX),
		   x1, y1);
        g2.setStroke(def);
    }


*/
    public void paintComponent(Graphics g) {
        try {
            super.paintComponent(g);
            //Graphics2D g2d = (Graphics2D) g;
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            BufferedImage image = ImageIO.read(classLoader.getResourceAsStream(object.getImageUrl()));
            ImageIcon icon = new ImageIcon(image);
            //Composite originalComposite = g2d.getComposite();
            //g2d.setComposite(makeComposite(0.5f));
            g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
            //g2d.setComposite(originalComposite);
            //g2d.setPaint(Color.blue);
            //g.fillPolygon(uptrix, uptriy, 3);
            //paintArrow(g2d, getWidth()/2, getHeight()/2, 3, getHeight()/2);
            //g.setColor(Color.white);
            //g.setFont(new Font("Serif",0,10));
            //	    g.drawString(df.format(0.15569),15, getHeight()/2 -5);
        } catch (IOException ex) {
            System.out.println("hier");
            Logger.getLogger(editableGridLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    class PopupTriggerListener extends MouseAdapter {

        public void mousePressed(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                menu.show(copy, ev.getComponent(), ev.getX(), ev.getY());

            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                menu.show(copy, ev.getComponent(), ev.getX(), ev.getY());
            }

        }

        public void mouseClicked(MouseEvent ev) {
        }
    }
}
