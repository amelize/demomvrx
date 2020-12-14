package ru.sebely.demoapplication


import android.os.Parcelable
import com.airbnb.mvrx.*
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import ru.sebely.demoapplication.core.BaseFragment
import ru.sebely.demoapplication.databinding.FragmentPromoBinding

data class PromoState(val activePosition : Int = 0) : MavericksState

class PromoViewModel(initialState: PromoState) : MavericksViewModel<PromoState>(initialState) {
}

@Parcelize
data class PromoParams(val id : Int, val url : String) : Parcelable

class PromoFragment : BaseFragment<FragmentPromoBinding>(R.layout.fragment_promo) {
    private val viewModel : PromoViewModel by fragmentViewModel()
    private val args by args<PromoParams>()

    companion object {
        @JvmStatic
        fun newInstance(id : Int, url :String) =
                PromoFragment().apply {
                    arguments = PromoParams(id, url).asMavericksArgs()
                }
    }

    override fun invalidate() = withState(viewModel) {
        state ->
            Glide.with(this)
                .load(args.url)
                .centerCrop()
                .into(binding.promoImage)
            Unit
    }
}