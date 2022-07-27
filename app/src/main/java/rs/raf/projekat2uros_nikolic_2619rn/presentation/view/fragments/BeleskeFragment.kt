package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2uros_nikolic_2619rn.R
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske
import rs.raf.projekat2uros_nikolic_2619rn.databinding.FragmentInputBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.contract.BeleskeConcract
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.adapter.BeleskaAdapter
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.states.BeleskaState
import rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel.BeleskeViewModel

class BeleskeFragment : Fragment(R.layout.fragment_input) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val beleskeViewModel: BeleskeConcract.ViewModel by sharedViewModel<BeleskeViewModel>()

    private var _binding: FragmentInputBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: BeleskaAdapter
    private lateinit var switch: Switch
    private lateinit var dodaj: ImageButton
    private lateinit var pretraga: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputBinding.inflate(inflater, container, false)
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
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BeleskaAdapter({beleska: Beleske ->
            beleskeViewModel.deleteBeleske(beleska.id)
        },
            {beleska: Beleske ->
                val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                //transaction.addToBackStack(null)
                transaction.replace(R.id.mainFragmentFcv, EditFragment(beleska.id, beleska.naslov, beleska.sadrzaj, beleska.arhiviran))
                transaction.remove(this)
                transaction.commit()
            },
            {beleska: Beleske ->
                var pom = beleska.arhiviran
                if (pom == true){
                    beleska.arhiviran = false
                }
                else{
                    beleska.arhiviran = true
                }
                beleskeViewModel.updateBeleske(beleska.id, beleska.naslov, beleska.sadrzaj, beleska.arhiviran)
            })
        binding.recyclerView.adapter = adapter
    }

    private fun initListeners() {
        dodaj = binding.imageButton
        switch = binding.switch1
        pretraga = binding.beleskeEt

        pretraga.doAfterTextChanged {
            beleskeViewModel.getAllByNaslovSadrzaj(it.toString())
        }

        dodaj.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            //transaction.addToBackStack(null)
            transaction.replace(R.id.mainFragmentFcv, DodajFragment())
            transaction.remove(this)
            transaction.commit()
        }

        switch.setOnClickListener {
            if(switch.isChecked){
                beleskeViewModel.getAllByArhiviran()
            }
            else{
                beleskeViewModel.getAllBeleske()
            }
        }

        dodaj.setOnLongClickListener(){
            val pref: SharedPreferences by inject()
            pref.edit().clear().apply()
            return@setOnLongClickListener true
        }
    }

    private fun initObservers() {
        beleskeViewModel.beleskeState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        if(switch.isChecked){
            beleskeViewModel.getAllByArhiviran()
            return
        }
        else{
            beleskeViewModel.getAllBeleske()
            return
        }
       // beleskeViewModel.getAllBeleske()
    }

    private fun renderState(state: BeleskaState) {
        when (state) {
            is BeleskaState.Success -> {
                adapter.submitList(state.beleska)
            }
            is BeleskaState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}