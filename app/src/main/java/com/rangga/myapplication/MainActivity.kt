package com.rangga.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.cardview.widget.CardView
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var mainLayout: ConstraintLayout
    private lateinit var tvQuote: TextView
    private lateinit var tvAuthor: TextView
    private lateinit var btnKlik: Button
    private lateinit var btnCopy: ImageButton
    private lateinit var btnShare: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var historyContainer: LinearLayout
    
    // History & Storage
    private lateinit var sharedPreferences: SharedPreferences
    private val historyList = mutableListOf<Pair<String, String>>() // Pair of Quote, Author
    private val PREFS_NAME = "QuoteHistoryPrefs"
    private val KEY_HISTORY = "history_list"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize Views
        mainLayout = findViewById(R.id.mainLayout)
        tvQuote = findViewById(R.id.tvQuote)
        tvAuthor = findViewById(R.id.tvAuthor)
        btnKlik = findViewById(R.id.btnKlik)
        btnCopy = findViewById(R.id.btnCopy)
        btnShare = findViewById(R.id.btnShare)
        progressBar = findViewById(R.id.progressBar)
        historyContainer = findViewById(R.id.historyContainer)

        // Init Prefs & Load History
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadHistory()
        refreshHistoryUI()
        
        // Initial random gradient
        setRandomGradient()

        // Get Quote Action
        btnKlik.setOnClickListener {
            fetchQuote()
        }

        // Copy Action
        btnCopy.setOnClickListener {
            val quote = tvQuote.text.toString()
            val author = tvAuthor.text.toString()
            if (quote.isNotEmpty()) {
                copyToClipboard("$quote\n$author")
            }
        }

        // Share Action
        btnShare.setOnClickListener {
            val quote = tvQuote.text.toString()
            val author = tvAuthor.text.toString()
             if (quote.isNotEmpty()) {
                shareQuote("$quote\n$author")
            }
        }
    }

    private fun fetchQuote() {
        progressBar.visibility = View.VISIBLE
        btnKlik.isEnabled = false

        ApiClient.instance.getRandomQuote().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                progressBar.visibility = View.GONE
                btnKlik.isEnabled = true

                if (response.isSuccessful) {
                    val body = response.body()
                    val newQuote = body?.quote ?: "No Quote"
                    val newAuthor = body?.author ?: "Unknown"

                    updateUI(newQuote, newAuthor)
                    addToHistory(newQuote, newAuthor)
                    setRandomGradient()
                } else {
                    Toast.makeText(this@MainActivity, "Gagal: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                btnKlik.isEnabled = true
                Toast.makeText(this@MainActivity, "Error Jaringan: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateUI(quote: String, author: String) {
        tvQuote.text = "\"$quote\""
        tvAuthor.text = "- $author"
    }

    private fun copyToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Quote", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Quote berhasil disalin!", Toast.LENGTH_SHORT).show()
    }

    private fun shareQuote(text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Bagikan Quote ke...")
        startActivity(shareIntent)
    }

    private fun setRandomGradient() {
        val colors = arrayOf(
            intArrayOf(Color.parseColor("#FF512F"), Color.parseColor("#DD2476")), // Bloody Mary (Strong Orange-Red)
            intArrayOf(Color.parseColor("#11998e"), Color.parseColor("#38ef7d")), // Lush (Strong Green-Teal)
            intArrayOf(Color.parseColor("#0575E6"), Color.parseColor("#021B79")), // Navy (Deep Blue)
            intArrayOf(Color.parseColor("#8E2DE2"), Color.parseColor("#4A00E0")), // Amin (Vibrant Purple)
            intArrayOf(Color.parseColor("#C33764"), Color.parseColor("#1D2671")), // Celestial (Pink-Dark Blue)
            intArrayOf(Color.parseColor("#F2994A"), Color.parseColor("#F2C94C"))  // Sun (Bright Orange-Yellow)
        )
        val randomColor = colors[Random.nextInt(colors.size)]
        
        val gradient = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            randomColor
        )
        
        // Save padding before setting background (as setBackground resets padding)
        val pLeft = mainLayout.paddingLeft
        val pTop = mainLayout.paddingTop
        val pRight = mainLayout.paddingRight
        val pBottom = mainLayout.paddingBottom
        
        mainLayout.background = gradient
        
        // Restore padding
        mainLayout.setPadding(pLeft, pTop, pRight, pBottom)
    }

    private fun addToHistory(quote: String, author: String) {
        // Add to top of main list
        historyList.add(0, quote to author)
        
        // Limit to 10
        if (historyList.size > 10) {
            historyList.removeAt(historyList.size - 1)
        }
        
        saveHistory()
        refreshHistoryUI()
    }

    private fun saveHistory() {
        val jsonArray = JSONArray()
        for ((q, a) in historyList) {
            val item = org.json.JSONObject()
            item.put("q", q)
            item.put("a", a)
            jsonArray.put(item)
        }
        sharedPreferences.edit().putString(KEY_HISTORY, jsonArray.toString()).apply()
    }

    private fun loadHistory() {
        val jsonString = sharedPreferences.getString(KEY_HISTORY, null)
        if (jsonString != null) {
            historyList.clear()
            try {
                val jsonArray = JSONArray(jsonString)
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    historyList.add(item.getString("q") to item.getString("a"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun refreshHistoryUI() {
        historyContainer.removeAllViews()
        
        for ((quote, author) in historyList) {
            // Create CardView for each item
            val card = CardView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 16) // Bottom margin
            card.layoutParams = params
            card.radius = 24f
            card.cardElevation = 4f
            card.setContentPadding(24, 24, 24, 24)
            card.setCardBackgroundColor(Color.WHITE)

            // Content Container
            val itemLayout = LinearLayout(this)
            itemLayout.orientation = LinearLayout.VERTICAL
            
            val tvItemQuote = TextView(this)
            tvItemQuote.text = "\"$quote\""
            tvItemQuote.textSize = 14f
            tvItemQuote.setTextColor(Color.parseColor("#424242"))
            tvItemQuote.maxLines = 2
            tvItemQuote.ellipsize = android.text.TextUtils.TruncateAt.END
            tvItemQuote.typeface = android.graphics.Typeface.SERIF
            
            val tvItemAuthor = TextView(this)
            tvItemAuthor.text = "- $author"
            tvItemAuthor.textSize = 12f
            tvItemAuthor.setTextColor(Color.GRAY)
            tvItemAuthor.gravity = android.view.Gravity.END
            tvItemAuthor.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            itemLayout.addView(tvItemQuote)
            itemLayout.addView(tvItemAuthor)
            card.addView(itemLayout)
            
            // Interaction: Restore to main view
            card.setOnClickListener {
                updateUI(quote, author)
                Toast.makeText(this, "Quote dipulihkan ke atas", Toast.LENGTH_SHORT).show()
                // Don't scroll up automatically, let user decide
            }

            historyContainer.addView(card)
        }
    }
}