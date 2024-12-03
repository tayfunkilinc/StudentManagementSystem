import java.util.List;
// course, stutent, instructor için kullanılsın diye oluşturuyoruz
//data tipinden Bağımsız CRUD işlemleri listeleyen interface - generic bir interface oluşturuyoruz
public interface Repository <S,U>{
    void createTable();

    void save(S entity);

    List<S> findAll();

    void update(S entity);

    void deleteById(U id);

    S findById(U id);

}
