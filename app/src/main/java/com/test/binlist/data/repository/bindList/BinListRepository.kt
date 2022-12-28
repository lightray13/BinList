package com.test.binlist.data.repository.bindList

import javax.inject.Inject

class BinListRepository @Inject constructor(
    binListLocalDataSource: BinListLocalDataSource
) {
    val binList = binListLocalDataSource.binList
}