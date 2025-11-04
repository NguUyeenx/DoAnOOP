import java.util.Scanner;

public final class Input {
    // Scanner dung chung cho toan bo app (KHONG close)
    private static final Scanner SC = new Scanner(System.in);

    private Input() {}

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SC.nextLine();
    }

    public static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine();
            if (s != null) s = s.trim();
            if (s != null && !s.isEmpty()) return s;
            System.out.println("Khong duoc de trong, vui long nhap lai.");
        }
    }

    public static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String s = SC.nextLine();
            try {
                int v = Integer.parseInt(s.trim());
                if (v >= min && v <= max) return v;
            } catch (Exception ignored) {}
            System.out.println("Gia tri khong hop le. Can so nguyen trong khoang [" + min + ", " + max + "].");
        }
    }

    public static double readNonNegativeDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = SC.nextLine();
            if (line != null) line = line.trim();
            try {
                double v = Double.parseDouble(line);
                if (v >= 0) return v;
            } catch (Exception ignored) {}
            System.out.println("Gia tri khong hop le (can so >= 0). Vui long nhap lai.");
        }
    }
}