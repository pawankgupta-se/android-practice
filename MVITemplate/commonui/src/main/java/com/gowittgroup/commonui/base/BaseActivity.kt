package com.gowittgroup.commonui.base

import android.os.Bundle

abstract class BaseActivity<INTENT: ViewIntent, ACTION: ViewAction, STATE: ViewState, VM: BaseViewModel<INTENT, ACTION, STATE>>(private val modelClass: Class<VM>): RootBaseActivity(), IViewRenderer<STATE> {
    private lateinit var viewState: STATE
    val mState get() = viewState

    private val viewModel: VM by lazy {
        viewModelProvider(
            this.viewModelFactory,
            modelClass.kotlin
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        viewModel.state.observe(this){
            viewState = it
            render(it)
        }
        initData()
        initEvent()
    }

    abstract fun initUi()
    abstract fun initData()
    abstract fun initEvent()

    fun dispatchIntent(intent: INTENT){
        viewModel.dispatchIntent(intent)
    }
}
