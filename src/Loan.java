import java.time.LocalDate;

public class Loan {
    private String bookId;
    private String memberId;
    private LocalDate dueDate;
    private LocalDate borrowDate;

    Loan(String bookId,String memberId,LocalDate dueDate,LocalDate borrowDate){
        this.bookId = bookId;
        this.memberId = memberId;
        this.dueDate = dueDate;
        this.borrowDate = borrowDate;
    }

    public String getBookId(){
        return this.bookId;
    }

    public String getMemberId(){
        return this.memberId;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }

    public LocalDate getBorrowDate(){
        return this.borrowDate;
    }
}
