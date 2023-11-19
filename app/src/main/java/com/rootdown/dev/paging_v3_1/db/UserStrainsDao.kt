package com.rootdown.dev.paging_v3_1.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import com.rootdown.dev.paging_v3_1.data.UserDatabaseStrain


@Dao
interface UserStrainsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserDatabaseStrain(vararg userStrain: UserDatabaseStrain)
    @Query("select * from userdatabasestrain")
    fun getUserStrains(): LiveData<List<UserDatabaseStrain>>
    @Update
    suspend fun updateStrainOwnerId(strain: DatabaseStrain)
    @Query("SELECT * FROM databasestrain WHERE strainOwnerId = :q")
    fun getStrainWithOwnerId(q: Int): LiveData<List<DatabaseStrain>>
    @Query("DELETE FROM userdatabasestrain WHERE id = :strainId")
    suspend fun deleteUserStrain(strainId: Int)
    @Query("SELECT * FROM userdatabasestrain WHERE strain_name LIKE :queryString")
    fun strainById(queryString: String?): LiveData<UserDatabaseStrain>

}