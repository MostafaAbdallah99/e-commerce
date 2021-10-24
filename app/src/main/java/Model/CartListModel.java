package Model;

public class CartListModel {

    private Integer image;
    private Integer add;
    private Integer minus;
    private Integer delete;
    private String title;
    private String quantity;
    private String price;
    private String cartNumber;

    public CartListModel(Integer image, String title, String price, Integer add, Integer minus, Integer delete, String quantity, String cartNumber) {
        this.setImage(image);
        this.setTitle(title);
        this.setAdd(add);
        this.setPrice(price);
        this.setMinus(minus);
        this.setDelete(delete);
        this.setQuantity(quantity);
        this.setCartNumber(cartNumber);
    }


    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getAdd() {
        return add;
    }

    public void setAdd(Integer add) {
        this.add = add;
    }

    public Integer getMinus() {
        return minus;
    }

    public void setMinus(Integer minus) {
        this.minus = minus;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCartNumber() {
        return cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }
}
