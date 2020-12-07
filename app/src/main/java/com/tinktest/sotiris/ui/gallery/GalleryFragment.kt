package com.tinktest.sotiris.ui.gallery

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.transition.TransitionInflater
import com.tinktest.sotiris.databinding.FragmentGalleryBinding
import com.tinktest.sotiris.models.PugInfo
import com.tinktest.sotiris.ui.gallery.adapter.PugsAdapter
import timber.log.Timber

class GalleryFragment : Fragment() {
    private lateinit var viewModel: GalleryViewModel
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var pugsAdapter: PugsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        Timber.d("onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("onViewCreated")
        initializeTheView()
        getTheContent()
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView")
    }

    private fun initializeTheView() {
        pugsAdapter = PugsAdapter()

        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = pugsAdapter

        // When user hits back button transition takes backward
        postponeEnterTransition()
        binding.recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }

        pugsAdapter.itemClickListener = object : PugsAdapter.ItemClickListener {
            override fun onClick(aView: View, photo:ImageView, item: PugInfo) {
                val pugItem = PugInfo(item.name, item.description, item.imageUrl)
                val actionToPugDetails = GalleryFragmentDirections.actionNavToDetails().setPugDetails(pugItem)

                val extras = FragmentNavigatorExtras(photo to item.imageUrl)

                //TODO: Use transition animations
                aView.findNavController().navigate(actionToPugDetails, extras)
            }
        }

        viewModel.pugs.observe(viewLifecycleOwner, { pugsWithInfo ->
            binding.loading.content.visibility = View.GONE

            if (pugsWithInfo != null) {
                showTheContent(pugsWithInfo)
            } else {
                showError()
            }
        })
    }

    private fun getTheContent() {
        binding.loading.root.visibility = View.VISIBLE

        viewModel.getPugs()
    }

    private fun showTheContent(pugsWithInfo: ArrayList<PugInfo>) {
        if (pugsWithInfo.isNotEmpty()) {
            binding.empty.content.visibility = View.GONE

            pugsAdapter.setItems(pugsWithInfo)
            binding.loading.content.visibility = View.GONE
        } else {
            binding.empty.content.visibility = View.VISIBLE
            binding.loading.content.visibility = View.GONE
        }
    }

    //TODO: Proper error handling
    private fun showError() {
        binding.error.content.visibility = View.VISIBLE
        binding.loading.content.visibility = View.GONE

        binding.error.retry.setOnClickListener {
            binding.error.content.visibility = View.GONE

            getTheContent()
        }
    }
}