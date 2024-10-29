package org.example;
import CLI.CLIContext;
import CLI.Commands.*;

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

    CLIContext context = new CLIContext();
    ExecuteArgs cd = new cdCommand(context);
    Execute pwd = new pwdCommand(context);

    pwd.execute();
    String[] arg = {"../"};
    cd.execute(arg);
    pwd.execute();

    }
}