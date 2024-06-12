package Java_assignment_02;

import java.util.ArrayList;

public class ArticleController  {
    public void getLinks(ArticleService articleService, String url) {

        MySqlArticleRepository mySqlArticleRepository = new MySqlArticleRepository();
        ArrayList<String> getLinks = articleService.getLinks(url);
        for (int i = 0; i < getLinks.size(); i++) {
            Article article = articleService.getArticle(getLinks.get(i));
            System.out.printf("%d - %s\n", i + 1, article.getTitle());
            mySqlArticleRepository.save(article);
        }
    }

}
