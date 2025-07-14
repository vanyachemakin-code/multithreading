import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SiteMap {
    private final String folderPath;

    public SiteMap(String folderPath) {
        this.folderPath = folderPath;
    }

    public void createSiteMap(String links) {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(folderPath +"/site_map.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        printWriter.write(links);
        printWriter.close();
    }
}
