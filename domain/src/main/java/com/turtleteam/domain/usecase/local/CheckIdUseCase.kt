package com.turtleteam.domain.usecase.local

import com.turtleteam.domain.repository.StorageRepository

class CheckIdUseCase(private val storage: StorageRepository) {
    fun execute(id: Int): Boolean {
        val ids = storage.setIds() ?: emptyList()
        ids.map { if (id.toString() == it) return true }
        return false
    }
}