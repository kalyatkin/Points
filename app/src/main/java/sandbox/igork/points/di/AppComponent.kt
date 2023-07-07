package sandbox.igork.points.di

import dagger.Component
import sandbox.igork.points.MainActivity
import sandbox.igork.points.presentation.PointsViewModel

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewMode: PointsViewModel)
}

val appComponent = DaggerAppComponent.create()