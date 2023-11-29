package beans.clients.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
class RetrofitConfig {

    @Value("${retrofit.client.url}")
    private String url;

    @Bean
    RetrofitContract retrofitContract(ObjectMapper objectMapper) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(RetrofitContract.class);

    }

}
