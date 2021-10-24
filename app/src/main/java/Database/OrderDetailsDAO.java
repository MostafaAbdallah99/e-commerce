package Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface OrderDetailsDAO {
    @Insert
    void insertOrderDetail(OrderDetails orderDetail);
}
