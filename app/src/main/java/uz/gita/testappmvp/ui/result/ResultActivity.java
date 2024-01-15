package uz.gita.testappmvp.ui.result;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.ArrayList;
import java.util.List;

import uz.gita.testappmvp.R;

public class ResultActivity extends AppCompatActivity implements ResultContract.View {
    TextView txtCorrectCount,txtWrongCount,txtSkipCount;
    AppCompatButton btnBack;
    ResultContract.Presenter presenter;
    List<TextView> textViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        textViews = new ArrayList<>();
        loadViews();
        presenter = new ResultPresenter(this);
        attachClickListeners();
    }
    private void loadViews(){
        txtCorrectCount = findViewById(R.id.txt_correct_count);
        txtWrongCount = findViewById(R.id.txt_wrong_count);
        txtSkipCount = findViewById(R.id.txt_skip_count);
        btnBack = findViewById(R.id.btnBack);

        textViews.add(findViewById(R.id.txt_question1));
        textViews.add(findViewById(R.id.txt_question2));
        textViews.add(findViewById(R.id.txt_question3));
        textViews.add(findViewById(R.id.txt_question4));
        textViews.add(findViewById(R.id.txt_question5));
        textViews.add(findViewById(R.id.txt_question6));
        textViews.add(findViewById(R.id.txt_question7));
        textViews.add(findViewById(R.id.txt_question8));
        textViews.add(findViewById(R.id.txt_question9));
        textViews.add(findViewById(R.id.txt_question10));
    }

    private void attachClickListeners(){
        btnBack.setOnClickListener(view -> presenter.backToMenuClicked());
    }
    @Override
    public void putCounts(int correctCount, int wrongCount, int skippedCount) {
        txtCorrectCount.setText(correctCount+"");
        txtWrongCount.setText(wrongCount+"");
        txtSkipCount.setText(skippedCount+"");
    }

    @Override
    public void setStates(List<Integer> states) {
        for (int i = 0; i < textViews.size(); i++) {
            if (states.get(i).equals(1)){
                textViews.get(i).setBackgroundColor(getResources().getColor(R.color.correct));
            }else if (states.get(i).equals(2)){
                textViews.get(i).setBackgroundColor(getResources().getColor(R.color.wrong));
            }else {
                textViews.get(i).setBackgroundColor(getResources().getColor(R.color.skipped));
            }
        }
    }

    @Override
    public void backToMenu() {
        finish();

    }
}
