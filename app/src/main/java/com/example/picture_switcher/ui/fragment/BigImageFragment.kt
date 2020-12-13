package com.example.picture_switcher.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.picture_switcher.R
import com.example.picture_switcher.data.UnsplashPhoto
import com.example.picture_switcher.ui.activity.MainActivity
import com.example.picture_switcher.utils.DownloadImage
import com.squareup.picasso.Picasso


const val SELECTED_IMAGE = "selectedImage"

class BigImageFragment : Fragment() {

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var bigImageView: ImageView
    private lateinit var saveImage: TextView

    private var unsplashPhoto: UnsplashPhoto? = null
    private lateinit var mainActivity: MainActivity

    companion object {
        fun newInstance(
            unsplashPhoto: UnsplashPhoto
        ): BigImageFragment {
            return BigImageFragment().apply {
                arguments = bundleOf(SELECTED_IMAGE to unsplashPhoto)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_big_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        unsplashPhoto = requireArguments().getParcelable(SELECTED_IMAGE) as UnsplashPhoto?

        initViews()
    }

    private fun initViews() {
        toolbar = view!!.findViewById(R.id.toolbar)
        bigImageView = view!!.findViewById(R.id.big_image_view)
        saveImage = view!!.findViewById(R.id.save_image_text_view)

        toolbar.setNavigationOnClickListener {
            mainActivity.onBackPressed()
        }

        saveImage.setOnClickListener {
            DownloadImage().download(activity!!, unsplashPhoto!!.urls.regular)
        }

        Picasso.get().load(unsplashPhoto?.urls?.regular).into(bigImageView)
    }
}