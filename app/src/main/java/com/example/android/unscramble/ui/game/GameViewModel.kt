package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score
    private var _currentWordCount = MutableLiveData<Int>(0)
    val currentWordCount: MutableLiveData<Int>
        get() = _currentWordCount
    private lateinit var currentWord: String
    private val usedWordsList = mutableListOf<String>()
    private  var _currentScrambledWord = MutableLiveData<String>()
    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord



    init {
        Log.d("GameFragment", "GameViewModel created!")
        _score.value = 0
        _currentWordCount.value = 0
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        usedWordsList.clear()
        getNextWord()
    }


    private fun getNextWord(){
        currentWord = allWordsList.random()

        if (usedWordsList.contains(currentWord)) {
            getNextWord()
        } else {
            usedWordsList.add(currentWord)
            _currentWordCount.value = _currentWordCount.value?.inc()
            Log.d("cWC",_currentWordCount.value.toString())
        }

        val tempWord = currentWord.toCharArray();
        tempWord.shuffle()
        _currentScrambledWord.value = String(tempWord)

        while (currentWord == _currentScrambledWord.value) {
            tempWord.shuffle()
            _currentScrambledWord.value = String(tempWord)
        }
    }

     fun nextWord(): Boolean {
        if (_currentWordCount.value!! < MAX_NO_OF_WORDS ) {
            getNextWord()
            return true
        } else {
            return false
        }
    }

    private fun increaseScore() {
       _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        if (playerWord == currentWord) {
            increaseScore()
            return true
        }
        return false
    }




}