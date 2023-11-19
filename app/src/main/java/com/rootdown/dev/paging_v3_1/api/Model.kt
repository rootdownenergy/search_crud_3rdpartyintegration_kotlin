package com.rootdown.dev.paging_v3_1.api

import androidx.room.PrimaryKey


data class UserContentImage(
    val name: String,
    val description: String,
    val cover_image: String
)

data class Strain(
    val strain_name: String?,
    val strain_description: String?,
    val thc: String?,
    val cbd: String?,
    val cbn: String?,
    val strain_tag_words: String?,
    val strain_image: String?,
    val strain_type: String?,
    val updated_at: String?,
    val created_at: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class DatabaseCoordinates(
    val lat: Double,
    val lng: Double
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

data class UserStrain(
    val id: Int,
    val strainOwnerId: Int?,
    val strain_name: String?,
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

data class UserProfile(
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