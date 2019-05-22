package dk.nytmodultest.studieportal.ui.activities

import android.content.Intent
import android.media.MediaPlayer
import dk.nytmodultest.studieportal.domain.model.*
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import com.google.gson.Gson
import dk.nytmodultest.studieportal.Config
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestExerciseCommand
import dk.nytmodultest.studieportal.domain.commands.SubmitAnswersCommand
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.widget.*


class Listening : AppCompatActivity(){
    private val audio_url = "${Config.BACKEND}audio/lytning_2.mp3"


    private var barTimeMillis = 0
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar
    private lateinit var result: Exercise

    private val mSeekbarUpdateHandler = Handler()
    private val mUpdateSeekbar = object : Runnable {
        override fun run() {
            seekBar.setProgress(mediaPlayer.getCurrentPosition())
            mSeekbarUpdateHandler.postDelayed(this, 10)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)

        doAsync{
            result = RequestExerciseCommand("listening",ProfileActivity.ONLINE_USER,"multiple choice",1).execute()
            uiThread {
                activity_listening_instruction.text = result.studentInstructions
                val recyclerView = findViewById<RecyclerView>(R.id.activity_listening_recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this@Listening,LinearLayout.VERTICAL, false)

                val questions = ArrayList<Question>()
                for(q in result.questions){
                    questions.add(q)
                }



                val adapter = ListeningAdapter(questions, this@Listening)
                recyclerView.adapter = adapter

                activity_listening_confirmBtn.setOnClickListener{
                    val listOfClicks = (recyclerView.adapter as ListeningAdapter).answers
                    d("Lene","Listening list of clicks (answers?): $listOfClicks")

                    doAsync{
                        SubmitAnswersCommand(ProfileActivity.ONLINE_USER,questions,listOfClicks).execute()
                        uiThread {
                            Toast.makeText(this@Listening,"Your answers have been submitted",Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }

                    val gson = Gson()
                    val resultToString = gson.toJson(result)
                    val resultIntent = Intent(this@Listening, Resultpage::class.java)
                    resultIntent.putExtra("result", resultToString)
                    resultIntent.putExtra("listOfClicks", listOfClicks)
                    startActivity(resultIntent)

                }

                activity_listening_transcript.setOnClickListener{

                    val transcriptField = activity_listening_transText
                    if (transcriptField.text.length == 0) {
                        transcriptField.text = result.mediaText
                    } else {
                        transcriptField.text = ""
                    }
                }

                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(audio_url)
                mediaPlayer.prepareAsync()

                seekBar = findViewById<SeekBar>(R.id.seekBar)

                mediaPlayer.setOnPreparedListener(MediaPlayer.OnPreparedListener {
                    seekBar.setMax(mediaPlayer.getDuration())
                    findViewById<TextView>(R.id.seekBarStart).text = displayTime(0)
                    findViewById<TextView>(R.id.seekBarEnd).text = displayTime(mediaPlayer.duration)

                })


                seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                        barTimeMillis = mediaPlayer.currentPosition
                        if (fromUser){
                            mediaPlayer.seekTo(progress)
                        }
                        findViewById<TextView>(R.id.seekBarStart).text = displayTime(barTimeMillis)
                        if (mediaPlayer.isPlaying){
                            play_audiobtn.setImageResource(R.drawable.media_pause_sml)
                        }else {
                            play_audiobtn.setImageResource(R.drawable.media_play_sml)
                        }
                        if ((mediaPlayer.duration-progress)==0) {
                            play_audiobtn.setImageResource(R.drawable.media_play_sml)
                            mediaPlayer.seekTo(0)
                        }

                    }
                    override fun onStartTrackingTouch(seekBar: SeekBar) {
                        // Write code to perform some action when touch is started.
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar) {

                    }
                })


                play_audiobtn.setOnClickListener{

                    if (!mediaPlayer.isPlaying){
                        mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);
                        mediaPlayer.start()

                    }else{
                        mediaPlayer.pause()
                        play_audiobtn.setImageResource(R.drawable.media_play_sml)

                    }

                }

                repeat_audiobtn.setOnClickListener{
                    mediaPlayer.seekTo(0)
                    mediaPlayer.start()
                }

                back_audiobtn.setOnClickListener{
                    mediaPlayer.seekTo(barTimeMillis-10000)
                }

                fwrd_audiobtn.setOnClickListener{
                    mediaPlayer.seekTo(barTimeMillis+10000)
                }

            }

        }
        }
    fun displayTime(millis: Int): String {
        var minutes = (millis / 1000 / 60).toString()
        var seconds = (millis / 1000 % 60).toString()
        if (seconds.length < 2){
            seconds = "0"+seconds
        }
        return "${minutes}:${seconds}"
    }
    }

