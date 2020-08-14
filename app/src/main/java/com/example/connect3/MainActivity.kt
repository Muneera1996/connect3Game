package com.example.connect3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // 0: yellow 1: red 2: empty

    var gameActive = true;
    var activePlayer = 0;
    var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    var winningStates = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8), intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun dropIn(view: View) {
        var counter: ImageView = view as ImageView
        Log.d("TAG", counter.tag.toString());
        var getState = Integer.parseInt(counter.tag.toString());


        if (gameState[getState] == 2 && gameActive) {
            gameState.set(getState, activePlayer)

            counter.translationY = -1000f
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow)
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red)
                activePlayer = 0;

            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300)

            for (i: Int in 0 until winningStates.size) {
                if (gameState[winningStates[i][0]] == gameState[winningStates[i][1]] && gameState[winningStates[i][1]] == gameState[winningStates[i][2]] && gameState[winningStates[i][0]] != 2) {
                    // Somone has won!

                    gameActive = false

                    var winner = ""

                    if (activePlayer == 1) {

                        winner = "Yellow"


                    } else {

                        winner = "Red"

                    }
                    Toast.makeText(this, "$winner is winner", Toast.LENGTH_LONG).show()
                    winnerTextView.text = "$winner has won"
                    winnerTextView.visibility = View.VISIBLE
                    playAgainButton.visibility = View.VISIBLE

                }


            }
        }
    }

    fun playAgain(view: View) {
        winnerTextView.visibility = View.INVISIBLE
        playAgainButton.visibility = View.INVISIBLE

        for (i in 0 until gridLayout.childCount) {

            val counter = gridLayout.getChildAt(i) as ImageView

            counter.setImageDrawable(null)

        }

        for (i in gameState.indices) {

            gameState[i] = 2

        }

        activePlayer = 0

        gameActive = true
    }
}


