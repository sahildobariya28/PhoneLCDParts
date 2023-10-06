package com.phone.phonelcdparts.data.service

import com.phone.phonelcdparts.domain.model.PostModel
import io.ktor.http.HttpStatusCode

interface PostService {
    suspend fun getPostList(): Pair<List<PostModel>, HttpStatusCode?>
}