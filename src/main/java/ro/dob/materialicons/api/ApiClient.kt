package ro.dob.materialicons.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import ro.dob.materialicons.api.response.FetchIconsResponse
import ro.dob.materialicons.api.response.InitializeApiResponse
import ro.dob.materialicons.model.BASE_URL
import ro.dob.materialicons.model.ID

interface ApiClient
{
    companion object
    {
        val Instance : ApiClient by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(ApiClient::class.java)
        }
    }

    @GET("/api/init")
    fun initialize() : Call<InitializeApiResponse>

    @GET("/api/package/{packageId}")
    fun fetchIcons(
        @Path("packageId") packageId : ID,
        @Query("iconName") iconName : String)
        : Call<FetchIconsResponse>

    @Streaming
    @GET("/api/download/package/{iconId}/android-system")
    fun downloadIcon(
        @Path("iconId") iconId : ID)
        : Call<ResponseBody>
}
