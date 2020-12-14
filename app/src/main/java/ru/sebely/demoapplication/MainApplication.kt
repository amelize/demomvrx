package ru.sebely.demoapplication

import android.app.Application
import com.airbnb.mvrx.launcher.MavericksLauncherMockActivity
import com.airbnb.mvrx.mocking.MockableMavericks
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import ru.sebely.demoapplication.mocks.NetworkMock
import ru.sebely.demoapplication.network.DeliveryService


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MockableMavericks.initialize(this)

        MavericksLauncherMockActivity.activityToShowMock = LauncherActivity::class

        startKoin {
            androidContext(this@MainApplication)
            modules(deliveryServiceModule)
        }
    }
}

private val deliveryServiceModule = module {
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    val behavior = NetworkBehavior.create()

    factory {
        Retrofit.Builder()
            .baseUrl("https://fake-food-delivery.com/")
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single {
        //get<Retrofit>().create(DeliveryService::class.java)

        NetworkMock(
            MockRetrofit.Builder(get<Retrofit>())
                .networkBehavior(behavior).build().create(DeliveryService::class.java)
        ) as DeliveryService
    }
}