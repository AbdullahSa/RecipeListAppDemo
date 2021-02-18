package com.sample.recipelistapp.presentation.saverecipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sample.recipelistapp.R
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.databinding.FragmentSaveRecipeBinding
import com.sample.recipelistapp.presentation.MainActivity
import com.sample.recipelistapp.presentation.adapter.photolist.PhotoListAdapter
import com.sample.recipelistapp.util.Constants.PICK_IMAGE
import com.sample.recipelistapp.util.navigation.RecipePagesDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

@AndroidEntryPoint
class SaveRecipeFragment : Fragment() {

    @Inject
    lateinit var viewModel: SaveRecipeViewModel

    private lateinit var binding: FragmentSaveRecipeBinding

    private lateinit var adapter: PhotoListAdapter

    private val photoList = arrayListOf(RecipePhoto(photoPath = null, editable = true))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_save_recipe, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as MainActivity).toolbar.tvTitle.text = getString(R.string.title_add_recipe)
        adapter = PhotoListAdapter({
            startActivityForResult(viewModel.openGallery(), PICK_IMAGE)
        }) {
            photoList.remove(it)
            adapter.submitList(photoList)
            adapter.notifyDataSetChanged()
        }
        binding.rvImageList.adapter = adapter
        adapter.submitList(photoList)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            stateOfSavingLiveData.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, getString(R.string.message_success_saving), Toast.LENGTH_SHORT).show()
                (activity as MainActivity).viewModel.navigateTo(
                    RecipePagesDestination.Back,
                    null,
                    false
                )
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE) {
            photoList.add(
                RecipePhoto(
                    photoPath = data?.data.toString(),
                    editable = true
                )
            )
            adapter.submitList(photoList)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save_recipe, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> {
                photoList.removeAt(0)
                val recipe = RecipeWithPhotos(
                    RecipeItem(
                        title = binding.etTitle.text.toString(),
                        description = binding.etDescription.text.toString()
                    ), photoList
                )
                viewModel.saveRecipe(recipe)
            }
        }
        return true
    }
}