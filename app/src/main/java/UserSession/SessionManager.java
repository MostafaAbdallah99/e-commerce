package UserSession;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

public class SessionManager {

    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String IS_REMEMBER_ME = "IsRememberMe";
    public static final String KEY_CUSTOMER_NAME = "customerName";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_SESSION_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_SESSION_PASSWORD = "password";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_BIRTH_DATE = "birthDate";
    public static final String KEY_JOB = "job";
    public static final String USER_SESSION = "userLoginSession";
    public static final String REMEMBER_ME_SESSION = "rememberMe";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context _context, String sessionName){
        context = _context;
        usersSession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }

    public void createLoginSession(String customerName, String email, String password, String gender, String birthDate, String job){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_CUSTOMER_NAME, customerName);
        editor.putString(KEY_BIRTH_DATE, birthDate);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_JOB, job);
        editor.putString(KEY_PASSWORD, password);
        editor.commit();
    }

    public void createRememberMeSession(String email, String password){
        editor.putBoolean(IS_REMEMBER_ME, true);
        editor.putString(KEY_SESSION_EMAIL, email);
        editor.putString(KEY_SESSION_PASSWORD, password);
        editor.commit();
    }

    public HashMap<String, String> getRememberMeDetailFromSession(){
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_SESSION_EMAIL, usersSession.getString(KEY_SESSION_EMAIL, null));
        userData.put(KEY_SESSION_PASSWORD, usersSession.getString(KEY_SESSION_PASSWORD, null));
        return userData;
    }

    public boolean checkRememberMe(){
        return usersSession.getBoolean(IS_REMEMBER_ME, false);
    }
}
