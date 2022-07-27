package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske
import rs.raf.projekat2uros_nikolic_2619rn.databinding.LayoutItemBeleskeBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.diff.BeleskaDiffCallback
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.viewholder.BeleskaViewHolder
import java.util.function.Consumer

class BeleskaAdapter(
    private val obrisiClick: Consumer<Beleske>,
    private val editClick: Consumer<Beleske>,
    private val arhivirajClick: Consumer<Beleske>
) : ListAdapter<Beleske, BeleskaViewHolder>(BeleskaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeleskaViewHolder {
        val itemBinding = LayoutItemBeleskeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BeleskaViewHolder(itemBinding, {position: Int ->
            val beleske = getItem(position)
            obrisiClick.accept(beleske)
        },{position: Int ->
            val beleske = getItem(position)
            editClick.accept(beleske)
        },{position: Int ->
            val beleske = getItem(position)
            arhivirajClick.accept(beleske)
        })
    }

    override fun onBindViewHolder(holder: BeleskaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}