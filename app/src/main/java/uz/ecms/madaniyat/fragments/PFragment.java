package uz.ecms.madaniyat.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import uz.ecms.madaniyat.MainActivity;
import uz.ecms.madaniyat.R;
import uz.ecms.madaniyat.adapters.PAdapter;
import uz.ecms.madaniyat.adapters.QAdapter;
import uz.ecms.madaniyat.models.PResponse;
import uz.ecms.madaniyat.mvvm.MainViewModel;
import uz.ecms.madaniyat.mvvm.PListener;

public class PFragment extends Fragment implements PListener {
    MainViewModel mainViewModel;
    RecyclerView recyclerView;
    PAdapter pAdapter;
    ProgressDialog dialog;
    NestedScrollView nestedScrollView;
    Button btn;
    private int question_id;


    public PFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_p, container, false);
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
        mainViewModel.pListener = this;

        btn.setVisibility(View.INVISIBLE);
        if (getArguments() != null) {
            question_id = getArguments().getInt("id");
            mainViewModel.getPoll(question_id);
            MainActivity.title.setText(getArguments().getString("title"));
            btn.setOnClickListener(v -> {
                if (pAdapter != null) {
                    boolean isAllItemChecked = true;
                    int position = 0;
                    for (int item : pAdapter.get_checked()) {
                        if (item == 0) {
                            isAllItemChecked = false;
                            break;
                        }
                        position++;
                    }
                    if (isAllItemChecked) {
                        pAdapter.show_result();
                        show_result(pAdapter.get_checked(), pAdapter.getmData());
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
    public void onFinished(List<PResponse> data) {
        pAdapter = new PAdapter(getContext(), data);
        recyclerView.setAdapter(pAdapter);
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
        MainActivity.title.setText("Anketalar ro'yxati");
    }

    private void show_result(int[] checked, List<PResponse> data) {
        String message;
        int jami_ball= 0;


        for (int i = 0; i < checked.length; i++) {
            switch (checked[i]) {
                case 1: {
                    jami_ball += data.get(i).getBall_a();
                }
                break;
                case 2: {
                    jami_ball += data.get(i).getBall_b();
                }
                break;
                case 3: {
                    jami_ball += data.get(i).getBall_c();
                }
                break;
                case 4: {
                    jami_ball += data.get(i).getBall_d();
                }
                break;
            }


        }

        message = "<h2 style=\"text-align: center;\"><span style=\"color: #000000;\">Test natijalari</span></h2><br>\n" +
                "<center><h3><span style=\"color: #339966;\">Jami ball: " + jami_ball + "</span></h3></center>";
        new AlertDialog.Builder(getContext())
                .setMessage(Html.fromHtml(message))
                .setPositiveButton("Yopish", null)
                .show();
    }
}