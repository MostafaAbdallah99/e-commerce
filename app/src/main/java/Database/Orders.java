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

@Entity(foreignKeys = @ForeignKey(entity = Customers.class, parentColumns = "CustomerID", childColumns = "CustomerID", onDelete = CASCADE,
        onUpdate = CASCADE), indices = {@Index(value = {"CustomerID"})})

public class Orders {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(typeAffinity = INTEGER)
    private int OrdID;

    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String OrdDate;

    @NonNull
    @ColumnInfo(typeAffinity = TEXT)
    private String Address;

    @NonNull
    @ColumnInfo(typeAffinity = INTEGER)
    private int CustomerID;

    public int getOrdID() {
        return OrdID;
    }

    public void setOrdID(int ordID) {
        OrdID = ordID;
    }

    @NonNull
    public String getOrdDate() {
        return OrdDate;
    }

    public void setOrdDate(@NonNull String ordDate) {
        OrdDate = ordDate;
    }

    @NonNull
    public String getAddress() {
        return Address;
    }

    public void setAddress(@NonNull String address) {
        Address = address;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        CustomerID = CustomerID;
    }
}
