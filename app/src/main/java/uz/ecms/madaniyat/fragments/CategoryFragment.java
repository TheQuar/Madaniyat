package uz.ecms.madaniyat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uz.ecms.madaniyat.MainActivity;
import uz.ecms.madaniyat.R;


public class CategoryFragment extends Fragment {


    public CategoryFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_carigory, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        QTypeFragment qTypeFragment = new QTypeFragment();
        Bundle bundle = new Bundle();

        view.findViewById(R.id.q_button).setOnClickListener(v -> {
            bundle.putString("title", "Testlar ro'yxati");
            bundle.putString("type", "Test");
            qTypeFragment.setArguments(bundle);
            openFragment(qTypeFragment);
        });

        view.findViewById(R.id.a_button).setOnClickListener(v -> {
            bundle.putString("title", "Anketa ro'yxati");
            bundle.putString("type", "Poll");
            qTypeFragment.setArguments(bundle);
            openFragment(qTypeFragment);
        });

    }

    private void openFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_controller, fragment).addToBackStack("").commit();

    }
}