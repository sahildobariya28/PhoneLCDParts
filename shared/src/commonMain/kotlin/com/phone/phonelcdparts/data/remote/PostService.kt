package com.phone.phonelcdparts.data.remote

import com.phone.phonelcdparts.data.model.PostItemDTO
import io.ktor.http.HttpStatusCode

interface PostService {
    suspend fun getPostList(): Pair<List<PostItemDTO>, HttpStatusCode?>
}