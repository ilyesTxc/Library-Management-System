import java.time.LocalDate;
import java.util.*;

public class Library {
    // class that ties everything together
    private BookRepository repository;
    private List<Librarian> librarians;
    private List<Member> members;
    private TreeMap<LocalDate, List<Loan>> loansByDueDate;

    Library(){
        this.repository = new BookRepository();
        this.librarians = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loansByDueDate = new TreeMap<>();
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
            Loan bookLoan = new Loan(b.getId(),member.getId(),LocalDate.now().plusDays(14),LocalDate.now());
            LocalDate dueDate = LocalDate.now().plusDays(14);

            loansByDueDate.computeIfAbsent(dueDate, date -> new ArrayList<>()).add(bookLoan);

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

        List<Loan> loansOnDate = loansByDueDate.get(borrowDate); // list of loans on that date

        if(loansOnDate == null){
            System.out.println("No loans found on this date.");
            return;
        }

        boolean removed = loansOnDate.removeIf(loan -> loan.getBookId().equals(b.getId())
                            && loan.getMemberId().equals(member.getId()));

        if(!removed){
            System.out.println("This member did not borrow this book on this date");
            return;
        }

        member.removeBorrowedBook(b.getId());
        b.incrementAvaialbleCopies();

        if(loansOnDate.isEmpty()){
            loansByDueDate.remove(borrowDate);
        }
    }

    public List<Loan> listOverdueLoans(LocalDate today){
        // return the list of loans due before today (count today as well)
        SortedMap<LocalDate, List<Loan>> result = loansByDueDate.headMap(today, true);
        List<Loan> resultLoan = new ArrayList<>();

        for(LocalDate date : result.keySet()){
            resultLoan.addAll(result.get(date));
        }

        return resultLoan;

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
