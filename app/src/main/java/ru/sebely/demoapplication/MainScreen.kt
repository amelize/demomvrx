package ru.sebely.demoapplication

import android.graphics.Color
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.airbnb.mvrx.*
import com.airbnb.mvrx.mocking.mockSingleViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.inject
import ru.sebely.demoapplication.adapter.MenuPagesAdapter
import ru.sebely.demoapplication.adapter.PromoAdapter
import ru.sebely.demoapplication.core.BaseFragment
import ru.sebely.demoapplication.databinding.FragmentMainScreenBinding
import ru.sebely.demoapplication.mocks.promoMockState
import ru.sebely.demoapplication.model.MenuCategory
import ru.sebely.demoapplication.model.MenuResponse
import ru.sebely.demoapplication.model.PromoItem
import ru.sebely.demoapplication.model.PromoResponse
import ru.sebely.demoapplication.network.DeliveryService

private const val MIN_SCALE = 0.55f
private const val MIN_ALPHA = 0.0f

class AlphaAnimation : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        view.apply {
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 0 -> {
                    alpha = 1f
                }
                position <= 1 -> { // [-1,1]
                    val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))

                    alpha = (MIN_ALPHA +
                            (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}

data class MainState(val promos : List<PromoItem> = emptyList(),
                    val categories : List<MenuCategory> = emptyList(),
                    val promoRequest : Async<PromoResponse> = Uninitialized,
                    val menuRequest : Async<MenuResponse> = Uninitialized) : MavericksState

class MainViewModel(initialState: MainState,  private val deliveryService: DeliveryService) : MavericksViewModel<MainState>(initialState) {
    init {
        fetchPromoParams()
        fetchMenu()
    }

    private fun fetchPromoParams() = withState {
        state ->
            if (state.promoRequest is Loading) return@withState

        suspend {
            deliveryService.promoList()
        }.execute(Dispatchers.IO) { copy(promoRequest = it, promos = (it()?.list ?: emptyList())) }
    }

    private fun fetchMenu() = withState {
            state ->
        if (state.menuRequest is Loading) return@withState

        suspend {
            deliveryService.menu()
        }.execute(Dispatchers.IO) { copy(menuRequest = it, categories = (it()?.categories ?: emptyList())) }
    }


    companion object : MavericksViewModelFactory<MainViewModel, MainState> {

        override fun create(viewModelContext: ViewModelContext, state: MainState): MainViewModel {
            val service: DeliveryService by viewModelContext.activity.inject()
            return MainViewModel(state, service)
        }
    }
}

class MainScreen : BaseFragment<FragmentMainScreenBinding>(R.layout.fragment_main_screen) {
    private val viewModel : MainViewModel by fragmentViewModel()

    override fun invalidate() = withState(viewModel) {
            state ->
                binding.menuPages.adapter = MenuPagesAdapter(state.categories, childFragmentManager, lifecycle)
                binding.menuPages.setPageTransformer(AlphaAnimation())

                binding.promo.adapter = PromoAdapter(state.promos, childFragmentManager)
                binding.dots.attachViewPager(binding.promo)
                binding.dots.setDotTint(Color.WHITE)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainScreen()
    }

    override fun provideMocks() = mockSingleViewModel(
        viewModelReference = MainScreen::viewModel,
        defaultState = promoMockState,
        defaultArgs = null
    ) {
        stateForLoadingAndFailure { ::promoRequest }
    }
}