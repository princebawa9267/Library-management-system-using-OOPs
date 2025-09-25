import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;   //This is unique id number that is mention over the Library card.
    private String name;
    private Long uniRollNo; // uniRollNo is roll number of the student who is studying 
    private Course course; //for courses enum is used because user account should be created with valid course details
    private List<Book> borrowedBooks;

    private static Long sequence = 0L;

    public User(String name, Long uniRollNo, Course course){
        id = ++sequence;
        this.name = name;
        this.uniRollNo = uniRollNo;
        this.course = course;
        borrowedBooks = new ArrayList<>();
    }

    // Getter 
    public String getName(){
        return name;
    }
    public Long getId(){
        return id;
    }
    public Long getUniRollNo(){
        return uniRollNo;
    }
    public Course getCourse(){
        return course;
    }
    public List<Book> getBorrowBooks(){
        return borrowedBooks;
    }

    // Setter
    public void addBook(Book book){
        borrowedBooks.add(book);
        System.out.println("INFO : Added book : "+book.getName());
    }
    public void removeBook(Book book){
        borrowedBooks.remove(book);
        System.out.println("INFO : Deleted book "+ book.getName());
    }
}


