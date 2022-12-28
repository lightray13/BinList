package com.test.binlist.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.binlist.R
import com.test.binlist.data.local.database.CardListEntity
import com.test.binlist.databinding.ItemCardListBinding

class CardListAdapter : RecyclerView.Adapter<CardListAdapter.CardListViewHolder>() {
    private val cardList = mutableListOf<CardListEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardListViewHolder {
        return CardListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardListViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun updateList(list: List<CardListEntity>) {
        this.cardList.clear()
        this.cardList.addAll(list)
        notifyDataSetChanged()
    }

    class CardListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCardListBinding.bind(itemView)

        fun bind(model: CardListEntity) {

            binding.schemeTextView.text = model.scheme
            binding.brandTextView.text = model.brand
            binding.cardNumberLengthTextView.text = model.length.toString()
            if (model.luhn == true) {
                binding.cardNumberLuhnYesTextView.setTextColor(Color.BLACK)
                binding.cardNumberLuhnNoTextView.setTextColor(Color.GRAY)
            } else {
                binding.cardNumberLuhnYesTextView.setTextColor(Color.GRAY)
                binding.cardNumberLuhnNoTextView.setTextColor(Color.BLACK)
            }
            binding.bankTextView.text = model.bankName
            binding.bankWebsiteTextView.text = model.url
            binding.bankPhoneTextView.text = model.phone
            if(model.type == "debit") {
                binding.typeDebitTextView.setTextColor(Color.BLACK)
                binding.typeCreditTextView.setTextColor(Color.GRAY)
            } else {
                binding.typeDebitTextView.setTextColor(Color.GRAY)
                binding.typeCreditTextView.setTextColor(Color.BLACK)
            }
            if (model.prepaid == true) {
                binding.prepaidYesTextView.setTextColor(Color.BLACK)
                binding.prepaidNoTextView.setTextColor(Color.GRAY)
            } else {
                binding.prepaidYesTextView.setTextColor(Color.GRAY)
                binding.prepaidNoTextView.setTextColor(Color.BLACK)
            }
            binding.countryTextView.text = "${model.emoji} ${model.countryName}"
            binding.countryLatitudeTextView.text = model.latitude?.toInt().toString()
            binding.countryLongitudeTextView.text = model.longitude?.toInt().toString()
            binding.bankPhoneTextView.text = model.phone
        }
    }
}