package com.jjg.testmvvm.model.network.set

import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.model.network.vo.resp.VoTranslate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiSerivce {

    @GET("/v2/translation/translate")
    fun translate(
        @Query("query") query: String,
        @Query("src_lang") src_lang: String,
        @Query("target_lang") target_lang: String
    ): Call<VoTranslate>

    @GET("/v2/search/web")
    fun search(
        @Query("query") query: String
    ): Call<VoSearch>
}