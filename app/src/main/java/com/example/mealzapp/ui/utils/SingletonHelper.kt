package com.example.mealzapp.ui.utils

// Util to create a singleton

open class SingletonHolder<out T: Any, in A>(creator: (A) -> T) {

    // Include it in the class,
    //companion object : SingletonHolder<ClassName, InputTypeVariable>(::ClassName)

    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}


open class SingletonHolderWithNoParameters<out T: Any>(creator: () -> T) {

    // Include it in the class,
    //companion object : SingletonHolder<ClassName>(::ClassName)

    private var creator: (() -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(): T {
        val checkInstance = instance
        if (checkInstance != null) {
            return checkInstance
        }

        return synchronized(this) {
            val checkInstanceAgain = instance
            if (checkInstanceAgain != null) {
                checkInstanceAgain
            } else {
                val created = creator!!()
                instance = created
                creator = null
                created
            }
        }
    }
}

