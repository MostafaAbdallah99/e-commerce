package Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ProductsDAO {
    @Insert
    void insertProduct(Products product);

    @Query("SELECT * FROM Products WHERE ProName = :productName LIMIT 1")
    Products getProductByVoiceOrText(String productName);

    @Query("SELECT Price FROM Products WHERE CatID = :category AND ProName = :productName LIMIT 1")
    String getPrice(int category, String productName);

    @Query("SELECT ProName FROM Products WHERE CatID = :category")
    List<String> getProducts(int category);

    @Query("SELECT CatID FROM Products WHERE ProName = :productName")
    int getCategory(String productName);

    @Query("SELECT ProID FROM Products WHERE ProName = :productName")
    int getProduct(String productName);

    @Query("SELECT Quantity FROM Products WHERE ProName = :productName")
    int getQuantity(String productName);
}
