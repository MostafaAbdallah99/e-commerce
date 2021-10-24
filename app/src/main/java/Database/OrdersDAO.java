package Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface OrdersDAO {
    @Insert
    void insertOrder(Orders order);
}
