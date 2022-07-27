package rs.raf.projekat2uros_nikolic_2619rn.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2uros_nikolic_2619rn.data.models.Raspored
import rs.raf.projekat2uros_nikolic_2619rn.databinding.LayoutItemMovieBinding

class RasporedViewHolder(private val itemBinding: LayoutItemMovieBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(raspored: Raspored) {
        itemBinding.titleTv.text = raspored.predmet + " - "+  raspored.tip
        itemBinding.profesorTv.text = raspored.nastavnik
        itemBinding.ucionicaTv.text = raspored.ucionica
        itemBinding.grupaTv.text = raspored.grupe
        itemBinding.danTv.text = raspored.dan
        itemBinding.terminTv.text = raspored.termin
    }

}