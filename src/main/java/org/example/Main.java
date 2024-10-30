package org.example;
import CLI.CLI;
import CLI.CLIContext;
import CLI.Commands.*;
import CLI.Command;
import CLI.Parser;
import java.util.Stack;

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

    CLI cli = new CLI();
    cli.start();

    }
}