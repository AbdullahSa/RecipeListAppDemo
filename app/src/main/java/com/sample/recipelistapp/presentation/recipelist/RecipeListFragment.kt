package com.sample.recipelistapp.presentation.recipelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sample.recipelistapp.R
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.databinding.FragmentRecipeListBinding
import com.sample.recipelistapp.presentation.MainActivity
import com.sample.recipelistapp.presentation.adapter.photolist.PhotoListAdapter
import com.sample.recipelistapp.presentation.adapter.recipelist.RecipeListAdapter
import com.sample.recipelistapp.util.navigation.RecipePagesDestination
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.view.*
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var viewModel: RecipeListViewModel

    private lateinit var binding: FragmentRecipeListBinding

    private val adapter: RecipeListAdapter by lazy {
        RecipeListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parent = (activity as MainActivity)
        parent.toolbar.tvTitle.text = getString(R.string.label_recipes_title)
        setHasOptionsMenu(true)
        binding.rvRecipeList.adapter = adapter
        viewModel.listLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_recipe, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add -> {
                (activity as MainActivity).viewModel.navigateTo(
                    RecipePagesDestination.SaveRecipePage,
                    null,
                    false
                )
            }
        }
        return true
    }
}