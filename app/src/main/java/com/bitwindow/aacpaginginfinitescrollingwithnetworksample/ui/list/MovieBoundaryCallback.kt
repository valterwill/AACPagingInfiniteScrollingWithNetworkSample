package com.bitwindow.aacpaginginfinitescrollingwithnetworksample.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.BoundaryState
import com.bitwindow.aacpaginginfinitescrollingwithnetworksample.data.vo.Movie
import timber.log.Timber
import java.util.*

class MovieBoundaryCallback : PagedList.BoundaryCallback<Movie>() {

    private val _boundaryState = MutableLiveData<BoundaryState<Date>>()
    val boundaryState : LiveData<BoundaryState<Date>>
        get() = _boundaryState

    companion object {
        const val DATABASE_PAGE_SIZE = 15
    }

    override fun onItemAtFrontLoaded(itemAtFront: Movie) {
        Timber.d(
            "onItemAtFrontLoaded %d %s %s ", itemAtFront.id,
            itemAtFront.title, itemAtFront.hashCode()
        )
        _boundaryState.value = BoundaryState.itemLoadedAtTop(itemAtFront.releaseDate)
    }

    override fun onZeroItemsLoaded() {
        Timber.d("onZeroItemsLoaded")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(Date())
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        Timber.d(
            "onItemAtFrontLoaded %d %s %s ", itemAtEnd.id,
            itemAtEnd.title, itemAtEnd.hashCode()
        )
        _boundaryState.value = BoundaryState.itemLoadedAtBottom(itemAtEnd.releaseDate)
    }

    fun refresh(){
        Timber.d("refresh")
        _boundaryState.value = BoundaryState.zeroItemsLoaded(Date())
    }

}