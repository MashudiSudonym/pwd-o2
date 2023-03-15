//package c.m.pwdo2
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import c.m.pwdo2.common.util.Resource
//import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
//import c.m.pwdo2.login.data.remote.LoginAPI
//import c.m.pwdo2.login.data.remote.dto.ListUserDTO
//import c.m.pwdo2.login.data.remote.dto.UserDTO
//import c.m.pwdo2.login.data.repository.LoginRepositoryImpl
//import c.m.pwdo2.login.domain.repository.LoginRepository
//import com.google.gson.Gson
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.last
//import kotlinx.coroutines.test.runTest
//import okhttp3.OkHttpClient
//import okhttp3.mockwebserver.MockResponse
//import okhttp3.mockwebserver.MockWebServer
//import org.junit.*
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import timber.log.Timber
//import java.util.concurrent.TimeUnit
//
//@ExperimentalCoroutinesApi
//class LoginRepositoryImplTest {
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val mainDispatcherRule = MainDispatcherRule()
//
//    private lateinit var loginRepository: LoginRepository
//    private lateinit var mockWebServer: MockWebServer
//    private lateinit var client: OkHttpClient
//    private lateinit var apiService: LoginAPI
//    private lateinit var dummyListUserDTO: ListUserDTO
//
//    @Before
//    fun setUp() {
//        mockWebServer = MockWebServer()
//        mockWebServer.start()
//
//        client = OkHttpClient.Builder()
//            .connectTimeout(1, TimeUnit.SECONDS)
//            .readTimeout(1, TimeUnit.SECONDS)
//            .writeTimeout(1, TimeUnit.SECONDS)
//            .build()
//
//        apiService = Retrofit.Builder()
//            .baseUrl(mockWebServer.url("https://dl.dropboxusercontent.com/s/sbwqgls08gmw22f/"))
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(LoginAPI::class.java)
//
//        loginRepository = LoginRepositoryImpl(apiService)
//
//        dummyListUserDTO = ListUserDTO(
//            listOf(
//                UserDTO(
//                    name = "Hubert",
//                    username = "hubert@gmail.com",
//                    password = "ganteng"
//                )
//            )
//        )
//    }
//
//    @After
//    fun tearDown() {
//        mockWebServer.shutdown()
//    }
//
//    @Test
//    fun `do get list user data`() = runTest {
//        val expectedResult =
//            MockResponse().setResponseCode(200).setBody(Gson().toJson(dummyListUserDTO))
//
//        mockWebServer.enqueue(expectedResult)
//
//        val actualResult = loginRepository.getUser().last().data
//
//        Assert.assertNotNull(actualResult)
//
//        println("expected : $expectedResult")
//        println("actual : $actualResult")
//
//        loginRepository.getUser().collect { result ->
//            when (result) {
//                is Resource.Error -> {}
//                is Resource.Loading -> {}
//                is Resource.Success -> {
//                    val findUserData = result.data?.user?.find { u ->
//                        u.username == "hubert@gmail.com"
//                    }
//
//                    println(findUserData)
//
//                    if ((findUserData?.username == "hubert@gmail.com") && (findUserData.password == "ganteng")
//                    ) {
//                        println("authentication TRUE")
//                    } else {
//                        println("authentication false")
//                    }
//                }
//            }
//        }
//    }
//}