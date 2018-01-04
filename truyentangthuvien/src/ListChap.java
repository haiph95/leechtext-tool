import dark.leech.text.get.ListGetter;
import dark.leech.text.models.Chapter;
import dark.leech.text.models.Properties;
import dark.leech.text.util.Http;
import dark.leech.text.util.SyntaxUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ListChap implements ListGetter {
    @Override
    public void getter(Properties properties) {
        ArrayList<Chapter> chapterList = new ArrayList<>();
        Document document = Http.get(properties.getUrl());
        if (document != null) {
            int storyId = 1;
            int totalPage = 1;
            try {
                storyId = Integer.parseInt(document.select("input[name=story_id]").val());
                totalPage = Integer.parseInt(extracNumber(document.select("nav.nav-pagination li:last-child a").attr("onclick")));
            } catch (Exception e) {
            }
            for (int i = 0; i <= totalPage; i++) {
                document = Http.get("https://truyen.tangthuvien.vn/doc-truyen/page/" + storyId + "?page=" + i);
                Elements el = document.select("div ul li a");
                for (Element e: el) {
                    chapterList.add(new Chapter(e.attr("href"), SyntaxUtils.fixName(e.text())));
                }
            }
            properties.setChapList(chapterList);
            properties.setSize(chapterList.size());
        }
    }


    private static String extracNumber(String s) {
        String text = s.replaceAll("[^0-9]", "");
        return text;
    }
}
