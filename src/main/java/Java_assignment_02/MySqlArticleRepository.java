package Java_assignment_02;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MySqlArticleRepository implements ArticleRepository {

   private final String MYSQL_CONNECTION = "jdbc:mysql://localhost:3306/article";
   private final String MYSQL_USER = "root";
   private final String MYSQL_PASSWORD = "";

    @Override
    public ArrayList<Article> findAll() {
        DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Article> articles = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USER, MYSQL_PASSWORD);
            String sql = "select * from articles where status = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String content = resultSet.getString("content");
                String thumbnail = resultSet.getString("thumbnail");
                String createdAt = resultSet.getString("createdAt");
                int status = resultSet.getInt("status");
                Article article = new Article();
                article.setId(id);
                article.setTitle(title);
                article.setDescription(description);
                article.setContent(content);
                article.setThumbnail(thumbnail);
                article.setCreatedAt(LocalDate.parse(createdAt));
                article.setStatus(status);
                articles.add(article);
            }
            // 4. Đóng kết nối.
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return articles;
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
        try (Connection conn = DriverManager.getConnection(MYSQL_CONNECTION, MYSQL_USER, MYSQL_PASSWORD)) {
            Article article1 = new Article();
            String sql = "UPDATE new_articles SET title = ?, description = ?, content = ?,thumbnail = ?,created_at = ?,update_at = ? where base_url = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, article1.getTitle());
            ps.setString(2, article1.getDescription());
            ps.setString(3, article1.getContent());
            ps.setString(4, article1.getThumbnail());
            ps.setString(5, article1.getCreatedAt().toString());
            ps.setString(6, article1.getUpdatedAt().toString());
            ps.setString(7, article1.getBaseUrl());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return article;
    }

    @Override
    public void deleteByUrl(String url) {
        try {
            // 1. Mở kết nối đến database.
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION,MYSQL_USER,MYSQL_PASSWORD);
            // 2. Tạo câu lệnh prepareStatement
            String prepareSql = "UPDATE article_crawler SET status = -1 delete_at = ? where base_url = ?";
            PreparedStatement ps = connection.prepareStatement(prepareSql);
            // 3. Thực thi câu lệnh
            ps.setString(1, Article.getDeleteAt().toString() );
            ps.setString(2, url);
            ps.execute();
            System.out.println("Deleted success!");
            // 4. Đóng kết nối.
            connection.close();
        } catch (SQLException e) {
            System.out.println("Có lỗi xảy ra, vui lòng thử lại sau.");
            e.printStackTrace();
        }
    }
}

