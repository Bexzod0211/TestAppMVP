package uz.gita.testappmvp.ui.result;

import java.util.List;

public interface ResultContract {
    interface Model {
        int  getCorrectCounts();
        int getWrongCounts();
        int getSkippedCounts();
        List<Integer> getStates();
    }

    interface View{
        void putCounts(int correctCount,int wrongCount,int skippedCount);
        void setStates(List<Integer> states);
        void backToMenu();
    }

    interface Presenter {
        void backToMenuClicked();
    }
}
