package uz.gita.testappmvp.ui.result;

import java.util.List;

import uz.gita.testappmvp.repository.AppRepository;

public class ResultModel implements ResultContract.Model{
    private AppRepository repository;

    public ResultModel(){
        repository = AppRepository.getInstance();
    }
    @Override
    public int getCorrectCounts() {
        return repository.getCorrectCount();
    }

    @Override
    public int getWrongCounts() {
        return repository.getWrongCount();
    }

    @Override
    public int getSkippedCounts() {
        return repository.getSkippedCount();
    }

    @Override
    public List<Integer> getStates() {
        return repository.getStates();
    }
}
