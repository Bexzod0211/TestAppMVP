package uz.gita.testappmvp.ui.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uz.gita.testappmvp.R;
import uz.gita.testappmvp.model.TestData;
import uz.gita.testappmvp.ui.result.ResultActivity;

public class TestActivity extends AppCompatActivity implements TestContract.View {
    private TextView txt_app_bar,txt_question,txt_question_pos;
    private List<TextView> buttons;
    private AppCompatButton btn_check,btn_skip,btn_next_full;
    private TestContract.Presenter presenter;
    private int selectedRadioPos;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        loadViews();
        presenter = new TestPresenter(this,getResources());
        attachClickListeners();
    }

    private void loadViews(){
        txt_app_bar = findViewById(R.id.txt_app_bar);
        txt_question = findViewById(R.id.txt_question);
        txt_question_pos = findViewById(R.id.txt_question_pos);
        btn_check = findViewById(R.id.btn_check);
        btn_skip = findViewById(R.id.btn_skip);
        img_back = findViewById(R.id.img_back);
        btn_next_full = findViewById(R.id.btn_next_full);

        buttons = new ArrayList<>();

        buttons.add(findViewById(R.id.radio1));
        buttons.add(findViewById(R.id.radio2));
        buttons.add(findViewById(R.id.radio3));
        buttons.add(findViewById(R.id.radio4));


    }


    private void attachClickListeners(){
        for (int i = 0; i < buttons.size(); i++) {
            int finalI = i;
            buttons.get(i).setOnClickListener(view -> {
                selectedRadioPos = finalI;
                presenter.clickedVariantButton(finalI,(buttons.get(selectedRadioPos).getText().toString()));
            });
        }

        btn_check.setOnClickListener(view -> presenter.checkBtnClicked(selectedRadioPos));
        btn_skip.setOnClickListener(view -> presenter.skipBtnClicked());
        img_back.setOnClickListener(view -> presenter.onBackPressed());
        btn_next_full.setOnClickListener(view -> presenter.nexBtnClicked());
    }


    @Override
    public void describeTest(TestData data, int currentPos, int max_count) {
        txt_question.setText(data.getQuestion());
        buttons.get(0).setText(data.getVariantA());
        buttons.get(1).setText(data.getVariantB());
        buttons.get(2).setText(data.getVariantC());
        buttons.get(3).setText(data.getVariantD());
        txt_question_pos.setText("Question "+currentPos+" of "+max_count);
    }

    @Override
    public void setAppBarName(String testName) {


        switch (testName){
            case "Js":
                txt_app_bar.setText("JavaScript");
                break;
            case "Java":
                txt_app_bar.setText("Java");
                break;
        }
    }

    @Override
    public void clearOldChecks() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBackgroundColor(getResources().getColor(R.color.color_game3));
            buttons.get(i).setTextColor(getResources().getColor(R.color.black));
        }
    }


    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public void variantButtonClickedByPos(int pos) {
        buttons.get(pos).setBackgroundColor(getResources().getColor(R.color.color_game4));
        buttons.get(pos).setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void setNextButtonState(boolean state) {
        btn_check.setEnabled(state);
    }

    @Override
    public void openResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showExitDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Exit").setMessage("Do you really want to exit")
                .setPositiveButton("Yes",(dialogInterface, i) -> {
                    dialogInterface.cancel();
                    finish();
                })
                .setNegativeButton("No",(dialogInterface, i) -> {
                    dialogInterface.cancel();
                })
                .create()
                .show();
    }

    @Override
    public void checkAnswer(int pos, String answer) {
        int correctAnswerPos = getCorrectAnswerPos(answer);
        if (pos == correctAnswerPos){
            buttons.get(pos).setBackgroundColor(getResources().getColor(R.color.correct));
        }else {
            buttons.get(pos).setBackgroundColor(getResources().getColor(R.color.wrong));
            buttons.get(correctAnswerPos).setBackgroundColor(getResources().getColor(R.color.correct));
        }

    }

    @Override
    public void setAllButtonsDisabled() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(false);
        }

    }

    @Override
    public void setAllButtonsEnabled() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(true);
        }
    }

    @Override
    public void setVisibilityToNextButton(boolean state) {
        if (state){
            btn_next_full.setVisibility(View.VISIBLE);
        }
        else {
            btn_next_full.setVisibility(View.GONE);
        }
    }

    @Override
    public void setVisibilityToCheckAndSkipButton(boolean state) {
        if (state){
            btn_skip.setVisibility(View.VISIBLE);
            btn_check.setVisibility(View.VISIBLE);
        }
        else {
            btn_check.setVisibility(View.GONE);
            btn_skip.setVisibility(View.GONE);
        }
    }


    private int getCorrectAnswerPos(String answer){
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().toString().trim().equals(answer.trim())){
                return i;
            }
        }
        return -1;
    }
}