package ceui.lisa.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    //用作登录，刷新token
    private static final String ACCOUNT_BASE_URL = "https://oauth.secure.pixiv.net/";

    //用作各个页面请求数据
    private static final String API_BASE_URL = "https://app-api.pixiv.net/";

    //用作获取会员token
    private static final String RANK_TOKEN_BASE_URL = "http://yxgtest.bangjia.me/";

    //用作注册账号
    private static final String SIGN_API = "https://accounts.pixiv.net/";



    public static AppApi getAppApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog", "retrofitBack = " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                //.dns(HttpDns.get())
                .addInterceptor(chain -> {
                    Request localRequest = chain.request().newBuilder()
                            .addHeader("User-Agent:", "PixivAndroidApp/5.0.134 (Android 6.0.1; D6653)")
                            .addHeader("Accept-Language", "zh_CN")
                            .build();
                    return chain.proceed(localRequest);
                })
                .addInterceptor(new TokenInterceptor())
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                //.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_BASE_URL)
                .build();
        return retrofit.create(AppApi.class);
    }


    public static SignApi getSignApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog", "retrofitBack = " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                //.dns(HttpDns.get())
                .addInterceptor(chain -> {
                    Request localRequest = chain.request().newBuilder()
                            .addHeader("User-Agent:", "PixivAndroidApp/5.0.134 (Android 6.0.1; D6653)")
                            .addHeader("Accept-Language", "zh_CN")
                            .build();
                    return chain.proceed(localRequest);
                })
                .addInterceptor(new TokenInterceptor())
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                //.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(SIGN_API)
                .build();
        return retrofit.create(SignApi.class);
    }

    public static AccountApi getAccountApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog", "retrofitBack = " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(chain -> {
                    Request localRequest = chain.request().newBuilder()
                            .addHeader("User-Agent:", "PixivAndroidApp/5.0.134 (Android 6.0.1; D6653)")
                            //.addHeader("Accept-Language:", "zh_CN")
                            .build();
                    return chain.proceed(localRequest);
                })
                .build();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ACCOUNT_BASE_URL)
                .build();
        return retrofit.create(AccountApi.class);
    }


    public static RankTokenApi getRankApi() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog", "retrofitBack = " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(chain -> {
                    Request localRequest = chain.request().newBuilder()
                            .addHeader("User-Agent:", "PixivAndroidApp/5.0.134 (Android 6.0.1; D6653)")
                            //.addHeader("Accept-Language:", "zh_CN")
                            .build();
                    return chain.proceed(localRequest);
                })
                .build();
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(RANK_TOKEN_BASE_URL)
                .build();
        return retrofit.create(RankTokenApi.class);
    }

    public static <T> T create(String baseUrl,final Class<T> service) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(
                message -> Log.i("RetrofitLog", "retrofitBack = " + message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .addInterceptor(chain -> {
                    Request localRequest = chain.request().newBuilder()
                            .addHeader("User-Agent:", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.80 Safari/537.36")
                            .addHeader("Accept-Encoding:","gzip, deflate")
                            .addHeader("Accept:","text/html")
                            .build();
                    return chain.proceed(localRequest);
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(service);
    }
}