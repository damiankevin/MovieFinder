package id.com.android.moviedb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import id.com.android.moviedb.TypeStatus
import id.com.android.moviedb.feature.interfacelayer.InterfaceContentCollection
import id.com.android.moviedb.holder.HolderListMovie
import id.com.android.moviedb.holder.HolderStatusFooter
import id.com.android.moviedb.model.ModelItemMovie
import id.com.android.moviedb.model.ModelMovie

class AdapterCollectionMovie(context: Context?) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_TYPE_FOOTER_STATUS: Int = 0
        const val ITEM_TYPE_MOVIE: Int = 1
        const val CLASS_TAG: String = "Adapter Movie"
    }

    var collectionMovie: ArrayList<ModelItemMovie> = ArrayList()
    private var itemContext: Context? = context
    var footerStatusCode: Long? = TypeStatus.INITIAL_STATUS
    var context: Context? = context
    var interfaceContentCollection: InterfaceContentCollection? = null

    override fun getItemViewType(position: Int): Int {
        return if (itemCount - 1 == position) { ITEM_TYPE_FOOTER_STATUS }
        else{
            ITEM_TYPE_MOVIE
        }

    }

    override fun getItemCount(): Int {
        return collectionMovie?.size!!+1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(itemContext)
        when (viewType) {
            ITEM_TYPE_MOVIE -> {
                val itemList = layoutInflater.inflate(HolderListMovie.LAYOUT_RESOURCE, parent, false)
                return HolderListMovie(itemList, itemContext,interfaceContentCollection)

            }
            else -> {
                val itemViewFooterStatus = layoutInflater.inflate(HolderStatusFooter.LAYOUT_RESOURCE, parent, false)
                return HolderStatusFooter(itemViewFooterStatus, context)
            }
        }
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        when (holder?.itemViewType) {
            ITEM_TYPE_MOVIE -> {
                (holder as? HolderListMovie)?.bindingContent(collectionMovie?.get(position))
            }
            ITEM_TYPE_FOOTER_STATUS -> {
                (holder as? HolderStatusFooter)?.bindingContent(footerStatusCode!!)
            }
        }
    }


}