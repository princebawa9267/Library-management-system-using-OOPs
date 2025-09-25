public class Book {
    private String isbn; //that is unique number given to the book with barcode usually issuer scan this code and get the book name, author, publisher and all such stuff.
    private String author ;
    private String name;
    private boolean isAvailable;
    private Long bookIssuedToLibraryId;

    public Book(String name, String author,String isbn ){
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.bookIssuedToLibraryId = -1L;
    }

    // Getters
    public boolean isBookAvailable(){
        return isAvailable;
    }
    public String getName(){
        return name;
    }
    public String getAuthor(){
        return author;
    }
    public String getIsbn(){
        return isbn;
    }
    public Long getBookIssuedUserLibraryId(){
        return bookIssuedToLibraryId;
    }

    // Setter
    public void setAvailable(boolean availableStatus){
        this.isAvailable = availableStatus;
    }
    public void setIssuedTo(Long libraryId){
        this.bookIssuedToLibraryId = libraryId;
    }

    @Override
    public String toString(){
        return "Title \"" + name + " \" Author : " + author + " ISBN : " + isbn + ", Availablity : " + isAvailable;
    }
}
