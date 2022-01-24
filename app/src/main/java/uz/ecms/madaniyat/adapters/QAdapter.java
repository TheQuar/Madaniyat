package uz.ecms.madaniyat.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.security.MessageDigest;
import java.util.List;

import uz.ecms.madaniyat.R;
import uz.ecms.madaniyat.models.QResponse;


public class QAdapter extends RecyclerView.Adapter<QAdapter.ViewHolder> {

    private final List<QResponse> mData;
    private final LayoutInflater mInflater;
    private boolean show_result = false;
    private int set_pos = 0;
    private final int[] checked_item;

    public QAdapter(Context context, List<QResponse> data) {
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

        holder.a.setText(mData.get(position).getAnswerA());
        holder.b.setText(mData.get(position).getAnswerB());
        holder.c.setText(mData.get(position).getAnswerC());
        holder.d.setText(mData.get(position).getAnswerD());

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
                case 1:
                    if (check(mData.get(position).getRightAnswer(), checked_item[position])) {
                        holder.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                        holder.a.setTextColor(Color.GREEN);
                    } else {
                        holder.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_false_answer, 0);
                        holder.a.setTextColor(Color.RED);
                    }
                    break;
                case 2:
                    if (check(mData.get(position).getRightAnswer(), checked_item[position])) {
                        holder.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                        holder.b.setTextColor(Color.GREEN);
                    } else {
                        holder.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_false_answer, 0);
                        holder.b.setTextColor(Color.RED);
                    }
                    break;
                case 3:
                    if (check(mData.get(position).getRightAnswer(), checked_item[position])) {
                        holder.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                        holder.c.setTextColor(Color.GREEN);
                    } else {
                        holder.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_false_answer, 0);
                        holder.c.setTextColor(Color.RED);
                    }
                    break;
                case 4:
                    if (check(mData.get(position).getRightAnswer(), checked_item[position])) {
                        holder.d.setTextColor(Color.GREEN);
                        holder.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    } else {
                        holder.d.setTextColor(Color.RED);
                        holder.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_false_answer, 0);
                    }
                    break;
            }


            switch (Integer.parseInt(mData.get(position).getRightAnswer())) {
                case 1:
                    holder.a.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.a.setTextColor(Color.GREEN);
                    break;
                case 2:
                    holder.b.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.b.setTextColor(Color.GREEN);
                    break;
                case 3:
                    holder.c.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.c.setTextColor(Color.GREEN);
                    break;
                case 4:
                    holder.d.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_true_answer, 0);
                    holder.d.setTextColor(Color.GREEN);
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

    public int[] get_right_answer() {
        int[] data = new int[mData.size()];

        for (int i = 0; i < mData.size(); i++) {
            data[i] = Integer.parseInt(mData.get(i).getRightAnswer());
        }
        return data;
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

    private boolean check(String right, int check) {
        return Integer.parseInt(right) == check;
    }


}