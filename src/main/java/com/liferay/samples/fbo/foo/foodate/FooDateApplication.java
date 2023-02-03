package com.liferay.samples.fbo.foo.foodate;

import java.io.IOException;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

@SpringBootApplication
public class FooDateApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooDateApplication.class, args);
	}

}
