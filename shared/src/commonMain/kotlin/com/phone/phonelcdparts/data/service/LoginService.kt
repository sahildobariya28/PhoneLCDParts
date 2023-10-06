package com.phone.phonelcdparts.data.service

import io.ktor.http.HttpStatusCode

interface LoginService {
    suspend fun getLogin(username: String, password: String): Pair<String, HttpStatusCode?>
}