package com.udacity.shoestore.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding
import com.udacity.shoestore.models.ShoeStoreViewModel
import com.udacity.shoestore.models.User

/**
 * Fragment for Login Destination
 */
class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var user: User
    private val viewModel by activityViewModels<ShoeStoreViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false
        )

        user = User("", "")
        binding.user = user
        binding.lifecycleOwner = viewLifecycleOwner

        binding.loginButton.setOnClickListener {
            loginUser()
        }

        binding.registerButton.setOnClickListener {
            registerUser()
        }
        return binding.root
    }

    private fun registerUser() {
        hideKeyboard()
        if (ifEmpty()) {
            return
        }
        if (viewModel.registerUser(user)) {
            welcomeUser()
        } else {
            showErrorSnackBar(getString(R.string.register_failure))
        }
    }

    private fun loginUser() {
        hideKeyboard()
        if (ifEmpty()) {
            return
        }
        if (viewModel.loginUser(user)) {
            welcomeUser()
        } else {
            showErrorSnackBar(getString(R.string.login_failure))
        }
    }

    private fun ifEmpty() : Boolean {
        if (user.email == "" || user.password == "") {
            showErrorSnackBar(getString(R.string.empty_fields))
            return true
        }
        return false
    }

    private fun showErrorSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun welcomeUser() {
        findNavController().navigate(LoginFragmentDirections.actionLoginToWelcome())
    }

    private fun hideKeyboard() {
        val inm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}