package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2uros_nikolic_2619rn.R
import rs.raf.projekat2uros_nikolic_2619rn.databinding.FragmentDodajBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.contract.BeleskeConcract
import rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel.BeleskeViewModel

class DodajFragment: Fragment(R.layout.fragment_dodaj) {

    private val beleskeViewModel: BeleskeConcract.ViewModel by sharedViewModel<BeleskeViewModel>()

    private var _binding: FragmentDodajBinding? = null
    private val binding get() = _binding!!
    private lateinit var dodaj: Button
    private lateinit var odustani: Button
    private lateinit var naslov: EditText
    private lateinit var sadrzaj: EditText


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDodajBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        initUI()
        initListeners()
    }

    private fun initUI(){
        dodaj = binding.dodajBtn
        odustani = binding.odustaniBtn
        naslov = binding.dodajNaslovTv
        sadrzaj = binding.dodajSadrzajTv
    }

    private fun initListeners(){
        dodaj.setOnClickListener {
            beleskeViewModel.insertBeleske(null,naslov.text.toString(),sadrzaj.text.toString(),false)

            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragmentFcv, GlavniFragment())
            transaction.commit()
        }

        odustani.setOnClickListener {
            val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.mainFragmentFcv, GlavniFragment())
            transaction.commit()
        }
    }
}