package com.example.melshaeir.newsapi.ui.MainActivity;

import android.content.Context;

import com.example.melshaeir.newsapi.Model.Sources;
import com.example.melshaeir.newsapi.Model.WebSite;
import com.example.melshaeir.newsapi.Network.Api;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.melshaeir.newsapi.util.Constants.BASE_URL;


public class Ui_Presenter {
    Context context;
    ui_view ui;

    public Ui_Presenter(Context context, ui_view ui) {
        this.context = context;
        this.ui = ui;
    }

    public Retrofit retrofitService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient().build())
                .build();

        return retrofit;
    }
    public  void  interfaceData()
    {
        Map<String,String> map = new HashMap<>();
        map.put("apiKey","19fc07f95b2048208962c73a2d680df5");
        Api api = retrofitService().create(Api.class);
       api.getNews(map).enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               Gson gson = new Gson();
               try {
                   WebSite webSite = gson.fromJson(response.body().string(),WebSite.class);
                   List<Sources> sources = webSite.getSources();
                   ui.getNews(sources);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }


           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });

    }
    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
