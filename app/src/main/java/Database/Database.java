package Database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Categories.class, Customers.class, OrderDetails.class, Orders.class, Products.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    private static final String DATABASE_NAME = "E_Commerce";
    private static Database databaseInstance;
    private static final Object LOCK = new Object();

    public static Database getInstance(Context context) {
        if(databaseInstance == null){
            synchronized (LOCK){
                databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, Database.DATABASE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return databaseInstance;
    }

    public abstract CustomersDAO customersDAO();
    public abstract OrderDetailsDAO orderDetailsDAO();
    public abstract ProductsDAO productsDAO();
    public abstract OrdersDAO ordersDAO();
    public abstract CategoriesDAO categoriesDAO();
}
