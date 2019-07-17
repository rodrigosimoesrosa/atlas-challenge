package br.com.atlas.extension

import br.com.atlas.api.error.EntityError
import br.com.atlas.base.entity.DomainMappable
import br.com.atlas.connectivity.Connectivity
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by rodrigosimoesrosa on 2019-07-16.
 * Copyright © 2019. All rights reserved.
 */
fun <TYPE> Single<TYPE>.mapNetworkErrors(): Single<TYPE> = onErrorResumeNext {
        error -> when (error) {
    is SocketTimeoutException,
    is UnknownHostException,
    is ConnectException -> Single.error(Connectivity.checkInternetNotFoundException(error))
    else -> Single.error(error) }
}

fun <ERROR : EntityError, TYPE> Single<TYPE>.mapErrorByClient(errorEntity: ERROR):
        Single<TYPE> = map { it }.onErrorResumeNext { error ->
    if (error is HttpException && error.code() >= 400) {
        Single.error<TYPE>(errorEntity.toThrowable())
    } else {
        Single.error(error)
    }
}

/**
 * Serialize API Error json
 */
fun <ERROR : EntityError, TYPE> Single<TYPE>.mapErrorByAPI(retrofit: Retrofit, errorClass: Class<ERROR>):
        Single<TYPE> = map { it }.onErrorResumeNext { error ->
    if (error is HttpException && error.code() >= 400) {
        val errorConverter = retrofit.responseBodyConverter<ERROR>(errorClass, arrayOfNulls<Annotation>(0))
        val errorEntity: ERROR = errorConverter.convert(error.response().errorBody())
        Single.error<TYPE>(errorEntity.toThrowable())
    } else {
        Single.error(error)
    }
}

fun <TYPE : DomainMappable<DOMAIN_TYPE>, DOMAIN_TYPE> Single<TYPE>.toDomain(): Single<DOMAIN_TYPE>
        = this.map { it.toDomain() }

fun <TYPE : DomainMappable<DOMAIN_TYPE>, DOMAIN_TYPE> Single<List<TYPE>>.listToDomain():
        Single<List<DOMAIN_TYPE>> = this.map { data -> data.map { it.toDomain() }
}