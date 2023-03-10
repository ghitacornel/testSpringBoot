package beans.clients.retrofit;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import retrofit2.Call;
import retrofit2.http.*;

public interface ExternalServiceContract {

    @GET("/externalService/{path}")
    Call<PersonResponseDto> invokeGET(@Path("path") String path, @Query("parameter") String query);

    @POST("/externalService")
    Call<PersonResponseDto> invokePOST(@Body PersonRequestDto inputModel);

}
