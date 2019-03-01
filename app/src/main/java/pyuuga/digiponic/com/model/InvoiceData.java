package pyuuga.digiponic.com.model;

public class InvoiceData {

    private String name, price;
    private int id, count;

    public InvoiceData(int id, String name, String price) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
