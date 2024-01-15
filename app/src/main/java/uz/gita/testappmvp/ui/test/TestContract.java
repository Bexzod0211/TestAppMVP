package uz.gita.testappmvp.ui.test;

import uz.gita.testappmvp.model.TestData;

public interface TestContract {
    interface Model {
        TestData getNextTestData();
        int getMaxCount();
        int getCurrentPos();
        boolean hasQuestion();
        String getTestName();
        void checkAnswer(String userAnswer);
        void addSkipCount();
        void writeCountsToRepository();
        void writeStatesToRepository();
        String getAnswer();
    }
    interface View {
        void describeTest(TestData data,int currentPos,int max_count);
        void setAppBarName(String testName);
        void clearOldChecks();
        void variantButtonClickedByPos(int pos);
        void setNextButtonState(boolean state);
        void openResultActivity();

        void showExitDialog();
        void checkAnswer(int pos,String answer);
        void setAllButtonsDisabled();
        void setAllButtonsEnabled();
        void setVisibilityToNextButton(boolean state);
        void setVisibilityToCheckAndSkipButton(boolean state);
    }

    interface Presenter {
        void clickedVariantButton(int pos,String userAnswer);
        void checkBtnClicked(int pos);
        void skipBtnClicked();
        void onBackPressed();
        void nexBtnClicked();
    }

}
