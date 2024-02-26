package mikel.echeverria.Activities;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import mikel.echeverria.Adapters.RecyclerDataAdapter;
import mikel.echeverria.Fragments.DataFragment;
import mikel.echeverria.Fragments.DetailsFragment;
import mikel.echeverria.Models.Mail;
import mikel.echeverria.Models.Util;
import mikel.echeverria.R;

public class MainActivity extends FragmentActivity implements DataFragment.DataListener {
    List<Mail> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        elements = Util.getDummyData(); // Cargamos los datos

        RecyclerDataAdapter adapter = new RecyclerDataAdapter(elements, this); // Creamos el adaptador
        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Obtenemos el recyclerView
        recyclerView.setHasFixedSize(true); // Establecemos el tamaño fijo
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Establecemos el layout
        recyclerView.setAdapter(adapter); // Establecemos el adaptador
    }

    @Override
    public void sendData(String from, String subject, String content) {
        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        if (detailsFragment != null) {
            detailsFragment.renderData(from, subject, content);
        }
    }
}