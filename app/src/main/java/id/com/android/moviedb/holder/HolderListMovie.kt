package id.com.android.moviedb.holder

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import id.com.android.moviedb.R
import id.com.android.moviedb.feature.interfacelayer.InterfaceContentCollection
import id.com.android.moviedb.model.ModelItemMovie
import id.com.android.moviedb.tools.UtilConstant
import id.com.android.moviedb.tools.UtilImage
import kotlinx.android.synthetic.main.holder_list_movie.view.*

class HolderListMovie(itemView : View?, context : Context?,interfaceContentCollection: InterfaceContentCollection?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE: Int  = id.com.android.moviedb.R.layout.holder_list_movie
        const val CLASS_TAG : String    = "Holder List Movie"
    }

    private var itemContext : Context?                  = context
    private var interfaceContentCollection              = interfaceContentCollection


    fun bindingContent (content : ModelItemMovie?) {

       UtilImage.loadImageWithoutPlaceholder(itemView?.view_holder_movie_image,  UtilConstant.BASE_URL_IMAGE_SMALL+content?.poster_path, itemContext)

        content?.title?.let {
            itemView?.view_holder_movie_title?.text = it.trim()
        }
        content?.overview?.let {
            itemView?.view_holder_movie_desc?.text = it.trim()
        }
        content?.release_date?.let {
            itemView?.view_holder_movie_date?.text = it.trim()
        }
        content?.hasFavourite?.let {
            itemView?.view_holder_love?.setImageResource(if (it) R.drawable.ic_heart_red else R.drawable.ic_heart)
        }
        itemView?.setOnClickListener {
            content?.let { interfaceContentCollection?.onContentSelected(it) }
        }

        itemView?.view_holder_love?.setOnClickListener {
            content?.let {
                if (it.hasFavourite == true) { interfaceContentCollection?.onContentUnFavourite(content, adapterPosition) }
                else { interfaceContentCollection?.onContentFavourite(it, adapterPosition) }
            }
        }

    }



}