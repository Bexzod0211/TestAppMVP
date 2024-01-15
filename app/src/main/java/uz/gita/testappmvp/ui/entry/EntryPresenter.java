package uz.gita.testappmvp.ui.entry;

public class EntryPresenter implements EntryContract.Presenter {
    EntryContract.View view;


    public EntryPresenter(EntryContract.View view){
        this.view = view;
    }

    @Override
    public void startBtnClicked() {
        view.openCatalogActivity();
    }

    @Override
    public void aboutBtnClicked() {
        view.showDialogAbout();
    }

    @Override
    public void exitBtnClicked() {
        view.exitApp();
    }
}
