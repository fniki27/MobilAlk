package com.example.mobilalkproject

object QuestionList {

    fun getQuestion() : ArrayList<Question>{

        val questionsList = ArrayList<Question>()

        val que1 = Question(1,
            R.drawable.argentina,
            "Argentina",
            "Australia",
            "Armenia", "Uruguay",
            1)

        val que2 = Question(2,
            R.drawable.albania,
            "China",
            "Morocco",
            "Albania", "Poland",
            3)

        val que3 = Question(3,
            R.drawable.australia,
            "United Kingdom",
            "USA",
            "Australia", "New Zealand",
            3)

        val que4 = Question(4,
            R.drawable.belgium,
            "Germany",
            "Belgium",
            "Luxembourg", "Netherlands",
            2)


        val que5 = Question(5,
            R.drawable.czechia,
            "Latvia",
            "Indonesia",
            "South Africa", "Czechia",
            4)

        val que6 = Question(6,
            R.drawable.thailand,
            "Austria",
            "Nepal",
            "Thailand", "Bulgaria",
            3)

        val que7 = Question(7,
            R.drawable.mexico,
            "Mexico",
            "Spain",
            "Italy", "Vietnam",
            1)

        val que8 = Question(8,
            R.drawable.norway,
            "Norway",
            "Denmark",
            "Finland", "Sweden",
            1)

        val que9 = Question(9,
            R.drawable.philippines,
            "Cyprus",
            "Czechia",
            "Philippines", "Ecuador",
            3)

        val que10 = Question(10,
            R.drawable.southafrica,
            "Egypt",
            "South Africa",
            "Sudan", "Thailand",
            2)


        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)
        questionsList.add(que9)
        questionsList.add(que10)

        return questionsList
    }
}