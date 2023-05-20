package com.tutorial.tvapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)

        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        val size = getWidthAndHeightInPercent(parent!!.context, 25) // Ubah persentase sesuai kebutuhan
        val margin = convertDpToPixel(5, parent.context) // Menambahkan jarak dalam piksel (misalnya 8dp)

        params.width = size
        params.height = size
        params.setMargins(margin, margin, margin, margin) // Menetapkan jarak untuk margin kiri, atas, kanan, dan bawah

        return ViewHolder(view)
    }
    fun convertDpToPixel(dp: Int, context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
    fun getWidthAndHeightInPercent(context: Context, percent: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels
        val size = if (screenWidth < screenHeight) screenWidth else screenHeight
        return (size * percent) / 100
    }

//    fun getWidthInPercent(context: Context, percent: Int): Int {
//        val width = context.resources.displayMetrics.widthPixels ?: 0
//        return (width * percent) / 100
//    }
//
//    fun getHeightInPercent(context: Context, percent: Int): Int {
//        val width = context.resources.displayMetrics.heightPixels ?: 0
//        return (width * percent) / 100
//    }
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