package Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CustomersDAO {
    @Insert
    void insertCustomer(Customers customer);

    @Query("SELECT COUNT(*) FROM Customers WHERE Username = :email")
    int checkEmail(String email);

    @Query("SELECT COUNT(*) FROM Customers WHERE Username = :email AND Password = :password")
    int checkUser(String email, String password);

    @Query("SELECT * FROM Customers WHERE Username = :email AND Password = :password LIMIT 1")
    Customers getUser(String email, String password);

    @Query("UPDATE Customers SET FirstSecurityQuestion = :firstQuestion, SecondSecurityQuestion = :secondQuestion WHERE Username = :email")
    void updateCustomerSecurity(String firstQuestion, String secondQuestion, String email);

    @Query("SELECT COUNT(*) FROM Customers WHERE FirstSecurityQuestion = :firstQuestion AND SecondSecurityQuestion = :secondQuestion AND Username = :email")
    int checkQuestions(String firstQuestion, String secondQuestion, String email);

    @Query("UPDATE Customers SET Password = :password WHERE Username = :email")
    void updatePassword(String password, String email);
}
