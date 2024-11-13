package product;

public class Product {

    private String name;
    private double price;
    private int stock;
    private double rating;

    public Product(String name, double price, int stock, double rating) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s - Fiyat: %.2f, Stok: %d, Değerlendirme: %.1f", this.name, this.price, this.stock, this.rating);
    }
}
