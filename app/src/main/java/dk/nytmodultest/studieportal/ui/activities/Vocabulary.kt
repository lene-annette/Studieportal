package dk.nytmodultest.studieportal.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import dk.nytmodultest.studieportal.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread
import dk.nytmodultest.studieportal.domain.commands.RequestWordsCommand
import dk.nytmodultest.studieportal.domain.commands.SubmitKnownWordCommand
import dk.nytmodultest.studieportal.domain.model.Word
import kotlinx.android.synthetic.main.activity_vocabulary.*

class Vocabulary : AppCompatActivity() {
    var words: MutableList<Word> = mutableListOf()
    lateinit var currentWord: Word
    private var knowCount = 0
    private var dontknowCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        getWords()

        vocab_know.setOnClickListener{
             actionOnWord(true)
        }

        vocab_dontknow.setOnClickListener{
              actionOnWord(false)
        }

        vocab_english.setOnClickListener{
             vocab_english.text = currentWord.english
        }
    }

    private fun getWords(){
        doAsync{
            words = RequestWordsCommand(ProfileActivity.ONLINE_USER, 5).execute() as MutableList<Word>
            uiThread{
                currentWord = words[0]
                vocab_danish.text = currentWord.word
                vocab_english.text = "Translate"
            }

        }
    }

    private fun actionOnWord(knownWord: Boolean){
        if (words.isNullOrEmpty()) {
            longToast("Nothing to practice at this moment. Try again later")
        } else {
            if (knownWord) {
                doAsync{
                    SubmitKnownWordCommand(ProfileActivity.ONLINE_USER,currentWord.id).execute()
                }
                knowCount += 1
                vocab_knowCount.text = knowCount.toString()
            }else {
                dontknowCount += 1
                vocab_dontKCount.text = dontknowCount.toString()
            }

            var temp = words.drop(1)
            if (temp.isNullOrEmpty()) {
                getWords()
            } else {
                words = temp as MutableList<Word>
                currentWord = words[0]
                vocab_danish.text = this.currentWord.word
                vocab_english.text = "Translate"
            }
        }
    }
}
