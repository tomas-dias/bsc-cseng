package com.example.kicksser

object Constants {

    const val TOTAL_QUESTIONS: String = "total-question"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val que1 = Question(
            1,
            "Quem é este jogador?",
            R.drawable.cr7guesser3,
            R.drawable.portugal,
            R.drawable.juventus,
            "Messi",
            "Neymar",
            "Cristiano Ronaldo",
            "Bruno Fernandes",
            3
        )

        val que2 = Question(
            2,
            "Quem é este jogador?",
            R.drawable.mbappe,
            R.drawable.franca,
            R.drawable.psg,
            "Benzema",
            "Nzonzi",
            "Danilo",
            "Mbappé",
            4
        )

        val que3 = Question(
            3,
            "Quem é este treinador?",
            R.drawable.fs,
            R.drawable.portugal,
            R.drawable.fp,
            "Fernando Santos",
            "Jorge Jesus",
            "José Mourinho",
            "Jesualdo Ferreira",
            1
        )

        val que4 = Question(
            4,
            "Quem é este jogador?",
            R.drawable.neuer,
            R.drawable.alemanha,
            R.drawable.bayern,
            "Neuer",
            "Ederson",
            "Oblak",
            "Courtois",
            1
        )

        val que5 = Question(
            5,
            "Quem é este jogador?",
            R.drawable.vertonghen,
            R.drawable.belgica,
            R.drawable.benfica,
            "Weigl",
            "Waldschmidt",
            "Vertonghen",
            "Hazard",
            3
        )

        val que6 = Question(
            6,
            "Quem é este jogador?",
            R.drawable.modric,
            R.drawable.croacia,
            R.drawable.realmadrid,
            "Rakitic",
            "Modric",
            "Seferovic",
            "Kovacic",
            2
        )

        val que7 = Question(
            7,
            "Quem é este jogador?",
            R.drawable.messi,
            R.drawable.argentina,
            R.drawable.barcelona,
            "Aguero",
            "Griezmann",
            "Neymar",
            "Messi",
            4
        )

        val que8 = Question(
            8,
            "Que equipa é esta?",
            R.drawable.benfica2,
            R.drawable.portugal,
            R.drawable.liganos,
            "Sporting",
            "Porto",
            "Braga",
            "Benfica",
            4
        )

        val que9 = Question(
            9,
            "Que equipa é esta?",
            R.drawable.manutd,
            R.drawable.inglaterra,
            R.drawable.premierleague,
            "Manchester City",
            "Manchester United",
            "Wolverhampton",
            "Chelsea",
            2
        )

        val que10 = Question(
            10,
            "Que seleção é esta?",
            R.drawable.portugal2,
            R.drawable.europe,
            R.drawable.uefalogo,
            "Inglaterra",
            "Espanha",
            "Portugal",
            "França",
            3
        )



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