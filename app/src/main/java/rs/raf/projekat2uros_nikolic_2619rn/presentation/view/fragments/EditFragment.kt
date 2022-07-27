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
import rs.raf.projekat2uros_nikolic_2619rn.databinding.FragmentEditBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.contract.BeleskeConcract
import rs.raf.projekat2uros_nikolic_2619rn.presentation.viewmodel.BeleskeViewModel

class EditFragment(
    private var id: Long?,
    private var naslovBeleske: String,
    private var sadrzajBeleske: String,
    private var arhiviran: Boolean
): Fragment(R.layout.fragment_edit) {

    private val beleskeViewModel: BeleskeConcract.ViewModel by sharedViewModel<BeleskeViewModel>()

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var naslov: EditText
    private lateinit var sadrzaj: EditText
    private lateinit var izmeni: Button
    private lateinit var odustani: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
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
        naslov = binding.editNaslovTv
        sadrzaj = binding.editSadrzajTv
        izmeni = binding.izmeniBtn
        odustani = binding.odustaniEditBtn

        naslov.setText(naslovBeleske)
        sadrzaj.setText(sadrzajBeleske)
    }

    private fun initListeners(){
        izmeni.setOnClickListener {
            beleskeViewModel.updateBeleske(id, naslov.text.toString(), sadrzaj.text.toString(), arhiviran)

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