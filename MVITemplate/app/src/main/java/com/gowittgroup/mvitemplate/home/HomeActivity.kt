package com.gowittgroup.mvitemplate.home

import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.gowittgroup.commonui.base.BaseActivity
import com.gowittgroup.commonui.base.getMessage
import com.gowittgroup.commonui.base.runIfTrue
import com.gowittgroup.mvitemplate.databinding.ActivityMainBinding

class HomeActivity: BaseActivity<HomeIntent, HomeAction, HomeState, HomeViewModel>(HomeViewModel::class.java) {

    private val mAdapter = CharactersAdapter()
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun initUi() {
       _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.homeListCharacters.adapter  = mAdapter
    }

    override fun initData() {
        dispatchIntent(HomeIntent.LoadAllCharacters)
    }

    override fun initEvent() {
        binding.homeSearchImage.setOnClickListener{
            binding.homeSearchText.text.isNullOrBlank().not().runIfTrue{
                dispatchIntent(HomeIntent.SearchCharacter(binding.homeSearchText.text.toString()))
            }
        }

        binding.homeSearchText.doOnTextChanged{
            text, _, _, _ ->
            text.isNullOrBlank()
                .and(mState is HomeState.ResultSearch)
                .runIfTrue { dispatchIntent(HomeIntent.ClearSearch) }
        }
    }

    override fun render(state: HomeState) {
        binding.homeProgress.isVisible = state is HomeState.Loading
        binding.homeMessage.isVisible = state is HomeState.Exception
        binding.homeListCharacters.isVisible = state is HomeState.ResultSearch || state is HomeState.ResultAllPersona

        when(state){
            is HomeState.ResultAllPersona -> {
                mAdapter.updateList(state.data)
            }

            is HomeState.ResultSearch -> {
                mAdapter.updateList(state.data)
            }

            is HomeState.Exception -> {
                binding.homeMessage.text = state.callErrors.getMessage(this)
            }

            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
