package com.dicoding.dicodingevents.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.dicodingevents.data.remote.repository.EventRepository
import com.dicoding.dicodingevents.data.remote.repository.Result
import com.dicoding.dicodingevents.data.local.EventEntity
import kotlinx.coroutines.launch

class UpcomingViewModel(private val eventRepository: EventRepository) : ViewModel() {

    val upcomingEvents: LiveData<Result<List<EventEntity>>> = eventRepository.getUpcomingEvents()

    fun toggleFavorite(event: EventEntity) {
        viewModelScope.launch {
            eventRepository.setEventFavorite(event, !event.isFavorited)
        }
    }

    fun deleteFavoritedEvent(event: EventEntity) {
        viewModelScope.launch {
            eventRepository.setEventFavorite(event, false)
        }
    }
}
