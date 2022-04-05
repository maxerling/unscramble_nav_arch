package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private var score = 0
    private var _currentWordCount = 0
    private lateinit var currentWord: String
    private val usedWordsList = mutableListOf<String>()
    private lateinit  var _currentScrambledWord: String
    val currentScrambledWord: String
        get() = _currentScrambledWord



    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }


    private fun getNextWord(){
        currentWord = allWordsList.random()

        if (usedWordsList.contains(currentWord)) {
            getNextWord()
        } else {
            usedWordsList.add(currentWord)
            _currentWordCount++
        }

        val tempWord = currentWord.toCharArray();
        tempWord.shuffle()
        _currentScrambledWord = String(tempWord)

        while (currentWord == _currentScrambledWord) {
            tempWord.shuffle()
            _currentScrambledWord = String(tempWord)
        }
    }

    private fun nextWord(): Boolean {
        if (_currentWordCount < MAX_NO_OF_WORDS ) {
            getNextWord()
            return true
        } else {
            return false
        }
    }
}