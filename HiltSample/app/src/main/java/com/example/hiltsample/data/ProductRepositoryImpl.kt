package com.example.hiltsample.data

import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(): ProductRepository {
    override fun getProductCount() = 50
}
