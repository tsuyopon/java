package GraddleLib;

public class App {
    public String libfunction() {
        return "This is library";
    }

    public static void main(String[] args) {
        System.out.println(new App().libfunction());
    }
}
