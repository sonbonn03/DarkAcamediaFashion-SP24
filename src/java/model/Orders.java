package model;

public class Orders {

    private String id, fullName, phone, address;
    private float total;
    private String CreatedAt, accountId,note;

    public Orders() {
        
    }

    public Orders(String id, String fullName, String phone, String address, float total, String CreatedAt, String accountId,String note) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.total = total;
        this.CreatedAt = CreatedAt;
        this.accountId = accountId;
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String CreatedAt) {
        this.CreatedAt = CreatedAt;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Orders{" + "id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", address=" + address + ", total=" + total + ", CreatedAt=" + CreatedAt + ", accountId=" + accountId + ", note=" + note + '}';
    }

}
