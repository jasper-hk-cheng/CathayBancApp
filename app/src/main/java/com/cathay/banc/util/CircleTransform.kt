package com.cathay.banc.util

import android.graphics.*
import com.squareup.picasso.Transformation

object CircleTransform : Transformation {

    override fun transform(source: Bitmap): Bitmap {

        val size = source.width.coerceAtMost(source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        /*
            clip as square at the part of the rectangle center
         */
        val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
        if (squaredBitmap != source) {
            source.recycle()
        }
        /*
            ellipsis
         */
        val shader = BitmapShader(
            squaredBitmap,
            Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        val paint = Paint()
        paint.shader = shader
        paint.isAntiAlias = true
        /*
            circle radius
         */
        val radius = size / 2f
        /*
            create canvas and draw
         */
        val bitmap = Bitmap.createBitmap(size, size, source.config)
        val canvas = Canvas(bitmap)
        canvas.drawCircle(radius, radius, radius, paint)
        /*
            release bitmap
         */
        squaredBitmap.recycle()
        //
        return bitmap
    }

    override fun key(): String {
        return "circle"
    }
}