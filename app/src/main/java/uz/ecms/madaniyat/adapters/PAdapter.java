package uz.ecms.madaniyat.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.ecms.madaniyat.R;
import uz.ecms.madaniyat.models.PResponse;
import uz.ecms.madaniyat.models.QResponse;


public class PAdapter extends RecyclerView.Adapter<PAdapter.ViewHolder> {

    private final List<PResponse> mData;
    private final LayoutInflater mInflater;
    private boolean show_result = false;
    private int set_pos = 0;
    private final int[] checked_item;

    public PAdapter(Context context, List<PResponse> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        checked_item = new int[mData.size()];
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.q_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.question_title.setText(mData.get(position).getQuestion());

        holder.a.setText(mData.get(position).getAnswer_a());
        holder.b.setText(mData.get(position).getAnswer_b());
        holder.c.setText(mData.get(position).getAnswer_c());
        holder.d.setText(mData.get(position).getAnswer_d());

//        holder.a.setChecked(false);

        switch (checked_item[position]) {
            case 1:
                holder.a.setChecked(true);
                break;
            case 2:
                holder.b.setChecked(true);
                break;
            case 3:
                holder.c.setChecked(true);
                break;
            case 4:
                holder.d.setChecked(true);
                break;
        }
        holder.a.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) checked_item[position] = 1;
        });
        holder.b.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) checked_item[position] = 2;
        });
        holder.c.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) checked_item[position] = 3;
        });
        holder.d.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) checked_item[position] = 4;
        });


        if (show_result && set_pos == position) {
            switch (checked_item[position]) {
                case 1: {
                    holder.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.a.setTextColor(Color.GREEN);
                }
                break;
                case 2: {
                    holder.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.b.setTextColor(Color.GREEN);
                }
                break;
                case 3: {
                    holder.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.c.setTextColor(Color.GREEN);
                }
                break;
                case 4: {
                    holder.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.d.setTextColor(Color.GREEN);
                }
                break;

            }


            holder.a.setClickable(false);
            holder.b.setClickable(false);
            holder.c.setClickable(false);
            holder.d.setClickable(false);
            set_pos++;
        }
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public int[] get_checked() {
        return checked_item;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView question_title;
        RadioButton a, b, c, d;

        ViewHolder(View itemView) {
            super(itemView);
            question_title = itemView.findViewById(R.id.question_title);
            a = itemView.findViewById(R.id.v_a);
            b = itemView.findViewById(R.id.v_b);
            c = itemView.findViewById(R.id.v_c);
            d = itemView.findViewById(R.id.v_d);
        }
    }


    public void show_result() {
        show_result = true;
        notifyDataSetChanged();
    }

    public List<PResponse> getmData() {
        return mData;
    }

}