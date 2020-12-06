package com.tinktest.sotiris.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.tinktest.sotiris.R
import com.tinktest.sotiris.databinding.FragmentDetailsBinding
import com.tinktest.sotiris.models.PugInfo

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private var pugDetails: PugInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        arguments?.let {
            val safeArgs = DetailsFragmentArgs.fromBundle(it)
            pugDetails = safeArgs.pugDetails
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeTheView()
        //getTheContent()
    }

    private fun initializeTheView() {
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(binding.toolbar.context, R.drawable.ic_arrow_back_white_36dp)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        populateTheDetails()

        /*
        viewModel.postComments.observe(viewLifecycleOwner, Observer { comments ->
            binding.loading.content.visibility = View.GONE

            if (comments != null) {
                populateTheComments(comments)
            } else {
                handleError()
            }
        })
         */
    }

    private fun getTheContent() {
        binding.loading.root.visibility = View.VISIBLE
        binding.error.content.visibility = View.GONE

        //viewModel.getCommentsForPost(pugI!!.postId)
    }

    private fun populateTheDetails() {
        if(pugDetails != null) {
            binding.backdrop.load(pugDetails?.imageUrl)
            binding.pugtitle.text = pugDetails!!.name
            binding.pugDescription.text = resources.getString(R.string.lorem_dogum)
        }
    }

    //TODO: Proper error handling
    private fun handleError() {
        binding.error.content.visibility = View.VISIBLE

        binding.error.retry.setOnClickListener {
            binding.error.content.visibility = View.GONE

            getTheContent()
        }
    }
}