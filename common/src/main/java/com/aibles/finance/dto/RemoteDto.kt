package com.aibles.finance.dto

import com.aibles.finance.DomainModel

interface RemoteDto {
    fun mapToDomainModel(): DomainModel
    fun mapToLocalDto(): LocalDto
}