package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Files;
import java.nio.file.Path;

class Notepad extends JFrame {
    private JTextArea textArea = new JTextArea(800, 800);

    Notepad() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuItem openMenuItem = new JMenuItem("Open File");
        openMenuItem.addActionListener(this::open);
        JMenuItem saveMenuItem = new JMenuItem("Save File");
        saveMenuItem.addActionListener(this::save);

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(saveMenuItem);
        fileMenu.add(openMenuItem);

        JMenuBar menubar = new JMenuBar();
        menubar.add(fileMenu);

        add(menubar, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        setSize(600, 800);

    }

    private void save(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            try {
                Path selectedFile = fileChooser.getSelectedFile().toPath();
                String fileContents = this.textArea.getText();
                Files.writeString(selectedFile, fileContents);
            } catch (Exception ex) {
                showMessageForError("Cannot save file", ex);
            }
        }
    }

    private void showMessageForError(String title, Exception ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), title, JOptionPane.ERROR_MESSAGE);
    }

    private void open(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int i = fileChooser.showOpenDialog(this);
        if (i == JFileChooser.APPROVE_OPTION) {
            try {
                Path selectedFile = fileChooser.getSelectedFile().toPath();
                String fileContents = Files.readString(selectedFile);
                this.textArea.setText(fileContents);
            } catch (Exception ex) {
                showMessageForError("Cannot open file", ex);
            }
        }
    }


}

