package beans.clients.retrofit;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExternalRestClientRetrofit {

    @Value("${retrofit.client.url}")
    private String url;

    public PersonResponseDto invokePOST(PersonRequestDto inputModel) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ExternalServiceContract externalServiceContract = retrofit.create(ExternalServiceContract.class);

        try {
            return externalServiceContract.invokePOST(inputModel).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PersonResponseDto invokeGET(String path, String query) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ExternalServiceContract externalServiceContract = retrofit.create(ExternalServiceContract.class);

        try {
            return externalServiceContract.invokeGET(path, query).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
