import java.util.Scanner;

//uygulamayı başlatan ve controller: ıstemcı - kullanıcı ıle ıletısıme gecer
public class Runner {
    public static Scanner inp = new Scanner(System.in);
    public static void main(String[] args) {
        start();
    }

    private static void start() {

        StudentService service = new StudentService();
        //7-c uygulama başlatıldığında tablo oluşturulsun
        service.createStudentTable();

        int select;
        int id;
        do {
            System.out.println("=========================================================");
            System.out.println("Öğrenci Yönetim Paneli");
            System.out.println("1-Öğrenci Kaydetme");
            System.out.println("2-Tüm Öğrencileri Görüntüleme");
            System.out.println("3-Öğrenciyi Güncelleme");
            System.out.println("4-Öğrenciyi Silme");
            System.out.println("5-Tek Bir Öğrenciyi Görüntüleme");
            System.out.println("6-Tum Ogrencilerin Ad-Soyad Bilgilerini Rapora Yazdirma");
            System.out.println("0-ÇIKIŞ");
            System.out.println("İşlem Seçiniz:  ");
            select = inp.nextInt();
            inp.nextLine(); // bunu sonradan bir okuma daha yaparız dıye her zaman okuma işlemlerinden sonra bu kodu yazmak buffer boşaltımı için önemli

            switch (select)
            {
                case 1:
                    //bilgileri verilen öğrenciyi kaydetme
                    Student newStudent = service.getStudentInfo();
                    new Thread(()->{
                        service.saveStudent(newStudent);
                    }).start();
                    break;
                case 2:
                    //öğrencileri konsola yazdırma
                    service.printAllStudent();
                    break;
                case 3:
                    //id si verilen öğrenciyi güncelleme
                    id = getIdInfo();
                    service.updateStudentById(id);
                    break;
                case 4:
                    //id si verilen öğrenciyi silme
                    id=getIdInfo();
                    service.deleteStudentById(id);
                    break;
                case 5:
                    //id si verilen öğrenciyi görüntüleme
                    id = getIdInfo();
                    service.printStudentById(id);
                    break;
                case 6:
                    //rapor yazdirma islevi yavas olabilecegi icin bu islevi ayri bir thread icinde yapacagiz
                    new Thread(()->{
                        service.generateReport();
                    }).start();
                    break;
                case 0:
                    System.out.println("İyi Günler");
                    break;
                default:
                    System.out.println("Hatalı Giriş");
                    break;
            }

        }while (select!=0);
    }
    private static int getIdInfo(){
        System.out.println("Öğrenci id: ");
        int id = inp.nextInt();
        inp.nextLine();
        return id;
    }
}
