package com.jjg.testmvvm.model.network.vo.resp
import com.google.gson.annotations.SerializedName

data class VoTranslate (
    @SerializedName("translated_text")
    val translatedText:  List< List<String>>
)

