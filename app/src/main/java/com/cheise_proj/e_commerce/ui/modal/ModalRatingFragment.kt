package com.cheise_proj.e_commerce.ui.modal

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.utils.LAYOUT_PADDING_SMALL
import com.cheise_proj.e_commerce.utils.RealPathUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_modal_rating.*
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class ModalRatingFragment : BottomSheetDialogFragment() {
    companion object {
        const val MODAL_RATING_TAG = "ModalRatingFragment"
        fun newInstance() =
            ModalRatingFragment()

        private const val GALLERY_REQUEST = 100
        private const val STORAGE_REQUEST = 220
    }

    private val imageList: ArrayList<String> = arrayListOf()
    private lateinit var viewModel: ModelViewModel
    private var captureImagePath = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_image.setOnClickListener {
            if (isStoragePermissionGranted()) {
                pickAnImage()
            }
        }
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[ModelViewModel::class.java]
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.imageList.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { data -> loadImages(data) })
    }

    private fun loadImages(data: List<String>?) {
        img_container.removeAllViews()
        data?.let { img ->
            Timber.i("data: $img")
            img.forEach { imageUri ->
                val imageView = LayoutInflater.from(context)
                    .inflate(R.layout.image_review_placeholder, img_container, false) as ImageView
                imageView.setPadding(LAYOUT_PADDING_SMALL, 0, LAYOUT_PADDING_SMALL, 0)
                GlideApp.with(requireContext()).load(imageUri).centerCrop().into(imageView)
                img_container.addView(imageView)
            }
        }
    }

    private fun getGalleryIntent(): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        return intent
    }


    private fun pickAnImage() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile: File? = createImageFile()
        val photoUri = photoFile?.let {
            FileProvider.getUriForFile(
                requireContext(), getString(R.string.file_provider_authority, context?.packageName),
                it
            )
        }
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        val chooser = Intent.createChooser(getGalleryIntent(), "Select an option")
        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(cameraIntent))
        activity?.startActivityFromFragment(this, chooser, GALLERY_REQUEST)
        Timber.i("open camera / gallery intent")
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? =
          requireContext().cacheDir
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir).apply {
            captureImagePath = this.absolutePath

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST) {
            if (data != null) {
                val selectedFile = data.data
                try {
                    selectedFile?.let { uri ->
                        captureImagePath = context?.let { RealPathUtil.getRealPath(it, uri) } ?: ""
                    }
                } catch (e: Exception) {

                    e.printStackTrace()
                }

                Timber.i("image uri $captureImagePath")
                imageList.add(captureImagePath)
                viewModel.addImage(imageList)
            } else {
                Timber.i("image uri $captureImagePath")
                imageList.add(captureImagePath)
                viewModel.addImage(imageList)
            }
        }
    }

    private fun isStoragePermissionGranted(): Boolean {
        return if (checkSelfPermission(
                requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Timber.i("Permission is granted")
            true
        } else {
            Timber.i("Permission is revoked")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_REQUEST
            )
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickAnImage()
        }
    }


}
