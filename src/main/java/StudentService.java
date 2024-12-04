import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
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
    //9-a tum ogrencileri konsola yazdirma
    public  void printAllStudent(){
        List<Student> students = repo.findAll();
        System.out.println("-----------------------TUM OGRENCILER-----------------------------");
        for (Student student: students){
            System.out.println("id: "+ student.getId() +
                    " ad: " + student.getName() +
                    " soyad: " + student.getLastname() +
                    " yas: " + student.getAge() +
                    " sehir: " + student.getCity());
        }
    }
    //10-a-id si verilen ogrenciyi getirme
    public Student getStudentById(int id){
        Student student = (Student) repo.findById(id);
        return student;
    }
    //10-b id si verilen ogrenciyi goruntuleme
    public void printStudentById(int id){
        Student foundStudent = getStudentById(id);
        if (foundStudent == null){
            System.out.println("ID Verilen Ogrenci Bulunamadi");
        }
        else {
            System.out.println(foundStudent);
        }
    }
    //11-a id si verilen ogrencinin bilgilerini yeni bilgiler ile degistirme
    public void updateStudentById(int id){
        //bu id ile ogrenci var mi?
        Student foundStudent = getStudentById(id);
        if (foundStudent==null) {
            System.out.println("Kusura Bakma Boyle Bir Ogrenci Bulunamadi");
        }else { // harry potter
            //girilecek olan yeni bilgiler nedir?
            Student newStudent = getStudentInfo();
            //var olan ogrencinin bilgilerini yeni ogrenci ile degistirecegiz
            foundStudent.setName(newStudent.getName());
            foundStudent.setLastname(newStudent.getLastname());
            foundStudent.setCity(newStudent.getCity());
            foundStudent.setAge(newStudent.getAge());
            //id ayni kalacak
            //foundstudent: name:yeni lastname:yeni city:yeni age:yeni id:eski
            repo.update(foundStudent);
        }
    }
    //12-a idsi verilen ogrenciyi silme
    public void deleteStudentById(int id){
        repo.deleteById(id);
    }
    //13-tum ogrencilerin ad-soyas bilgilerini rapora yazdirma
    public void generateReport(){
        List<Student> allStudents = repo.findAll();
        System.err.println("Report is Loading....");
        try {
            Thread.sleep(10000);
            FileWriter writer = new FileWriter("student_report.txt"); // burda her seferinde default File writer dan dolayi bastan yazar
            writer.write("*** Student Report ***\n");
            writer.write("---------------------------\n");
            for (Student student : allStudents){
                writer.write("Ad : " + student.getName() + " Soyad: " + student.getLastname() + "\n");
            }
            writer.close();
            System.err.println("Report Generated an Printed to stuntent_report.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
