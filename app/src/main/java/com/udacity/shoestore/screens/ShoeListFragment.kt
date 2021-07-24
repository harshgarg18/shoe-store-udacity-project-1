package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeStoreViewModel

class ShoeListFragment : Fragment() {

    private lateinit var binding: ShoeListFragmentBinding
    private val viewModel by activityViewModels<ShoeStoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_list_fragment, container, false
        )

        viewModel.shoeList.observe(viewLifecycleOwner, {
            it.forEach { shoe ->
                addShoeLayout(shoe)
            }
        })

        binding.addShoeButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListToShoeDetails())
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addShoeLayout(shoe: Shoe) {
        val shoeItemBinding: ShoeItemBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.shoe_item, binding.shoeListLinearLayout, false
        )
        shoeItemBinding.shoe = shoe

        binding.shoeListLinearLayout.addView(shoeItemBinding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }
}