package com.ssmy.cuddle.ui.main.contents.community.view.activitys

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.ssmy.cuddle.R
import com.ssmy.cuddle.databinding.ActivityPostCreationBinding
import com.ssmy.cuddle.ui.base.BaseActivity
import com.ssmy.cuddle.ui.main.contents.community.view.adapters.PhotoAdapter
import com.ssmy.cuddle.ui.main.contents.community.viewmodels.PostCreationViewModel

class PostCreationActivity : BaseActivity<PostCreationViewModel>() {

    override val viewModel: PostCreationViewModel by viewModels()
    private lateinit var binding: ActivityPostCreationBinding
    private lateinit var photoPickerLauncher: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var fallbackPhotoPickerLauncher: ActivityResultLauncher<Intent>
    private val photoAdapter by lazy { PhotoAdapter(viewModel) }

    private var maxPhotos = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_creation)

        setupRecyclerView()
        setupPhotoPicker()

        viewModel.selectedPhotos.observe(this) { photos ->
            photoAdapter.submitList(photos)
        }

        viewModel.isAddPhotoButtonEnabled.observe(this) { isEnabled ->
            binding.addPhotoButton.isEnabled = isEnabled
        }

        viewModel.photoCountText.observe(this) { countText ->
            binding.photoCountText.text = countText
        }

        binding.addPhotoButton.setOnClickListener {
            launchPhotoPicker()
        }

        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupRecyclerView() {
        binding.photoRecyclerView.apply {
            layoutManager = GridLayoutManager(this@PostCreationActivity, 5)
            adapter = photoAdapter
        }
    }

    private fun setupPhotoPicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            photoPickerLauncher = registerForActivityResult(
                ActivityResultContracts.PickMultipleVisualMedia(maxPhotos)
            ) { selectedUris: List<Uri>? ->
                selectedUris?.let { uris ->
                    if (uris.isNotEmpty()) {
                        viewModel.addPhotos(uris)
                    } else {
                        Toast.makeText(this, "선택된 파일이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            fallbackPhotoPickerLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == RESULT_OK) {
                    val currentPhotoCount = viewModel.selectedPhotos.value?.size ?: 0
                    val remaining = maxPhotos - currentPhotoCount

                    val selectedUris = result.data?.clipData?.let { clipData ->
                        (0 until clipData.itemCount.coerceAtMost(remaining)).map { index ->
                            clipData.getItemAt(index).uri
                        }
                    } ?: result.data?.data?.let { listOf(it) }

                    selectedUris?.let { uris ->
                        viewModel.addPhotos(uris)
                    }
                }
            }
        }
    }

    private fun launchPhotoPicker() {
        val currentPhotoCount = viewModel.selectedPhotos.value?.size ?: 0
        val remaining = maxPhotos - currentPhotoCount

        if (remaining > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                photoPickerLauncher.launch(null)
            } else {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                }
                fallbackPhotoPickerLauncher.launch(intent)
            }
        } else {
            Toast.makeText(this, "선택할 수 있는 파일은 ${maxPhotos}개 입니다", Toast.LENGTH_SHORT).show()
        }
    }
}