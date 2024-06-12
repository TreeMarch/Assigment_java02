package Java_assignment_02;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        ArrayList<Article> articles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while(true) {
            System.out.println("Enter your choice");
            System.out.println("1.Crawl data from vnExpress page");
            System.out.println("2.Crawl date from my page");
            System.out.println("3. Show all articles");
            System.out.println("4. Exit");

            switch(choice) {
                case 1:
                    System.out.println("01");
                    break;
                case 2:
                    System.out.println("02");
                    break;
                case 3:
                    System.out.println("03");
                    break;
                case 4:
                    break;
                default:
                    System.out.println("invalid choice");
            }
        }



    }
}


