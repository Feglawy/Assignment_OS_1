package CLI.Operators;

import java.io.File;
import java.io.FileWriter;

public class redirectOperator {
    public void redirect(String input, String filePath, boolean append) {
        try {
            File file = new File(filePath);
            try (FileWriter fileWriter = new FileWriter(file, append)) {
                fileWriter.write(input);
            } catch (Exception e) {
                System.err.println("An Error occurred while writing: " + e.getCause());
            }
        } catch (Exception e) {
            System.err.println("An Error occurred while writing: " + e.getCause());
        }
    }
}
