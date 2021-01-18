package com.android.fundamentals.workshop03

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.fundamentals.workshop03.Ws03DbContract
import com.android.fundamentals.workshop03.solution.Ws03SolutionDataBase
import com.android.fundamentals.workshop03.solution.Ws03SolutionLocationEntity
import com.android.fundamentals.workshop03.solution.Ws03SolutionLocationsDao

// TODO 10: Make this class abstract and extend from "RoomDatabase()".
//  Mark with @Database() annotation with "entities = [Ws03LocationEntity::class], version = 1" as params.
@Database(entities = [Ws03LocationEntity::class], version = 1)
abstract class Ws03DataBase : RoomDatabase() {

	// TODO 11: Listed all DAOs as abstract properties (we have only one "Ws03LocationsDao").
	abstract val locationsDao: Ws03LocationsDao

	companion object {

		fun create(applicationContext: Context): Ws03DataBase = Room.databaseBuilder(
			applicationContext,
			Ws03DataBase::class.java,
			Ws03DbContract.DATABASE_NAME
		).fallbackToDestructiveMigration().build()

		// TODO 12: Add a function to create a DataBase with "application: Context" as param.
		//  It returns an instance of "Ws03DataBase".
		//  To create a DB, we have to create "Room.databaseBuilder" with params:
		//  (@NonNull Context context, @NonNull Class<T> klass, @NonNull String dbName).
		//  Provide "applicationContext", "Ws03DataBase::class.java" and "DATABASE_NAME" from the "Ws03DbContract".
		//  Add ".fallbackToDestructiveMigration()", build() the Builder.
	}
}