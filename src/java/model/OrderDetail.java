package model;

public class OrderDetail {
    private String id;
    private int quantity;
    private float price;
    private String pid,oid;
    private Product p ;
    public OrderDetail() {
    }

    public OrderDetail(String id, int quantity, float price, String pid, String oid) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.pid = pid;
        this.oid = oid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }
    
}
