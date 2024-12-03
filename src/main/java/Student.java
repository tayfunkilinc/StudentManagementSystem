//domaın-entity - kalıcı hala getırmek ıstedıgımız durumlar bu class ta yapılır
public class Student {
    private Integer id;  //burda none prımıtıve secmemızın sebebı primitive gırseydık varsayılan degerı 0 olurdu ben ılk deger atansın ıstemıyorum id bos gecilmesi durumunda bos oldugu bilinsin istiyorum
    private String name;
    private String lastname;
    private String city;
    private int age;
    //ogrencı notu gırılmemesi ve 0 alması durmunu ayırt etmek ıcın non-primitive kullanılması daha mantıklı - yanı fild anlamına göre tür belirlenir

    //parametreli ve parametresiz constructure olusturalım
    public Student() {
    }

    public Student(String name, String lastname, String city, int age) {
        this.name = name;
        this.lastname = lastname;
        this.city = city;
        this.age = age;
    }

    //getter-setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }
}
