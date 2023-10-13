import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tileset {

    private int width;
    private int height;

    private ArrayList<BufferedImage> images;

    public Tileset(ArrayList<BufferedImage> images) {
        this.images = images;
        for (BufferedImage image : images){
            addWidth(image.getWidth());
            addHeight(image.getHeight());
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private void addWidth(int width){
        setWidth(getWidth() + width);
    }

    private int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    private void addHeight(int height){
        setHeight(getHeight() + height);
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    // TODO: 13/10/2023 Comprendre pourquoi l'image final à une longueur et une largeur aussi grande 
    public BufferedImage getTilesetImage(){
        BufferedImage tilesetImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = tilesetImage.createGraphics();

        int x = 0;

        for (BufferedImage image : getImages()) {
            g.drawImage(image, x, 0, null);
            x += image.getWidth();
        }
        g.dispose();

        return tilesetImage;
    }
}
