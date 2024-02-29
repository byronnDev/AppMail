package mikel.echeverria.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mikel.echeverria.Activities.MailActivity;
import mikel.echeverria.Activities.MainActivity;
import mikel.echeverria.Models.Mail;
import mikel.echeverria.R;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.RecyclerDataHolder> {

    private List<Mail> dataList;
    private LayoutInflater inflater;
    private Context context;

    public RecyclerDataAdapter(List<Mail> dataList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerDataAdapter.RecyclerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_mail, parent, false); // Inflamos la vista
        return new RecyclerDataAdapter.RecyclerDataHolder(view); // Devolvemos la vista
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapter.RecyclerDataHolder holder, int position) {
        holder.bindData(dataList.get(position)); // Llamamos al método BindData
    }

    @Override
    public int getItemCount() { return dataList.size(); } // Devuelve el tamaño de la lista

    public class RecyclerDataHolder extends RecyclerView.ViewHolder {
        Drawable color;
        TextView txtLetra;
        TextView title;
        TextView description;

        RelativeLayout cardView;

        public RecyclerDataHolder(@NonNull View itemView) {
            super(itemView);
            txtLetra = itemView.findViewById(R.id.txtLetra);
            color = txtLetra.getBackground();
            title = itemView.findViewById(R.id.txtTitulo);
            description = itemView.findViewById(R.id.txtContenido);
            cardView = itemView.findViewById(R.id.cardView);
        }

        void bindData(@NonNull final Mail item) {
            renderMailItem(item);
            // Set an OnClickListener
            doCardClickFunction(item);
        }

        private void doCardClickFunction(@NonNull Mail item) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Envía los datos a la actividad
                    // Si el movil está en modo horizontal
                    int rotation = getRotation();
                    manageRotation(rotation, item);
                }
            });
        }

        private void renderMailItem(@NonNull Mail item) {
            setInformation(item);
            setImageAndColor(item);
        }

        private void setImageAndColor(@NonNull Mail item) {
            setLetter(item);
            setBackgroundColorOfImage(item);
        }

        private void setLetter(@NonNull Mail item) {
            txtLetra.setText(String.valueOf(item.getSenderName().toUpperCase().charAt(0)));
        }

        private void setBackgroundColorOfImage(@NonNull Mail item) {
            int colorInt = getColorInt(item);
            color.setTint(colorInt); // Establecemos el color
        }

        private void setInformation(@NonNull Mail item) {
            title.setText(item.getSubject());
            description.setText(item.getMessage());
        }
    }

    private static int getColorInt(@NonNull Mail item) {
        int colorInt = Color.parseColor("#" + item.getColor()); // Obtenemos el color en formato int
        return colorInt;
    }

    private void manageRotation(int rotation, @NonNull Mail item) {
        if (isHorizontal(rotation)) {
            sendAndRenderData(item);
        } else if (isVertical(rotation)) {
            // Si el movil está en modo vertical
            sendDataToMailActivityAndStart(item);
        }
    }

    private void sendAndRenderData(@NonNull Mail item) {
        ((MainActivity) context).sendData(item.getSenderName(), item.getSubject(), item.getMessage());
    }

    private static boolean isVertical(int rotation) {
        return rotation == Configuration.ORIENTATION_PORTRAIT;
    }

    private static boolean isHorizontal(int rotation) {
        return rotation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private int getRotation() {
        return context.getResources().getConfiguration().orientation;
    }

    private void sendDataToMailActivityAndStart(@NonNull Mail item) {
        Intent intent = sendDataToMailActivity(item);
        context.startActivity(intent);
    }

    @NonNull
    private Intent sendDataToMailActivity(@NonNull Mail item) {
        Intent intent = new Intent(context, MailActivity.class);
        intent.putExtra("from", item.getSenderName());
        intent.putExtra("subject", item.getSubject());
        intent.putExtra("content", item.getMessage());
        return intent;
    }
}