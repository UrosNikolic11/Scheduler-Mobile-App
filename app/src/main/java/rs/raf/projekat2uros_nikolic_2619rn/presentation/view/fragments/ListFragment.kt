package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2uros_nikolic_2619rn.R
import rs.raf.projekat2uros_nikolic_2619rn.databinding.FragmentListBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.contract.MainContract
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.adapter.RasporedAdapter
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.RasporedState
import rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel.MainViewModel
import timber.log.Timber

class ListFragment : Fragment(R.layout.fragment_list), AdapterView.OnItemSelectedListener {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by viewModel<MainViewModel>()

    private var _binding: FragmentListBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: RasporedAdapter
    private lateinit var grupeSpiner: Spinner
    private lateinit var daniSpiner: Spinner
    private lateinit var traziBtn: Button
    private lateinit var traziEt: EditText
//    private var grupa = "Grupa"
//    private var dan = "Dan"
//    private var pretraga = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.listRv.layoutManager = LinearLayoutManager(context)
        adapter = RasporedAdapter()
        binding.listRv.adapter = adapter
    }

    private fun initListeners() {
        grupeSpiner = binding.grupaSpiner
        daniSpiner = binding.danSpiner
        traziBtn = binding.traziBtn
        traziEt = binding.pretraziEt
        grupeSpiner.onItemSelectedListener = this
        daniSpiner.onItemSelectedListener = this

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.dani,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            daniSpiner.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.grupe,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            grupeSpiner.adapter = adapter
        }

        traziBtn.setOnClickListener {
            var pretraga = traziEt.text.toString()
            var grupa = grupeSpiner.selectedItem.toString()
            var dan = daniSpiner.selectedItem.toString()
            mainViewModel.getAllByGrupaDanPP(grupa, dan, pretraga)
        }
    }

    private fun initObservers() {
        mainViewModel.rasporedState.observe(viewLifecycleOwner, Observer {
            Timber.e(it.toString())
            renderState(it)
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        mainViewModel.getAll()
        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
        mainViewModel.fetchAll()
    }

    private fun renderState(state: RasporedState) {
        when (state) {
            is RasporedState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.raspored)
            }
            is RasporedState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RasporedState.DataFetched -> {
                showLoadingState(false)
            }
            is RasporedState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.listRv.isVisible = !loading
        binding.loadingPb.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
       var grupa = grupeSpiner.selectedItem.toString()
       var dan = daniSpiner.selectedItem.toString()
        mainViewModel.getAllByGrupaDanPP(grupa, dan, traziEt.text.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}