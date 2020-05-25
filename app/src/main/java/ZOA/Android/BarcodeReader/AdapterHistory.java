package ZOA.Android.BarcodeReader;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HistoryViewHolder> {

    private ActivityHistory parent;
    private ArrayList<History> histories;

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        public Button BtnItemCode;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            BtnItemCode = itemView.findViewById(R.id.btnItemCode);
        }
    }

    public AdapterHistory(ActivityHistory par, ArrayList<History> list) {
        this.histories = list;
        this.parent = par;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_history, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        holder.BtnItemCode.setText(histories.get(position).GetCreateTime() + " : " + histories.get(position).GetItemBarcode());

        holder.BtnItemCode.setOnClickListener(v -> {
            parent.OpenBarcode(histories.get(position).GetItemBarcode());
        });
    }

    @Override
    public int getItemCount() {
        return this.histories.size();
    }
}
