package uz.gita.testappmvp.ui.test;

import android.content.res.Resources;

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View view;
    private TestContract.Model model;
    private String testName;
    private String userAnswer;
    private boolean bool;
    public TestPresenter(TestContract.View view, Resources resources){
        this.view = view;
        model = new TestModel(resources);
        view.setAppBarName(model.getTestName());
        loadNexTest();
    }

    private boolean loadNexTest(){
        if (model.hasQuestion()) {
            view.clearOldChecks();
            view.setNextButtonState(false);
            view.describeTest(model.getNextTestData(), model.getCurrentPos(), model.getMaxCount());
            return true;
        }
        else {
            model.writeCountsToRepository();
            model.writeStatesToRepository();
            view.openResultActivity();
            return false;
        }
    }


    @Override
    public void clickedVariantButton(int pos,String userAnswer) {
        view.clearOldChecks();
        view.variantButtonClickedByPos(pos);
        view.setNextButtonState(true);
        this.userAnswer = userAnswer;
    }

    @Override
    public void checkBtnClicked(int pos) {
            model.checkAnswer(userAnswer);
            view.checkAnswer(pos,model.getAnswer());
            view.setVisibilityToCheckAndSkipButton(false);
            view.setVisibilityToNextButton(true);
            view.setAllButtonsDisabled();





    }

    @Override
    public void skipBtnClicked() {
        model.addSkipCount();
        loadNexTest();
    }

    @Override
    public void onBackPressed() {
        view.showExitDialog();
    }

    @Override
    public void nexBtnClicked() {
        view.setAllButtonsEnabled();
        if (loadNexTest()) {
            view.setVisibilityToCheckAndSkipButton(true);
            view.setVisibilityToNextButton(false);
        }
    }
}
