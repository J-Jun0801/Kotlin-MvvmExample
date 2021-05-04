package com.jjg.testmvvm.model.network.vo.resp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jjg.testmvvm.model.util.date.DateUtil

data class VoSearch(
    @SerializedName("documents")
    @Expose
    val documents: ArrayList<Document>,

    @SerializedName("meta")
    @Expose
    val meta: Meta
)

data class Document(
    @SerializedName("contents")
    @Expose
    val contents: String,

    @SerializedName("datetime")
    @Expose
    val datetime: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("url")
    @Expose
    val url: String
) {

    fun getDateTime(): String {
        val date = DateUtil.iso8601ToDate(datetime)
        return DateUtil.dateToDateFormat(date, "yyyy년 MM월 dd일 a HH시 mm분 ss초")
    }
}

data class Meta(
    @SerializedName("isEnd")
    @Expose
    val isEnd: Boolean,

    @SerializedName("pageableCount")
    @Expose
    val pageableCount: Long,

    @SerializedName("totalCount")
    @Expose
    val totalCount: Long
)
