package ru.sebely.demoapplication

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import ru.sebely.demoapplication.core.BaseFragment
import ru.sebely.demoapplication.databinding.MenuFragmentBinding
import ru.sebely.demoapplication.model.MenuCategory
import java.math.BigDecimal


data class MenuState(
    val position: Int = 0,
    val subList: List<String> = emptyList(),
    val items: MenuCategory = MenuCategory(
        products = emptyList(),
        id = 0,
        categoryName = ""
    )
) : MavericksState

class MenuViewModel(initialState: MenuState) : MavericksViewModel<MenuState>(initialState) {
    fun setParams(position: Int, subList: List<String>, items: MenuCategory) = setState {
        copy(position = position, subList = subList, items = items)
    }
}

@Parcelize
data class MenuParams(val position: Int, val subList: List<String>, val items: MenuCategory) : Parcelable

object Adapters {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide.with(imageView.context).load(url).centerCrop().into(imageView)
    }
}

class MenuFragment : BaseFragment<MenuFragmentBinding>(R.layout.menu_fragment) {
    private val viewModel : MenuViewModel by fragmentViewModel()
    private val args by args<MenuParams>()

    companion object {
        fun newInstance(position: Int, subList: List<String>, items: MenuCategory) = MenuFragment().apply {
            arguments = MenuParams(position, subList, items).asMavericksArgs()
        }
    }


    override fun invalidate() = withState(viewModel) { state ->
            val size = state.subList.size
            binding.menuList.withModels {
                menuHeaderView {
                    id("header")
                    firstName(state.subList.getOrElse(state.position) { "" })
                    secondName(state.subList.getOrElse((state.position + 1) % size) { "" })
                    thirdName(state.subList.getOrElse((state.position + 2) % size) { "" })
                }

                state.items.products.forEach { item ->
                        menuItemView {
                            id(item.id)
                            itemId(item.id)
                            title(item.title)
                            description(item.description)
                            quantity(item.parameters)
                            price("${BigDecimal(item.price).toPlainString()} SGD") // TODO: Currency + move to custom adapter
                            imageUrl(item.image)
                        }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setParams(args.position, args.subList, args.items)
    }
}