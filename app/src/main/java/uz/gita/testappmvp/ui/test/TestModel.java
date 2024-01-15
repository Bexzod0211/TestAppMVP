package uz.gita.testappmvp.ui.test;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uz.gita.testappmvp.model.TestData;
import uz.gita.testappmvp.repository.AppRepository;

public class TestModel implements TestContract.Model {

    private List<TestData> list;
    private final int MAX_COUNT = 10;
    private AppRepository repository;
    private int currentPos;
    private int correctCount;
    private int wrongCount;
    private int skipCount;
    List<Integer> states;
    public TestModel(Resources resources){
        list = new ArrayList<>();
        repository = AppRepository.getInstance();
        states = new ArrayList<>(MAX_COUNT);
        getNeedData(resources);
    }


    private void getNeedData(Resources resources){
        switch (repository.getCatalogName()){
            case "Js":list.addAll(repository.loadDataOfJavaScript(resources,MAX_COUNT));
            break;
            case "Java":list.addAll(repository.loadDataOfJava(resources,MAX_COUNT));
            break;
        }
    }


    @Override
    public TestData getNextTestData() {
        return list.get(currentPos++);
    }

    @Override
    public int getMaxCount() {
        return MAX_COUNT;
    }


    @Override
    public int getCurrentPos() {
        return currentPos;
    }

    @Override
    public boolean hasQuestion() {
        return currentPos < MAX_COUNT;
    }

    @Override
    public String getTestName() {
        return repository.getCatalogName();
    }

    @Override
    public void checkAnswer(String userAnswer) {
        Log.d("TTT","Correct Answer : "+list.get(currentPos-1).getAnswer()+" | User Answer : "+userAnswer);
        if (list.get(currentPos-1).getAnswer().equals(userAnswer)){
            correctCount++;
            states.add(1);
        }else {
            wrongCount++;
            states.add(2);
        }
    }

    @Override
    public void addSkipCount() {
        skipCount++;
        states.add(3);
    }

    @Override
    public void writeCountsToRepository() {
        repository.setCorrectCount(correctCount);
        repository.setWrongCount(wrongCount);
        repository.setSkippedCount(skipCount);
    }

    @Override
    public void writeStatesToRepository() {
        repository.writeStates(states);
    }

    @Override
    public String getAnswer() {
        return list.get(currentPos-1).getAnswer();
    }
}
