import java.util.Scanner;

//servıce: bussıness manttiksal lojik ıslemleri burda yapılır
// service class ları repo classları ıle görüşür
public class StudentService {
    Scanner inp = Runner.inp;  // runnerda tanımladığımızı burda kullanmış olduk

    //repositorynin methodlarını kullanabılmek için obje oluşturalım
    private Repository repo = new StudentRepository(); // burada paşka başka constroctor lar oluşturabiliriz

    //7-a student tablosunu oluşturma
    public void createStudentTable(){
        repo.createTable();
    }
    //8-a verilen bilgiler ile ogrenciyi kaydetmek
    public Student getStudentInfo(){
        System.out.println("Ad: ");
        String name = inp.nextLine();
        System.out.println("Soyad: ");
        String lastname = inp.nextLine();
        System.out.println("Sehir: ");
        String city = inp.next();
        System.out.println("Yas: ");
        int yas = inp.nextInt();
        inp.nextLine();

       return  new Student(name,lastname,city,yas); //id otomatik olusturulu null olarak duruyor bir ekleme yapilmadigi icin
    }
    public void saveStudent(Student newStudent){
        repo.save(newStudent);
    }

}
