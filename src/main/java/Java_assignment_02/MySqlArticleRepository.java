package Java_assignment_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlArticleRepository implements ArticleRepository {

   private final String MYSQL_CONNECTION = "jdbc:mysql://localhost:3306/article";
   private final String MYSQL_USER = "root";
   private final String MYSQL_PASSWORD = "";

    @Override
    public ArrayList<Article> findAll() {
        return null;
    }

    @Override
    public Article findByUrl(String url) {
        return null;
    }

    @Override
    public Article save(Article article) {
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USER, MYSQL_PASSWORD)) {
            String sql = "INSERT INTO article_crawler (base_url, title, description,content,thumbnail,status) VALUES (?, ?, ?, ?, ? ,1)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, article.getBaseUrl());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getDescription());
            ps.setString(4, article.getContent());
            ps.setString(5, article.getThumbnail());
//            ps.setInt(6, article.getCreatedAt());
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article update(Article article) {
        return null;
    }

    @Override
    public void deleteByUrl(String url) {

    }
}
