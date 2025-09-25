import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        // Pre dummy data is added to reduce the headache of again and agian creating user and book from starting33

        // Add user
        library.addUser(new User("Karan Aujla", 12201101L, Course.BTECH_COMPUTER_SCIENCE));
        library.addUser(new User("Robin Sarwara", 12201102L, Course.BTECH_COMPUTER_SCIENCE));
        library.addUser(new User("Neha", 12201103L, Course.BTECH_COMPUTER_SCIENCE));
        library.addUser(new User("Harleen Sani", 12201104L, Course.BTECH_COMPUTER_SCIENCE));

        // Add new book
        library.addBook(new Book("Effective Java (3rd Edition)", "Joshua Bloch", "978-0134685991"));
        library.addBook(new Book("Python Crash Course (2nd Edition)", "Eric Matthes", "978-1593279288"));
        library.addBook(new Book("The C Programming Language (2nd Edition)", "Brian W. Kernighan, Dennis M. Ritchie","978-0131103627"));
        library.addBook(new Book("Eloquent JavaScript (3rd Edition)", "Marijn Haverbeke", "978-1593279509"));
        library.addBook(new Book("The Rust Programming Language (2nd Edition)", "Steve Klabnik, Carol Nichols","978-1718503106"));

        
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello sir, Welcome ");

        mainLoop : while(true){
            
            System.out.println("\n\n1. Add new user");
            System.out.println("2. Add new book");
            System.out.println("3. Check book Availability");
            System.out.println("4. Issue book");
            System.err.println("5. Return book");
            System.out.println("6. Show all students");
            System.out.println("7. Show all books");
            System.out.println("8. Exit");

            System.out.print("Choose (1-8) from above options : ");
            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> library.addUserByLibrarian(scan);
                case 2 -> library.addBookByLibrarian(scan);
                case 3 -> library.checkBook(scan);
                case 4 -> library.issueBook(scan);
                case 5 -> library.returnBook(scan);
                case 6 -> library.showAllUsers();
                case 7 -> library.showAllBooks();
                case 8 -> {
                    break mainLoop;
                }
                default -> System.out.println("Invalid input");
            }
        }

    }
}