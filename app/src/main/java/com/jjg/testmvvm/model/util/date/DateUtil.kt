package com.jjg.testmvvm.model.util.date

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        /**
         *	문자열 (날짜)				    => SimpleDateFormat
         *	yyyy-MM-dd HH:mm:ss			    => yyyy-MM-dd HH:mm:ss
         *	yyyy-MM-dd HH:mm:ss.SSS			=> yyyy-MM-dd HH:mm:ss.SSS
         *
         *	yyyy-MM-dd HH:mm:ssZ			=> yyyy-MM-dd HH:mm:ssX
         *	yyyy-MM-dd HH:mm:ss+09			=> yyyy-MM-dd HH:mm:ssX
         *	yyyy-MM-dd HH:mm:ss+0900		=> yyyy-MM-dd HH:mm:ssX
         *	yyyy-MM-dd HH:mm:ss+09:00		=> yyyy-MM-dd HH:mm:ssXXX
         *	yyyy-MM-dd HH:mm:ssKST			=> yyyy-MM-dd HH:mm:ssZ
         *
         *  yyyy-MM-dd HH:mm:ss.SSSZ		=> yyyy-MM-dd HH:mm:ss.SSSX
         *  yyyy-MM-dd HH:mm:ss.SSS+09		=> yyyy-MM-dd HH:mm:ss.SSSX
         *  yyyy-MM-dd HH:mm:ss.SSS+0900    => yyyy-MM-dd HH:mm:ss.SSSX
         *  yyyy-MM-dd HH:mm:ss.SSS+09:00	=> yyyy-MM-dd HH:mm:ss.SSSXXX
         *  yyyy-MM-dd HH:mm:ss.SSSKST		=> yyyy-MM-dd HH:mm:ss.SSSZ
         *
         *  yyyy-MM-ddTHH:mm:ssZ	        => yyyy-MM-dd'T'HH:mm:ssX
         *  yyyy-MM-ddTHH:mm:ss+09			=> yyyy-MM-dd'T'HH:mm:ssX
         *  yyyy-MM-ddTHH:mm:ss+0900		=> yyyy-MM-dd'T'HH:mm:ssX
         *  yyyy-MM-ddTHH:mm:ss+09:00		=> yyyy-MM-dd'T'HH:mm:ssX
         *  yyyy-MM-ddTHH:mm:ssKST			=> yyyy-MM-dd'T'HH:mm:ssZ
         *
         *  yyyy-MM-ddTHH:mm:ss.SSSZ		=> yyyy-MM-dd'T'HH:mm:ss.SSSX
         *  yyyy-MM-ddTHH:mm:ss.SSS+09		=> yyyy-MM-dd'T'HH:mm:ss.SSSX
         *  yyyy-MM-ddTHH:mm:ss.SSS+0900	=> yyyy-MM-dd'T'HH:mm:ss.SSSX
         *  yyyy-MM-ddTHH:mm:ss.SSS+09:00	=> yyyy-MM-dd'T'HH:mm:ss.SSSXXX
         *  yyyy-MM-ddTHH:mm:ss.SSSKST		=> yyyy-MM-dd'T'HH:mm:ss.SSSZ
         *
         *
         * iso8601 >> date
         * @param strDate strIso8601 ex) "2021-04-17T00:00:00.000+09:00"

         * @return Date
         */
        fun iso8601ToDate(strDate: String): Date {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val date: Date = dateFormat.parse(strDate)
            return date
        }

        /**
         * date >> string date format
         *
         * @param date  Calendar.getInstance().time
         * @param dateFormat "yyyy년 MM월 dd일 a HH시 mm분 ss초"
         *
         * @return 20201년 05월 04일  오전 00시 00분 00초
         */
        fun dateToDateFormat(date:Date,dateFormat:String):String{
            val resultFormat = SimpleDateFormat(dateFormat)
            return resultFormat.format(date)
        }
    }
}