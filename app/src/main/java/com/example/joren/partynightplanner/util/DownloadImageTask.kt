package com.example.joren.partynightplanner.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import android.widget.Toast
import java.net.URL

class DownloadImageTask(val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlOfImage = urls[0]
        return try {
            val inputStream = URL(urlOfImage).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
            null
        }
    }
    override fun onPostExecute(result: Bitmap?) {
        if(result!=null){
            // Display the downloaded image into image view
            imageView.setImageBitmap(result)
        }else{
            Toast.makeText(imageView.context,"Error downloading image", Toast.LENGTH_SHORT).show()
        }
    }
}

