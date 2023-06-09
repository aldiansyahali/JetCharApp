package com.ali.jetcharapp.data

import com.ali.jetcharapp.model.CharacterData
import com.ali.jetcharapp.model.OrderCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val characters = mutableListOf<OrderCharacter>()

    init {
        if (characters.isEmpty()) {
            CharacterData.characterdata.forEach {
                characters.add(OrderCharacter(it, 0))
            }
        }
    }

    fun getCharacter(): Flow<MutableList<OrderCharacter>> {
        return flowOf(characters)
    }


    fun getOrderById(characterId: Long): OrderCharacter {
        return characters.first() {
            it.character.characterID == characterId
        }
    }
}
