import dark.leech.text.get.InfoGetter;
import dark.leech.text.models.Properties;
import dark.leech.text.util.Http;
import org.jsoup.nodes.Document;

public class Info implements InfoGetter {
    @Override
    public void getter(Properties properties) {
        Document doc = Http.get(properties.getUrl());

        if (doc != null) {
            properties.setName(doc.select("h1.title").text());
            properties.setAuthor(doc.select(".info > div:first-child a").text());
            properties.setCover(doc.select(".books img").attr("src"), "https://truyen.tangthuvien.vn");
            properties.setGioiThieu(doc.select("div.desc-text").html());
            properties.setForum(false);
        }
    }
}
