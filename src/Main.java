import java.time.LocalDate;
import java.util.List;

public class Main{

    public static void main(String[] args) {
        // create librarians, members, books, try borrowing out of stock books...
        // no need to manually create loans
        Book book1 = new Book("90lOM3", "Vive Bourguiba", "Adem khechine", 3);
        Book book2 = new Book("122KSL", "SevenKeys", "Rayen Massoudi", 5);
        Book book3 = new Book("3A88IL","PFE","Kouja Mohamed Aziz", 2);

        Member member1 = new Member("X","Achref Ayechi");
        Member member2 = new Member("Y", "Iyed Gouia");
        Member member3 = new Member("Z", "Melek Arbi");

        Librarian librarian = new Librarian("O", "Aam anwer");

        BookRepository bookRepository = new BookRepository();
        Library library = new Library();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        System.out.println("All books:");
        for (Book book : library.getAllBooks()) {
            System.out.println(book);
        }

        System.out.println("\nBorrowing book3:");
        library.borrowBook(book3, member1);
        library.borrowBook(book3, member2);
        //this one should fail because there are no more copies (out of stock)
        library.borrowBook(book3, member3);

        System.out.println("\nAvailable copies of book3:");
        System.out.println(book3.getAvailableCopies());

        System.out.println("\nFind book by ID");
        Book foundBook = library.findBookById("122KSL");
        //this one should fail
        Book foundBook2 = library.findBookById("AZEZEZ");

        System.out.println("\nSearch books by author:");
        List<Book> booksByAuthor = library.searchBookByAuthor("Kouja Mohamed Aziz");

        for(Book b : booksByAuthor){
            System.out.println(b);
        }

        System.out.println("\nOverdue Books");
        List<Loan> overDueLoans = library.listOverdueLoans(LocalDate.now().plusDays(20));

        for(Loan l : overDueLoans){
            System.out.println(l);
        }

    }
}