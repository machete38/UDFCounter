package com.example.udfcounter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterTextView = findViewById<TextView>(R.id.counterTextView)
        val incrementButton = findViewById<Button>(R.id.incrementButton)
        val decrementButton = findViewById<Button>(R.id.decrementButton)
        val resetButton = findViewById<Button>(R.id.resetButton)

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                counterTextView.text = "Count: ${state.count}"
            }
        }

        incrementButton.setOnClickListener {
            dispatchAction(CounterAction.Increment)
        }
        decrementButton.setOnClickListener {
            dispatchAction(CounterAction.Decrement)
        }
        resetButton.setOnClickListener{
            dispatchAction(CounterAction.Reset)
        }


}
    private fun dispatchAction(action: CounterAction) {
        viewModel.dispatch(action)
    }
}