package com.team3990.techscouting.ui.dataSheet

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.team3990.techscouting.App
import com.team3990.techscouting.common.adapter.DataSheetItemLookupDetails
import com.team3990.techscouting.R
import com.team3990.techscouting.common.adapter.DataSheetAdapter
import com.team3990.techscouting.common.interfaces.DataSheet
import com.team3990.techscouting.factory.viewmodel.MatchDataSheetListVMFactory
import kotlinx.android.synthetic.main.fragment_data_sheet_list.*

class MatchDataSheetListFragment : Fragment() {

    /** Properties */

    private var actionMode: ActionMode? = null
    private lateinit var adapter: DataSheetAdapter

    private val viewModel: MatchDataSheetListViewModel by lazy {
        val dataSheetRepository = App.repositoryFactory.provideDataSheetRepository(
            App.database.matchDataDao()
        )

        // Get an instance of the factory used to create Match Data Sheet List VMs.
        val matchDataSheetListVMFactory = MatchDataSheetListVMFactory(
                dataSheetRepository
        )

        ViewModelProviders.of(this, matchDataSheetListVMFactory).get(MatchDataSheetListViewModel::class.java)
    }

    private val activatedListener: OnItemActivatedListener<Long> = OnItemActivatedListener { item, e ->
        val selectedDataSheet = adapter.getDataSheet(item.position)

        // Create a bundle instance containing the selected data sheet.
        val bundle = bundleOf("selectedDataSheet" to selectedDataSheet)

        // Navigate to the Match Data Sheet Detail Fragment.
        findNavController().navigate(R.id.action_dataSheetListFragment_to_matchDataSheetDetailFragment, bundle)

        true
    }
    private val selectionObserver: SelectionTracker.SelectionObserver<Long> = object : SelectionTracker.SelectionObserver<Long>() {
        override fun onSelectionChanged() {
            super.onSelectionChanged()

            // Call the method handling data sheets selection.
            onDataSheetSelectionChanged()
        }
    }
    private val actionModeCallback: ActionMode.Callback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            val menuInflater = mode.menuInflater

            // Inflate the appropriate menu for the action mode.
            menuInflater.inflate(R.menu.menu_context, menu)

            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean = when (item.itemId) {
            R.id.deleteMenuItem -> {
                mode.finish()
                true
            }
            R.id.shareMenuItem -> {
                mode.finish()
                true
            }
            else -> false
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            actionMode = null
            adapter.tracker?.clearSelection()
        }
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

        // Setup the selection tracker.
        setupSelectionTracker()

        // Prepare the view to enter the loading state.
        onPrepareForLoading()

        // Start observing data sheets.
        viewModel.dataSheets.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) onDataSheetLoaded(it)
            else onEmptyDataSet()
        })
    }

    override fun onPause() {
        super.onPause()

        // Exit the action mode, if applicable.
        actionMode?.finish()
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

    private fun setupSelectionTracker() {
        val selectionTracker = SelectionTracker.Builder<Long>(
            "fragment_match_data_sheet_list_selection",
            dataSheetRecyclerView,
            StableIdKeyProvider(dataSheetRecyclerView),
            DataSheetItemLookupDetails(dataSheetRecyclerView),
            StorageStrategy.createLongStorage()
        ).withOnItemActivatedListener(activatedListener
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()
        ).build()

        // Initialize the observer property of the created selection tracker
        // instance.
        selectionTracker.addObserver(selectionObserver)

        // Initialize the tracker property of the adapter of the recycler view.
        adapter.tracker = selectionTracker
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

    private fun onDataSheetSelectionChanged() {
        val selectedItemsCount = adapter.tracker!!.selection!!.size()

        // Initialize the action mode, if applicable.
        if (actionMode == null) {
            actionMode = activity?.startActionMode(actionModeCallback)
        }

        // Enter the action mode, if applicable.
        if (selectedItemsCount > 0) {
            actionMode?.title = resources.getQuantityString(R.plurals.selection_count, selectedItemsCount, selectedItemsCount)
        } else {
            actionMode?.finish()
        }
    }

}
