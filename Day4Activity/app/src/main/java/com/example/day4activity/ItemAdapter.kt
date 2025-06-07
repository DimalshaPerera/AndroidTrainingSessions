
package com.example.day4activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.day4activity.data.Item

class ForumAdapter(private val items: List<Item>) : RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.textView9)
        val descriptionText: TextView = itemView.findViewById(R.id.textView10)
        val avatarContainer: LinearLayout = itemView.findViewById(R.id.avatarContainer)
        val joinForumLayout: LinearLayout = itemView.findViewById(R.id.joinForumLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ForumViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        val item = items[position]

        holder.titleText.text = item.title
        holder.descriptionText.text = item.description

        val avatarViews = listOf<ImageView>(
            holder.avatarContainer.findViewById(R.id.imageView4),
            holder.avatarContainer.findViewById(R.id.imageView5),
            holder.avatarContainer.findViewById(R.id.imageView6)
        )

        item.userAvatars.forEachIndexed { index, avatarRes ->
            if (index < avatarViews.size) {
                avatarViews[index].setImageResource(avatarRes)
            }
        }


        holder.joinForumLayout.setOnClickListener {
        }
    }

    override fun getItemCount(): Int = items.size
}