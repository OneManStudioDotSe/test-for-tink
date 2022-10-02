package com.tinktest.sotiris.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import coil.load
import com.tinktest.sotiris.R
import com.tinktest.sotiris.databinding.FragmentDetailsBinding
import com.tinktest.sotiris.models.DogFunFact
import com.tinktest.sotiris.models.PugInfo
import java.util.concurrent.TimeUnit

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: DetailsViewModel
    private var pugDetails: PugInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        arguments?.let {
            val safeArgs = DetailsFragmentArgs.fromBundle(it)
            pugDetails = safeArgs.pugDetails
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeTheView()

        setTheDogPhotoAndName()
        pretendToGetTheDetails()
    }

    private fun initializeTheView() {
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(binding.toolbar.context, R.drawable.ic_arrow_back_white_36dp)
        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        viewModel.dogFunFact.observe(viewLifecycleOwner) {
            populateTheDetails(it)
        }
    }

    private fun setTheDogPhotoAndName() {
        if(pugDetails != null) {
            binding.backdrop.transitionName = pugDetails?.imageUrl

            binding.backdrop.load(pugDetails?.imageUrl)
            binding.pugtitle.text = pugDetails!!.name
        }
    }
    private fun pretendToGetTheDetails() {
        binding.loading.root.visibility = View.VISIBLE

        viewModel.getDogFunFact()
    }

    private fun populateTheDetails(dogFunFact: DogFunFact) {
        binding.funFact.text = String.format(getString(R.string.did_you_know_that, dogFunFact.fact).toLowerCase())
        binding.pugDescription.text = resources.getString(R.string.lorem_dogum)

        binding.loading.content.visibility = View.GONE
    }
}