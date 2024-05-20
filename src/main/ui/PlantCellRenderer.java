package ui;

import model.Plant;

import javax.swing.*;
import java.awt.*;

// custom renderer that displays the image of the plant along with its name and info
public class PlantCellRenderer extends JLabel implements ListCellRenderer<Plant> {

    // MODIFIES: this
    // EFFECTS: constructs a rendereer for plants
    public PlantCellRenderer() {
        setOpaque(true);
    }

    // code influenced by https://www.codejava.net/java-se/swing/jlist-custom-renderer-example
    // code influenced by https://www.youtube.com/watch?v=1q7VzBiEchk
    // MODIFIES: this
    // EFFECTS: sets the icon and text of each plant to be displayed
    @Override
    public Component getListCellRendererComponent(JList<? extends Plant> list,
                                                  Plant value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {

        String name = value.getName();
        String info = value.getInfo();
        ImageIcon img = new ImageIcon("./data/" + name + ".jpeg");
        Image image = img.getImage();
        Image imgScale = image.getScaledInstance(100,100, 100);
        ImageIcon scaledIcon = new ImageIcon(imgScale);

        this.setIcon(scaledIcon);
        this.setText(name + " (" + value.getType().toString() + ") \n       " + info);


        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
