package com.gitfit.android.di

import androidx.room.Room
import com.gitfit.android.data.local.db.AppDatabase
import com.gitfit.android.data.local.prefs.PreferenceProvider
import com.gitfit.android.data.remote.RetrofitClient
import com.gitfit.android.repos.ActivityRepository
import com.gitfit.android.repos.ActivityTypeRepository
import com.gitfit.android.repos.GitFitAPIRepository
import com.gitfit.android.ui.home.home.HomeViewModel
import com.gitfit.android.ui.home.journal.JournalViewModel
import com.gitfit.android.ui.home.profile.ProfileViewModel
import com.gitfit.android.ui.login.LoginViewModel
import com.gitfit.android.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    single { RetrofitClient() }
    single { get<RetrofitClient>().getGitFitService() }

    single { PreferenceProvider(androidApplication().applicationContext) }

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "activity_database").build() }
    single { get<AppDatabase>().activityDao() }
    single { get<AppDatabase>().activityTypeDao() }
    single { GitFitAPIRepository(get()) }
    single { ActivityRepository(get()) }
    single { ActivityTypeRepository(get()) }

    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }

    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { JournalViewModel(get(), get(), get(), get()) }
}