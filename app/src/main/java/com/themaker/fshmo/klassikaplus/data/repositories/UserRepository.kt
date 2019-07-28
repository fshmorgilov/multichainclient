package com.themaker.fshmo.klassikaplus.data.repositories

import com.themaker.fshmo.klassikaplus.data.preferences.Preferences
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val preferences: Preferences
) :BaseRepository(){
    fun provideWalletNumber():String{
        return "1AHcmfsgT7Hztn9hMwvpfZeFMgmnYsmNZVZGMo"
        TODO("wallet from preferences")
    }

}