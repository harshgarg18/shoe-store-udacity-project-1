package com.udacity.shoestore.screens

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeStoreViewModel
import timber.log.Timber

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: ShoeDetailsFragmentBinding
    private val viewModel by activityViewModels<ShoeStoreViewModel>()
    private lateinit var shoe: Shoe

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_details_fragment,
            container,
            false
        )

        // Instantiate the Shoe Object with empty/initial values
        shoe = Shoe("", 0.0, "", "")

        binding.shoe = shoe
        binding.lifecycleOwner = viewLifecycleOwner

        binding.saveButton.setOnClickListener {
            saveShoe()
            navigateBack()
        }

        binding.cancelButton.setOnClickListener {
            navigateBack()
        }

        return binding.root
    }

    private fun saveShoe() {
        Timber.i("Adding Shoe: $shoe")
        viewModel.addShoeToList(shoe)
    }

    private fun navigateBack() {
        hideKeyboard()
        findNavController().navigateUp()
    }

    private fun hideKeyboard() {
        val inm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}