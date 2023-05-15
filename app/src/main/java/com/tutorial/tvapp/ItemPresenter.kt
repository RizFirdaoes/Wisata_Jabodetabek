package com.tutorial.tvapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {

        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)

        val params = view.layoutParams
        params.width = getWidthInPercent(parent!!.context, 12)
        params.height = getHeightInPercent(parent!!.context, 32)

        return ViewHolder(view)

    }

    fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    fun getHeightInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.heightPixels ?: 0
        return (width * percent) / 100
    }
    fun getDrawableResourceId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {

        val content = item as? DataModel.Result.Detail

        val imageview = viewHolder?.view?.findViewById<ImageView>(R.id.poster_image)
//        val backdropImageView = viewHolder?.view?.findViewById<ImageView>(R.id.imgBanner)

        val resourceId = getDrawableResourceId(viewHolder?.view?.context!!, content?.poster_path ?: "")
        Glide.with(viewHolder.view.context)
            .load(resourceId)
            .into(imageview!!)
//
//        val backdropResourceId = getDrawableResourceId(viewHolder?.view?.context!!, content?.backdrop_path ?: "")
//        Glide.with(viewHolder.view.context)
//            .load(backdropResourceId)
//            .into(backdropImageView!!)


    }


    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}