package dk.nytmodultest.studieportal.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dk.nytmodultest.studieportal.R


class TranscriptFrag: Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //return super.onCreateView(inflater, container, savedInstanceState)
        return inflater!!.inflate(R.layout.fragment_transcript, container, false)

    }
}