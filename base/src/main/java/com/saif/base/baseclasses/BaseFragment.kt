package com.saif.base.baseclasses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    lateinit var mViewDataBinding: T

//    lateinit var sharedViewModel: SharedViewModel

    private var mActivity: BaseActivity<*>? = null

    abstract fun layoutID(): Int
    abstract fun initializeComponents()
    abstract fun setObservers()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layoutID(), null, false)
        subscribeToNetworkLiveData()
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToShareLiveData()
        initializeComponents()
        setObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>)
            this.mActivity = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    open fun subscribeToShareLiveData() {
    }

    open fun subscribeToNetworkLiveData() {
        // All Network Tasks
    }





//    protected open fun showError(message: String) {
//        showToast(message,R.drawable.ic_baseline_error_outline_24)
//    }
//
//    protected open fun showSuccessToast(message: String) {
//        showToast(message, R.drawable.ic_baseline_check_circle_outline_24)
//    }



}