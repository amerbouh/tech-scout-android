package com.team3990.techscouting.views.dataSheet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team3990.techscouting.R

class SharedDataSheetFragment : Fragment() {

    companion object {
        fun newInstance() = SharedDataSheetFragment()
    }

    private lateinit var viewModel: SharedDataSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shared_data_sheet_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SharedDataSheetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
