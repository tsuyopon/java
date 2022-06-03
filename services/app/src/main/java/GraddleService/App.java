package GraddleService;

public class App {
    public String getService() {
        return "This is Service!";
    }

    public static void main(String[] args) {
        System.out.println(new App().getService());
    }
}
