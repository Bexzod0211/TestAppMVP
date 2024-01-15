package uz.gita.testappmvp.ui.catalog;

public interface CatalogContract {

    interface Model {
        void writeCatalogNameTORepository(String catalogName);
    }
    interface View {
        void openTestWithName(String testName);
        void finishActivity();
    }

    interface Presenter {
        void clickBtnByCatalogName(String catalogName);
        void backBtnClicked();
    }
}
