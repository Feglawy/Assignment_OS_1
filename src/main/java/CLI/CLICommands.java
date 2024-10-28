package CLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CLICommands {
    private String currentDirectory;

    public CLICommands() {
        this.currentDirectory = System.getProperty("user.dir");
    }

    public CLICommands(String dir) {
        Path nPath = Paths.get(dir).normalize();
        boolean wrongDir = false;
        if (!Files.exists(nPath)) {
            System.err.println("Wrong path " + dir + " not found");
            wrongDir = true;
        }
        
        if (!wrongDir && !Files.isDirectory(nPath)) {
            System.err.println(nPath + " is not a directory");
            wrongDir = true;
        }

        if (wrongDir) {
            System.out.println("redirecting to users home");
            this.currentDirectory = System.getProperty("user.home");
            return;
        }

        currentDirectory = nPath.toString();
    }

    /// returns current directory
    public String pwd() {
        System.out.println(currentDirectory);
        return this.currentDirectory;
    }

    /// changes the current directory
    public void cd(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: cd <dir>");
            return;
        }
        Path nPath = Paths.get(currentDirectory, args[0]).normalize();
        if (Files.isDirectory(nPath)) {
            currentDirectory = nPath.toString();
        } else {
            System.err.println("Directory Does NOT exist : " + args[0]);
        }
    }

    /// lists all files in the current directory
    public void ls(String[] args) {
        File dir = new File(currentDirectory);
        File[] files = dir.listFiles();

        boolean showAll = false;
        boolean reverse = false;

        for (String arg : args) {
            if (arg.equals("-a")) {
                showAll = true;
            } else if (arg.equals("-r")) {
                reverse = !reverse;
            } else {
                System.err.println(arg + " is not known argument for ls.");
                System.err.println("Available arguments -a and -r");
                return;
            }
        }

        if (files != null) {
            if (!showAll) {
                files = Arrays.stream(files)
                        .filter(file -> !file.getName().startsWith("."))
                        .toArray(File[]::new);
            }

            if (reverse) {
                Arrays.sort(files, Comparator.comparing(File::getName).reversed());
            }
            else {
                Arrays.sort(files, Comparator.comparing(File::getName));
            }

            for (File file : files) {
                System.out.println(file.getName() + (file.isDirectory()? "/": ""));
            }
        } else {
            System.err.println("Unable to list files.");
        }
    }

    public void mkdir(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: mkdir <dir>");
            return;
        }
        File dir = new File(currentDirectory, args[0]);
        if (dir.mkdir()) {
            System.out.println("Directory created successfully");
        }else {
            System.err.println("Failed to create directory");
        }
    }

    public void rmdir(String[] args){
        if (args.length != 1) {
            System.out.println("Usage: rmdir <dir>");
            return;
        }
        File dir = new File(currentDirectory, args[0]);
        if (dir.isDirectory() && dir.delete()) {
            System.out.println("Directory deleted successfully");
        }else {
            System.err.println("Failed to delete directory");
        }
    }

    public void touch(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: touch <file>");
            return;
        }
        File file = new File(currentDirectory, args[0]);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + args[0]);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Failed to create file.");
        }
    }

    public void mv(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: mv <source> <destination>");
            return;
        }
        Path source = Paths.get(currentDirectory, args[0]);
        Path destination = Paths.get(currentDirectory, args[1]);
        try {
            Files.move(source, destination);
            System.out.println("Moved " + args[0] + " to " + args[1]);
        } catch (IOException e) {
            System.err.println("Failed to move file.");
        }
    }

    public void rm(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: rm <file>");
            return;
        }
        File file = new File(currentDirectory, args[0]);
        if (file.delete()) {
            System.out.println("File removed: "+ args[0]);
        } else {
            System.err.println("Couldn't remove " + args[0]);
        }
    }

    public void cat(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: cat <filename>");
            return;
        }
        try {
            File file = new File(currentDirectory, args[0]);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                System.out.println(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't read file : File not found.");
        }
    }

    public void redirectOutput(String str, String filename, boolean append) {
        try {
            File file = new File(currentDirectory, filename);
            try (FileWriter fileWriter = new FileWriter(file, append)){
                    fileWriter.write(str);
                    fileWriter.close();
            } catch (Exception e) {
                System.err.println("An Error occurred while writing: " + e.getCause());
            }
        } catch (Exception e) {
            System.err.println("An Error occurred while writing: " + e.getCause());
        }
    }

    public void pipeLine(String[] args) {
        
    }

    public static void displayHelp() {
        System.out.println("Available commands:");
        System.out.println("pwd             -   Print current directory");
        System.out.println("cd <dir>        -   Change directory");
        System.out.println("ls              -   List files in current directory");
        System.out.println("mkdir <dir>     -   Create a directory");
        System.out.println("rmdir <dir>     -   Remove a directory");
        System.out.println("touch <file>    -   Create an empty file");
        System.out.println("mv <source> <destination>   -   Move/rename a file or directory");
        System.out.println("rm <file>       -   Remove a file");
        System.out.println("cat <file>      -   Display contents of a file");
        System.out.println("> <file>        -   Redirect output to file");
        System.out.println(">> <file>       -   Appends output to file");
        System.out.println("exit            -   Exit the CLI");
    }

    public static void exit() {
        System.out.println("Exiting CLI.");
        System.exit(0);
    }
}

