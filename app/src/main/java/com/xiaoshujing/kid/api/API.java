package com.xiaoshujing.kid.api;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zzz on 10/28/16.
 */

public class API {
    private static API api = new API();

    private static final String BASE_URL = "http://59.110.23.25/api/v1/";

    public static ApiService getService() {
        return api.service;
    }

    private ApiService service;

    //构造方法私有
    private API() {
//        File cacheFile = new File(App.app.getCacheDir(), "[response]");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(mTokenInterceptor)
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
//                .readTimeout(1, TimeUnit.HOURS)
                .writeTimeout(1, TimeUnit.HOURS)
                .connectTimeout(1, TimeUnit.HOURS)
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        service = retrofit.create(ApiService.class);
    }

    Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            HttpUrl url = originalRequest.url().newBuilder()
//                    .addQueryParameter("_platform", "Android")
//                    .addQueryParameter("_device", Build.MODEL)
//                    .addQueryParameter("_os_version", Build.VERSION.RELEASE)
//                    .addQueryParameter("_app_version", App.getVersionName())
//                    .addQueryParameter("_lang", Locale.getDefault().getLanguage())
//                    .addQueryParameter("_installment_id", App.app.getUDID())
                    .build();
            Request.Builder builder = originalRequest.newBuilder()
                    .url(url);
//                    header("Authorization", User.getToken());

            if (originalRequest.body() != null) {
                Buffer buffer = new Buffer();
                originalRequest.body().writeTo(buffer);
                RequestBody body = RequestBody.create(originalRequest.body().contentType(), buffer.readUtf8()
                        .replace("\"created_at\":", "\"created_at1\":")
                        .replace("\"date_joined\":", "\"date_joined1\":")
                        .replace("\"baby\":", "\"baby1\":")
                        .replace("\"updated_at\":", "\"updated_at1\":"));

                builder.method(originalRequest.method(), body);
            }
            return chain.proceed(builder.build());
        }
    };

    /**
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            String cacheControl = chain.request().cacheControl().toString();

//            ResponseBody body = ResponseBody.create(originalResponse.body().contentType(), originalResponse.body().string()
//                    .replace("unavailable_date\": \"\"", "unavailable_date\": []")
//                    .replace("unavailable_interval\": \"\"", "unavailable_interval\": []"));
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
//                    .body(body)
                    .removeHeader("Pragma")
                    .build();
        }
    };
}

