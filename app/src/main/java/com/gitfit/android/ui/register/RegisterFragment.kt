package com.gitfit.android.ui.register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gitfit.android.R
import com.gitfit.android.databinding.FragmentRegisterBinding
import com.gitfit.android.utils.navigateWithoutComingBack
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerViewModel.userRegister.username = arguments!!.getString("username")!!
        registerViewModel.userRegister.githubToken = arguments!!.getString("githubToken")!!

        registerViewModel.openHomeActivityEvent.observe(this, Observer {
            openHomeFragment()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRegisterBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_register, container, false
            )
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    private fun openHomeFragment() {
        findNavController().navigateWithoutComingBack(R.id.nav_graph_main, R.id.nav_graph_auth)
    }

}