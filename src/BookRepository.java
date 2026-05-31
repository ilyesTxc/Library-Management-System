import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class BookRepository implements Repository<Book>{
    private HashMap<String,Book> bookStore;

    BookRepository(){
        this.bookStore = new HashMap<>();
    }

    public void save(Book book){
        if(book == null){
            return;
        }
        bookStore.put(book.getId(),book);
    }

    public Book findById(String id){
        return bookStore.get(id);
    }

    public  List<Book> findAll(){
        return new ArrayList<>(bookStore.values());
    }



}
