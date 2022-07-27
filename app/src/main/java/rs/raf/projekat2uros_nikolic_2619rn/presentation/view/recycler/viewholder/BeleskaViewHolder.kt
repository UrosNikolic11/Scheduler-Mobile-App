package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske
import rs.raf.projekat2uros_nikolic_2619rn.databinding.LayoutItemBeleskeBinding
import java.util.function.Consumer

class BeleskaViewHolder(
    private val itemBinding: LayoutItemBeleskeBinding,
    private val obrisiClick: Consumer<Int>,
    private val editClick: Consumer<Int>,
    private val arhivirajClick: Consumer<Int>
) : RecyclerView.ViewHolder(itemBinding.root) {

    init{
        itemBinding.imageButton2.setOnClickListener{
            obrisiClick.accept(bindingAdapterPosition)
        }

        itemBinding.imageButton3.setOnClickListener{
            editClick.accept(bindingAdapterPosition)
        }

        itemBinding.imageButton4.setOnClickListener{
            arhivirajClick.accept(bindingAdapterPosition)
        }
    }

    fun bind(beleska: Beleske){
        itemBinding.naslovTv.text = beleska.naslov
        itemBinding.sadrzajTv.text = beleska.sadrzaj
    }
}