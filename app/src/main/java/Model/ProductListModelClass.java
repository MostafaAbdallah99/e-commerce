package Model;

public class ProductListModelClass {

    Integer image;
    String title;
    Integer like;
    String price;

    public ProductListModelClass(Integer image, String title, Integer like, String price) {
        this.image = image;
        this.title = title;
        this.like = like;
        this.price = price;
    }

    public Integer getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public Integer getLike() {
        return like;
    }

    public String getPrice(){return price;}
}
