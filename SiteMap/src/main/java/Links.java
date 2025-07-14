
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.RecursiveTask;

public class Links extends RecursiveTask<String> {

    private final CopyOnWriteArraySet<String> links;
    private final String url;

    public Links(String url) {
        this.url = url;
        this.links = new CopyOnWriteArraySet<>();
    }

    public Links(String url, CopyOnWriteArraySet<String> links) {
        this.url = url;
        this.links = links;
    }

    @Override
    protected String compute() {
        Set<Links> tasks = new TreeSet<>(Comparator.comparing(link -> link.url));

        for (Element element : getLinksInUrl(url)) {
            String link = element.absUrl("href");
            if (link.startsWith(url) && !link.contains("#") && !links.contains(link)) {
                Links newLink = new Links(link, links);
                newLink.fork();
                links.add(link);
                tasks.add(newLink);
            }
        }

        StringBuilder sb = new StringBuilder(getTab() + url + System.lineSeparator());
        for (Links task : tasks) {
            sb.append(task.join());
        }
        return sb.toString();
    }

    private Elements getLinksInUrl(String url) {
        Document doc;
        try {
            Thread.sleep(100);
            doc = Jsoup.connect(url).timeout(60 * 1000).get();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return doc.select("a[href]");
    }

    private String getTab() {
        return StringUtils.repeat("\t", url.lastIndexOf("/") != url.length() - 1 ?
                        StringUtils.countMatches(url, "/") - 2 :
                        StringUtils.countMatches(url, "/") - 3);
    }
}
