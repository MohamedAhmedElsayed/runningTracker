package com.thirdwayv.tracker.features.history.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thirdwayv.tracker.core.base.extentions.toFormattedTimeString
import com.thirdwayv.tracker.databinding.TripDetailsBinding
 import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel

class HistoryAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    val diffCallBack = object : DiffUtil.ItemCallback<TrackingTripModel>() {

        override fun areItemsTheSame(
            oldItem: TrackingTripModel,
            newItem: TrackingTripModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TrackingTripModel,
            newItem: TrackingTripModel
        ): Boolean {
            return oldItem.isCompleted == newItem.isCompleted
                    && oldItem.steps == newItem.steps
                    && oldItem.duration == newItem.duration
                    && oldItem.distace == newItem.distace
                    && oldItem.locations == newItem.locations
        }

    }
    private val differ = AsyncListDiffer(this, diffCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {

        return HistoryViewHolder(
            TripDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        holder.bind(differ.currentList[position])

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<TrackingTripModel>) {
        differ.submitList(list)
    }

    class HistoryViewHolder
    constructor(
        private val historyItem: TripDetailsBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(historyItem.root) {

        fun bind(item: TrackingTripModel) {
            with(historyItem) {
                itemView.setOnClickListener {
                    interaction?.onItemSelected(adapterPosition, item)
                }
                stepsTv.text = item.steps.toString()
                durationTv.text = item.duration.toFormattedTimeString()
                distanceTv.text = String.format("%.2f", item.distace)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: TrackingTripModel)
    }


}
