package com.team3990.techscouting.views.matchScouting

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team3990.techscouting.R

class MatchDataInputFragment : Fragment() {

    companion object {
        fun newInstance() = MatchDataInputFragment()
    }

    private lateinit var viewModel: MatchDataInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_data_input_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchDataInputViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
