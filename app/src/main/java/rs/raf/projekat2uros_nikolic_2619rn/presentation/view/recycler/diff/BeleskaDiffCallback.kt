package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Beleske

class BeleskaDiffCallback : DiffUtil.ItemCallback<Beleske>() {
    override fun areItemsTheSame(oldItem: Beleske, newItem: Beleske): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Beleske, newItem: Beleske): Boolean {
        return oldItem.naslov == newItem.naslov
    }
}