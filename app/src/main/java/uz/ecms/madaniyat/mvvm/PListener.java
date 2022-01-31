package uz.ecms.madaniyat.mvvm;

import java.util.List;

import uz.ecms.madaniyat.models.PResponse;
import uz.ecms.madaniyat.models.QResponse;


public interface PListener {
    void onStarted();

    void onFinished(List<PResponse> data);

    void onFailure(Throwable error);
}
