package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Path;

class Notepad extends JFrame {
    private JTextArea area = new JTextArea(800, 800);

    Notepad() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuItem open = new JMenuItem("Open File");
        open.addActionListener(this::open);
        JMenuItem save = new JMenuItem("Save File");
        save.addActionListener(this::save);

        JMenu file = new JMenu("File");
        file.add(save);
        file.add(open);

        JMenuBar menubar = new JMenuBar();
        menubar.add(file);

        add(menubar, BorderLayout.NORTH);
        add(area, BorderLayout.CENTER);
        setSize(600, 800);

    }

    private void save(ActionEvent e) {
        JFileChooser fs = new JFileChooser();
        int option = fs.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                Path selectedFile = fs.getSelectedFile().toPath();
                String fileContents = this.area.getText();
                Files.writeString(selectedFile, fileContents);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void open(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        int i = fc.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            try {
                Path selectedFile = fc.getSelectedFile().toPath();
                String fileContents = Files.readString(selectedFile);
                this.area.setText(fileContents);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


}

