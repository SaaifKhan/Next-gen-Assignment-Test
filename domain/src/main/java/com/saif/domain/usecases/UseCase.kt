package com.saif.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<out Type, in Params> {

    abstract suspend fun run(params: Params): Flow<Type>

    suspend operator fun invoke(params: Params) : Flow<Type>  = run(params).flowOn(Dispatchers.IO)

    class None
}