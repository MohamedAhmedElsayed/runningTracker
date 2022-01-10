package com.thirdwayv.tracker.core.base.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import com.thirdwayv.tracker.R

abstract class BaseFragment<ViewBindingType : ViewBinding> : Fragment() {
    private var _binding: ViewBindingType? = null

    /**
     * Binding variable to be used for accessing views.
     * */


    protected val binding
        get() = requireNotNull(_binding)

    /**
     *Calls the abstract function to return the ViewBinding.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = setupViewBinding(inflater, container)
        return requireNotNull(_binding).root
    }

    /**
     * abstract function to get ViewBinding
     * */
    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): ViewBindingType

    /**
     * clear view binding
     * */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /**
     * use default navController method to navigate in the app and  attaching default nav options
     */
    fun navigateTo(action: NavDirections) {
        findNavController().navigate(action, getDefaultNavOptions())
    }

    /**
     * Method provides default navOptions that will be applied on all of the navigation
     */
    private fun getDefaultNavOptions() = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }
}