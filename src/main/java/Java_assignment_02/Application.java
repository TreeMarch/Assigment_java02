package Java_assignment_02;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        VnExpressArticleService vnExpressArticleService = new VnExpressArticleService();
        MyArticleService myArticleService = new MyArticleService();
        ArticleController articleController = new ArticleController();
        ArrayList<Article> articles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("Please enter your choice: ");
            System.out.println("1. Crawl information from VnExpress.");
            System.out.println("2. Crawl information from my source");
            System.out.println("3. Show list");
            System.out.println("4. Exit the program");
            System.out.println("---------------------------------------");
            int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                articleController.getLinks(vnExpressArticleService,"https://vnexpress.net/the-thao");
                break;
            case 2:
                articleController.getLinks(myArticleService, "https://vnexpress.net/doi-song");
                break;
            case 3:
                while (true){
                    System.out.println("Please enter your choice: ");
                    System.out.println("1. Display all information .");
                    System.out.println("2. Display information based on URL .");
                    System.out.println("3.Exit the program");
                    int selection = scanner.nextInt();
                    switch(selection){
                        case 1:
                            System.out.println("find all");
                            break;
                        case 2:
                            System.out.println("find information based on URL");
                            break;
                        case 3:
                            System.out.println("Exit the program");
                            System.exit(1);
                            break;
                    }
                    break;
                }
            case 4:
                System.out.println("Thank you for using our program");
                System.out.println("Closing Program");
                System.exit(1);
                break;
            default:
                System.out.println("Invalid Choice");
                break;
        }
        }
    }
}


