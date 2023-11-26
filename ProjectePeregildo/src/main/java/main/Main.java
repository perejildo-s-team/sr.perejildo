package main;

import controler.Controller;

/**
 * The Main class contains the entry point of the application.
 */
public class Main {

    /**
     * The main method initializes and starts the application by invoking the init method of the Controller class.
     *
     * @param args The command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Create an instance of the Controller class and invoke its init method
        new Controller().init();
    }
}
