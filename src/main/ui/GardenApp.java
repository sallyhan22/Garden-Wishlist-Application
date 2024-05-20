package ui;


import model.Garden;
import model.Plant;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static model.PlantType.*;

// represents a garden application
// Code influenced by JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class GardenApp {
    private String userName;
    private Scanner scanner;
    private String addCommand = "add";
    private String removeCommand = "remove";
    private String viewGardenCommand = "view";
    private String quitCommand = "quit";
    private String saveCommand = "save";
    private String loadCommand = "load";
    private Garden garden;
    private Plant p1;
    private Plant p2;
    private Plant p3;
    private List<Plant> availablePlants;
    private static final String JSON_STORE = "./data/garden.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: starts the garden application
    // SOURCES: Bank teller application
    public GardenApp() {
        scanner = new Scanner(System.in);
        garden = new Garden("");
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        runGarden();
    }

    // EFFECTS: runs the garden application
    // SOURCES: Bank teller application
    public void runGarden() {
        System.out.println("Please enter your name: ");
        userName = scanner.nextLine();
        System.out.println("Welcome to your garden, " + userName + "!");
        String input = null;

        boolean keepRunning = true;

        init();

        while (keepRunning) {
            showMainMenu();
            input = scanner.nextLine();
            if (Objects.equals(input, quitCommand)) {
                keepRunning = false;
            } else {
                processInput(input);
            }
        }
        endProgram();
    }

    // EFFECTS: displays the main menu and options for users to input
    public void showMainMenu() {
        System.out.println("\nTo view your garden, enter " + viewGardenCommand);
        System.out.println("To save your current garden, enter " + saveCommand);
        System.out.println("To load your saved garden, enter " + loadCommand);
        System.out.println("To quit, enter " + quitCommand);
    }

    // MODIFIES: this
    // EFFECTS: initializes an empty garden and the list of available plants to add
    // SOURCES: Bank teller application
    public void init() {
        garden = new Garden(userName);
        p1 = new Plant("Tomato [0]", FRUIT, "Tomatoes require plenty of direct sunlight "
                + "(at least 6-8 hours) daily.");
        p2 = new Plant("Parsley [1]", HERB, "Parsley can grow well in full-sun or part-sun "
                + "environments, but direct sunlight will optimize growth.");
        p3 = new Plant("Garlic [2]", VEGETABLE, "Garlic requires a full-sun environment"
                + "(6-8 hours of direct sunlight), and grows best in well-drained soil.");
        availablePlants = new ArrayList<>();
        availablePlants.add(p1);
        availablePlants.add(p2);
        availablePlants.add(p3);
    }

    // EFFECTS: processes the user input
    // SOURCES: Bank teller application
    public void processInput(String input) {
        if (Objects.equals(input, viewGardenCommand)) {
            seeGarden();
        } else if (Objects.equals(input, loadCommand)) {
            loadGarden();
        } else if (Objects.equals(input, saveCommand)) {
            saveGarden();
        } else {
            System.out.println("Sorry, your input was not recognized.");
        }
    }

    // EFFECTS: displays garden and options to continue, shows plant info when plant name is inputted
    public void seeGarden() {
        List<String> plantNames = new ArrayList<>();
        for (Plant p: garden.showGarden()) {
            plantNames.add(p.getName());
        }
        System.out.println(plantNames);

        String input = null;
        System.out.println("\nTo add a plant, enter " + addCommand);
        System.out.println("To remove a plant, enter " + removeCommand);
        System.out.println("To get info about a plant, enter its name and number (e.g 'Tomato [0]')");

        input = scanner.nextLine();

        if (Objects.equals(input, addCommand)) {
            addToGarden();
        } else if (Objects.equals(input, removeCommand)) {
            removeFromGarden();
        } else if (plantNames.contains(input)) {
            System.out.println(garden.findPlantInfo(input));
        } else {
            System.out.println("Sorry, your input was not recognized.");
        }
    }

    // EFFECTS: adds a plant from the list of available plants to garden when given the number in the plant's name
    public void addToGarden() {
        int input = 0;
        System.out.println("\nAvailable plants to add: \n - " + availablePlants.get(0).getName()
                + "\n - " + availablePlants.get(1).getName() + "\n - " + availablePlants.get(2).getName());

        System.out.println("Enter the number of whichever plant you wish to add: ");

        input = scanner.nextInt();

        if ((input >= 0) && (input <= (availablePlants.size() - 1))) {
            garden.addPlant(availablePlants.get(input));
            System.out.println(availablePlants.get(input).getName() + " has been successfully added!");
        } else {
            System.out.println("Sorry, your input was not recognized.");
        }
    }

    // EFFECTS: removes a plant from the garden when given the plant's name
    public void removeFromGarden() {
        if (garden.showGarden().size() == 0) {
            System.out.println("There are no plants to remove.");
        } else {
            String input = null;
            System.out.println("\nEnter the name and number of the plant you wish to remove (e.g. 'Tomato [0]'): ");
            input = scanner.nextLine();

            if (garden.showPlantNames().contains(input)) {
                garden.removePlant(input);
                System.out.println("Successfully removed!");
            } else {
                System.out.println("Input not recognized");
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveGarden() {
        try {
            jsonWriter.open();
            jsonWriter.write(garden);
            jsonWriter.close();
            System.out.println("Saved " + userName + "'s garden to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads garden from file
    public void loadGarden() {
        try {
            garden = jsonReader.read();
            System.out.println("Loaded garden from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints an ending statement
    public void endProgram() {
        System.out.println("\nThanks for visiting your garden, " + userName + "!");
    }
}
