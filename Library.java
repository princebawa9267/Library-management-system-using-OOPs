import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<User> users;
    private List<Book> books;

    public Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
    }

    // Add new user
    public void addUser(User user) {
        users.add(user);
    }

    // Add new user by librarian
    public void addUserByLibrarian(Scanner sc) {
        System.out.print("Enter Roll number : ");
        Long id = sc.nextLong();
        sc.nextLine();
        System.out.print("Enter name : ");
        String name = sc.nextLine();
        System.out.println("\nSelect course");
        int i = 0;
        for (Course course : Course.values()) {
            System.out.println(++i + ". " + course);
        }
        sc.nextInt();
        User user = new User(name, id, Course.values()[i - 1]);
        users.add(user);
        System.out.println("User created successfully\n");
    }

    // Add books (This is helpful in hard code.)
    public void addBook(Book book) {
        books.add(book);
    }

    // This also help to add books (This is helpful in dynamic creation)
    public void addBookByLibrarian(Scanner sc) {
        System.out.print("Enter book name : ");
        String name = sc.nextLine();
        System.out.print("Enter author name : ");
        String author = sc.nextLine();
        System.out.print("Enter ISBN : ");
        String isbn = sc.nextLine();
        Book book = new Book(name, author, isbn);
        books.add(book);
    }


    // Helper Method to find objects

    public Book findBookByIsbn(String isbn){
        for(Book book : books){
            if(book.getIsbn().equals(isbn)){
                return book;
            }
        }
        return null; //Not found
    }

    public User findUserByLibraryID(Long id){
        for(User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User findUserByUniversityID(Long id){
        for(User user : users){
            if(user.getUniRollNo().equals(id)){
                return user;
            }
        }
        return null;
    }

    

    public void checkBook(Scanner sc){
        System.out.print("Enter ISBN no. ");
        String isbn = sc.nextLine();
        Book book = findBookByIsbn(isbn);
        if(book.equals(null)) System.out.print("This " + "Book not exist in library ");
        else{book.toString();}
    }

    // Show all users that exists
    public void showAllUsers(){
        System.out.println("All user that exists in database\n\n");
        for(User user : users){
            System.out.println("Library id : " + user.getId());;
            System.out.println("Name : "+ user.getName());
            System.out.println("University roll number : " + user.getUniRollNo());
            System.out.println("Course : "+ user.getCourse());
            System.out.print("Books borrowed : ");
            if(user.getBorrowBooks().isEmpty()) System.out.println("NULL");
            else{
                int i = 0 ;
                for(Book book : user.getBorrowBooks()){
                    System.out.print(i + ". " +book.getName() + "   ");
                }
            }
            System.out.println("");
        }
    }

    // Show all books that are available in library
    public void showAllBooks(){
        for(Book book : books){
            System.out.println(book.toString());
        }
    }

    // Issue book to the user
    public void issueBook(Scanner sc){
        System.out.println("Choose mode to Issue book ");
        System.out.println("1. Student Library id number");
        System.out.println("2. Student University id number");
        int choice = sc.nextInt();
        User user = null;
        switch (choice) {
            case 1 -> {
                System.out.print("Enter Library Id number : ");
                Long id = sc.nextLong();
                sc.nextLine();
                user = findUserByLibraryID(id);
            }
            case 2 -> {
                System.out.print("Enter University Id number : ");
                Long id = sc.nextLong();
                sc.nextLine();
                user = findUserByUniversityID(id);
            }
            default -> System.out.println("Invalid choice");
        }

        if(user == null) System.out.println("Id number is invalid");
        else{
            System.out.print("Enter the book ISBN number : ");
            Book book = findBookByIsbn(sc.nextLine());
            if(book == null) System.out.println("Invalid book ISBN number ");
            else{
                if(book.isBookAvailable()){
                    book.setAvailable(false);
                    book.setIssuedTo(user.getId());   //This is library id of this student
                    user.addBook(book);
                    System.out.println("Book is added to " + user.getName() + "'s account");
                }
                else{
                    System.out.println("Book is not available right now!!!!");
                }  
            }
        }

    }

    public void returnBook(Scanner sc){
        System.out.println("Enter Book ISBN number");
        String bookISBN = sc.nextLine();
        Book book = findBookByIsbn(bookISBN);
        book.setAvailable(true);
        Long id = book.getBookIssuedUserLibraryId();
        User user = findUserByLibraryID(id);
        user.removeBook(book);
        book.setIssuedTo(-1L);
    }

}
