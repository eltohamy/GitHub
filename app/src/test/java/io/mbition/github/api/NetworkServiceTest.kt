package io.mbition.github.api

import assertk.assert
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import io.mbition.github.deps.factories.NetworkingFactory
import io.mbition.github.ui.activities.io.mbition.github.networking.NetworkService
import kotlinx.coroutines.experimental.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.nio.charset.StandardCharsets

/**
 * Created by Tohamy on 3/15/2018.
 */
class NetworkServiceTest {

    lateinit var networkService: NetworkService

    val mockWebServer = MockWebServer()

    @Before
    fun createService() {
        networkService = NetworkingFactory.createService(false, mockWebServer.url("/"))
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getUsers() = runBlocking {
        enqueueResponse("users.json")
        val users = networkService.getUsers().await()

        val request = mockWebServer.takeRequest()
        assert(request.path).isEqualTo("/users")

        assert(users.size).isEqualTo(30)
        assert(users[0].login).isEqualTo("mojombo")
        assert(users[0].id).isEqualTo(1)
        assert(users[0].avatarUrl).isEqualTo("https://avatars0.githubusercontent.com/u/1?v=4")
    }

    @Test
    fun getUserDetails() = runBlocking {
        enqueueResponse("michael.json")
        val michael = networkService.getUserDetails(1).await()

        val request = mockWebServer.takeRequest()
        assert(request.path).isEqualTo("/users/1")

        assert(michael).isNotNull()
        assert(michael.avatarUrl).isEqualTo("https://avatars2.githubusercontent.com/u/1825798?v=4")
        assert(michael.location).isEqualTo("San Francisco, CA")
        assert(michael.id).isEqualTo(1825798)
    }

    @Test
    fun getRepos() = runBlocking {
        enqueueResponse("repos.json")
        val repos = networkService.getRepos(1).await()

        val request = mockWebServer.takeRequest()
        assert(request.path).isEqualTo("/users/1/repos")

        assert(repos.size).isEqualTo(3)

        assert(repos[0].fullName).isEqualTo("1/discourse_docker")

        assert(repos[0].owner).isNotNull()
        assert(repos[0].owner!!.login).isEqualTo("1")
        assert(repos[0].owner!!.url).isEqualTo("https://api.github.com/users/1")

        assert(repos[1].fullName).isEqualTo("1/geode")
        assert(repos[2].fullName).isEqualTo("1/python-youtube-library")
    }


    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/" + fileName)
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        headers.forEach { key, value -> mockResponse.addHeader(key, value) }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }
}
