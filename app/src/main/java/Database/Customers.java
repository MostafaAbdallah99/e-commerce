package Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import static androidx.room.ColumnInfo.TEXT;
import static androidx.room.ColumnInfo.INTEGER;

@Entity(indices = {@Index(value = {"Username"},
        unique = true)})
public class Customers {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = INTEGER)
    private int CustomerID;

    @ColumnInfo(typeAffinity = TEXT)
    private String CutName;

    @ColumnInfo(typeAffinity = TEXT)
    private String Username;

    @ColumnInfo(typeAffinity = TEXT)
    private String Password;

    @ColumnInfo(typeAffinity = TEXT)
    private String Job;

    @ColumnInfo(typeAffinity = TEXT)
    private String BirthDate;

    @ColumnInfo(typeAffinity = TEXT)
    private String Gender;

    @ColumnInfo(typeAffinity = TEXT)
    private String FirstSecurityQuestion;

    @ColumnInfo(typeAffinity = TEXT)
    private String SecondSecurityQuestion;

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    @NonNull
    public String getCutName() {
        return CutName;
    }

    public void setCutName(@NonNull String cutName) {
        CutName = cutName;
    }

    @NonNull
    public String getUsername() {
        return Username;
    }

    public void setUsername(@NonNull String username) {
        Username = username;
    }

    @NonNull
    public String getPassword() {
        return Password;
    }

    public void setPassword(@NonNull String password) {
        Password = password;
    }

    @NonNull
    public String getJob() {
        return Job;
    }

    public void setJob(@NonNull String job) {
        Job = job;
    }

    @NonNull
    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(@NonNull String birthDate) {
        BirthDate = birthDate;
    }

    @NonNull
    public String getGender() {
        return Gender;
    }

    public void setGender(@NonNull String gender) {
        Gender = gender;
    }

    @NonNull
    public String getFirstSecurityQuestion() {
        return FirstSecurityQuestion;
    }

    public void setFirstSecurityQuestion(@NonNull String firstSecurityQuestion) {
        FirstSecurityQuestion = firstSecurityQuestion;
    }

    @NonNull
    public String getSecondSecurityQuestion() {
        return SecondSecurityQuestion;
    }

    public void setSecondSecurityQuestion(@NonNull String secondSecurityQuestion) {
        SecondSecurityQuestion = secondSecurityQuestion;
    }
}


