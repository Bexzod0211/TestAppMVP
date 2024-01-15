package uz.gita.testappmvp.ui.entry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import uz.gita.testappmvp.ui.dialogs.CustomExitDialog;
import uz.gita.testappmvp.R;
import uz.gita.testappmvp.ui.catalog.CatalogActivity;

public class EntryActivity extends AppCompatActivity implements EntryContract.View{
    private FrameLayout frameStart, frameAbout,frameExit;
    private EntryContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        presenter = new EntryPresenter(this);
        loadViews();
        attachClickListeners();
    }

    private void loadViews(){
        frameStart = findViewById(R.id.frame_start);
        frameAbout = findViewById(R.id.frame_about);
        frameExit = findViewById(R.id.frame_exit);
    }

    private void attachClickListeners(){
        frameStart.setOnClickListener(view -> presenter.startBtnClicked());
        frameAbout.setOnClickListener(view -> presenter.aboutBtnClicked());
        frameExit.setOnClickListener(view -> presenter.exitBtnClicked());
    }

    @Override
    public void openCatalogActivity() {
        Intent intent = new Intent(this, CatalogActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDialogAbout() {
        new AlertDialog.Builder(this)
                .setTitle("About")
                .setMessage("Bu dastur Mamatxalilov Bexzod tomonidan " +
                        "GITA Dasturchilar academiyasida o'qish davrida tuzilgan, " +
                        "dastur o'z ichida xa hil dasturlash tilllardidan o'z bilimingizni sinash uchun testlar yig'ilgan." +
                        "Dasturdan foydalanib baxramand bo'ling")
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                })
                .create()
                .show();
    }

    @Override
    public void exitApp() {
        finish();
    }




}