package uz.gita.testappmvp.ui.catalog;

import uz.gita.testappmvp.repository.AppRepository;

public class CatalogModel implements CatalogContract.Model{

    private AppRepository repository;

    public CatalogModel(){
        repository = AppRepository.getInstance();
    }

    @Override
    public void writeCatalogNameTORepository(String catalogName) {
        repository.writeCatalogName(catalogName);
    }
}
