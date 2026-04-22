
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Library lib = new Library();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Show Books");
            System.out.println("3. Add User");
            System.out.println("4. Show Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Search by Title");
            System.out.println("8. Search by Author");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Title: ");
                    String title = sc.next();   // single word

                    System.out.print("Enter Author: ");
                    String author = sc.next();  // single word

                    lib.addBook(new Book(id, title, author));
                    break;

                case 2:
                    lib.showBooks();
                    break;

                case 3:
                    System.out.print("Enter User ID: ");
                    int uid = sc.nextInt();

                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    lib.addUser(new User(uid, name));
                    break;

                case 4:
                    lib.showUsers();
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();

                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();

                    lib.issueBook(bid, userId);
                    break;

                case 6:
                    System.out.print("Enter Book ID: ");
                    int rid = sc.nextInt();

                    lib.returnBook(rid, sc);
                    break;

                case 7:
                    System.out.print("Enter Title: ");
                    String t = sc.next();

                    lib.searchByTitle(t);
                    break;

                case 8:
                    System.out.print("Enter Author: ");
                    String a = sc.next();

                    lib.searchByAuthor(a);
                    break;

                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
