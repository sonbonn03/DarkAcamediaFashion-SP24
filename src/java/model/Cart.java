package model;

import dal.ProductDAO;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> list;

    public Cart() {
    }

    public Cart(List<Item> list) {
        this.list = list;
    }

    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {
        this.list = list;
    }



    public Item getItemByID(int id) {
        for (Item i : list) {
            if (i.getProduct().getId() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        return getItemByID(id).getQuantity();
    }

    public void addItem(Item i) {
        //neu da ton tai
        if (getItemByID(i.getProduct().getId()) != null ) {
            Item t = getItemByID(i.getProduct().getId());
            t.setQuantity(i.getQuantity());
        } else {
            list.add(i);
        }
    }

    public void removeItem(int id) {
        if (getItemByID(id) != null ) {
            list.remove(getItemByID(id));
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (Item i : list) {
            total += i.getPrice() * i.getQuantity();
        }
        return total;
    }

    public Product getProductById(int id, List<Product> list) {
        for (Product p : list) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Cart(String txt, List<Product> listP) {
        list = new ArrayList<>();
       ProductDAO producDao = new ProductDAO();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/"); // 4:2/3:1/4:2 => 4:2 và 3:1 // list 4:4, 3:1
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    String text = n[1];
                    int quantity = Integer.parseInt(text);
                    if (getItemByID(id) != null) {
                        getItemByID(id).setQuantity(getQuantityByID(id) + quantity);
                    } else {
                        Product p = getProductById(id, listP);
                        Item t = new Item(p, quantity, p.getPrice());
                        addItem(t);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
}
