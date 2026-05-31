import java.util.Objects;

public class Book {
    private String id;
    private String title;
    private String author;
    private int availableCopies;

    Book(String id, String title,String author, int availableCopies){
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public String getId(){
        return id;
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Book other = (Book) obj;

        return Objects.equals(this.id,other.id);
    }

    public int getAvailableCopies(){
        return this.availableCopies;
    }

    public void decreaseAvaialbleCopies(){
        this.availableCopies--;
    }

    public void incrementAvaialbleCopies(){
        this.availableCopies++;
    }

    public String getAuthor(){
        return this.author;
    }

}
