package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Raspored

class RasporedDiffCallback : DiffUtil.ItemCallback<Raspored>() {

    override fun areItemsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Raspored, newItem: Raspored): Boolean {
        return oldItem.nastavnik == newItem.nastavnik
    }

}