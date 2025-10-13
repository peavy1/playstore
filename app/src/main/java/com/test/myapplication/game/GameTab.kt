package com.test.myapplication.game

import androidx.fragment.app.Fragment
import com.test.myapplication.R
import com.test.myapplication.game.fragment.GameCategoryFragment
import com.test.myapplication.game.fragment.GameRankFragment
import com.test.myapplication.game.fragment.GameRecommendFragment
import com.test.myapplication.game.fragment.GameKidsFragment
import com.test.myapplication.game.fragment.GameNewFragment

sealed class GameTab(
    val titleId: Int
)  {

    abstract fun getFragment(): Fragment

    class Recommend: GameTab(R.string.game_tab_recommend) {
        override fun getFragment() = GameRecommendFragment()
    }

    class Rank: GameTab(R.string.game_tab_popular) {
        override fun getFragment() = GameRankFragment()
    }

    class Work: GameTab(R.string.game_tab_kids) {
        override fun getFragment() = GameKidsFragment()
    }

    class New: GameTab(R.string.game_tab_new) {
        override fun getFragment() = GameNewFragment()
    }

    class Category: GameTab(R.string.game_tab_category) {
        override fun getFragment() = GameCategoryFragment()
    }
}

