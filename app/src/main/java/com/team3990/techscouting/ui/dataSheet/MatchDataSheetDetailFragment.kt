package com.team3990.techscouting.ui.dataSheet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.team3990.techscouting.R
import com.team3990.techscouting.databinding.FragmentMatchDataSheetDetailBinding
import com.team3990.techscouting.factory.viewmodel.MatchDataSheetDetailVMFactory
import com.team3990.techscouting.model.MatchData

class MatchDataSheetDetailFragment : Fragment() {

    /** Properties */

    private val viewModel: MatchDataSheetDetailViewModel by lazy {
        val selectedDataSheet = arguments?.getSerializable("selectedDataSheet") as MatchData

        // Get an instance of the Match Data Sheet Detial VM Factory.
        val matchDataSheetVMFactory = MatchDataSheetDetailVMFactory(resources, selectedDataSheet)

        ViewModelProviders.of(this, matchDataSheetVMFactory).get(MatchDataSheetDetailViewModel::class.java)
    }

    /** Fragment's lifecycle */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMatchDataSheetDetailBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_match_data_sheet_detail, container, false)

        // Initialize the binding's view model property.
        binding.viewmodel = viewModel

        return binding.root
    }

}
