package com.aibles.finance.dto

import com.aibles.finance.DomainModel

interface LocalDto {
    fun mapToDomainModel(): DomainModel
    fun mapToRemoteDto(): RemoteDto
}