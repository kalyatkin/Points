package sandbox.igork.points.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import sandbox.igork.points.data.RepositoryImpl
import sandbox.igork.points.domain.GetPointsUseCase
import sandbox.igork.points.domain.Repository
import sandbox.igork.points.presentation.PointsViewModel

@Module
abstract class AppModule {

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository
}