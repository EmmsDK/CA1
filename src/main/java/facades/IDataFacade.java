package facades;
import java.util.List;
import errorhandling.EntityNotFoundException;

public interface IDataFacade<T> {
    T create(T t);
    //T getByString(String fill) throws EntityNotFoundException;
    //T getByInt(int fill) throws EntityNotFoundException;
    List<T> getAll();
    T update(T t) throws EntityNotFoundException;
    T delete(int id) throws EntityNotFoundException;
}
