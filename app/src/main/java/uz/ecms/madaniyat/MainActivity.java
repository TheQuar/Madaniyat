package uz.ecms.madaniyat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import uz.ecms.madaniyat.fragments.CategoryFragment;
import uz.ecms.madaniyat.fragments.QTypeFragment;

public class MainActivity extends AppCompatActivity {

    public static TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        title.setText("DIAGNOSTIK MADANIYAT");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_controller, new CategoryFragment()).commit();

    }

}