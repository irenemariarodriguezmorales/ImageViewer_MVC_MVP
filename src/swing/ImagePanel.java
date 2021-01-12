package swing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ImagePanel extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;
    private BufferedImage bitmap2;
    private int offset;
    private Shift shift;

    public ImagePanel() { 
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paint(Graphics g) {
        //Box box = new Box(data.getWidth(),data.getHeight(), this.getWidth(), this.getHeight());
        //g.drawImage(data,box.x,box.y,box.width, box.height, null);
        if(bitmap != null) g.drawImage(bitmap,offset,0,null);
        if(bitmap2 != null && offset != 0) g.drawImage(bitmap2,offset < 0 ? bitmap.getWidth()+offset:offset-bitmap2.getWidth(),0,null);
        //Box box = new Box(bitmap.getWidth(),bitmap.getHeight(), this.getWidth(), this.getHeight());
        //g.drawImage(bitmap,box.x,box.y,box.width, box.height, null);
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.bitmap = loadBitmap(image);
        repaint();
    }

    private static BufferedImage loadBitmap(Image image) {
        try {
            return ImageIO.read(new File(image.getName()));
        }catch(IOException ex){
            return null;
        }
    }
    private Image imageAt(int shift) {
        if(shift > 0) return this.shift.left();
        if(shift < 0) return this.shift.right();
        return null;
    }

    @Override
    public Image currentImage() {
     return this.image;   
    }
    private BufferedImage read(File file) {
         return null;
        }   


    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }
    private static class Box {
        final int x;
        final int y;
        final int width;
        final int height;
        private Box(double imageWidth, double imageHeight, double panelWidth, double panelHeight) {
            double imageRatio = imageWidth / imageHeight;
            double panelRatio = panelWidth / panelHeight;
            this.width = (int) (imageRatio >= panelRatio ? panelWidth: imageWidth * panelHeight/imageHeight);
            this.height = (int) (imageRatio <= panelRatio ? panelHeight: imageHeight * panelWidth/imageWidth);
            this.x = (int) ((panelWidth - this.width)/2);
            this.y = (int) ((panelHeight - this.height)/2);
        }
    }    

    

    private class MouseHandler implements MouseListener, MouseMotionListener {

        private int initial;

        @Override
        public void mouseClicked(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
            this.initial = me.getX();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            int shift = shift(me.getX());
            if( Math.abs(shift) > getWidth() / 2) {
                image = imageAt(shift);
                bitmap = loadBitmap(image);
            }
            offset = 0;
            repaint();
        }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int shift = shift(e.getX());
                if(shift == 0) bitmap2 = null;
                else if (offset/shift <= 0) bitmap2 = loadBitmap(imageAt(shift));
                offset = shift;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }


            private int shift(int x) {
                return x - initial;
            }

        }
    }
    