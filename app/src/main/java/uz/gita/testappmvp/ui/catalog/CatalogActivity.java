package uz.gita.testappmvp.ui.catalog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import uz.gita.testappmvp.R;
import uz.gita.testappmvp.ui.test.TestActivity;

public class CatalogActivity extends AppCompatActivity implements CatalogContract.View {
    private CatalogContract.Presenter presenter;
    LinearLayout line_js,line_java,line_c_sh,line_python;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        presenter = new CatalogPresenter(this);
        loadViews();
        attachListeners();
    }

    private void loadViews(){
        line_js = findViewById(R.id.line_js);
        line_java = findViewById(R.id.line_java);
        line_c_sh = findViewById(R.id.line_c_sh);
        line_python = findViewById(R.id.line_python);
        img_back = findViewById(R.id.img_Back);
    }


    private void attachListeners(){
        line_js.setOnClickListener(view -> presenter.clickBtnByCatalogName("Js"));
        line_java.setOnClickListener(view -> presenter.clickBtnByCatalogName("Java"));
        line_c_sh.setOnClickListener(view -> presenter.clickBtnByCatalogName("C#"));
        line_python.setOnClickListener(view -> presenter.clickBtnByCatalogName("Python"));
        img_back.setOnClickListener(view -> presenter.backBtnClicked());
    }

    @Override
    public void openTestWithName(String testName) {
        Intent intent = new Intent(this, TestActivity.class);


        if (testName.equals("C#")||testName.equals("Python")){
            Toast.makeText(this, "Bu katalog endi tayyorlonmoqda", Toast.LENGTH_SHORT).show();
            return;
        }

        intent.putExtra("Test_name",testName);
        startActivity(intent);
    }

    @Override
    public void finishActivity() {
        finish();
    }
}