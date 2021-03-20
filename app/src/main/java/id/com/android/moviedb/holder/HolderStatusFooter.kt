package id.com.android.moviedb.holder

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import id.com.android.moviedb.R
import id.com.android.moviedb.TypeStatus
import id.com.android.moviedb.tools.UtilConstant
import kotlinx.android.synthetic.main.holder_status_footer.view.*

class HolderStatusFooter(itemView: View?, context: Context?) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView!!) {

    companion object {
        const val LAYOUT_RESOURCE : Int     = id.com.android.moviedb.R.layout.holder_status_footer
        const val CLASS_TAG : String        = "Holder Status Footer"
    }

    private var indicatorAnimation: AnimatedVectorDrawableCompat?   = null
    private var itemContext: Context?                               = context

    fun bindingContent (statusCode : Long) {
        when (statusCode) {
            TypeStatus.ERROR_CONNECTION -> {
                itemView?.view_status_footer_progress?.visibility = View.GONE
                itemView?.view_status_footer_title_textview?.visibility = View.VISIBLE
                itemView?.view_status_footer_desc_textview?.visibility = View.VISIBLE
            }
            TypeStatus.ERROR_DATA_EMPTY -> {
                itemView?.view_status_footer_progress?.visibility = View.GONE
                itemView?.view_status_footer_title_textview?.visibility = View.VISIBLE
                itemView?.view_status_footer_desc_textview?.visibility = View.VISIBLE
            }

            TypeStatus.LOADING_PROGRESS -> {
                itemView?.view_status_footer_progress?.visibility = View.VISIBLE
                itemView?.view_status_footer_title_textview?.visibility = View.GONE
                itemView?.view_status_footer_desc_textview?.visibility = View.GONE
            }
            TypeStatus.SUCCESS_LOAD -> {
                itemView?.view_status_footer_progress?.visibility = View.GONE
                itemView?.view_status_footer_title_textview?.visibility = View.GONE
                itemView?.view_status_footer_desc_textview?.visibility = View.GONE
            }

            TypeStatus.ERROR_DATA_COMPLETE -> {
                itemView?.view_status_footer_progress?.visibility = View.GONE
                itemView?.view_status_footer_title_textview?.visibility = View.VISIBLE
                itemView?.view_status_footer_desc_textview?.visibility = View.VISIBLE
            }

        }
        showContentLoading()
        showContentStatus(statusCode)
        showFullscreenStatus()
    }

    private fun showFullscreenStatus() {
        if (adapterPosition == 0) {
            itemView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        } else {
            itemView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
    }

    private fun showContentStatus(statusCode: Long) {
        itemView?.view_status_footer_title_textview?.text =
            TypeStatus.getStatusTitle(itemContext, statusCode)
        itemView?.view_status_footer_title_textview?.setCompoundDrawablesWithIntrinsicBounds(
            UtilConstant.INITIAL_RESOURCE, TypeStatus.getStatusIcon(statusCode),
            UtilConstant.INITIAL_RESOURCE,  UtilConstant.INITIAL_RESOURCE)
        itemView?.view_status_footer_desc_textview?.text =
            TypeStatus.getStatusMessage(itemContext, statusCode)
    }

    private fun showContentLoading() {
        if (itemView?.view_status_footer_progress?.visibility == View.GONE && indicatorAnimation != null) {
            indicatorAnimation?.stop()
            indicatorAnimation = null
        } else {
            itemContext?.let {
                indicatorAnimation = AnimatedVectorDrawableCompat.create(it, R.drawable.al_loading_content_black)
                itemView?.view_status_footer_progress?.setImageDrawable(indicatorAnimation)
                indicatorAnimation?.start()
            }
        }
    }

}