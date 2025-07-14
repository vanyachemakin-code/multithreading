import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final String START_URL = "https://market.yandex.ru";
    private static final String FOLDER_PATH =
            "/Users/vanyachemakin/IdeaProjects/multithreading/SiteMap/src/main/java/data";


    public static void main(String[] args) {
        String link = new ForkJoinPool().invoke(new Links(START_URL));
        SiteMap siteMap = new SiteMap(FOLDER_PATH);
        siteMap.createSiteMap(link);
    }
}
