package com.sample.recipelistapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * RecipePhoto image item model for image list
 */
@Entity(tableName = "photo")
data class RecipePhoto(
    @PrimaryKey(autoGenerate = true)
    val photoId: Long = 0,
    @ForeignKey
        (
        entity = RecipeItem::class,
        parentColumns = ["recipeId"],
        childColumns = ["recipeOwnerId"],
        onDelete = CASCADE
    )
    var recipeOwnerId: Long = 0,
    val photoPath: String?,
    var editable: Boolean?
)