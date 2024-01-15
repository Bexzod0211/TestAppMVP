package uz.gita.testappmvp.ui.entry;

public interface EntryContract {
    interface View {
        void openCatalogActivity();
        void showDialogAbout();
        void exitApp();
    }

    interface Presenter {
        void startBtnClicked();
        void aboutBtnClicked();
        void exitBtnClicked();

    }
}
