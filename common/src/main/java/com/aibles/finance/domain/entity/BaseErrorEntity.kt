package com.aibles.finance.domain.entity


data class BaseErrorEntity(
    val status: Int? = 0,
    val statusMessage: String? = "",
    val timestamp: String? = "",
    val data: Data? = Data(),
){
    data class Data(
        val code: String? = "",
        val detail: String? = "",
    )
}