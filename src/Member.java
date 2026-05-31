import java.util.HashSet;

public class Member extends Person{
    private HashSet<String> booksBorrowed;

    Member(String id, String name){
        super(id,name);
        this.booksBorrowed = new HashSet<>();
    }

    public void role(){}

    public String getId(){
        return this.getPersonId();
    }

    public void addBorrowedBook(String id){
        booksBorrowed.add(id);
    }

    public void removeBorrowedBook(String id){
        booksBorrowed.remove(id);
    }

}
