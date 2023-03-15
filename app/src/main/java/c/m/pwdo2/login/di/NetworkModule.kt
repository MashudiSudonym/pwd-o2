package c.m.pwdo2.login.di

import c.m.pwdo2.login.data.remote.LoginAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoginAPI(retrofit: Retrofit): LoginAPI {
        return retrofit.create(LoginAPI::class.java)
    }
}