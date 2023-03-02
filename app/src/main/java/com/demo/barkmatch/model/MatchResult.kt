package com.demo.barkmatch.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Data class to store the result of the breed/{name}/images query. A singular 'message' array gets returned with only strings,
 * So no need to create further models. If the results were more complex however, then perhaps a further type "Match" might make sense, for example.
 *
 * Implements the Parcelable interface so that we can easily pass results from one Fragment to another.
 */
data class MatchResult(
    @SerializedName("message")
    val messageList: List<String>
) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.createStringArrayList() ?: emptyList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(messageList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchResult> {
        override fun createFromParcel(parcel: Parcel): MatchResult {
            return MatchResult(parcel)
        }

        override fun newArray(size: Int): Array<MatchResult?> {
            return arrayOfNulls(size)
        }
    }
}
