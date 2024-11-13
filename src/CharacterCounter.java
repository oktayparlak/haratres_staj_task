import java.util.Scanner;

public class CharacterCounter {

    public static void start() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Maksimum karakter sayısını belirleyin: ");
            int maxCharCount = scanner.nextInt();
            scanner.nextLine();
            String sentence;

            while (true) {
                System.out.print("Bir cümle girin: ");
                sentence = scanner.nextLine();
                if (sentence.length() > maxCharCount) {
                    System.out.println("Girilen cümle karakter limitini aşıyor. Lütfen tekrar giriniz.");
                } else {
                    break;
                }
            }

            boolean caseSensitive = false;
            while (true) {
                System.out.println("Büyük/küçük harf duyarlılığı aktif olsun mu? (E/H)");
                String caseOption = scanner.nextLine().trim().toLowerCase();
                if (caseOption.equals("e")) {
                    caseSensitive = true;
                    break;
                } else if (caseOption.equals("h")) {
                    break;
                } else {
                    System.out.println("Lütfen geçerli bir cevap giriniz (Evet veya Hayır).");
                }
            }

            char characterToCount;
            while (true) {
                System.out.print("Analiz etmek için bir karakter girin: ");
                String input = scanner.nextLine();

                if (input.length() == 1) {
                    characterToCount = input.charAt(0);
                    break;
                } else {
                    System.out.println("Geçerli bir karakter giriniz.");
                }
            }

            int count = 0;
            for (char c : sentence.toCharArray()) {
                if (caseSensitive) {
                    if (c == characterToCount) {
                        count++;
                    }
                } else {
                    if (Character.toLowerCase(c) == Character.toLowerCase(characterToCount)) {
                        count++;
                    }
                }
            }

            System.out.println("Girilen cümlede" + characterToCount + " karakteri " + count + " kez geçmektedir.");

        } catch (Exception e) {
            if (e instanceof java.util.InputMismatchException) {
                System.out.println("Lütfen geçerli bir karakter girerek tekrar deneyin.");
            } else {
                System.out.println("Bir hata oluştu. Lütfen tekrar deneyin.");
            }
        }

    }
}