package com.aibles.finance

import com.aibles.finance.dto.LocalDto
import com.aibles.finance.dto.RemoteDto

interface DomainModel {
    fun toLocalDto(): LocalDto
    fun toRemoteDto(): RemoteDto
}