package uz.gita.testappmvp.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import uz.gita.testappmvp.model.TestData;

public class MySharedPreferences {

    private static MySharedPreferences instance;
    private SharedPreferences preferences;
    private String QUESTIONS_LIST = "QUESTIONS_LIST";
    private String QUESTION_POS = "QUESTION_POS";
    private MySharedPreferences(Context context){
        preferences = context.getSharedPreferences("TEST_APP_MVP",Context.MODE_PRIVATE);
    }

    public static void init(Context context){
        if (instance == null){
            instance = new MySharedPreferences(context);
        }
    }

    public static MySharedPreferences getInstance(){
        return instance;
    }

    public void saveList(List<TestData> list){
        if (list == null){
            preferences.edit().putString(QUESTIONS_LIST,"").apply();
        }
        else {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            preferences.edit().putString(QUESTIONS_LIST, json).apply();
        }
    }


    public List<TestData> getSavedList(){
        String json = preferences.getString(QUESTIONS_LIST,"");

        Type type = new TypeToken<ArrayList<TestData>>() {

        }.getType();

        Gson gson = new Gson();
        List<TestData> list = gson.fromJson(json,type);

        if (json.equals("")){
            return null;
        }else {
            return list;
        }

    }


    public void saveQuestionPos(int pos){
        preferences.edit().putInt(QUESTION_POS,pos).apply();
    }

    public int getQuestionPos(){
        return preferences.getInt(QUESTION_POS,0);
    }



}
