package rs.raf.projekat2uros_nikolic_2619rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2uros_nikolic_2619rn.data.datasources.local.StudentDataBase
import rs.raf.projekat2uros_nikolic_2619rn.data.datasources.remote.RasporedService
import rs.raf.projekat2uros_nikolic_2619rn.data.repositories.RasporedRepository
import rs.raf.projekat2uros_nikolic_2619rn.data.repositories.RasporedRepositoryImpl
import rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel.MainViewModel

val rasporedModule = module {

    viewModel { MainViewModel(rasporedRepository = get()) }

    single<RasporedRepository> { RasporedRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<StudentDataBase>().getRasporedDao() }

    single<RasporedService> { create(retrofit = get()) }

}

