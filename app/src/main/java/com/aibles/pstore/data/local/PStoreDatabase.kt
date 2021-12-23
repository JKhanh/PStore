package com.aibles.pstore.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aibles.pstore.model.entities.dto.ItemCartLocal

@Database(
    entities = [ItemCartLocal::class],
    version = 1,
    exportSchema = false
)
abstract class PStoreDatabase: RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object{
        fun buildDatabase(context: Context): PStoreDatabase =
            Room.databaseBuilder(context.applicationContext,
                PStoreDatabase::class.java,
                "pstore.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}