package uz.ecms.madaniyat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLData;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import okhttp3.internal.Util;
import uz.ecms.madaniyat.R;
import uz.ecms.madaniyat.models.QTypeResponse;

public class QTypeAdapter extends RecyclerView.Adapter<QTypeAdapter.ViewHolder> {

    private List<QTypeResponse> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public QTypeAdapter(Context context, List<QTypeResponse> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.q_type_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        QTypeResponse item_data = mData.get(position);
        holder.q_name.setText(item_data.getName());
        if (item_data.getCreatedAt() != 0)
            holder.q_create_time.setText(new SimpleDateFormat("dd-MMMM, yyyy").format(new Date(item_data.getCreatedAt())));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView q_name, q_create_time, start_btn;

        ViewHolder(View itemView) {
            super(itemView);
            q_name = itemView.findViewById(R.id.q_name);
            q_create_time = itemView.findViewById(R.id.q_create_time);
            start_btn = itemView.findViewById(R.id.start_btn);

            start_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, mData.get(getPosition()).getId(), mData.get(getAdapterPosition()).getName());
        }
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int id, String title);
    }


}