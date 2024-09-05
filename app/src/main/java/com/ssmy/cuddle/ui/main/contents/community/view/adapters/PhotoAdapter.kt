package com.ssmy.cuddle.ui.main.contents.community.view.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ssmy.cuddle.databinding.ItemPhotoBinding
import com.ssmy.cuddle.ui.main.contents.community.viewmodels.PostCreationViewModel

/**
 * doc 주석
 * @author wookjin
 * @since 9/4/24
 **/
class PhotoAdapter(private val viewModel: PostCreationViewModel) :
    ListAdapter<Uri, PhotoAdapter.PhotoViewHolder>(UriDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photoUri: Uri) {
            binding.photoImage.setImageURI(photoUri)

            // Set delete button functionality
            binding.deletePhotoButton.setOnClickListener {
                viewModel.removePhoto(photoUri)
            }
        }
    }

    class UriDiffCallback : DiffUtil.ItemCallback<Uri>() {
        override fun areItemsTheSame(oldItem: Uri, newItem: Uri): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Uri, newItem: Uri): Boolean = oldItem == newItem
    }
}