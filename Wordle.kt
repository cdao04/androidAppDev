package com.example.project1

import java.util.Scanner
import android.content.res.Resources
import android.util.Log

class Wordle {
    // Instance variables
    private var wordToFind : String = "STATE"
    private var tries : Int = 0
    private var list : ArrayList<String> = ArrayList<String>()

    // constant variables
    companion object {
        const val MAX_TRIES : Int = 6
        const val WORD_LENGTH : Int = 5
    }

    // constructor -> initializes the list of words and the word to be guessed
    constructor (res : Resources) {
        readFile(res)
        pickRandomWord()
    }

    // readFile -> accepts Resources parameter and fills in list with the words read from the file
    // all words added to the list of words are capitalized
    fun readFile(res : Resources) {
        val inStream = res.openRawResource(R.raw.words)
        val scanner = Scanner(inStream)

        // gets word on each line
        // capitalize the word and add it to the list
        var word = scanner.next()
        while (scanner.hasNext()) {
            var capWord = word.uppercase()
            list.add(capWord)
            // scans next word in file
            word = scanner.next()
        }
    }

    // pickRandomWord -> randomly picks a word from the list and assigns it to word
    fun pickRandomWord() {
        wordToFind = list.random()
    }

    // testBikes -> sets word to be discovered to 'BIKES'
    fun testBikes() {
        wordToFind = "BIKES"
    }

    // won -> returns the result of whether the word guessed matches the word
    fun won(guess : String) : Boolean {
        if (guess == wordToFind) {
            return true
        } else {
            return false
        }
    }

    // play -> returns an array of integers; the integers are made up of the values:
    // 0 (incorrect letter, or letter already taken into account),
    // 1 (good letter, correct position) and
    // 2 (good letter, wrong position)
    fun play(guess : String) : Array<Int> {
        // array to store integers
        val arr : Array<Int> = Array<Int>(5, {i -> -1})

        // array to keep track of visited letters in wordToFind
        val arrTrack : Array<Int> = Array<Int>(5, {i -> 0})

        val wrongOrUsed : Int = 0
        val correct : Int = 1
        val wrongPos : Int = 2

        // loop through each character in  the guessed word and find the resulting int
        for (i in 0..guess.length - 1) {
            for (j in 0..wordToFind.length - 1) {
                // check is characters match
                if (guess[i] == wordToFind [j]) {
                    // check if indices match
                    // if match index val is 1, otherwise check if letter already taken into account
                    if (i == j) {
                        arr[i] = correct
                        arrTrack[j] = 1
                        break
                    } else {
                        // check if letter appears and matches later from word guessed
                        // or has already been taken into account
                        if (arrTrack[j] == 1 || guess[j] == wordToFind[j]) {
                            arr[i] = wrongOrUsed
                        } else {
                            arr[i] = wrongPos
                            arrTrack[j] = 1
                        }
                    }
                }
            }

            // checks if arr value changed after checking if anything matches the word parameter
            // if not (letter not in wordToFind) defaults to 0
            if (arr[i] == -1) {
                arr[i] = wrongOrUsed
            }
        }

        return arr
    }

    // getter functions for instance variables
    fun getList () : ArrayList<String> {
        return list
    }

    fun getWord () : String {
        return wordToFind
    }


}