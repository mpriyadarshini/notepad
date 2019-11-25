package notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.*;

class Notepad extends JFrame implements ActionListener {
    private JTextArea area = new JTextArea(800, 800);
    private JMenuItem open = new JMenuItem("Open File");
    private JMenuItem save = new JMenuItem("Save File");

    Notepad() {
        open.addActionListener(this);
        save.addActionListener(this);

        JMenu file = new JMenu();
        file.add(save);
        file.add(open);

        JMenuBar menubar = new JMenuBar();
        menubar.add(file);

        add(menubar);
        add(area);
        setSize(600,800);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.open) {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                try {
					Scanner sc = new Scanner(new FileReader(fc.getSelectedFile().getPath()));
					while (sc.hasNext()) {
						this.area.append(sc.nextLine() + "\n");

					}
				}
					catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }


        } else if (e.getSource() == this.save) {
            JFileChooser fs = new JFileChooser();
            int option = fs.showSaveDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(fs.getSelectedFile().getPath()));
                    out.write(this.area.getText());
                    out.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }



}

