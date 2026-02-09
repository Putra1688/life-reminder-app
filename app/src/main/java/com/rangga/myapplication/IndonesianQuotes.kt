package com.rangga.myapplication

data class QuoteModel(val quote: String, val author: String)

object IndonesianQuotes {
    private val list = listOf(
        QuoteModel("Ilmu itu bukan yang dihafal, tetapi yang memberi manfaat.", "Imam Syafi'i"),
        QuoteModel("Hati yang paling tenang adalah hati yang dipenuhi dengan rasa syukur.", "Ali bin Abi Thalib"),
        QuoteModel("Balas dendam terbaik adalah dengan menjadikan dirimu menjadi lebih baik.", "Ali bin Abi Thalib"),
        QuoteModel("Orang yang paling berakal adalah orang yang paling banyak mengingat mati.", "Hasan al-Bashri"),
        QuoteModel("Kesabaran itu ada dua macam: sabar atas sesuatu yang tidak kau inginkan dan sabar menahan diri.", "Ali bin Abi Thalib"),
        QuoteModel("Jangan jelaskan dirimu kepada siapa pun, karena yang menyukaimu tidak butuh itu.", "Ali bin Abi Thalib"),
        QuoteModel("Barang siapa yang tidak mencicipi pahitnya menuntut ilmu, ia akan menelan hinanya kebodohan.", "Imam Syafi'i"),
        QuoteModel("Dunia ini hanya tiga hari: Kemarin, besok yang mungkin tak kau temui, dan hari ini.", "Hasan al-Bashri"),
        QuoteModel("Semakin banyak ilmu yang kau miliki, semakin besar pula rasa takutmu kepada Allah.", "Abu Bakar Ash-Shiddiq"),
        QuoteModel("Kekayaan yang paling utama adalah kekayaan jiwa.", "Umar bin Khattab"),
        QuoteModel("Jangan biarkan kesulitan membuatmu gelisah, karena hanya di malam yang gelaplah bintang bersinar.", "Ali bin Abi Thalib"),
        QuoteModel("Berpikirlah sebelum bertindak, karena dengan begitu kau tidak akan menyesal.", "Umar bin Khattab"),
        QuoteModel("Keyakinan adalah pemenang yang tenang.", "Buya Hamka"),
        QuoteModel("Penderitaan jiwa lebih buruk daripada penderitaan badan.", "Ibnu Sina"),
        QuoteModel("Ilmu tanpa amal adalah kegilaan, dan amal tanpa ilmu adalah kesia-siaan.", "Imam Al-Ghazali"),
        QuoteModel("Tugas kita bukanlah untuk berhasil. Tugas kita adalah untuk mencoba.", "Buya Hamka"),
        QuoteModel("Hiduplah seolah-olah kamu mati besok. Belajarlah seolah-olah kamu hidup selamanya.", "Mahatma Gandhi"),
        QuoteModel("Jangan berduka, apa pun yang hilang darimu akan kembali dalam bentuk lain.", "Jalaluddin Rumi"),
        QuoteModel("Kecantikan yang abadi adalah kecantikan adab dan ketinggian ilmu seseorang.", "Buya Hamka"),
        QuoteModel("Sabar memiliki dua sisi: sisi pertama adalah sabar, dan sisi lainnya adalah syukur.", "Ibnu Qayyim Al-Jauziyyah"),
        QuoteModel("Kesuksesan sejati adalah ketika engkau mampu bermanfaat bagi orang lain.", "Ali bin Abi Thalib"),
        QuoteModel("Barangsiapa bersungguh-sungguh, maka dia akan berhasil.", "Man Jadda Wajada"),
        QuoteModel("Keberhasilan tidak diukur dari posisi, tapi dari hambatan yang berhasil ia atasi.", "Buya Hamka"),
        QuoteModel("Orang yang sukses adalah mereka yang mampu mengatur waktunya dengan bijak.", "Hasan al-Bashri"),
        QuoteModel("Rezeki itu datangnya dari Allah, tapi usaha untuk menjemputnya adalah tugas kita.", "Ibnu Qayyim Al-Jauziyyah"),
        QuoteModel("Kegagalan dalam kebaikan lebih mulia daripada kemenangan dalam kejahatan.", "Buya Hamka"),
        QuoteModel("Ketenangan dalam bekerja adalah separuh dari keberhasilan.", "Ibnu Sina"),
        QuoteModel("Siapa yang menanam, dia yang akan menuai hasilnya.", "Pepatah Arab"),
        QuoteModel("Tinggalkanlah kesenangan yang menghalangimu dari mencapai tujuan yang lebih besar.", "Ali bin Abi Thalib")
    )

    fun getRandomQuote(): Pair<String, String> {
        val item = list.random()
        return item.quote to item.author
    }
}
