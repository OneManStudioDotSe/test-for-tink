package se.onemanstudio.test.tink.ui.gallery

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import se.onemanstudio.test.tink.R
import se.onemanstudio.test.tink.databinding.FragmentGalleryBinding
import se.onemanstudio.test.tink.models.PugInfo
import se.onemanstudio.test.tink.repository.RepoSource
import se.onemanstudio.test.tink.ui.gallery.adapter.PugsAdapter
import timber.log.Timber

class GalleryFragment : Fragment() {
    private lateinit var viewModel: GalleryViewModel
    private lateinit var binding: FragmentGalleryBinding
    private lateinit var pugsAdapter: PugsAdapter
    private var selectionForColumns = 1
    private var selectionForSource = RepoSource.DOG_CEO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeTheView()
        getTheContent()
    }

    private fun initializeTheView() {
        binding.appbar.inflateMenu(R.menu.menu_home)
        binding.appbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.actions_columns_amount -> showColumnsDialog()
                R.id.actions_switch_source -> showSourceDialog()
                R.id.actions_about -> showAboutDialog()
            }
            true
        }

        pugsAdapter = PugsAdapter()

        binding.recyclerView.hasFixedSize()
        binding.recyclerView.adapter = pugsAdapter

        setTheAdapter()

        postponeEnterTransition()
        binding.recyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }

        pugsAdapter.itemClickListener = object : PugsAdapter.ItemClickListener {
            override fun onClick(aView: View, photo: ImageView, item: PugInfo) {
                val pugItem = PugInfo(item.name, item.description, item.imageUrl)
                val actionToPugDetails =
                    GalleryFragmentDirections.actionNavToDetails().setPugDetails(pugItem)

                val extras = FragmentNavigatorExtras(photo to item.imageUrl)

                aView.findNavController().navigate(actionToPugDetails, extras)
            }
        }

        viewModel.pugs.observe(viewLifecycleOwner) { pugsWithInfo ->
            binding.loading.content.visibility = View.GONE

            if (pugsWithInfo != null) {
                showTheContent(pugsWithInfo)
            } else {
                showError()
            }
        }
    }

    private fun setTheAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, selectionForColumns + 1)
    }

    private fun getTheContent() {
        binding.loading.root.visibility = View.VISIBLE

        when(selectionForSource) {
            RepoSource.PUG_ME -> viewModel.getPugs()
            RepoSource.DOG_CEO -> viewModel.getPugsFromDogCeo()
        }

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

    private fun showError() {
        binding.error.content.visibility = View.VISIBLE
        binding.loading.content.visibility = View.GONE

        binding.error.retry.setOnClickListener {
            binding.error.content.visibility = View.GONE

            getTheContent()
        }
    }

    private fun showAboutDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("About this app")
            .setMessage("This is the test app for Tink as implemented by Sotiris Falieris. You can contact me at sotiris@onemanstudio.se")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showSourceDialog() {
        val singleItems = arrayOf("PugMe", "DogCeo")
        val checkedItem = selectionForSource.ordinal

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Source of pugs?")
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                getTheContent()
            }

            .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                when (which) {
                    0 -> selectionForSource = RepoSource.PUG_ME
                    1 -> selectionForSource = RepoSource.DOG_CEO
                }
            }
            .show()
    }

    private fun showColumnsDialog() {
        val singleItems = arrayOf("1", "2", "3", "4")
        val checkedItem = selectionForColumns

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("How many columns?")
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                setTheAdapter()
            }

            .setSingleChoiceItems(singleItems, checkedItem) { dialog, which ->
                selectionForColumns = which
            }
            .show()
    }
}