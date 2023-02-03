package com.liferay.samples.fbo.foo.foodate;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestController
public class MyController {
 
    @GetMapping("/")
    String foo() {

        Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(Level.BODY);
            
        OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new BasicAuthInterceptor("test@liferay.com", "test1234"))
            .addInterceptor(logging)
            .build();

        Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .baseUrl("http://localhost:8080/")
            .build();

        FooService service = retrofit.create(FooService.class);

        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        Foo foo = new Foo();
        foo.setDate(new Date());
        foo.setExternalReferenceCode(uuidAsString);
        foo.setTitle("Title");

        Call<Foo> fooCall = service.createFoo(foo);

        try {
            Response<Foo> fooResponse = fooCall.execute();

            if(fooResponse.code() == 200) {
                Foo createdFoo = fooResponse.body();
                System.out.println("Foo: " + createdFoo.getId());
                return createdFoo.getTitle() + " " + createdFoo.getId();
            } else {
                return String.valueOf(fooResponse.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
