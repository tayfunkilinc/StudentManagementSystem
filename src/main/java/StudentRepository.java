//repository:veritabanı ile ilgili işlemler
//create table , record, update, delete, findAll
//student, instructor,course .... gibi başka başka alanlarda olabilir
// bu yapılacakların herkesin yaptıgı işleri standarlaştırmak inin interface tanımlama ve kurala bağlamak daha güzel olur
//bu katmanda herhangi bir veri uzerinde degisiklik yapilmaz veriler cekilir ve iletilir

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    //9-tum ogrencilerin database den cekilmesi
    @Override
    public List<Student> findAll() {
        JdbcUtils.setConnection();
        JdbcUtils.setStatement();
        List<Student> allStudent =new ArrayList<>();
        try {
            ResultSet rs = JdbcUtils.st.executeQuery("SELECT * FROM t_student");
            while (rs.next()){
                Student student = new Student(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("city"),
                        rs.getInt("age"));
                student.setId(rs.getInt("id"));
                allStudent.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return allStudent;
    }

    //11-verilen yeni bilgisi ile varolan ogrenciyi guncelleme
    @Override
    public void update(Student student) { // student: ogrencinin yeni bilgileri
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("UPDATE t_student SET name=?, lastname=?, city=?, age=? WHERE id=?");
        try {
            JdbcUtils.prst.setString(1,student.getName());
            JdbcUtils.prst.setString(2,student.getLastname());
            JdbcUtils.prst.setString(3,student.getCity());
            JdbcUtils.prst.setInt(4,student.getAge());
            JdbcUtils.prst.setInt(5, student.getId());
            int updated = JdbcUtils.prst.executeUpdate();
            if (updated>0){
                System.out.println("Guncelleme Basarili" + updated);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //12-id si verilen ogrenciyi silme islemi
    @Override
    public void deleteById(Integer id) {
        JdbcUtils.setConnection();
        JdbcUtils.setStatement();

        try {
            int deleted = JdbcUtils.st.executeUpdate("DELETE FROM t_student WHERE id="+id);
            //id ye sahip kayit varsa 1 kayit yoksa 0 kayit silinir
            if (deleted>0){
                System.out.println(id+". Ogrenci Silindi Byy");
            }else {
                System.out.println("Ogrenci Bulunamadi");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //10-id si verilen ogrenciyi bulma islemi
    @Override
    public Student findById(Integer id) {
        Student student =null; // geriye donderecegimiz deger uyusmama ihtimaline karsi null olarak set edildi
        JdbcUtils.setConnection();
        JdbcUtils.setPreparedStatement("SELECT * FROM t_student WHERE id=?");
        try {
            JdbcUtils.prst.setInt(1,id);
            ResultSet rs = JdbcUtils.prst.executeQuery();
            if (rs.next()){
                student =new Student(rs.getString("name"),
                        rs.getString("lastname"),
                        rs.getString("city"),
                        rs.getInt("age"));
                student.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.st.close();
                JdbcUtils.connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return student;
    }
}
