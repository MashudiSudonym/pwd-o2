package c.m.pwdo2.oxygen_requirement_and_area.di

import c.m.pwdo2.oxygen_requirement_and_area.data.repository.OxygenRequirementAndAreaRepositoryImpl
import c.m.pwdo2.oxygen_requirement_and_area.domain.repository.OxygenRequirementAndAreaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindOxygenRequirementAndAreaRepository(oxygenRequirementAndAreaRepositoryImpl: OxygenRequirementAndAreaRepositoryImpl): OxygenRequirementAndAreaRepository
}