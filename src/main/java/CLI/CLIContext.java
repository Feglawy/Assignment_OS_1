package CLI;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CLIContext
{
    private String currentDirectory;

    public CLIContext() {
        setCurrentDirectory(System.getProperty("user.dir"));
    }

    public CLIContext(String dir) {
        setCurrentDirectory(dir);
    }

    public String getCurrentDirectory() {
        return this.currentDirectory;
    }
    public void setCurrentDirectory(String dir) {
        if (dir.equals("~")) {
            redirectToHome();
            return;
        }

        Path nPath = Paths.get(dir).normalize();
        boolean wrongDir = false;

        if (!Files.exists(nPath)) { // checks if the path exists
            System.err.println("Wrong path " + dir + " not found");
            wrongDir = true;
        }

        if (!wrongDir && !Files.isDirectory(nPath)) { // checks if the path is a directory
            System.err.println(nPath + " is not a directory");
            wrongDir = true;
        }

        if (wrongDir && currentDirectory.isEmpty()) { // redirect to home if current dir is empty
            System.out.println("Redirecting to user's home");
            redirectToHome();
            return;
        }

        if (!wrongDir) { // set current directory to the new one if all checks are passed
            currentDirectory = nPath.toAbsolutePath().toString();
        }
    }


    private void redirectToHome() {
        this.currentDirectory = System.getProperty("user.home");
    }
}

