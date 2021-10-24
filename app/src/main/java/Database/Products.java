package Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Categories.class, parentColumns = "CatID", childColumns = "CatID", onDelete = CASCADE,
        onUpdate = CASCADE), indices = {@Index(value = {"CatID"})})
public class Products {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = INTEGER)
    private int ProID;

    @ColumnInfo(typeAffinity = INTEGER)
    private int Quantity;

    @ColumnInfo(typeAffinity = TEXT)
    private String Price;

    @ColumnInfo(typeAffinity = TEXT)
    private String ProName;

    @ColumnInfo(typeAffinity = TEXT)
    private String barcode;

    @ColumnInfo(typeAffinity = INTEGER)
    private int CatID;

    public int getProID() {
        return ProID;
    }

    public void setProID(int proID) {
        ProID = proID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @NonNull
    public String getProName() {
        return ProName;
    }

    public void setProName(@NonNull String proName) {
        ProName = proName;
    }

    @NonNull
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NonNull String barcode) {
        this.barcode = barcode;
    }

    public int getCatID() {
        return CatID;
    }

    public void setCatID(int catID) {
        CatID = catID;
    }
}
