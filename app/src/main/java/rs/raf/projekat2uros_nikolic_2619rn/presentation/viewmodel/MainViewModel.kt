package rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Resource
import rs.raf.projekat2uros_nikolic_2619rn.data.repositories.RasporedRepository
import rs.raf.projekat2uros_nikolic_2619rn.presentation.contract.MainContract
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.AddBeleskaState
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.RasporedState
import timber.log.Timber

class MainViewModel(
    private val rasporedRepository: RasporedRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val rasporedState: MutableLiveData<RasporedState> = MutableLiveData()
    override val addDone: MutableLiveData<AddBeleskaState> = MutableLiveData()
    private val publishSubject: PublishSubject<String> = PublishSubject.create()

//    //ovo mozda ne treba
//    init {
//        val subscription = publishSubject
//            .debounce(200, TimeUnit.MILLISECONDS)
//            .distinctUntilChanged()
//            .switchMap {
//                rasporedRepository
//                    .getAllByName(it)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .doOnError {
//                        Timber.e("Error in publish subject")
//                        Timber.e(it)
//                    }
//            }
//            .subscribe(
//                {
//                    rasporedState.value = RasporedState.Success(it)
//                },
//                {
//                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                    Timber.e(it)
//                }
//            )
//        subscriptions.add(subscription)
//    }

    override fun fetchAll() {
        val subscription = rasporedRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> rasporedState.value = RasporedState.Loading
                        is Resource.Success -> rasporedState.value = RasporedState.DataFetched
                        is Resource.Error -> rasporedState.value = RasporedState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAll() {
        val subscription = rasporedRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    rasporedState.value = RasporedState.Success(it)
                },
                {
                    rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllByGrupaDanPP(grupa: String, dan: String, trazi: String) {
        var g: String = grupa
        var d: String = dan
        var pp: String = trazi

        if(grupa.equals("Grupa")){
            g = ""
        }

        if(dan.equals("Dan")){
            d = ""
        }

        if(trazi.equals("")){
           pp = ""
        }


            println("SVA 3")
            val subscription = rasporedRepository
                .getAllByGrupaDanPP(g, d, pp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        rasporedState.value = RasporedState.Success(it)
                    },
                    {
                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
                        Timber.e(it)
                    }
                )
            subscriptions.add(subscription)
            return


//        if(g && d && !pp){//dobar
//            val subscription = rasporedRepository
//                .getAllByGrupaDan(grupa, dan)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        rasporedState.value = RasporedState.Success(it)
//                    },
//                    {
//                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//            return
//        }
//
//        if(g && !d && pp){
//            val subscription = rasporedRepository
//                .getAllByGrupaPP(grupa, trazi)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        rasporedState.value = RasporedState.Success(it)
//                    },
//                    {
//                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//            return
//        }
//
//        if(!g && d && pp){
//            println("DAN PP")
//            val subscription = rasporedRepository
//                .getAllByDanPP(dan, trazi)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        rasporedState.value = RasporedState.Success(it)
//                    },
//                    {
//                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//            return
//        }
//
//        if(g && !d && !pp){
//            val subscription = rasporedRepository
//                .getAllByGrupa(grupa)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        rasporedState.value = RasporedState.Success(it)
//                    },
//                    {
//                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//            return
//        }
//
//        if(!g && d && !pp){
//            val subscription = rasporedRepository
//                .getAllByDan(dan)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        rasporedState.value = RasporedState.Success(it)
//                    },
//                    {
//                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//            return
//        }
//
//        if(!g && !d && pp){
//            val subscription = rasporedRepository
//                .getAllByPP(trazi)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    {
//                        rasporedState.value = RasporedState.Success(it)
//                    },
//                    {
//                        rasporedState.value = RasporedState.Error("Error happened while fetching data from db")
//                        Timber.e(it)
//                    }
//                )
//            subscriptions.add(subscription)
//            return
//        }

    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}