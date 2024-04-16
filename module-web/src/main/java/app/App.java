package app;

import dto.AuthorDTO;
import dto.NewsDTO;
import service.AuthorService;
import service.GeneralService;
import service.NewsService;

import java.util.Scanner;

public class App implements GeneralApp{

    GeneralService<NewsDTO> newsService = new NewsService();
    GeneralService<AuthorDTO> authorService = new AuthorService();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void startApp() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addNews();
                    break;
                case 2:
                    addAuthor();
                    break;
                case 3:
                    editNews();
                    break;
                case 4:
                    deleteNews();
                    break;
                case 5:
                    viewAllNews();
                    break;
                case 6:
                    viewsNewsById();
                    break;
                case 7: return;
                default:
                    System.out.println("INVALID CHOICE");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. ADD NEWS");
        System.out.println("2. ADD AUTHOR");
        System.out.println("3. EDIT NEWS");
        System.out.println("4. DELETE NEWS");
        System.out.println("5. VIEW ALL NEWS");
        System.out.println("6. VIEW NEWS BY ID");
        System.out.println("7. EXIT");
        System.out.print("YOUR CHOICE: ");
    }

    private void addNews() {
        NewsDTO newsDTO = new NewsDTO();

        System.out.print("TITLE: ");
        newsDTO.setTitle(scanner.next());
        System.out.print("CONTEXT: ");
        newsDTO.setContent(scanner.next());
        System.out.print("AUTHOR: ");
        newsDTO.setAuthorId((long)scanner.nextInt());

        newsService.create(newsDTO);
    }

    private void addAuthor() {
        AuthorDTO authorDTO = new AuthorDTO();

        System.out.print("NAME: ");
        authorDTO.setName(scanner.next());

        newsService.create(authorDTO);
    }

    private void editNews() {
        NewsDTO newsDTO = new NewsDTO();

        System.out.print("TITLE: ");
        newsDTO.setTitle(scanner.next());
        System.out.print("CONTEXT: ");
        newsDTO.setContent(scanner.next());
        System.out.print("AUTHOR: ");
        newsDTO.setAuthorId((long)scanner.nextInt());
        System.out.print("ID FOR UPDATE: ");
        long id = scanner.nextLong();

        newsService.update(newsDTO, id);
    }

    private void deleteNews() {
        System.out.print("ID FOR DELETE: ");
        long id = scanner.nextLong();

        if (newsService.delete(id)) System.out.println("DELETE SUCCESS");
        else System.out.println("INVALID DELETE");
    }

    private void viewAllNews() {
        System.out.println(newsService.getAll());
    }

    private void viewsNewsById() {
        System.out.print("ID: ");
        long id = scanner.nextLong();

        System.out.println(newsService.getById(id));
    }
}
