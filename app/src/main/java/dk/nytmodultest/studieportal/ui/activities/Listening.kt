package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.Toast
import com.google.gson.Gson
import dk.nytmodultest.studieportal.domain.model.Exercise
import dk.nytmodultest.studieportal.domain.model.Questions
import kotlinx.android.synthetic.main.activity_listening.*
import java.net.URL


class Listening : AppCompatActivity(){

    private var barPosition = 0
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false

    private val mSeekbarUpdateHandler = Handler()
    private val mUpdateSeekbar = object : Runnable {
        override fun run() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition())
            mSeekbarUpdateHandler.postDelayed(this, 10)
        }
    }

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

            //var mp3url: String = "http://10.0.2.2:8000/audio/01-sæ1_du3mo2_track1.mp3"//"localhost:8000/audio/" + myresult.media
            var mp3url: String = "http://s1download-universal-soundbank.com/mp3/sounds/9333.mp3"

            //mediaPlayer.setAudioAttributes(AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())

            mediaPlayer = MediaPlayer()
            mediaPlayer.setDataSource(mp3url)
            mediaPlayer.prepare()

            //mSeekBar.setMax(mFileDuration); // where mFileDuration is mMediaPlayer.getDuration();




            seekBar = findViewById<SeekBar>(dk.nytmodultest.studieportal.R.id.seekBar)
            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    //skriv kode
                    barPosition = progress
                    if (fromUser){
                        mediaPlayer.seekTo(progress)


                    }
                    if (mediaPlayer.getDuration() / 1000 == mediaPlayer.getCurrentPosition() / 1000) {

                        return;
                    }


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
            seekBar.setMax(mediaPlayer.getDuration())
            //mSeekbarUpdateHandler.removeCallbacks(runnable);




            play_audiobtn.setOnClickListener{

                if (!mediaPlayer.isPlaying){
                    Toast.makeText(this@Listening,"lyd1",Toast.LENGTH_SHORT).show()
                    //mediaPlayer.prepare()
                    Toast.makeText(this@Listening,"lyd2",Toast.LENGTH_SHORT).show()
                    mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);
                    mediaPlayer.start()
                    Toast.makeText(this@Listening,"lyd3",Toast.LENGTH_SHORT).show()
                }else{
                    mediaPlayer.seekTo(0)
                    //Toast.makeText(this@Listening,"lyd1",Toast.LENGTH_SHORT).show()
                    //mediaPlayer.prepare()
                    //Toast.makeText(this@Listening,"lyd2",Toast.LENGTH_SHORT).show()
                    //mediaPlayer.start()
                    //Toast.makeText(this@Listening,"lyd3",Toast.LENGTH_SHORT).show()

                }
                //on pause:
                //
                //mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);


                /*
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
                */

            }



            //****************************************************************************



            val recyclerView = findViewById(dk.nytmodultest.studieportal.R.id.activity_listening_recyclerView) as RecyclerView
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
        setContentView(dk.nytmodultest.studieportal.R.layout.activity_listening)


        //act_textcontent.setMovementMethod(ScrollingMovementMethod());
        GetListeningExercise().execute()



    }




}