import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.splashscreenlastproject.R
import com.example.splashscreenlastproject.model.Article
import com.squareup.picasso.Picasso

class ArticleAdapter (
    val articles : List<Article>
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private val  IMAGE_BASE = "https://c4.wallpaperflare.com/wallpaper/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ArticleViewHolder{
        var inflate = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv, parent, false)

        return ArticleViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int,) {
        var data = articles.get(position)

        holder.title.text = data.title
        holder.creator.text = data.creator
        Glide.with(holder.itemView)
            .load(IMAGE_BASE + data.image)
            .into(holder.itemView.findViewById<ImageView>(R.id.image_view))
//        Picasso.get().load(IMAGE_BASE + data.image).fit().centerCrop().
    }

    override fun getItemCount(): Int = articles.size
    class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView = itemView.findViewById(R.id.title_view)
        var creator : TextView = itemView.findViewById(R.id.creator_view)
        var image : ImageView = itemView.findViewById(R.id.image_view)
    }
}