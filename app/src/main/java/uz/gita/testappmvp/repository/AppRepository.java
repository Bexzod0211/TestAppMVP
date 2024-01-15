package uz.gita.testappmvp.repository;

import android.content.res.Resources;
import android.icu.number.Scale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uz.gita.testappmvp.R;
import uz.gita.testappmvp.db.MySharedPreferences;
import uz.gita.testappmvp.model.TestData;

public class AppRepository {
    private static AppRepository instance;
    private String catalogName;
    private List<TestData> list;

    private int correctCount,wrongCount,skippedCount;
    private MySharedPreferences pref;
    private List<Integer> states;
    private AppRepository(){
        pref = MySharedPreferences.getInstance();
    }

    public static AppRepository getInstance(){
        if (instance == null){
            instance = new AppRepository();
        }

        return instance;
    }

    public List<TestData> loadDataOfJava(Resources resources,int max_count) {
        list = new ArrayList<>();

        String[] javaQuestions = resources.getStringArray(R.array.java_questions);
        String[] answers = resources.getStringArray(R.array.java_answers);
        String[] variantsA = resources.getStringArray(R.array.java_variant_a);
        String[] variantsB = resources.getStringArray(R.array.java_variant_b);
        String[] variantsC = resources.getStringArray(R.array.java_variant_c);
        String[] variantsD = resources.getStringArray(R.array.java_variant_d);


        for (int i = 0; i < javaQuestions.length; i++) {
            list.add(new TestData(javaQuestions[i], variantsA[i], variantsB[i], variantsC[i], variantsD[i], answers[i]));
        }


        shuffle();

        return list.subList(0,max_count);

    }

    public List<TestData> loadDataOfJavaScript(Resources resources,int max_count) {

        list = new ArrayList<>();

        String[] jsQuestions = resources.getStringArray(R.array.javascript_questions);
        String[] answers = resources.getStringArray(R.array.javascript_answers);
        String[] variantsA = resources.getStringArray(R.array.javascript_variants_a);
        String[] variantsB = resources.getStringArray(R.array.javascript_variants_b);
        String[] variantsC = resources.getStringArray(R.array.javascript_variants_c);
        String[] variantsD = resources.getStringArray(R.array.javascript_variants_d);



        for (int i = 0; i < jsQuestions.length; i++) {
            list.add(new TestData(jsQuestions[i], variantsA[i], variantsB[i], variantsC[i], variantsD[i], answers[i]));
        }

        shuffle();

        return list.subList(0,max_count);

    }

    public void shuffle(){
        Collections.shuffle(list);
    }


    public void writeCatalogName(String catalogName){
        this.catalogName = catalogName;
    }


    public String getCatalogName(){
        return catalogName;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public void setSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public void writeStates(List<Integer> states){
        this.states = new ArrayList<>(states.size());
        this.states.addAll(states);
    }

    public List<Integer> getStates(){
        return states;
    }
}
