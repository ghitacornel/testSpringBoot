package beans.clients.retrofit;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExternalServiceContract {

    @GET("/externalService/{input}")
    Call<PersonResponseDto> invokeGET(@Path("input") String input);

    @POST("/externalService")
    Call<PersonResponseDto> invokePOST(@Body PersonRequestDto inputModel);

}
