import java.time.LocalDate;
import java.util.*;

public class Library {
    // class that ties everything together
    private BookRepository repository;
    private List<Librarian> librarians;
    private List<Member> members;
    private TreeMap<LocalDate, List<Loan>> loans;

    Library(){
        this.repository = new BookRepository();
        this.librarians = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new TreeMap<>();
    }

    public void addBook(Book b){
        repository.save(b);
    }

    public Book findBookById(String id){
        return repository.findById(id);
    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public void borrowBook(Book b, Member member){
        if(b == null || member == null){
            return ;
        }
        // set a loan date
        if(b.getAvailableCopies() > 0){
            Loan bookLoan = new Loan(b.getId(),member.getId(),LocalDate.now().plusDays(14));
            LocalDate today = LocalDate.now();

            loans.computeIfAbsent(today, date -> new ArrayList<>()).add(bookLoan);

            member.addBorrowedBook(b.getId());
            b.decreaseAvaialbleCopies();
        }else{
            System.out.println("There are no available copies of this book at this time");
        }
    }

    public void returnBook(LocalDate borrowDate,Book b, Member member){
        if (borrowDate == null || b == null || member == null) {
            return;
        }

        member.removeBorrowedBook(b.getId());
        b.incrementAvaialbleCopies();

        List<Loan> loansOnDate = loans.get(borrowDate); // list of loands on that date

        if(loansOnDate == null){
            return ;
        }

        loansOnDate.removeIf(loan -> loan.getBookId().equals(b.getId())
                            && loan.getMemberId().equals(member.getId()));

        if(loansOnDate == null){
            loans.remove(borrowDate);
        }
    }

    public List<Loan> listOverdueLoans(LocalDate today){
        List<Loan> result = new ArrayList<>();

        for(LocalDate date : loans.keySet()){
            for(Loan l : loans.get(date)){
                if(l.getDueDate().isBefore(today)){
                    result.add(l);
                }
            }
        }
        return result;
    }

    public List<Book> searchBookByAuthor(String author){
        List<Book> result = new ArrayList<>();

        if(author == null){
            return result;
        }

        for(Book b : repository.findAll()){
            if(b.getAuthor().equals(author)){
                result.add(b);
            }
        }
        return result;
    }

}
