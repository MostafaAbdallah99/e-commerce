package Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ColumnInfo.INTEGER;
import static androidx.room.ForeignKey.CASCADE;

@Entity(primaryKeys = {"OrdID", "ProID"},
        foreignKeys = {@ForeignKey(entity = Orders.class, parentColumns = "OrdID", childColumns = "OrdID", onDelete = CASCADE, onUpdate = CASCADE),
                @ForeignKey(entity = Products.class, parentColumns = "ProID", childColumns = "ProID", onDelete = CASCADE, onUpdate = CASCADE)},
        indices = {@Index(value = "OrdID"), @Index(value = "ProID")}
)
public class OrderDetails {

    @ColumnInfo(typeAffinity = INTEGER)
    private int OrdID;

    @ColumnInfo(typeAffinity = INTEGER)
    private int ProID;

    @ColumnInfo(typeAffinity = INTEGER)
    private int Quantity;

    public int getOrdID() {
        return OrdID;
    }

    public void setOrdID(int ordID) {
        OrdID = ordID;
    }

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
}
