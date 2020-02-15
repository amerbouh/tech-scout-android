package com.team3990.techscouting.views.dataSheet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.team3990.techscouting.R

class DataSheetListFragment : Fragment() {

    companion object {
        fun newInstance() = DataSheetListFragment()
    }

    private lateinit var viewModel: DataSheetListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_sheet_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DataSheetListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
