package com.liferay.samples.fbo.foo.foodate;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface FooService {
    @POST("/o/c/foos/")
    Call<Foo> createFoo(@Body Foo foo);
}
