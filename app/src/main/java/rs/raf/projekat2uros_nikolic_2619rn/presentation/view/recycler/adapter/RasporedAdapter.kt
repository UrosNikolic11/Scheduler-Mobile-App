package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Raspored
import rs.raf.projekat2uros_nikolic_2619rn.databinding.LayoutItemMovieBinding
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.diff.RasporedDiffCallback
import rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.viewholder.RasporedViewHolder

class RasporedAdapter : ListAdapter<Raspored, RasporedViewHolder>(RasporedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RasporedViewHolder {
        val itemBinding = LayoutItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RasporedViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RasporedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}