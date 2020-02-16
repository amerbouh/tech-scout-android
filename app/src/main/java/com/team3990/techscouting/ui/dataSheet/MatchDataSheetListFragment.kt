package com.team3990.techscouting.ui.dataSheet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.team3990.techscouting.App
import com.team3990.techscouting.R
import com.team3990.techscouting.common.adapter.DataSheetAdapter
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.factory.MatchDataSheetListVMFactory
import kotlinx.android.synthetic.main.fragment_data_sheet_list.*

class MatchDataSheetListFragment : Fragment() {

    /** Properties */

    private lateinit var adapter: DataSheetAdapter
    private val viewModel: MatchDataSheetListViewModel by lazy {
        val dataSheetRepository = App.repositoryFactory.provideDataSheetRepository(
            App.database.matchDataDao()
        )

        // Get an instance of the factory used to create Match Data Sheet List VMs.
        val matchDataSheetListVMFactory = MatchDataSheetListVMFactory(dataSheetRepository)

        ViewModelProviders.of(this, matchDataSheetListVMFactory).get(MatchDataSheetListViewModel::class.java)
    }

    /** Fragment's lifecycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the adapter instance.
        adapter = DataSheetAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_data_sheet_list, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Setup the recycler view displaying the data sheet.
        setupRecyclerView()

        // Prepare the view to enter the loading state.
        onPrepareForLoading()

        // Start observing data sheets.
        viewModel.dataSheets.observe(viewLifecycleOwner, Observer {
            if (it.size < 0) onDataSheetLoaded(it)
            else onEmptyDataSet()
        })
    }

   /** Methods */

    private fun setupRecyclerView() {
       val linearLayoutManager = LinearLayoutManager(requireContext())
       val dividerItemDecoration = DividerItemDecoration(dataSheetRecyclerView.context, linearLayoutManager.orientation)

       // Setup the recycler view.
       dataSheetRecyclerView.apply {
           adapter = this@MatchDataSheetListFragment.adapter
           layoutManager = linearLayoutManager
           addItemDecoration(dividerItemDecoration)
       }
   }

    private fun onEmptyDataSet() {
        dataSheetRecyclerView.visibility = View.INVISIBLE
        progressBar.visibility = View.INVISIBLE
        emptyDataSetLinearLayout.visibility = View.VISIBLE
    }

    private fun onPrepareForLoading() {
        dataSheetRecyclerView.visibility = View.INVISIBLE
        emptyDataSetLinearLayout.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun onDataSheetLoaded(dataSheet: Array<DataSheet>) {
        progressBar.visibility = View.GONE
        emptyDataSetLinearLayout.visibility = View.GONE
        dataSheetRecyclerView.visibility = View.VISIBLE

        // Pass the loaded data sheets to the recycler view's adapter.
        adapter.setDataSheets(dataSheet)
    }

}
