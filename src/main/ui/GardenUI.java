package ui;

import model.Event;
import model.EventLog;
import model.Garden;
import model.Plant;
import model.PlantType;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// code influenced by https://www.javatpoint.com/java-jpanel
// represents a gui for gardening app
public class GardenUI extends JFrame {
    private Garden garden;
    private JFrame frame;
    private JPanel mainPanel;
    private JLabel title;
    private JButton saveButton;
    private JButton viewGardenButton;
    private JButton addPlantsButton;
    private Dimension dimensions = new Dimension(700, 700);
    private Color col = new Color(80, 183, 85);
    private JFrame addPlantsFrame;
    private PlantCellRenderer plantDisplay;
    private DefaultListModel<Plant> model;
    private JList<Plant> plantList;
    private Plant tomato = new Plant("Tomato", PlantType.FRUIT, "tomato stuff");
    private Plant lemon = new Plant("Lemon", PlantType.FRUIT, "lemon stuff");
    private Plant garlic = new Plant("Garlic", PlantType.HERB, "garlic stuff");
    private Plant jalapeno = new Plant("Jalapeno", PlantType.VEGETABLE, "jalapeno stuff");
    private JButton addToGardenButton;
    private JPanel topPanel;
    private JPanel plantsPanel;
    private JFrame viewGardenFrame;
    private JPanel topGardenPanel;
    private JPanel displayGardenPanel;
    private JButton deletePlantButton;
    private DefaultListModel<Plant> gardenDisplay;
    private JList<Plant> gardenList;
    private static final String JSON_STORE = "./data/garden.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // MODIFIES: this
    // EFFECTS: constructs the main frame of the application
    public GardenUI() {
        garden = new Garden("My Garden");
        frame = new JFrame();
        frame = mainMenuFrame();
        setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        plantDisplay = new PlantCellRenderer();
        gardenDisplay = new DefaultListModel<>();
        gardenList = new JList<>(gardenDisplay);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        loadDialog();
        exit();
    }


    public static void main(String[] args) {
        new GardenUI();
    }

    // MODIFIES: this
    // EFFECTS: sets frame properties
    public JFrame mainMenuFrame() {
        frame.getContentPane().setBackground(col);
        mainPanel = new JPanel();
        mainPanel = makeMainPanel();
        frame.add(mainPanel);
        frame.setSize(dimensions.width, dimensions.height);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    // MODIFIES: this
    // EFFECTS: creates the main panel and its properties
    public JPanel makeMainPanel() {
        mainPanel.setLayout(new GridLayout(0, 1, 0, 15));
        mainPanel.setBackground(col);
        JPanel panel = new JPanel();
        panel.setBackground(col);
        panel.setPreferredSize(new Dimension(50, 50));
        mainPanel.add(panel);

        mainPanel.add(makeTitle());
        mainPanel.add(addPlantsButton());
        mainPanel.add(viewGardenButton());
        mainPanel.add(makeSaveButton());

        return mainPanel;

    }

    // MODIFIES: this
    // EFFECTS: create title for the main menu
    public JLabel makeTitle() {
        title = new JLabel("Welcome, " + System.getProperty("user.name")
                + "! Ready to start gardening?", JLabel.CENTER);
        title.setForeground(Color.white);
        Font font = new Font(Font.DIALOG_INPUT, Font.BOLD, 15);
        title.setFont(font);
        return title;
    }

    // MODIFIES: this
    // EFFECTS: creates button to add plants and takes users to new window when clicked
    public JButton addPlantsButton() {
        addPlantsButton = new JButton("Add plants");
        addPlantsButton.setBounds(100, 100, 80, 30);
        addPlantsButton.setFocusable(false);
        addPlantsButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPlantsFrame();
            }
        });
        return addPlantsButton;
    }

    // MODIFIES: this
    // EFFECTS: sets up the frame with available plants to add

    public JFrame addPlantsFrame() {

        addPlantsFrame = new JFrame();
        addPlantsFrame.setSize(dimensions.width, dimensions.height);
        addPlantsFrame.setLocationRelativeTo(null);
        addPlantsFrame.setVisible(true);
        addPlantsFrame.add(makeTopPanel(), BorderLayout.NORTH);
        addPlantsFrame.add(makePlantPanel(), BorderLayout.CENTER);

        return addPlantsFrame;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel at the top of frame with a button to add plants
    public JPanel makeTopPanel() {
        topPanel = new JPanel();
        topPanel.add(addToGardenButton());
        return topPanel;
    }

    // code influenced by https://www.codejava.net/java-se/swing/jlist-custom-renderer-example
    // MODIFIES: this
    // EFFECTS: creates a panel that displays the list of available plants
    public JPanel makePlantPanel() {
        plantsPanel = new JPanel();

        setUpPlantList();

        plantList = new JList<>(model);
        plantList.setCellRenderer(new PlantCellRenderer());
        plantsPanel.add(plantList);


        return plantsPanel;
    }

    // MODIFIES: this
    // EFFECTS: adds available plants to be displayed
    public void setUpPlantList() {
        model = new DefaultListModel<>();
        model.addElement(tomato);
        model.addElement(lemon);
        model.addElement(garlic);
        model.addElement(jalapeno);
    }


    // MODIFIES: this
    // EFFECTS: adds selected plants to garden
    public JButton addToGardenButton() {
        addToGardenButton = new JButton("Add to garden");
        addToGardenButton.setVisible(true);
        addToGardenButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = plantList.getSelectedIndex();
                if (index != -1) {
                    Plant selected = model.getElementAt(index);
                    gardenDisplay.addElement(selected);
                    gardenList.setCellRenderer(new PlantCellRenderer());
                    garden.addPlant(selected);
                }
            }
        });
        return addToGardenButton;

    }

    // MODIFIES: this
    // EFFECTS: creates button to view existing garden and takes users to new window when clicked
    public JButton viewGardenButton() {
        viewGardenButton = new JButton("View garden");
        viewGardenButton.setBounds(100, 100, 80, 30);
        viewGardenButton.setFocusable(false);
        viewGardenButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewGardenFrame();
            }
        });
        return viewGardenButton;
    }

    // MODIFIES: this
    // EFFECTS: creates frame to view current garden
    public JFrame viewGardenFrame() {
        viewGardenFrame = new JFrame();
        viewGardenFrame.setSize(dimensions.width, dimensions.height);
        viewGardenFrame.setLocationRelativeTo(null);
        viewGardenFrame.setVisible(true);
        viewGardenFrame.add(makeTopGardenPanel(), BorderLayout.NORTH);
        viewGardenFrame.add(makeDisplayGardenPanel(), BorderLayout.CENTER);
        return viewGardenFrame;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel at top of frame with button to delete plants
    public JPanel makeTopGardenPanel() {
        topGardenPanel = new JPanel();
        topGardenPanel.setVisible(true);
        topGardenPanel.add(makeDeletePlantButton());
        return topGardenPanel;
    }

    // MODIFIES: this
    // EFFECTS: displays the plants that have been added to the garden
    public JPanel makeDisplayGardenPanel() {
        displayGardenPanel = new JPanel();
        displayGardenPanel.setVisible(true);
        displayGardenPanel.add(gardenList);
        return displayGardenPanel;

    }

    // MODIFIES: this
    // EFFECTS: creates button that allows users to delete a plant from current garden
    public JButton makeDeletePlantButton() {
        deletePlantButton = new JButton("Delete plant");
        deletePlantButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = gardenList.getSelectedIndex();
                if (index != -1) {
                    Plant selected = gardenDisplay.getElementAt(index);
                    gardenDisplay.removeElement(selected);
                    gardenList.setCellRenderer(new PlantCellRenderer());
                    garden.removePlant(selected.getName());
                }
            }
        });
        return deletePlantButton;
    }

    // MODIFIES: this
    // EFFECTS: saves the current garden
    public JButton makeSaveButton() {
        saveButton = new JButton("Save garden");
        saveButton.setBounds(50, 200, 80, 30);
        saveButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGardenToFile();
            }
        });
        return saveButton;
    }

    // code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    // EFFECTS: saves the garden to file
    private void saveGardenToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(garden);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // code influenced by https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    // code influenced by https://www.javatpoint.com/java-joptionpane
    // EFFECTS: displays dialog to load garden
    private void loadDialog() {
        int n = JOptionPane.showConfirmDialog(frame, "Load your previous garden?",
                "Questions about loading", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_OPTION) {
            try {
                garden = jsonReader.read();
                gardenDisplay.clear();

                for (Plant p : garden.showGarden()) {
                    gardenDisplay.addElement(p);
                }

                gardenList.setCellRenderer(new PlantCellRenderer());

            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }

    }

    // EFFECTS: prints event log to console when window is closed
    public void exit() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                setVisible(false);
                dispose();
                for (Event event: EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }
        });
    }
}
