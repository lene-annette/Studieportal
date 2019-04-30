package dk.nytmodultest.studieportal.ui.activities

//import dk.nytmodultest.studieportal.domain.commands.RequestStudentCommand
import android.os.AsyncTask
import kotlinx.android.synthetic.main.activity_listening.*
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import dk.nytmodultest.studieportal.R
import dk.nytmodultest.studieportal.domain.commands.RequestMultiChoiceCommand
import java.lang.Exception

class Listening : AppCompatActivity(){

    private inner class MyTask: AsyncTask<String, Int, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            var i = 0
            while(i<=10){
                try{
                    Thread.sleep(1000)
                    publishProgress(i)
                    i++
                }catch(e:Exception){
                    return(e.localizedMessage)
                }
            }
            return "Button Pressed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            val counter = values.get(0)
            act_textcontent.text = "counter = ${counter}"
        }

        override fun onPostExecute(result: String?) {
            //super.onPostExecute(result)
            act_textcontent.text = "${result}"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listening)
        //for at få indhold:
        //val task = MyTask().execute()
        MyTask().execute()



        /*
        doAsync(){
            val result = RequestMultiChoiceCommand().execute()
            uiThread{
                act_textcontent.text = "${result}"
            }
        }
        */
    }




}