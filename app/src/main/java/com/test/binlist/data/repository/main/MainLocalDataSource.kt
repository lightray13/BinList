package com.test.binlist.data.repository.main

import com.test.binlist.data.local.database.CardDatabase
import com.test.binlist.data.local.database.CardListEntity
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(private val database: CardDatabase) {

    suspend fun insertCardIntoDatabase(card: CardListEntity): Long {
        return database.cardListDao().insertCardListEntity(card)
    }
}