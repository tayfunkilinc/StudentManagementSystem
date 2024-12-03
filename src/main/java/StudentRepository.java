//repository:veritabanı ile ilgili işlemler
//create table , record, update, delete, findAll
//student, instructor,course .... gibi başka başka alanlarda olabilir
// bu yapılacakların herkesin yaptıgı işleri standarlaştırmak inin interface tanımlama ve kurala bağlamak daha güzel olur


import java.sql.SQLException;
import java.util.List;

public class StudentRepository implements Repository<Student, Integer>{

    //7-t_student tablosunu oluşturma
    @Override
    public void createTable() {
        JdbcUtils.setConnection(); // bağlantı oluşturuldu kapılar açıldı
        JdbcUtils.setStatement(); // statement başlattık

        try {
            JdbcUtils.st.execute("CREATE TABLE IF NOT EXISTS t_student(\n" +
                    "id SERIAL UNIQUE,\n" +
                    "name VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0),\n" +
                    "lastname VARCHAR(50) NOT NULL CHECK(LENGTH(name)>0),\n" +
                    "city VARCHAR(50) NOT NULL, \n" +
                    "age INTEGER NOT NULL CHECK(age>0)  )"  );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //8-öğrenciyi kaydetme
    @Override
    public void save(Student student) {
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("INSERT INTO t_student(name, lastname, city, age) VALUES(?,?,?,?)");

        try {
            JdbcUtils.prst.setString(1, student.getName());
            JdbcUtils.prst.setString(2,student.getLastname());
            JdbcUtils.prst.setString(3,student.getCity());
            JdbcUtils.prst.setInt(4,student.getAge());
            JdbcUtils.prst.executeUpdate();
            System.out.println("------Basarili Bir Sekilde Kaydedildi------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                JdbcUtils.prst.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Student findById(Integer id) {
        return null;
    }
}
