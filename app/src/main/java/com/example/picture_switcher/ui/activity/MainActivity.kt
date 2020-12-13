package com.example.picture_switcher.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picture_switcher.R
import com.example.picture_switcher.data.UnsplashPhoto
import com.example.picture_switcher.network.ApiClient
import com.example.picture_switcher.network.OnResultListener
import com.example.picture_switcher.ui.adapter.PictureAdapter
import com.example.picture_switcher.ui.fragment.BigImageFragment
import com.example.picture_switcher.utils.KeyboardUtil


const val ERROR_TAG = "ERROR"
const val FRAGMENT_TAG = "bigPictureFragmentTag"

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var searchView: SearchView? = null
    private var fragmentContainer: FrameLayout? = null
    private var noSearchImagesTextView: TextView? = null

    lateinit var layoutManager: LinearLayoutManager

    private var randomPhotoAdapter: PictureAdapter? = null
    private var searchPhotoAdapter: PictureAdapter? = null
    private var photoList: MutableList<UnsplashPhoto>? = mutableListOf()
    private var searchPhotoList: MutableList<UnsplashPhoto>? = mutableListOf()
    private var currentPage: Int = 1
    private var request: String? = null

    private val apiClient = ApiClient()

    companion object {
        private const val REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!haveStoragePermission()) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE
            )
        }

        initViews()

        layoutManager = GridLayoutManager(this, 2)
        recyclerView?.layoutManager = layoutManager

        getRandomPhoto()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)
        noSearchImagesTextView = findViewById(R.id.no_search_images_text_view)
        fragmentContainer = findViewById(R.id.fragment_container)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                request = query.toString()
                searchPhotoList!!.clear()
                searchPhoto()
                KeyboardUtil().hideSoftKeyboard(searchView)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getRandomPhoto() {
        apiClient.getRandomPhoto(object : OnResultListener {
            override fun onSuccess(photoListResponse: MutableList<UnsplashPhoto>) {
                photoList!!.addAll(photoListResponse)
                updatedAdapter(true, photoList!!)
            }

            override fun onEmptySearch() {
            }

            override fun onError(message: String?) {
                Log.e(ERROR_TAG, message.toString())
                showErrorToast()
            }
        }, currentPage)
    }

    private fun searchPhoto() {
        apiClient.searchPhoto(request!!, object : OnResultListener {
            override fun onSuccess(photoListResponse: MutableList<UnsplashPhoto>) {
                noSearchImagesTextView?.visibility = View.GONE
                searchPhotoList!!.addAll(photoListResponse)
                updatedAdapter(false, searchPhotoList!!)
            }

            override fun onEmptySearch() {
                photoList?.clear()
                if (randomPhotoAdapter != null) {
                    randomPhotoAdapter!!.notifyDataSetChanged()
                }
                noSearchImagesTextView?.visibility = View.VISIBLE
            }

            override fun onError(message: String?) {
                Log.e(ERROR_TAG, message.toString())
                showErrorToast()
            }
        }, currentPage)
    }

    private fun updatedAdapter(isRandomPhoto: Boolean, photoList: MutableList<UnsplashPhoto>) {
        if (isRandomPhoto) {
            if (randomPhotoAdapter != null) {
                randomPhotoAdapter!!.notifyDataSetChanged()
            } else {
                randomPhotoAdapter = PictureAdapter(
                    isRandomPhoto,
                    photoList,
                    object : PictureAdapter.NeedNewPage {
                        override fun nextPageRandom() {
                            currentPage++
                            getRandomPhoto()
                        }

                        override fun nextPageSearch() {
                            currentPage++
                            searchPhoto()
                        }

                    }, object : PictureAdapter.OnImageClickCallback {
                        override fun onImageClick(unsplashPhoto: UnsplashPhoto) {
                            showBigImageFragment(unsplashPhoto)
                        }

                    })
                recyclerView?.adapter = randomPhotoAdapter
            }
        } else {
            if (searchPhotoAdapter != null) {
                searchPhotoAdapter!!.notifyDataSetChanged()
            } else {
                currentPage = 1
                searchPhotoAdapter = PictureAdapter(
                    isRandomPhoto,
                    photoList,
                    object : PictureAdapter.NeedNewPage {
                        override fun nextPageRandom() {
                            currentPage++
                            getRandomPhoto()
                        }

                        override fun nextPageSearch() {
                            currentPage++
                            searchPhoto()
                        }

                    }, object : PictureAdapter.OnImageClickCallback {
                        override fun onImageClick(unsplashPhoto: UnsplashPhoto) {
                            showBigImageFragment(unsplashPhoto)
                        }

                    })
                recyclerView?.adapter = searchPhotoAdapter
            }
        }
    }

    fun showBigImageFragment(unsplashPhoto: UnsplashPhoto) {
        try {
            val bugImageFragment: BigImageFragment =
                BigImageFragment.newInstance(unsplashPhoto)

            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(
                    R.id.fragment_container,
                    bugImageFragment,
                    FRAGMENT_TAG
                )
                .addToBackStack(bugImageFragment.javaClass.name)
                .commit()
        } catch (illExc: IllegalArgumentException) {
        } catch (nullExc: NullPointerException) {
        }
    }

    private fun showErrorToast() {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
    }

    private fun haveStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    1
                )
                false
            }
        } else {
            true
        }
    }
}