package com.rootdown.dev.paging_v3_1.api

import androidx.annotation.Keep
import com.rootdown.dev.paging_v3_1.data.DatabaseLatLng
import com.rootdown.dev.paging_v3_1.data.DatabaseStrain
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class NetworkStrainContainer(val data: List<NetworkStrain>)

@Keep
@JsonClass(generateAdapter = true)
data class NetworkProfilesContainer(val data: List<NetworkProfiles>)

@Keep
@JsonClass(generateAdapter = true)
data class NetworkLatLngContainer(val data: List<NetworkLatLng>)

@Keep
@JsonClass(generateAdapter = true)
data class NetworkLatLng(
    val id: Int,
    val lat: Double,
    val lng: Double)

@JvmName("asDomainModelLatLng")
fun NetworkLatLngContainer.asDatabaseModel(): List<DatabaseLatLng> {
    return data.map {
        DatabaseLatLng(
            id = it.id,
            lat = it.lat,
            lng = it.lng
        )
    }
}

@Keep
@JsonClass(generateAdapter = true)
data class NetworkStrain(
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
    val created_at: String
    )

@Keep
@JsonClass(generateAdapter = true)
data class NetworkProfiles(
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
    val updated_at: String?)


@JvmName("asDomainModelDatabaseStrain")
fun NetworkStrainContainer.asDatabaseModel(): List<DatabaseStrain> {
    return data.map {
        DatabaseStrain(
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