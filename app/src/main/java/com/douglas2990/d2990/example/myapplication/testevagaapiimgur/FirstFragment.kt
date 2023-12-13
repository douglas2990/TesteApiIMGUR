package com.douglas2990.d2990.example.myapplication.testevagaapiimgur

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.adapter.CatsAdapter
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.databinding.FragmentFirstBinding
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.screenstate.CatsScreenState
import com.douglas2990.d2990.example.myapplication.testevagaapiimgur.viewModel.CatsViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val viewModel: CatsViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cats()

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun cats(){
        viewModel.state.observe(viewLifecycleOwner,
            Observer { state ->
                binding.recyclerFirst.isVisible = state is CatsScreenState.Success

                when (state){
                    is CatsScreenState.Loading -> {}
                    is CatsScreenState.Success ->
                        binding.recyclerFirst.adapter = CatsAdapter(state.data)
                    is CatsScreenState.Error -> Toast.makeText(context, state.messageId, Toast.LENGTH_LONG).show()
                }

            })
    }
}