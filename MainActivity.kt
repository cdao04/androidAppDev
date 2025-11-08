package com.example.project1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log

// constant variables
// val WORD_LENGTH : Int = 5
// val MAX_TRIES : Int = 6

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Start of MainActivity code for project???
        var wordle : Wordle = Wordle (resources)

        // log the list of words
        var listOfWords = wordle.getList()
        Log.w("MainActivity", "List of words:")
        // iterate through list and log them
        for (i in 0..listOfWords.size - 1) {
            Log.w("MainActivity", listOfWords[i])
         }

        // log the word to be found
        Log.w("MainActivity", "The word to be guessed is: " + wordle.getWord())

        // change word to be guessed to 'BIKES'
        wordle.testBikes()

        // call the play function on the 7 words in the array
        val arrGuess : Array<String> =
            arrayOf("STOPS", "BIKES", "JAVAX", "VALID", "VITAL", "VISOR", "BABEL")
        for (i in 0..arrGuess.size - 1) {
            var result = wordle.play(arrGuess[i])
            Log.w("MainActivity", arrGuess[i] + "   " + convertToString(result))
        }


    }

    // convert array of ints to a string
    fun convertToString (arr : Array<Int>) : String {
        var str : String = ""
        for (i in 0..arr.size - 1) {
            str += arr[i].toString()
        }
        return str
    }



}