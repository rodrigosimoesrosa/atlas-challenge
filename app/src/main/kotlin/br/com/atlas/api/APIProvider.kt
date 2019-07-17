package br.com.atlas.api

import br.com.atlas.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
object APIProvider {

    private const val WRITE_TIME_OUT = 60L
    private const val READ_TIME_OUT = 120L

    private val LEVEL = HttpLoggingInterceptor.Level.HEADERS

    private fun getOkHttpBuilder(writeTimeout: Long, readTimeout: Long): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
    }

    interface API<Methods> {
        fun getAPI(): Methods
        fun getRetrofit(): Retrofit
    }

    fun <Methods> getAPI(apiClass: Class<Methods>,
                         reactive: Boolean = true): API<Methods> {
        return APIImplementation(apiClass, reactive)
    }

    class APIImplementation<Methods>(private val apiClass: Class<Methods>,
                                     private val reactive: Boolean) : BaseAPI(),
        API<Methods> {

        override fun getAPI(): Methods {
            return build(apiClass = apiClass, reactive = reactive)
        }

    }

    interface APIBehavior {
        fun getRetrofit(): Retrofit
        fun getBaseURL(): String
        fun <Methods> build(apiClass: Class<Methods>,
                            baseURL: String = getBaseURL(),
                            reactive: Boolean = true,
                            headers: Map<String, String>? = null): Methods?
    }

    abstract class BaseAPI : APIBehavior {

        private lateinit var retrofit: Retrofit

        override fun getRetrofit(): Retrofit {
            return retrofit
        }

        override fun getBaseURL(): String {
            return BuildConfig.ENVIRONMENT_API
        }

        override fun <Methods> build(apiClass: Class<Methods>,
                                     baseURL: String,
                                     reactive: Boolean,
                                     headers: Map<String, String>?): Methods {

            val okHttpClient = getOkHttpBuilder(
                WRITE_TIME_OUT,
                READ_TIME_OUT
            )
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = LEVEL
                okHttpClient.addInterceptor(loggingInterceptor)
            }

            okHttpClient.addNetworkInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()

                headers?.forEach { requestBuilder.addHeader(it.key, it.value) }

                requestBuilder.method(original.method(), original.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }

            val client = okHttpClient.build()
            val gsonConverterFactory = GsonConverterFactory.create(GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create())

            val builder = Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(gsonConverterFactory)

            if (reactive) {
                builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            }

            retrofit = builder.build()

            return retrofit.create(apiClass)
        }
    }
}
