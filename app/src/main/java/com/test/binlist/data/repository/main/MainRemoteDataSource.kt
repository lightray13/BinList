package com.test.binlist.data.repository.main

import com.test.binlist.api.ApiInterface
import com.test.binlist.api.model.Card
import javax.inject.Inject
import com.test.binlist.api.Result
import com.test.binlist.util.Constants

class MainRemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun bin(bin: String): Result<Card> {
        try {
            val response = service.bin(bin)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return Result.Error(Constants.GENERIC_ERROR)
        } catch (e: Exception) {
            return Result.Error(e.message ?: e.toString())
        }
    }
}