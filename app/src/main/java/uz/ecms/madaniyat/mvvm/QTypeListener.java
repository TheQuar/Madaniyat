package uz.ecms.madaniyat.mvvm;

import java.util.List;

import uz.ecms.madaniyat.models.QTypeResponse;

public interface QTypeListener {
    void onStarted();
    void onFinished(List<QTypeResponse> data);
    void onFailure(Throwable error);
}
