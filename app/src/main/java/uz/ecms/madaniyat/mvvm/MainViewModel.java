package uz.ecms.madaniyat.mvvm;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.ecms.madaniyat.config.DataType;
import uz.ecms.madaniyat.models.PResponse;
import uz.ecms.madaniyat.models.QResponse;
import uz.ecms.madaniyat.models.QTypeResponse;
import uz.ecms.madaniyat.network.RestAdapter;

public class MainViewModel extends ViewModel {
    public QTypeListener qTypeListener;
    public QListener qListener;
    public PListener pListener;

    public void getQPList(DataType.QType type) {
        switch (type) {
            case POLL: {
                qTypeListener.onStarted();
                RestAdapter.create().getPollType().enqueue(new Callback<List<QTypeResponse>>() {
                    @Override
                    public void onResponse(Call<List<QTypeResponse>> call, Response<List<QTypeResponse>> response) {
                        if (response.isSuccessful()) {
                            qTypeListener.onFinished(response.body());
                        } else {
                            qTypeListener.onFailure(new Throwable("error response"));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<QTypeResponse>> call, Throwable t) {
                        qTypeListener.onFailure(t);
                    }
                });
            }
            break;
            case TEST: {
                qTypeListener.onStarted();
                RestAdapter.create().getQueryType().enqueue(new Callback<List<QTypeResponse>>() {
                    @Override
                    public void onResponse(Call<List<QTypeResponse>> call, Response<List<QTypeResponse>> response) {
                        if (response.isSuccessful()) {
                            qTypeListener.onFinished(response.body());
                        } else {
                            qTypeListener.onFailure(new Throwable("error response"));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<QTypeResponse>> call, Throwable t) {
                        qTypeListener.onFailure(t);
                    }
                });
            }
            break;
        }

    }

    public void getQuestion(int id) {
        qListener.onStarted();
        RestAdapter.create().getQuery(id).enqueue(new Callback<List<QResponse>>() {
            @Override
            public void onResponse(Call<List<QResponse>> call, Response<List<QResponse>> response) {
                if (response.isSuccessful()) {
                    qListener.onFinished(response.body());
                } else {
                    qListener.onFailure(new Throwable("error response"));
                }
            }

            @Override
            public void onFailure(Call<List<QResponse>> call, Throwable t) {
                qListener.onFailure(t);
            }
        });
    }

    public void getPoll(int id){
        Log.d("Quar_log", "id:"+id);
        pListener.onStarted();
        RestAdapter.create().getPoll(id).enqueue(new Callback<List<PResponse>>() {
            @Override
            public void onResponse(Call<List<PResponse>> call, Response<List<PResponse>> response) {
                if (response.isSuccessful()) {
                    pListener.onFinished(response.body());
                } else {
                    pListener.onFailure(new Throwable("error response"));
                }
            }

            @Override
            public void onFailure(Call<List<PResponse>> call, Throwable t) {
                pListener.onFailure(t);
            }
        });
    }
}
