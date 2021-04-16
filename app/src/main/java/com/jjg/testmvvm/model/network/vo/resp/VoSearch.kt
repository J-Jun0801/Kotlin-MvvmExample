package com.jjg.testmvvm.model.network.vo.resp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
)

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
