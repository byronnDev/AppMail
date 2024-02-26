package mikel.echeverria.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import mikel.echeverria.R;

public class DetailsFragment extends Fragment {
    TextView txtSubject;
    TextView txtFrom;
    TextView txtContent;
    TextView lblDefaultMessage;
    TextView lblFrom;
    TextView lblSubject;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        txtFrom = view.findViewById(R.id.txtFrom);
        txtSubject = view.findViewById(R.id.txtSubject);
        txtContent = view.findViewById(R.id.txtContent);
        lblDefaultMessage = view.findViewById(R.id.lblDefaultMessage);
        lblFrom = view.findViewById(R.id.lblFrom);
        lblSubject = view.findViewById(R.id.lblSubject);

        showDefaultMessage(); // Mostramos el mensaje por defecto
        return view;
    }


    public void renderData(String from, String subject, String content) {
        // Ocultamos el mensaje por defecto
        hideDefaultMessage();
        // Mostramos los datos
        txtFrom.setText(from);
        txtSubject.setText(subject);
        txtContent.setText(content);
    }

    public void showDefaultMessage() {
        lblDefaultMessage.setVisibility(View.VISIBLE);
        lblFrom.setVisibility(View.INVISIBLE);
        lblSubject.setVisibility(View.INVISIBLE);
    }

    public void hideDefaultMessage() {
        lblDefaultMessage.setVisibility(View.GONE);
        lblFrom.setVisibility(View.VISIBLE);
        lblSubject.setVisibility(View.VISIBLE);
    }
}