package Java_assignment_02;

import java.util.ArrayList;

public interface ArticleService {
    // cho phep lay ds link tu 1 nguon
    ArrayList<String> getLinks(String url);
    // cho phep lay thong tin ds link tu 1 nguon
    Article getArticle(String url);
}
