package uz.ecms.madaniyat.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.ecms.madaniyat.MainActivity;
import uz.ecms.madaniyat.R;
import uz.ecms.madaniyat.adapters.QAdapter;
import uz.ecms.madaniyat.models.QResponse;
import uz.ecms.madaniyat.mvvm.MainViewModel;
import uz.ecms.madaniyat.mvvm.QListener;


public class QFragment extends Fragment implements QListener {
    MainViewModel mainViewModel;
    RecyclerView recyclerView;
    QAdapter qAdapter;
    ProgressDialog dialog;
    NestedScrollView nestedScrollView;
    Button btn;
    private int question_id;

    public QFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nestedScrollView = view.findViewById(R.id.net_scroll);
        btn = view.findViewById(R.id.result_button);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Ma'lumot yuklanmoqda");

        recyclerView = view.findViewById(R.id.q_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.qListener = this;

        btn.setVisibility(View.INVISIBLE);
        if (getArguments() != null) {
            question_id = getArguments().getInt("id");
            mainViewModel.getQuestion(question_id);
            MainActivity.title.setText(getArguments().getString("title"));
            btn.setOnClickListener(v -> {
                if (qAdapter != null) {
                    boolean isAllItemChecked = true;
                    int position = 0;
                    for (int item : qAdapter.get_checked()) {
                        if (item == 0) {
                            isAllItemChecked = false;
                            break;
                        }
                        position++;
                    }
                    if (isAllItemChecked) {
                        qAdapter.show_result();
                        show_result(qAdapter.get_checked(), qAdapter.get_right_answer());
                    } else {
                        nestedScrollView.smoothScrollTo(0, (int) recyclerView.getChildAt(position).getY());
                    }
                }
            });
        } else {
            new AlertDialog.Builder(getContext())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Xatolik yuz berdi")
                    .setMessage("Ma'lumotlar to'liq olinmadi")
                    .setPositiveButton("orqaga qaytish", (dialog, which) -> mainViewModel.getQuestion(question_id))
                    .show();
        }

    }

    @Override
    public void onStarted() {
        dialog.show();
    }


    @Override
    public void onFinished(List<QResponse> data) {
        qAdapter = new QAdapter(getContext(), data);
        recyclerView.setAdapter(qAdapter);
        btn.setVisibility(View.VISIBLE);
        dialog.dismiss();
    }

    @Override
    public void onFailure(Throwable error) {
        new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xatolik yuz berdi")
                .setMessage(error.getMessage())
                .setPositiveButton("qayta urinish", (dialog, which) -> mainViewModel.getQuestion(question_id))
                .show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainActivity.title.setText("Testlar ro'yxati");
    }


    private void show_result(int[] checked, int[] right) {
        String message;
        int true_answer = 0, false_answer = 0;

        for (int i = 0; i < checked.length; i++) {
            if (checked[i] == right[i])
                true_answer++;
            else
                false_answer++;
        }

        message = "<h2 style=\"text-align: center;\"><span style=\"color: #000000;\">Test natijalari</span></h2><br>\n" +
                "<h3><span style=\"color: #339966;\">To'g'ri javob: " + true_answer + "</span></h3>\n" +
                "<h3><span style=\"color: #ff0000;\">Xato javob: " + false_answer + "&nbsp;</span> </h3>";
        new AlertDialog.Builder(getContext())
                .setMessage(Html.fromHtml(message))
                .setPositiveButton("Yopish", null)
                .show();
    }


}