package com.rootdown.dev.paging_v3_1.ui.user

import android.app.Application
import androidx.lifecycle.*
import com.rootdown.dev.paging_v3_1.api.UserProfile
import com.rootdown.dev.paging_v3_1.api.UserStrain
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import com.rootdown.dev.paging_v3_1.data.Repo
import com.rootdown.dev.paging_v3_1.data.UserDatabaseStrain
import com.rootdown.dev.paging_v3_1.data.UserRepo
import com.rootdown.dev.paging_v3_1.repo.UserContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject





@HiltViewModel
class UserContentViewModel @Inject constructor(
    private val repo: UserContent,
    state: SavedStateHandle,
    application: Application
) : ViewModel() {

    private val userContent = repo
    private val userStrainStateId = state.get<String?>("strId")
    private val getProfileId = state.get<Long>("profileId")

    val updatedProfile: LiveData<List<UserProfile>> = userContent.userRepos
    val updatedStrains: LiveData<List<UserStrain>> = userContent.userStrains
    val strainEditDetailed: LiveData<UserDatabaseStrain> = userContent.getStrain(userStrainStateId)
    val profileEditDetailed: LiveData<UserRepo> = userContent.getProfile(getProfileId)
    private val outputDir = application.filesDir.path
    private val dir = File(outputDir)
    private val files: Array<File> = dir.listFiles() as Array<File>
    private val xx = mutableListOf<String>()

    //private var filesList = MutableLiveData<List<String>>()

    private val fileLsUserContent: MutableLiveData<List<String>> = MutableLiveData<List<String>>();
    val filesLiveData : LiveData<List<String>> by this::fileLsUserContent

    init {
        fileLsUserContent.value = xx
    }

    fun refreshFilesList(){
        viewModelScope.launch {
            files.forEach {
                if(it.path.endsWith(".png"))
                {
                    xx.add(it.path.toString())
                }
            }
        }
    }


    /**
     * Launching a new coroutine to delete an item in a non-blocking way
     */
    suspend fun deleteUserProfile(profileId: Long) {
        userContent.deleteUserProfile(profileId)
    }

    suspend fun deleteUserStrain(strainId: Int) {
        userContent.deleteUserStrain(strainId)
    }

    fun checkNullProfileId(): Long
    {
        val argX: Long = 0L
        if(getProfileId == null)
        {
            return argX
        }
        else
        {
            return getProfileId
        }
    }

    fun updateUserStrain(
        id: Int,
        strainOwnerId: Int?,
        strain_name: String,
        strain_description: String?,
        thc: String?,
        cbd: String?,
        cbn: String?,
        strain_tag_words: String?,
        strain_image: String?,
        strain_type: String?,
        updated_at: String?,
        created_at: String?
    ){
        val updatedStrain = getUpdatedStrain(id, strainOwnerId, strain_name, strain_description, thc, cbd, cbn, strain_tag_words, strain_image, strain_type, updated_at, created_at)
        updateStrain(updatedStrain)
    }

    fun updateUserProfile(
        id: Long,
        profileOwnerId: Long?,
        menu_num_id: Int?,
        company_name: String,
        email_address: String,
        address: String?,
        unit_number: String?,
        city: String,
        state: String,
        zip: Int,
        status: String,
        contact_website: String?,
        hours_of_operation_sunday: String?,
        hours_of_operation_monday: String?,
        hours_of_operation_tuesday: String?,
        hours_of_operation_wednesday: String?,
        hours_of_operation_thursday: String?,
        hours_of_operation_friday: String?,
        hours_of_operation_saturday: String?,
        rating: Int?,
        medical: Byte?,
        recreational: Byte?,
        ada: Byte?,
        delivery: Byte?,
        delivery_only: Byte?,
        cbd: Byte?,
        edibles: Byte?,
        concentrates: Byte?,
        cc: Byte?,
        company_description: String?,
        phone_number: String?,
        lab_tested: Byte?,
        clones: Byte?,
        seeds: Byte?,
        paid_tier: Byte?,
        lat: Double,
        lng: Double,
        created_at: String?,
        updated_at: String?
    ){
        val updatedRepo = getUpdateRepo(id, profileOwnerId, menu_num_id, company_name,
            email_address, address, unit_number, city, state, zip, status, contact_website,
            hours_of_operation_sunday, hours_of_operation_monday, hours_of_operation_tuesday,
            hours_of_operation_wednesday, hours_of_operation_thursday, hours_of_operation_friday,
            hours_of_operation_saturday, rating, medical, recreational, ada, delivery, delivery_only,
            cbd, edibles, concentrates, cc, company_description, phone_number, lab_tested, clones,
            seeds, paid_tier, lat, lng, created_at, updated_at)

        updateRepo(updatedRepo)
    }
    // get files and store in Flow

    private fun updateRepo(repo: Repo)
    {
        viewModelScope.launch {
            userContent.updateRepo(repo)
        }
    }

    private fun updateStrain(strain: DatabaseStrain)
    {
        viewModelScope.launch {
            userContent.updateStrain(strain)
        }
    }

    private fun getUpdateRepo(
        id: Long,
        profileOwnerId: Long?,
        menu_num_id: Int?,
        company_name: String,
        email_address: String,
        address: String?,
        unit_number: String?,
        city: String,
        state: String,
        zip: Int,
        status: String,
        contact_website: String?,
        hours_of_operation_sunday: String?,
        hours_of_operation_monday: String?,
        hours_of_operation_tuesday: String?,
        hours_of_operation_wednesday: String?,
        hours_of_operation_thursday: String?,
        hours_of_operation_friday: String?,
        hours_of_operation_saturday: String?,
        rating: Int?,
        medical: Byte?,
        recreational: Byte?,
        ada: Byte?,
        delivery: Byte?,
        delivery_only: Byte?,
        cbd: Byte?,
        edibles: Byte?,
        concentrates: Byte?,
        cc: Byte?,
        company_description: String?,
        phone_number: String?,
        lab_tested: Byte?,
        clones: Byte?,
        seeds: Byte?,
        paid_tier: Byte?,
        lat: Double,
        lng: Double,
        created_at: String?,
        updated_at: String?
    ): Repo {
        return Repo(
            id = id,
            profileOwnerId = profileOwnerId,
            menu_num_id = menu_num_id,
            company_name = company_name,
            email_address = email_address,
            address = address,
            unit_number = unit_number,
            city = city,
            state = state,
            zip = zip,
            status = status,
            contact_website = contact_website,
            hours_of_operation_sunday = hours_of_operation_sunday,
            hours_of_operation_monday = hours_of_operation_monday,
            hours_of_operation_tuesday = hours_of_operation_tuesday,
            hours_of_operation_wednesday = hours_of_operation_wednesday,
            hours_of_operation_thursday = hours_of_operation_thursday,
            hours_of_operation_friday = hours_of_operation_friday,
            hours_of_operation_saturday = hours_of_operation_saturday,
            rating = rating,
            medical = medical,
            recreational = recreational,
            ada = ada,
            delivery = delivery,
            delivery_only = delivery_only,
            cbd = cbd,
            edibles = edibles,
            concentrates = concentrates,
            cc = cc,
            company_description = company_description,
            phone_number = phone_number,
            lab_tested = lab_tested,
            clones = clones,
            seeds = seeds,
            paid_tier = paid_tier,
            lat = lat,
            lng = lng,
            created_at = created_at,
            updated_at = updated_at
        )
    }

    private fun getUpdatedStrain(
        id: Int,
        strainOwnerId: Int?,
        strain_name: String,
        strain_description: String?,
        thc: String?,
        cbd: String?,
        cbn: String?,
        strain_tag_words: String?,
        strain_image: String?,
        strain_type: String?,
        updated_at: String?,
        created_at: String?
    ): DatabaseStrain {
        return DatabaseStrain(
            id = id,
            strainOwnerId = strainOwnerId,
            strain_name = strain_name,
            strain_description = strain_description,
            thc = thc,
            cbd = cbd,
            cbn = cbn,
            strain_tag_words = strain_tag_words,
            strain_image = strain_image,
            strain_type = strain_type,
            created_at = created_at,
            updated_at = updated_at
        )
    }


    //insert a profile
    fun insertProfile(profile: UserRepo) = viewModelScope.launch(Dispatchers.IO) {
        userContent.insertUserRepo(profile)
    }

    //insert a strain
    fun insertDatabaseStrain(strain: UserDatabaseStrain) = viewModelScope.launch(Dispatchers.IO) {
        userContent.insertUserStrain(strain)
    }

}