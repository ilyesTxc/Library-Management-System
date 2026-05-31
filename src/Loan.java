import java.time.LocalDate;

public class Loan {
    private String bookId;
    private String memberId;
    private LocalDate dueDate;

    Loan(String bookId,String memberId,LocalDate dueDate){
        this.bookId = bookId;
        this.memberId = memberId;
        this.dueDate = dueDate;
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

}
