package c.m.pwdo2.area_stp_requirement.di

import c.m.pwdo2.area_stp_requirement.data.repository.AreaStpRequirementRepositoryImpl
import c.m.pwdo2.area_stp_requirement.domain.repository.AreaStpRequirementRepository
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
    abstract fun bindAreaStpRequirementRepository(areaStpRequirementRepositoryImpl: AreaStpRequirementRepositoryImpl): AreaStpRequirementRepository
}