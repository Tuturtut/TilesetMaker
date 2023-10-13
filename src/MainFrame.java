import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainFrame {

    public JFrame frame;
    public JFileChooser folderChooser;

    public JMenuBar menuBar;

    // JMenu
    public JMenu menu;

    // Menu items
    public JMenuItem selectFolderItem;

    public JButton dowloadButton;

    public ArrayList<BufferedImage> images;
    public Tileset tileset;

    public MainFrame(String name, WindowSize windowSize) {
        // create a frame
        frame = new JFrame("TilesetMaker");

        // create a menubar
        menuBar = new JMenuBar();

        // create a menu
        menu = new JMenu("Menu");

        images = new ArrayList<>();

        // create menuitems
        selectFolderItem = new JMenuItem("Select a folder");
        selectFolderItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = folderChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION){
                    File selectedFolder = folderChooser.getSelectedFile();

                    for (File file : Objects.requireNonNull(selectedFolder.listFiles())){
                        if (Extention.getExtentionFromFile(file) != Extention.NONE){
                            try{
                            BufferedImage bufferedImage = ImageIO.read(file);
                                images.add(bufferedImage);
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }

                        }
                    }
                    tileset = new Tileset(images);
                    System.out.println(tileset.getTilesetImage());
                }
            }
        });


        dowloadButton = new JButton("Télécharger");
        dowloadButton.setBounds(100,100,100,40);

        // TODO: 13/10/2023 Faire un try catch au cas ou 'tileset' est nul 
        dowloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int userChoice = fileChooser.showSaveDialog(frame);
                if (userChoice == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                        // Assurez-vous que le fichier a l'extension appropriée (par exemple, .png)
                        String fileName = selectedFile.getName();
                        if (!fileName.endsWith(".png")) {
                            selectedFile = new File(selectedFile.getAbsolutePath() + ".png");
                        }

                        // Écrivez l'image dans le fichier sélectionné
                        ImageIO.write(tileset.getTilesetImage(), "png", selectedFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        // Gérez les erreurs d'écriture de fichier
                    }
                }
            }
        });

        frame.add(dowloadButton);

        // add menu items to menu
        menu.add(selectFolderItem);


        // add menu to menu bar
        menuBar.add(menu);

        // add menubar to frame
        frame.setJMenuBar(menuBar);



        frame.setName(name);
        frame.setSize(windowSize.getWidth(), windowSize.getHeight());
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
