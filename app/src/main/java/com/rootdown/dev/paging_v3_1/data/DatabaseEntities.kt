package com.rootdown.dev.paging_v3_1.data

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.rootdown.dev.paging_v3_1.api.DatabaseCoordinates
import com.rootdown.dev.paging_v3_1.api.Strain
import com.rootdown.dev.paging_v3_1.api.UserProfile
import com.rootdown.dev.paging_v3_1.api.UserStrain

@Keep
@Entity(tableName = "databasestrain")
data class DatabaseStrain constructor(
    @PrimaryKey
    val id: Int,
    val strainOwnerId: Int?,
    val strain_name: String,
    val strain_description: String?,
    val thc: String?,
    val cbd: String?,
    val cbn: String?,
    val strain_tag_words: String?,
    val strain_image: String?,
    val strain_type: String?,
    val updated_at: String?,
    val created_at: String?
)
@Keep
@Entity(tableName = "databaselatlng")
data class DatabaseLatLng constructor(
    @PrimaryKey
    val id: Int,
    val lat: Double,
    val lng: Double
)
@Keep
@Entity(tableName = "repos")
data class Repo(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    val profileOwnerId: Long?,
    @field:SerializedName("menu_num_id") val menu_num_id: Int?,
    @field:SerializedName("company_name") val company_name: String,
    @field:SerializedName("email_address") val email_address: String,
    @field:SerializedName("address") val address: String?,
    @field:SerializedName("unit_number") val unit_number: String?,
    @field:SerializedName("city") val city: String,
    @field:SerializedName("state") val state: String,
    @field:SerializedName("zip") val zip: Int,
    @field:SerializedName("status") val status: String,
    @field:SerializedName("contact_website") val contact_website: String?,
    @field:SerializedName("hours_of_operation_sunday") val hours_of_operation_sunday: String?,
    @field:SerializedName("hours_of_operation_monday") val hours_of_operation_monday: String?,
    @field:SerializedName("hours_of_operation_tuesday") val hours_of_operation_tuesday: String?,
    @field:SerializedName("hours_of_operation_wednesday") val hours_of_operation_wednesday: String?,
    @field:SerializedName("hours_of_operation_thursday") val hours_of_operation_thursday: String?,
    @field:SerializedName("hours_of_operation_friday") val hours_of_operation_friday: String?,
    @field:SerializedName("hours_of_operation_saturday") val hours_of_operation_saturday: String?,
    @field:SerializedName("rating") val rating: Int?,
    @field:SerializedName("medical") val medical: Byte?,
    @field:SerializedName("recreational") val recreational: Byte?,
    @field:SerializedName("ada") val ada: Byte?,
    @field:SerializedName("delivery") val delivery: Byte?,
    @field:SerializedName("delivery_only") val delivery_only: Byte?,
    @field:SerializedName("cbd") val cbd: Byte?,
    @field:SerializedName("edibles") val edibles: Byte?,
    @field:SerializedName("concentrates") val concentrates: Byte?,
    @field:SerializedName("cc") val cc: Byte?,
    @field:SerializedName("company_description") val company_description: String?,
    @field:SerializedName("phone_number") val phone_number: String?,
    @field:SerializedName("lab_tested") val lab_tested: Byte?,
    @field:SerializedName("clones") val clones: Byte?,
    @field:SerializedName("seeds") val seeds: Byte?,
    @field:SerializedName("paid_tier") val paid_tier: Byte?,
    @field:SerializedName("lat") val lat: Double,
    @field:SerializedName("lng") val lng: Double,
    @field:SerializedName("created_at") val created_at: String?,
    @field:SerializedName("updated_at") val updated_at: String?
)

// tables to save individual strains or dispensaries for user content feature set
@Entity(tableName = "userrepo")
data class UserRepo(
    @PrimaryKey
    val id: Long,
    val profileOwnerId: Long?,
    val menu_num_id: Int?,
    val company_name: String,
    val email_address: String,
    val address: String?,
    val unit_number: String?,
    val city: String,
    val state: String,
    val zip: Int,
    val status: String,
    val contact_website: String?,
    val hours_of_operation_sunday: String?,
    val hours_of_operation_monday: String?,
    val hours_of_operation_tuesday: String?,
    val hours_of_operation_wednesday: String?,
    val hours_of_operation_thursday: String?,
    val hours_of_operation_friday: String?,
    val hours_of_operation_saturday: String?,
    val rating: Int?,
    val medical: Byte?,
    val recreational: Byte?,
    val ada: Byte?,
    val delivery: Byte?,
    val delivery_only: Byte?,
    val cbd: Byte?,
    val edibles: Byte?,
    val concentrates: Byte?,
    val cc: Byte?,
    val company_description: String?,
    val phone_number: String?,
    val lab_tested: Byte?,
    val clones: Byte?,
    val seeds: Byte?,
    val paid_tier: Byte?,
    val lat: Double,
    val lng: Double,
    val created_at: String?,
    val updated_at: String?
)


@Entity(tableName = "userdatabasestrain")
data class UserDatabaseStrain(
    @PrimaryKey
    val id: Int,
    val strainOwnerId: Int?,
    val strain_name: String,
    val strain_description: String?,
    val thc: String?,
    val cbd: String?,
    val cbn: String?,
    val strain_tag_words: String?,
    val strain_image: String?,
    val strain_type: String?,
    val updated_at: String?,
    val created_at: String?
)


@JvmName("asDomainModelLatLng")
fun List<DatabaseLatLng>.asDomainModel(): List<DatabaseCoordinates>{
    return map {
        DatabaseCoordinates(
            lat = it.lat,
            lng = it.lng
        )
    }
}

@JvmName("asConvertor")
fun List<UserRepo>.asConvertor(): List<UserProfile>{
    return map {
        UserProfile(
            id = it.id,
            profileOwnerId = it.profileOwnerId,
            menu_num_id = it.menu_num_id,
            company_name = it.company_name,
            email_address = it.email_address,
            address = it.address,
            unit_number = it.unit_number,
            city = it.city,
            state = it.state,
            zip = it.zip,
            status = it.status,
            contact_website = it.contact_website,
            hours_of_operation_sunday = it.hours_of_operation_sunday,
            hours_of_operation_monday = it.hours_of_operation_monday,
            hours_of_operation_tuesday = it.hours_of_operation_tuesday,
            hours_of_operation_wednesday = it.hours_of_operation_wednesday,
            hours_of_operation_thursday = it.hours_of_operation_thursday,
            hours_of_operation_friday = it.hours_of_operation_friday,
            hours_of_operation_saturday = it.hours_of_operation_saturday,
            rating = it.rating,
            medical = it.medical,
            recreational = it.recreational,
            ada = it.ada,
            delivery = it.delivery,
            delivery_only = it.delivery_only,
            cbd = it.cbd,
            edibles = it.edibles,
            concentrates = it.concentrates,
            cc = it.cc,
            company_description = it.company_description,
            phone_number = it.phone_number,
            lab_tested = it.lab_tested,
            clones = it.clones,
            seeds = it.seeds,
            paid_tier = it.paid_tier,
            lat = it.lat,
            lng = it.lng,
            created_at = it.created_at,
            updated_at = it.updated_at)
    }
}

@JvmName("asDomainModelUserStrain")
fun List<UserDatabaseStrain>.asDomainModel(): List<UserStrain>{
    return map {
        UserStrain(
            id = it.id,
            strainOwnerId = it.strainOwnerId,
            strain_name = it.strain_name,
            strain_description = it.strain_description,
            thc = it.thc,
            cbd = it.cbd,
            cbn = it.cbn,
            strain_tag_words = it.strain_tag_words,
            strain_image = it.strain_image,
            strain_type = it.strain_type,
            created_at = it.created_at,
            updated_at = it.updated_at
        )
    }
}




@JvmName("asDomainModelUserStrainConverted")
fun MutableList<UserDatabaseStrain>.asDomainModel(): List<UserStrain>{
    return map {
        UserStrain(
            id = it.id,
            strainOwnerId = it.strainOwnerId,
            strain_name = it.strain_name,
            strain_description = it.strain_description,
            thc = it.thc,
            cbd = it.cbd,
            cbn = it.cbn,
            strain_tag_words = it.strain_tag_words,
            strain_image = it.strain_image,
            strain_type = it.strain_type,
            created_at = it.created_at,
            updated_at = it.updated_at
        )
    }
}



@JvmName("asDomainModelStrain")
fun List<DatabaseStrain>.asDomainModel(): List<Strain>{
    return map {
        Strain(
            strain_name = it.strain_name,
            strain_description = it.strain_description,
            thc = it.thc,
            cbd = it.cbd,
            cbn = it.cbn,
            strain_tag_words = it.strain_tag_words,
            strain_image = it.strain_image,
            strain_type = it.strain_type,
            created_at = it.created_at,
            updated_at = it.updated_at
        )
    }
}

