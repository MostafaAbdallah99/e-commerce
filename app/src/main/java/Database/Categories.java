package Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ColumnInfo.INTEGER;

@Entity(indices = {@Index(value = {"CatName"},
        unique = true)})
public class Categories {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = INTEGER)
    private int CatID;

    @ColumnInfo(typeAffinity = TEXT)
    private String CatName;

    public int getCatID() {
        return CatID;
    }

    @NonNull
    public String getCatName() {
        return this.CatName;
    }

    public void setCatID(int catID) {
        CatID = catID;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }
}
