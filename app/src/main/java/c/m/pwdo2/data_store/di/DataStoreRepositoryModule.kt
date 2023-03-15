package c.m.pwdo2.data_store.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import c.m.pwdo2.data_store.data.repository.DataStoreRepositoryImpl
import c.m.pwdo2.data_store.domain.repository.DataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.applicationDataStore: DataStore<Preferences> by preferencesDataStore(name = "c.m.pwdo2.local.data")

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

    companion object {
        @Provides
        @Singleton
        fun provideDataStorePreferences(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
            return applicationContext.applicationDataStore
        }
    }
}