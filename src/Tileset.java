import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tileset {

    private int maxWidth;
    private int maxHeight;

    private ArrayList<BufferedImage> images;

    public Tileset(ArrayList<BufferedImage> images) {
        this.images = images;
        for (BufferedImage image : images){
            if (image.getWidth() > getMaxWidth()) setMaxWidth(image.getWidth());
            if (image.getHeight() > getMaxHeight()) setMaxHeight(image.getHeight());
        }
    }


    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    private void addWidth(int width){
        setMaxWidth(getMaxWidth() + width);
    }

    private int getMaxHeight() {
        return maxHeight;
    }

    private void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    private void addHeight(int height){
        setMaxHeight(getMaxHeight() + height);
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    // TODO: 13/10/2023 Comprendre pourquoi l'image final à une longueur et une largeur aussi grande 
    public BufferedImage getTilesetImage(){
        int[] dividers = getNearestDividers(images.size());
        BufferedImage tilesetImage;
        if (dividers[2] == 0) {
            tilesetImage = new BufferedImage(dividers[0] * getMaxWidth(), dividers[1] * getMaxHeight(), BufferedImage.TYPE_INT_ARGB);
        } else {
            tilesetImage = new BufferedImage(dividers[0], dividers[1] + 1, BufferedImage.TYPE_INT_ARGB);
        }
        Graphics2D g = tilesetImage.createGraphics();

        int x = 0, y = 0;

        for (BufferedImage image : getImages()) {
            g.drawImage(image, x, y, null);
            if (x < dividers[0])
                x += image.getWidth();
            else {
                x = 0;
                y += image.getHeight();
            }
        }
        g.dispose();

        return tilesetImage;
    }

    public int[] getNearestDividers(int imageNumber) {
        int sqrt = (int) Math.sqrt(imageNumber); // La racine carrée de l'imageNumber

        int widthNumber = sqrt;
        int heightNumber = sqrt;
        int remainder = imageNumber - (sqrt * sqrt);

        // Si la racine carrée n'est pas un diviseur parfait, ajustez la largeur et la hauteur
        if (remainder > 0) {
            if (imageNumber % sqrt == 0) {
                widthNumber = sqrt;
                heightNumber = imageNumber / sqrt;
            } else {
                widthNumber = sqrt + 1;
                heightNumber = imageNumber / widthNumber;
            }
            remainder = imageNumber - (widthNumber * heightNumber);
        }

        return new int[] {widthNumber, heightNumber, remainder};
    }

}
