package com.example.hiltsample.presentation

import androidx.lifecycle.ViewModel
import com.example.hiltsample.data.ProductRepository
import com.example.hiltsample.data.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * Important to annotate with @HiltViewModel
 * Also annotate constructor with @Inject
 * For Lazy injection use Lazy<>
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository,
    //private val productRepository: ProductRepository /* Normal injection */
    private val productRepository: dagger.Lazy<ProductRepository>
) : ViewModel() {
    init {
        repository.getUserDetails(0)
        productRepository.get().getProductCount()
    }
}
