package Guide.suchelin.Vote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import Guide.suchelin.R
import Guide.suchelin.DataClass.StoreDataClass
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class VoteRvAdapter(val items: MutableList<StoreDataClass>):RecyclerView.Adapter<VoteRvAdapter.ViewHolder>() {
    interface ItemClick{
        fun onClick(view :View, position: Int)
    }
    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vote_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(itemClick != null){
            holder.itemView.setOnClickListener{v->
                itemClick!!.onClick(v, position)
            }
        }
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageViewShop = itemView.findViewById<ImageView>(R.id.vote_item_imageView)
        val textViewTitle = itemView.findViewById<TextView>(R.id.vote_item_store_textView)
        val michelinImage = itemView.findViewById<ImageView>(R.id.vote_item_michelin_imageView)

        fun bindItems(item: StoreDataClass){
            Glide.with(imageViewShop)
                .load(item.imageUrl)
                .centerCrop()
                .into(imageViewShop)

            textViewTitle.text = item.name
        }
    }

}