package com.turtleteam.domain.repository

interface StorageRepository {

    fun saveIds(list: List<String>)

    fun setIds(): List<String>?
}