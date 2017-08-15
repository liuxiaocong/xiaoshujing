package com.xiaoshujing.kid.api;

import com.xiaoshujing.kid.MyApplication;
import com.xiaoshujing.kid.common.Util;
import com.xiaoshujing.kid.data.UserInfoManage;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
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
 * Created by liuxiaocong on 5/10/16.
 */
public class RetrofitManager {
    private static RetrofitManager _ins;

    private volatile String baseUrl;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private volatile boolean isTokenRefreshing;
    private ApiService service;

    private static class RetrofitManagerHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static final RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    private RetrofitManager() {
        this.baseUrl = Util.SERVER + "/api/v1/";
        initOkHttp();
        initRetrofit();
    }

    private synchronized void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                //.addConverterFactory(IntegerConverterFactory.create())
                //must add at the end of other Converter
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private void initOkHttp() {
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "[response]");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        builder.cache(cache);
        builder.addInterceptor(mTokenInterceptor);
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(150, TimeUnit.SECONDS);
        builder.writeTimeout(150, TimeUnit.SECONDS);
        builder.readTimeout(150, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        };
        TrustManager[] xtmArray = new TrustManager[]{x509TrustManager};
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, xtmArray, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory, x509TrustManager);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;//allow all host
            }
        });

        okHttpClient = builder.build();
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
            String token = "apikey ";
            if (UserInfoManage.getInstance().getUserBean() != null) {
                token = token + UserInfoManage.getInstance().getUserBean().getPhone() + ":" + UserInfoManage.getInstance().getUserBean().getApi_key();
            } else {
                token = "apikey 15578158426:fed564faf5a1b0e34450e9df094e7557edf945e6";
            }
            Request.Builder builder = originalRequest.newBuilder()
                    .url(url)
                    .header("Authorization", token);
//                    .header("Authorization", User.getToken());


            if (originalRequest.body() != null &&
                    originalRequest.body().contentType() != null &&
                    originalRequest.body().contentType().subtype().equals("json")) {
                Buffer buffer = new Buffer();
                originalRequest.body().writeTo(buffer);
                if (buffer.size() < 20 * 1024) {
                    RequestBody body = RequestBody.create(originalRequest.body().contentType(), buffer.readUtf8()
                            .replace("\"created_at\":", "\"created_at1\":")
                            .replace("\"date_joined\":", "\"date_joined1\":")
//                            .replace("\"baby\":", "\"baby1\":")
                            .replace("\"updated_at\":", "\"updated_at1\":"));

                    builder.method(originalRequest.method(), body);
                }
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
                    .header("Cache-Control", "public, max-age=1")
                    //.header("Cache-Control", cacheControl)
//                    .body(body)
                    .removeHeader("Pragma")
                    .build();
        }
    };

    public ApiService getApiService() {
        if (service == null) {
            synchronized (RetrofitManagerHolder.INSTANCE) {
                service = RetrofitManagerHolder.INSTANCE.retrofit.create(ApiService.class);

            }
        }
        return service;
    }
}
