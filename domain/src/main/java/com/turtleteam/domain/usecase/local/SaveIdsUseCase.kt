package com.turtleteam.domain.usecase.local

import com.turtleteam.domain.repository.StorageRepository

class SaveIdsUseCase(private val repository: StorageRepository) {
    fun execute(id: Int): Boolean {
        val ids: MutableList<String> = repository.setIds()?.toMutableList() ?: mutableListOf()
        ids.map {
            if (id.toString() == it) {
                ids.remove(it)
                repository.saveIds(ids)
                return false
            }
        }
        ids.add(id.toString())
        repository.saveIds(ids)
        return true
    }
}