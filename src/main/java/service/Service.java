/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author adrian.stoicescu
 */
public class Service extends Thread {

    private static final int port = 8001;

    private static final int nThreads = 5;

    public static void main(String args[]) {
        try {
            new Service().start(port);
        } catch (Exception e) {
            System.out.println("Startup Error: " + e);
        }
    }

    public void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        System.out.println("Web server listening on port " + port);
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);        
        while (true) {
            executor.submit(new RequestHandler(server.accept()));
        }
    }
}
