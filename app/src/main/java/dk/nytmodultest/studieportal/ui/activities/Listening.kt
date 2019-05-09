package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import android.media.AudioAttributes
import android.media.MediaPlayer
import dk.nytmodultest.studieportal.domain.model.*
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import com.google.gson.Gson
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestMultiChoiceCommand
import java.net.URL
import kotlin.Exception

class Listening : AppCompatActivity(){

    private var barPosition = 0
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false

    private inner class GetListeningExercise: AsyncTask<String, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            val databaseGet: String
            //try{
            //use "10.0.2.2" instead of localhost (ottherwise you get an exception)
            databaseGet =  URL("http://10.0.2.2:8000/api/findQuestionsByExerciseId/1").readText()

            return databaseGet
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: String?) {
            //super.onPostExecute(result)
            val myresult = Gson().fromJson(result, Exercise::class.java)

            activity_listening_instruction.text = myresult.studentInstructions


            //****************************************************************************

            var mp3url: String = "localhost:8000/audio/" + myresult.media

            //mediaPlayer.setAudioAttributes(AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
            //mediaPlayer.setDataSource(mp3url)
            //mediaPlayer.prepare()
            //mediaPlayer.start()


            val seekBar = findViewById<SeekBar>(R.id.seekBar)
            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    //skriv kode
                    barPosition = progress
                }
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // Write code to perform some action when touch is started.
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Write code to perform some action when touch is stopped.
                    //Toast.makeText(this@Listening,"StopedTracking!: ${barPosition}",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@Listening,mp3url,Toast.LENGTH_SHORT).show()


                }
            })




            play_audiobtn.setOnClickListener{
                if(pause){
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    mediaPlayer.start()
                    pause = false
                    //Toast.makeText(this,"media playing",Toast.LENGTH_SHORT).show()
                }else{

                    //mediaPlayer = MediaPlayer.create(applicationContext,R.raw.school_bell)
                    mediaPlayer.start()
                    //Toast.makeText(this,"media playing",Toast.LENGTH_SHORT).show()
                }

            }



            //****************************************************************************



            val recyclerView = findViewById(R.id.activity_listening_recyclerView) as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this@Listening, LinearLayout.VERTICAL, false)

            val questions = ArrayList<Questions>()
            for (q in myresult.questions){
                questions.add(q)
            }
            val adapter = ListeningAdapter(questions, this@Listening)
            recyclerView.adapter = adapter



            activity_listening_confirmBtn.setOnClickListener{
                val minListeAfKlicks = (recyclerView.adapter as ListeningAdapter).mineSvar.toString()
                Toast.makeText(this@Listening,minListeAfKlicks,Toast.LENGTH_SHORT).show()
            }




        }



    }



    fun message(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)


        //act_textcontent.setMovementMethod(ScrollingMovementMethod());
        GetListeningExercise().execute()



    }




}