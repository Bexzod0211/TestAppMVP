package uz.gita.testappmvp.ui.catalog;

public class CatalogPresenter implements CatalogContract.Presenter {
    private CatalogContract.View view;
    private CatalogContract.Model model;
    public CatalogPresenter(CatalogContract.View view){

        this.view = view;
        model = new CatalogModel();
    }

    @Override
    public void clickBtnByCatalogName(String catalogName) {
        view.openTestWithName(catalogName);
        model.writeCatalogNameTORepository(catalogName);
    }

    @Override
    public void backBtnClicked() {
        view.finishActivity();
    }
}
