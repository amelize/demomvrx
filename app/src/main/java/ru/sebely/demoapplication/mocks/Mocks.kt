package ru.sebely.demoapplication.mocks

import com.airbnb.mvrx.Success
import ru.sebely.demoapplication.MainState
import ru.sebely.demoapplication.model.PromoItem
import ru.sebely.demoapplication.model.PromoResponse

val promoMockState by lazy {
    MainState(
        promos = listOf(
            PromoItem(1, "https://graphicriver.img.customer.envatousercontent.com/files/197250363/Presentation.jpg?auto=compress%2Cformat&fit=crop&crop=top&w=590&h=590&s=7d60680622ca4b3ebaba497af904a77e"),
            PromoItem(2, "https://image.shutterstock.com/image-vector/pepperoni-pizza-ads-delicious-ingredients-600w-1427946764.jpg"),
            PromoItem(2, "https://image.shutterstock.com/image-vector/vector-fast-food-special-offer-600w-421928812.jpg"),
        ),
        promoRequest = Success(
            value = PromoResponse(
                list =  listOf(
                    PromoItem(1, "https://graphicriver.img.customer.envatousercontent.com/files/197250363/Presentation.jpg?auto=compress%2Cformat&fit=crop&crop=top&w=590&h=590&s=7d60680622ca4b3ebaba497af904a77e"),
                    PromoItem(2, "https://image.shutterstock.com/image-vector/pepperoni-pizza-ads-delicious-ingredients-600w-1427946764.jpg"),
                    PromoItem(2, "https://image.shutterstock.com/image-vector/vector-fast-food-special-offer-600w-421928812.jpg"),
                ),
            )
        )
    )
}