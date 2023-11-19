package com.rootdown.dev.paging_v3_1.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import kotlinx.coroutines.flow.Flow

@Dao
interface StrainDao {

    @Query("SELECT COUNT(*) FROM databasestrain")
    fun getCount(): Int
    @Query("select * from databasestrain")
    fun getStrains(): LiveData<List<DatabaseStrain>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(strain: List<DatabaseStrain>)

    @Query("select * from databasestrain")
    fun getStrainLs(): LiveData<List<DatabaseStrain>>

    @Query("SELECT * FROM databasestrain WHERE strain_name LIKE :queryString")
    fun strainById(queryString: String?): LiveData<DatabaseStrain>

    @Query("SELECT * FROM databasestrain WHERE strain_type LIKE :query")
    fun strainByType(query: String): Flow<List<DatabaseStrain>>
}

