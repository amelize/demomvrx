package ru.sebely.demoapplication.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.mocking.MockableMavericksView
import ru.sebely.demoapplication.R
import ru.sebely.demoapplication.databinding.MenuFragmentBinding


abstract class BaseFragment<S : ViewDataBinding>(@LayoutRes val containerLayoutId: Int = 0) : Fragment(), MockableMavericksView {
    protected lateinit var binding : S

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerMockPrinter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<S>(inflater, containerLayoutId, container, false)

        registerMockPrinter()

        return binding.root
    }

}