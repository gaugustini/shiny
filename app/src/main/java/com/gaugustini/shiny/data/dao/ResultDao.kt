package com.gaugustini.shiny.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.gaugustini.shiny.data.entity.ResultEntity
import com.gaugustini.shiny.data.entity.ResultWithLanguage

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(results: List<ResultEntity>)

    @Query("DELETE FROM result")
    suspend fun clearResults()

    @Transaction
    suspend fun clearAndInsertResults(results: List<ResultEntity>) {
        clearResults()
        insertResults(results)
    }

    @Transaction
    @Query(
        """
        SELECT 
            result.result_id,
            l1.name AS head,
            l2.name AS body,
            l3.name AS arms,
            l4.name AS waist,
            l5.name AS legs,
            l6.name AS decoration_one,
            result.decoration_one_amount,
            l7.name AS decoration_two,
            result.decoration_two_amount,
            l8.name AS decoration_three,
            result.decoration_three_amount,
            l9.name AS decoration_four,
            result.decoration_four_amount,
            l10.name AS decoration_five,
            result.decoration_five_amount,
            l11.name AS decoration_six,
            result.decoration_six_amount,
            l12.name AS decoration_seven,
            result.decoration_seven_amount,
            l13.name AS decoration_eight,
            result.decoration_eight_amount,
            l14.name AS decoration_nine,
            result.decoration_nine_amount,
            l15.name AS decoration_ten,
            result.decoration_ten_amount
        FROM result
        JOIN language AS l1 ON result.head = l1.name_id AND l1.language_code = :language
        JOIN language AS l2 ON result.body = l2.name_id AND l2.language_code = :language
        JOIN language AS l3 ON result.arms = l3.name_id AND l3.language_code = :language
        JOIN language AS l4 ON result.waist = l4.name_id AND l4.language_code = :language
        JOIN language AS l5 ON result.legs = l5.name_id AND l5.language_code = :language
        LEFT JOIN language AS l6 ON result.decoration_one = l6.name_id AND l6.language_code = :language
        LEFT JOIN language AS l7 ON result.decoration_two = l7.name_id AND l7.language_code = :language
        LEFT JOIN language AS l8 ON result.decoration_three = l8.name_id AND l8.language_code = :language
        LEFT JOIN language AS l9 ON result.decoration_four = l9.name_id AND l9.language_code = :language
        LEFT JOIN language AS l10 ON result.decoration_five = l10.name_id AND l10.language_code = :language
        LEFT JOIN language AS l11 ON result.decoration_six = l11.name_id AND l11.language_code = :language
        LEFT JOIN language AS l12 ON result.decoration_seven = l12.name_id AND l12.language_code = :language
        LEFT JOIN language AS l13 ON result.decoration_eight = l13.name_id AND l13.language_code = :language
        LEFT JOIN language AS l14 ON result.decoration_nine = l14.name_id AND l14.language_code = :language
        LEFT JOIN language AS l15 ON result.decoration_ten = l15.name_id AND l15.language_code = :language
        """
    )
    suspend fun getResults(language: String): List<ResultWithLanguage>

}