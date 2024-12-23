package com.dicoding.dicodingevents.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.dicodingevents.data.remote.repository.EventRepository
import com.dicoding.dicodingevents.data.local.EventEntity
import kotlinx.coroutines.launch

class FavoriteViewModel(private val eventRepository: EventRepository) : ViewModel() {

    val favoriteEvents: LiveData<List<EventEntity>> = eventRepository.getFavoriteEvents()

    fun toggleFavorite(event: EventEntity) {
        viewModelScope.launch {
            eventRepository.setEventFavorite(event, !event.isFavorited)
        }
    }

    fun removeFavorite(event: EventEntity) {
        viewModelScope.launch {
            eventRepository.setEventFavorite(event, false)
        }
    }
}
