package rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2uros_nikolic_2619rn.data.repositories.BeleskeRepository
import rs.raf.projekat2uros_nikolic_2619rn.presentation.contract.BeleskeConcract
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.AddBeleskaState
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.BeleskaState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class BeleskeViewModel(private val beleskeRepository: BeleskeRepository): ViewModel(),BeleskeConcract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val beleskeState: MutableLiveData<BeleskaState> = MutableLiveData()
    override val addDone: MutableLiveData<AddBeleskaState> = MutableLiveData()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                beleskeRepository
                    .getAllByNaslovSadrzaj(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    beleskeState.value = BeleskaState.Success(it)
                },
                {
                    beleskeState.value = BeleskaState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllBeleske() {
        val subscription = beleskeRepository
            .getAllBeleske()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskaState.Success(it)
                },
                {
                    beleskeState.value = BeleskaState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteBeleske(id: Long?) {
        val subscription = beleskeRepository
            .deleteBeleske(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                   Timber.e("Deleted")
                },
                {
                    beleskeState.value = BeleskaState.Error("Error happened while deleting data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean) {
        val subscription = beleskeRepository
            .insertBeleske(id,naslov, sadrzaj, arhiviran)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addDone.value = AddBeleskaState.Success
                },
                {
                    addDone.value = AddBeleskaState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun updateBeleske(id: Long?, naslov: String, sadrzaj: String, arhiviran: Boolean) {
        val subscription = beleskeRepository
            .updateBeleske(id, naslov, sadrzaj, arhiviran)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Timber.e("Update mrtvi")
                },
                {
                    beleskeState.value = BeleskaState.Error("Error happened while updating data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByNaslovSadrzaj(filter: String) {
        publishSubject.onNext(filter)
    }

    override fun getAllByArhiviran() {
        val subscription = beleskeRepository
            .getAllByArhiviran()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    beleskeState.value = BeleskaState.Success(it)
                },
                {
                    beleskeState.value = BeleskaState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}