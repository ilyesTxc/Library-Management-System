import java.util.List;
import java.util.HashMap;

public class BookRepository implements Repository<Book>{
    private HashMap<String,Book> store;

    public void save(Book book){
        if(book == null){
            return;
        }

        

    }

    public Book findById(String id){
        return null;
    }

    public  List<Book> findAll(){
        return null;
    }



}
