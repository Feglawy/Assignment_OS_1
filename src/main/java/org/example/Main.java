package org.example;
import CLI.CLI;

public class Main {
    public static void main(String[] args) throws Exception {
        CLI cli = new CLI("C:\\Users\\abdallah\\Desktop\\New folder");
        String[] arg = {"help", "a.txt"};
        cli.redirectOutput(arg, true);
    }
}