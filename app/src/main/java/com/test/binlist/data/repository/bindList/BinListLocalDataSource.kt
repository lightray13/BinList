package com.test.binlist.data.repository.bindList

import androidx.lifecycle.LiveData
import com.test.binlist.data.local.database.CardDatabase
import com.test.binlist.data.local.database.CardListEntity
import javax.inject.Inject

class BinListLocalDataSource @Inject constructor(private val database: CardDatabase) {
    val binList: LiveData<List<CardListEntity>> = database.cardListDao().cardList()
}