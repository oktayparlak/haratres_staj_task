package product;

import java.util.*;

public class ProductManagementSystem {

    public static void start() {

        try (Scanner scanner = new Scanner(System.in)) {
            List<Product> products = new ArrayList<>();
            // Ürün ekleme
            System.out.print("Kaç farklı ürün gireceksiniz: ");
            int productCount = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizleyin

            for (int i = 0; i < productCount; i++) {
                System.out.println("Ürün " + (i + 1) + ":");

                String name;
                while (true) {
                    System.out.print("Ürün Adı (20 karaktere kadar): ");
                    name = scanner.nextLine();
                    if (name.length() <= 20) break;
                    System.out.println("Ürün adı 20 karakterden fazla olamaz. Lütfen tekrar girin.");
                }

                double price;
                while (true) {
                    System.out.print("Birim Fiyat (1 ile 100 arasında): ");
                    price = scanner.nextDouble();
                    if (price >= 1 && price <= 100) break;
                    System.out.println("Fiyat 1 ile 100 arasında olmalıdır. Lütfen tekrar girin.");
                }

                int stock;
                while (true) {
                    System.out.print("Stok Miktarı (en az 1): ");
                    stock = scanner.nextInt();
                    if (stock >= 1) break;
                    System.out.println("Stok miktarı en az 1 olmalıdır. Lütfen tekrar girin.");
                }

                System.out.print("Değerlendirme Puanı (5 üzerinden): ");
                double rating = scanner.nextDouble();
                scanner.nextLine(); // Satır sonu karakterini temizleyin

                products.add(new Product(name, price, stock, rating));
            }

            // Sıralama tercihi
            System.out.print("Ürünleri hangi kritere göre sıralamak istersiniz? (name/stock/rating): ");
            String criteria = scanner.nextLine().toLowerCase();

            System.out.print("Sıralama türü artan mı azalan mı olsun? (artan/azalan): ");
            String order = scanner.nextLine().toLowerCase();

            // Ürünleri sıralama
            Comparator<Product> comparator = null;
            if ("name".equals(criteria)) {
                comparator = Comparator.comparing(p -> p.getName());
            } else if ("stock".equals(criteria)) {
                comparator = Comparator.comparingInt(p -> p.getStock());
            } else if ("rating".equals(criteria)) {
                comparator = Comparator.comparingDouble(p -> p.getRating());
            }

            if (comparator != null) {
                if ("azalan".equals(order)) {
                    comparator = comparator.reversed();
                }
                Collections.sort(products, comparator);
            }

            // Sıralanmış ürünleri göster
            System.out.println("Sıralanmış Ürünler:");
            products.forEach(System.out::println);

            // Sepete ekleme
            List<Product> cart = new ArrayList<>();
            while (true) {
                System.out.print("Sepete ürün eklemek ister misiniz? (Evet/Hayır): ");
                String addToCart = scanner.nextLine().toLowerCase();

                if ("hayır".equals(addToCart)) break;

                System.out.print("Eklemek istediğiniz ürünün adı: ");
                String productName = scanner.nextLine();
                System.out.print("Eklemek istediğiniz adet: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Satır sonu karakterini temizleyin

                // Ürünü bul ve sepete ekle
                Product selectedProduct = products.stream()
                        .filter(p -> p.getName().equalsIgnoreCase(productName))
                        .findFirst()
                        .orElse(null);

                if (selectedProduct != null && selectedProduct.getStock() >= quantity) {
                    selectedProduct.setStock(selectedProduct.getStock() - quantity);
                    cart.add(new Product(selectedProduct.getName(), selectedProduct.getPrice() * quantity, quantity, selectedProduct.getRating()));
                } else {
                    System.out.println("Stokta yeterli ürün yok veya ürün bulunamadı. Lütfen tekrar deneyin.");
                }
            }

            // Sepet tutarını hesapla (İndirim uygulanması)
            double total = 0;
            if (!cart.isEmpty()) {
                Product firstProduct = cart.get(0);
                total += firstProduct.getPrice();
                for (int i = 1; i < cart.size(); i++) {
                    Product currentProduct = cart.get(i);
                    if (firstProduct.getPrice() > currentProduct.getPrice()) {
                        firstProduct.setPrice(firstProduct.getPrice() - currentProduct.getPrice());
                    }
                    total += currentProduct.getPrice();
                }
            }

            // Sepeti göster
            System.out.println("Sepetiniz:");
            cart.forEach(p -> System.out.printf("%s - Adet: %d, Toplam Fiyat: %.2f\n", p.getName(), p.getStock(), p.getPrice()));
            System.out.printf("Sepet Toplamı: %.2f\n", total);


        } catch (InputMismatchException e) {
            System.out.println("Lütfen doğru veri türü girin.");
        }

    }

}
