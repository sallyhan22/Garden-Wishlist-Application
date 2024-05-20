# My Personal Project

## Urban Gardening Application


My application will be about building a mini *urban garden*, or a garden in a limited/enclosed space in the city. This specific application allows users to add certain popular plants and vegetables to their miniature garden by dragging and dropping from a sidebar. General information about how to care for each plant can also be found in this application.  

This application is open for use to anyone, especially for those living in urban areas and are interested in learning more about or implementing their own mini garden in these spaces. 

This project is of interest to me for the following reasons:
- I've recently started gardening in the last few years, and it's a lot of fun!
- The number of urban areas in Vancouver is rising very quickly, so creating small green spaces is increasingly important
- I would like to learn more about growing my own vegetables in an apartment and hope to be able to share any useful information to others


## User Stories
- As a user, I want to be able to add a plant to my garden
- As a user, I want to be able to see a list of all the plants I have already added to my garden
- As a user, I want to be able to see a list of all the plants that I can add to my garden
- As a user, I want to be able to remove any plants from my garden
- As a user, I want to be able to select any plant from my garden and read the information about caring for that plant
- As a user, I want to be given the option to save my current garden to file when I try to quit the application
- As a user, I want to be given the option to load a saved garden from file when I start the application

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "Add Plants" button on the main menu. From the list of plants that are displayed in the window, selecting a plant by clicking on it followed by clicking the "Add to garden" button adds the selected plant to the current garden. 
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "View garden" button in the main menu. From the display of plants that are already added to your garden, selecting on one of them by clicking on it followed by clicking on the "Delete plant" button removes that selected plant from the display.
- You can locate my visual component by clicking on either the "Add plants" or the "View garden" button in the main menu to see a display of plants that have an image icon attached to them.
- You can save the state of my application by clicking on the "Save garden" button on the main menu.
- You can reload the state of my application by clicking the "YES" option in the pop-up dialog that is generated when the user first starts the application.

# Phase 4: Task 2
Wed Nov 29 15:32:34 PST 2023
Tomato was added to My Garden
Wed Nov 29 15:32:35 PST 2023
Lemon was added to My Garden
Wed Nov 29 15:32:36 PST 2023
Garlic was added to My Garden
Wed Nov 29 15:32:37 PST 2023
Jalapeno was added to My Garden
Wed Nov 29 15:32:40 PST 2023
Tomato was removed from My Garden
Wed Nov 29 15:32:42 PST 2023
Jalapeno was removed from My Garden
Wed Nov 29 15:32:46 PST 2023
Jalapeno was added to My Garden

# Phase 4: Task 3
If I had more time to work on this project, I would improve my design by creating the list of available plants in a separate class from my gardenUI class. Right now, I construct new plant objects and create the list and render it all in the same class, but I think my code would be more readable if the creation of the list was elsewhere and my gardenUI class would just have a method to call that list.
The current methods in that class are focused on modifying or rendering that list, and since the list can get quite long if a large number of plant objects are added, the code can get very messy, and take away from my intended focus of the gardenUI class. Since the available plants lists can't be changed in the running of the application, I think making this list in a class that is designated for generating a list of available plants would be better design. My gardenApp class can also call that new class as well, but the generated list would be of a different type than if the gardenUI class called it.  
