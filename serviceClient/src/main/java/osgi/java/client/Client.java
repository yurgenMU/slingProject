package osgi.java.client;

import osgi.service.printer.NodePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client implements Runnable {
    private NodePrinter service;
    private boolean stop;


    @Override
    public void run() {
        System.out.println("Enter a blank line to exit.");
        String path = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Loop endlessly.
        while (true) {
            try {
                // Ask the user to enter a word.
                System.out.print("Enter service name: ");

                path = in.readLine();


                // If the user entered a blank line, then
                // exit the loop.
                if (path.length() == 0) {
                    break;
                }
                // If there is no dictionary, then say so.
                else if (service == null) {
                    System.out.println("No service available.");
                }
                // Otherwise print whether the word is correct or not.
                else {
                    service.print(path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void start() {
        stop = false;
        new Thread(this).start();
    }

    void stop() {
        stop = true;
    }

    public NodePrinter getService() {
        return service;
    }

    public void setService(NodePrinter service) {
        this.service = service;
    }
}
