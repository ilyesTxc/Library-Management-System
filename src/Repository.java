import java.util.List;

public interface Repository<T> {
    public abstract void save(T arg);

    public abstract T findById(String id);

    public abstract List<T> findAll();
}
