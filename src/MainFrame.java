import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame {

        JFrame frame = new JFrame();
        JButton button = new JButton("Click");
        //JFileChooser

        public MainFrame(String name, WindowSize windowSize) {
            button.setBounds(130, 100, 100, 40);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }

            });


            frame.add(button);

            frame.setName(name);
            frame.setSize(windowSize.getWidth(), windowSize.getHeight());
            frame.setLayout(null);
            frame.setVisible(true);
        }




}
