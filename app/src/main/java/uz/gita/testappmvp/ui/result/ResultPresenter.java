package uz.gita.testappmvp.ui.result;

public class ResultPresenter implements ResultContract.Presenter {
    private ResultContract.View view;
    private ResultContract.Model model;

    public ResultPresenter(ResultContract.View view){
        this.view = view;
        model = new ResultModel();
        view.putCounts(model.getCorrectCounts(), model.getWrongCounts(), model.getSkippedCounts());
        view.setStates(model.getStates());
    }


    @Override
    public void backToMenuClicked() {
        view.backToMenu();
    }
}
