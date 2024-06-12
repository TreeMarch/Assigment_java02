package Java_assignment_02;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;

public class MyArticleService implements ArticleService {
    @Override
    public ArrayList<String> getLinks(String url) {
        HashSet<String> links = new HashSet<>(); // Collections chứa những phần tử với giá trị unique
        try {
            Document document = Jsoup.connect(url).get() ;
            Elements elements = document.getElementsByTag("a");
            for (Element element : elements) {
                String href = element.attr("href");
                if(href.contains("https://vnexpress.net") && href.contains(".html")){
                    links.add(href);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(links);
    }

    @Override
    public Article getArticle(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String title = doc.select("h1.title-detail").text();
            String description = doc.select("p.description").text();
            String content = doc.select("article.fck_detail p.Normal").text();
            String thumbnail = doc.select("div.fig-picture  img[itemprop=contentUrl]").attr("data-src");
            String createdAt = doc.select("span.date ").text();
            Article article = new Article();
            article.setBaseUrl(url);
            article.setTitle(title);
            article.setDescription(description);
            article.setContent(content);
            article.setThumbnail(thumbnail);
            article.setCreatedAt(createdAt);
            article.setStatus(1);
            return article;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}