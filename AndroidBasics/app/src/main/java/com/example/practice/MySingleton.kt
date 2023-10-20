package com.example.practice


class MySingleton {
    fun test(){
        print("From MySingleton's test method()")
    }
    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null

        fun getInstance(): MySingleton {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = MySingleton()
                }
            }
            return INSTANCE!!
        }
    }
}
