package com.team3990.techscouting.ui.dataSheet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team3990.techscouting.R

class MatchDataSheetDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MatchDataSheetDetailFragment()
    }

    private lateinit var viewModel: MatchDataSheetDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.match_data_sheet_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MatchDataSheetDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
