package com.rangga.myapplication

object IndonesianQuotes {
    val list = listOf(
        "Ilmu itu bukan yang dihafal, tetapi yang memberi manfaat. - Imam Syafi'i",
        "Hati yang paling tenang adalah hati yang dipenuhi dengan rasa syukur. - Ali bin Abi Thalib",
        "Balas dendam terbaik adalah dengan menjadikan dirimu menjadi lebih baik. - Ali bin Abi Thalib",
        "Orang yang paling berakal adalah orang yang paling banyak mengingat mati dan paling baik persiapannya. - Hasan al-Bashri",
        "Kesabaran itu ada dua macam: sabar atas sesuatu yang tidak kau inginkan dan sabar menahan diri dari sesuatu yang kau inginkan. - Ali bin Abi Thalib",
        "Jangan jelaskan dirimu kepada siapa pun, karena yang menyukaimu tidak butuh itu, dan yang membencimu tidak percaya itu. - Ali bin Abi Thalib",
        "Barang siapa yang tidak mencicipi pahitnya menuntut ilmu meski sesaat, ia akan menelan hinanya kebodohan sepanjang hidupnya. - Imam Syafi'i",
        "Dunia ini hanya tiga hari: Kemarin yang telah pergi, besok yang mungkin tak kau temui, dan hari ini maka beramallah. - Hasan al-Bashri",
        "Semakin banyak ilmu yang kau miliki, semakin besar pula rasa takutmu kepada Allah. - Abu Bakar Ash-Shiddiq",
        "Kekayaan yang paling utama adalah kekayaan jiwa. - Umar bin Khattab",
        "Jangan biarkan kesulitan membuatmu gelisah, karena hanya di malam yang gelaplah bintang-bintang tampak bersinar lebih terang. - Ali bin Abi Thalib",
        "Berpikirlah sebelum bertindak, karena dengan begitu kau tidak akan menyesal. - Umar bin Khattab",
        "Keyakinan adalah pemenang yang tenang. - Buya Hamka",
        "Penderitaan jiwa lebih buruk daripada penderitaan badan. - Ibnu Sina",
        "Ilmu tanpa amal adalah kegilaan, dan amal tanpa ilmu adalah kesia-siaan. - Imam Al-Ghazali",
        "Tugas kita bukanlah untuk berhasil. Tugas kita adalah untuk mencoba, karena di dalam mencoba itulah kita menemukan kesempatan untuk berhasil. - Buya Hamka",
        "Hiduplah seolah-olah kamu mati besok. Belajarlah seolah-olah kamu hidup selamanya. - Mahatma Gandhi (terinspirasi nilai universal, namun sering diasosiasikan dengan nilai Islam)",
        "Jangan berduka, apa pun yang hilang darimu akan kembali dalam bentuk lain. - Jalaluddin Rumi",
        "Kecantikan yang abadi adalah kecantikan adab dan ketinggian ilmu seseorang. - Buya Hamka",
        "Sabar memiliki dua sisi: sisi pertama adalah sabar, dan sisi lainnya adalah syukur kepada Allah. - Ibnu Qayyim Al-Jauziyyah",
        "Kesuksesan sejati adalah ketika engkau mampu bermanfaat bagi orang lain dengan apa yang kau miliki. - Ali bin Abi Thalib",
        "Barangsiapa bersungguh-sungguh, maka dia akan berhasil (Man Jadda Wajada). - Pepatah Arab",
        "Keberhasilan tidak diukur dari posisi yang dicapai seseorang, tapi dari hambatan yang berhasil ia atasi. - Buya Hamka",
        "Orang yang sukses adalah mereka yang mampu mengatur waktunya dengan bijak sebelum waktu itu habis. - Hasan al-Bashri",
        "Rezeki itu datangnya dari Allah, tapi usaha untuk menjemputnya adalah tugas kita sebagai hamba. - Ibnu Qayyim Al-Jauziyyah",
        "Jangan takut gagal dalam perjuangan, karena kegagalan dalam kebaikan lebih mulia daripada kemenangan dalam kejahatan. - Buya Hamka",
        "Ketenangan dalam bekerja adalah separuh dari keberhasilan. - Ibnu Sina",
        "Siapa yang menanam, dia yang akan menuai hasilnya. - Pepatah Arab",
        "Tinggalkanlah kesenangan yang menghalangimu dari mencapai tujuan yang lebih besar. - Ali bin Abi Thalib"
    )

    fun getRandomQuote(): Pair<String, String> {
        val fullQuote = list.random()
        val parts = fullQuote.split(" - ")
        return if (parts.size > 1) {
            parts[0] to parts[1]
        } else {
            fullQuote to "Unknown"
        }
    }
}
