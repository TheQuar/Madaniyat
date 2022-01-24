package uz.ecms.madaniyat.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uz.ecms.madaniyat.MainActivity;
import uz.ecms.madaniyat.R;
import uz.ecms.madaniyat.adapters.QTypeAdapter;
import uz.ecms.madaniyat.config.DataType;
import uz.ecms.madaniyat.models.QTypeResponse;
import uz.ecms.madaniyat.mvvm.MainViewModel;
import uz.ecms.madaniyat.mvvm.QTypeListener;

public class QTypeFragment extends Fragment implements QTypeAdapter.ItemClickListener, QTypeListener {

    RecyclerView recyclerView;
    QTypeAdapter qTypeAdapter;
    ProgressDialog dialog;
    MainViewModel mainViewModel;
    String title;
    DataType.QType type;

    public QTypeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question_type_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.title.setText("Testlar ro'yxati");
        recyclerView = view.findViewById(R.id.recycler_view_qtype);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Ma'lumot yuklanmoqda");

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.qTypeListener = this;

        if (getArguments() != null) {
            title = getArguments().getString("title");
            MainActivity.title.setText(title);

            if (getArguments().getString("type").equals("Test"))
                type = DataType.QType.TEST;
            else
                type = DataType.QType.POLL;

            mainViewModel.getQPList(type);

        }

    }

    @Override
    public void onItemClick(View view, int id, String title) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("title", title);
        QFragment qFragment = new QFragment();
        qFragment.setArguments(bundle);

        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_controller, qFragment)
                .addToBackStack("back")
                .commit();
    }

    @Override
    public void onStarted() {
        dialog.show();
    }

    @Override
    public void onFinished(List<QTypeResponse> data) {
        qTypeAdapter = new QTypeAdapter(getContext(), data);
        qTypeAdapter.setClickListener(QTypeFragment.this);
        recyclerView.setAdapter(qTypeAdapter);
        dialog.dismiss();
    }

    @Override
    public void onFailure(Throwable error) {
        dialog.dismiss();
        new AlertDialog.Builder(getContext())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Xatolik yuz berdi")
                .setMessage(error.getMessage())
                .setPositiveButton("qayta urinish", (dialog, which) -> mainViewModel.getQPList(type))
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MainActivity.title.setText("DIAGNOSTIK MADANIYAT");
    }
}