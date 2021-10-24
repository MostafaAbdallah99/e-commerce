package Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CategoriesDAO {
    @Insert
    void insertCategory(Categories category);
}
