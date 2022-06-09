package com.thecoder.JavaGame;

import com.thecoder.JavaGame.utils.Logger;

public class App {

    public static void main(String[] args) throws Exception {
        // Logger
        if (args.length == 2 && args[0].equals("-log")) {
            try {
                if (Logger.setLevel(Integer.parseInt(args[1]))) {
                    System.out.println("ERROR: Invalid log level");
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid log level");
                System.exit(1);
            }
        } else {
            Logger.setLevel(0);
        }

        // Debug
        Logger.debugInfo = true;

        // Starts game
        new Game().Start();;
    }
}
