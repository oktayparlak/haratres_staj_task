import product.ProductManagementSystem;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Soru: Karakter Sayısı Bulma");
            System.out.println("2. Soru: Ürün Yönetim Sistemi");
            System.out.print("Hangi soruyu görmek istersiniz? (1/2): ");
            int question = scanner.nextInt();
            if (question == 1) {
                CharacterCounter.start();
            } else if (question == 2) {
                ProductManagementSystem.start();
            } else {
                System.out.println("Geçersiz soru numarası girdiniz.");
            }

        }
}
