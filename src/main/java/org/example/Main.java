package org.example;
import CLI.CLICommands;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        PrintStream originalOut = System.out;
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        System.out.println("Abdallah");
//
//        System.setOut(originalOut);
//        System.setIn(System.in);
//
//        System.out.println(outputStream.toString());
        CLICommands cliCommands = new CLICommands("~");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            cliCommands.pwd();
            System.out.print("> ");
            String input = scanner.nextLine();
        }
    }
}