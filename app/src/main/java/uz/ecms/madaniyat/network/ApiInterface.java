package uz.ecms.madaniyat.network;

import java.util.List;
import java.util.function.Predicate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uz.ecms.madaniyat.models.PResponse;
import uz.ecms.madaniyat.models.QResponse;
import uz.ecms.madaniyat.models.QTypeResponse;

public interface ApiInterface {
    @GET("site/api-test")
    Call<List<QTypeResponse>> getQueryType();

    @GET("site/api-take")
    Call<List<QResponse>> getQuery(@Query("type_id") int type_id);

    @GET("site/api-form-type")
    Call<List<QTypeResponse>> getPollType();

    @GET("site/site/api-form")
    Call<List<PResponse>> getPoll(@Query("type_id") int type_id);

}
