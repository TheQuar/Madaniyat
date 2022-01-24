package uz.ecms.madaniyat.mvvm;

import java.util.List;

import uz.ecms.madaniyat.models.QResponse;


public interface QListener {
    void onStarted();

    void onFinished(List<QResponse> data);

    void onFailure(Throwable error);
}
