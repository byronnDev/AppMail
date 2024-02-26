package mikel.echeverria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mikel.echeverria.Fragments.DetailsFragment;
import mikel.echeverria.R;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class MailActivity extends AppCompatActivity {
    TextView txtSubject;
    TextView txtFrom;
    TextView txtContent;
    TextView lblDefaultMessage;
    TextView lblFrom;
    TextView lblSubject;
    private String from;
    private String subject;
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details);
        txtSubject = findViewById(R.id.txtSubject);
        txtFrom = findViewById(R.id.txtFrom);
        txtContent = findViewById(R.id.txtContent);
        lblDefaultMessage = findViewById(R.id.lblDefaultMessage);
        lblSubject = findViewById(R.id.lblSubject);
        lblFrom = findViewById(R.id.lblFrom);

        // Ocultamos el mensaje por defecto
        lblDefaultMessage.setVisibility(View.GONE);

        getData();
        setData();
    }

    public void getData() {
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        subject = intent.getStringExtra("subject");
        content = intent.getStringExtra("content");

    }

    public void setData() {
        txtFrom.setText(from);
        txtSubject.setText(subject);
        txtContent.setText(content);
    }
}