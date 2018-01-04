import dark.leech.text.get.ChapGetter;
import dark.leech.text.util.Http;
import org.jsoup.nodes.Document;


public class Chapter implements ChapGetter {
    @Override
    public String getter(String s) {
        Document document = Http.get(s);
        String text = null;
        if (document != null) {
            text = document.select(".chapter-c div.box-chap").first().text();
        }
        return text;
    }
}
