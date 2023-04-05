package com.montagat.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.montagat.R
import com.montagat.adapter.MontagAdapter
import com.montagat.databinding.FragmentHomeBinding
import com.montagat.intent.MontagIntent
import com.montagat.interfaces.DetailsMontagat
import com.montagat.model.MontagModel
import com.montagat.view_state.MontagViewState
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), DetailsMontagat {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var montagAdapter: MontagAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        getChannelFromViewModel()
        getStateFromViewModel()
    }


    private fun initAdapter() {
        montagAdapter = MontagAdapter(this@HomeFragment)
    }

    private fun getChannelFromViewModel() {
        lifecycleScope.launch {
            viewModel.montagatChannel.send(MontagIntent.fetchMontagat)
        }
    }

    private fun getStateFromViewModel() {
        lifecycleScope.launch  {
            viewModel.state.collect {
                when (it) {
                    is MontagViewState.Idle -> {
                        binding.txtName.visibility = View.VISIBLE
                        binding.txtName.text = "Idle"
                        binding.rVMontagat.visibility = View.GONE
                        binding.loadingMontagat.startShimmer()
                        binding.loadingMontagat.visibility = View.VISIBLE

                    }

                    is MontagViewState.Loading -> {
                        binding.loadingMontagat.startShimmer()
                        binding.loadingMontagat.visibility = View.VISIBLE
                        binding.txtName.visibility = View.GONE
                        binding.rVMontagat.visibility = View.GONE
                    }

                    is MontagViewState.getMontag -> {
                        binding.loadingMontagat.stopShimmer()
                        binding.loadingMontagat.visibility = View.GONE
                        binding.txtName.visibility = View.GONE
                        binding.rVMontagat.visibility = View.VISIBLE
                        montagAdapter.differ.submitList(it.montag.toList())
                        binding.rVMontagat.apply {
                            this.adapter = montagAdapter
                            this.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                            this.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                        }
                    }

                    is MontagViewState.Error -> {
                        binding.loadingMontagat.startShimmer()
                        binding.loadingMontagat.visibility = View.VISIBLE
                        binding.txtName.visibility = View.GONE
                        binding.rVMontagat.visibility = View.GONE
                        binding.txtName.text = "Error"
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun clickedOnMontagatForDetails(montagModel: MontagModel) {
        val bundle = Bundle().apply {
            this.putSerializable("details", montagModel)
        }
        findNavController().navigate(R.id.action_homeFragment_to_montagatDetailsFragment, bundle)
    }


}