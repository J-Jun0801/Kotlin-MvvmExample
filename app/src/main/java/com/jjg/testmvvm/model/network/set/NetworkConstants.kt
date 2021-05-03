package com.jjg.testmvvm.model.network.set

class NetworkConstants {

    companion object {
        const val BASE_URL = "https://dapi.kakao.com"
        private const val REST_API_KEY = "393eb89c4e03ed1062a9b30c11ee5e74"
        const val LAGN_KR = "kr"
        const val LAGN_EN = "en"

        const val H_KEY_AUTHORIZATION = "Authorization"
        const val H_VALUE_KAKAO_AK = "KakaoAK $REST_API_KEY"


        const val URL_TRANSLATE = "/v2/translation/translate"
        const val URL_SEARCH= "/v2/search/web"

    }
}