package rs.raf.projekat2uros_nikolic_2619rn.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2uros_nikolic_2619rn.data.datasources.local.StudentDataBase
import rs.raf.projekat2uros_nikolic_2619rn.data.repositories.BeleskeRepository
import rs.raf.projekat2uros_nikolic_2619rn.data.repositories.BeleskeRepositoryImpl
import rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel.BeleskeViewModel

val beleskeModule = module {
    viewModel { BeleskeViewModel(beleskeRepository = get()) }

    single<BeleskeRepository> { BeleskeRepositoryImpl(localDataSource = get()) }

    single { get<StudentDataBase>().getBeleskeDao() }
}