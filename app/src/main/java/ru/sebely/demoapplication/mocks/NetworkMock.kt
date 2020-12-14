package ru.sebely.demoapplication.mocks

import com.google.android.material.snackbar.BaseTransientBottomBar
import retrofit2.mock.BehaviorDelegate
import ru.sebely.demoapplication.model.*
import ru.sebely.demoapplication.network.DeliveryService

class NetworkMock(val delegate : BehaviorDelegate<DeliveryService> ) : DeliveryService {
    override suspend fun promoList(): PromoResponse {
        return  PromoResponse(
            list =  listOf(
                PromoItem(1, "https://graphicriver.img.customer.envatousercontent.com/files/197250363/Presentation.jpg?auto=compress%2Cformat&fit=crop&crop=top&w=590&h=590&s=7d60680622ca4b3ebaba497af904a77e"),
                PromoItem(2, "https://image.shutterstock.com/image-vector/pepperoni-pizza-ads-delicious-ingredients-600w-1427946764.jpg"),
                PromoItem(2, "https://image.shutterstock.com/image-vector/vector-fast-food-special-offer-600w-421928812.jpg"),
            ),
        )

        //
        // I could use json, but...
        //
        // return delegate.returningResponse("test").promoList()
    }

    override suspend fun menu(): MenuResponse {
        return MenuResponse(
            listOf(
                MenuCategory(
                    id = 1,
                    categoryName = "Sushi",
                    products = listOf(
                        Product( id = 1, price = 45.0, title = "Sushi One", description = "Best ever", parameters = "600 gr.", image = "https://raffyplovdiv.bg/files/images/601/fit_924_512.jpg"),
                        Product( id = 2, price = 12.0, title = "Sushi Two", description = "Best ever", parameters = "300 g", image = "https://raffyplovdiv.bg/files/images/604/fit_924_512.jpg"),
                        Product( id = 3, price = 44.0, title = "Sushi Three", description = "Best ever", parameters = "300 g", image = "https://raffyplovdiv.bg/files/images/602/fit_924_512.jpg"),
                        Product( id = 4, price = 16.0, title = "Sushi Four", description = "Best ever", parameters = "350 gr.", image = "https://raffyplovdiv.bg/files/images/605/fit_924_512.jpg"),
                        Product( id = 5, price = 16.0, title = "Sushi Cinq", description = "Best ever", parameters = "1615 gr.", image = "https://raffyplovdiv.bg/files/images/630/fit_924_512.jpg"),
                    )
                ),
                MenuCategory(
                    id = 2,
                    categoryName = "Pizza",
                    products = listOf(
                        Product( id = 1, price = 12.0, title = "Pizza Une", description = "Best ever", parameters = "300 g", image = "https://5.imimg.com/data5/JB/CY/MY-51761245/veg-pizza-500x500.png"),
                        Product( id = 2, price = 54.0, title = "Duex", description = "Best ever", parameters = "300 g", image = "https://5.imimg.com/data5/JB/CY/MY-51761245/veg-pizza-500x500.png"),
                        Product( id = 3, price = 55.0, title = "Trois", description = "Best ever", parameters = "300 g", image = "https://5.imimg.com/data5/ANDROID/Default/2020/8/UW/MZ/LL/57557085/product-jpeg-500x500.jpg"),
                        Product( id = 4, price = 34.0, title = "Four", description = "Best ever", parameters = "300 g", image = "https://www.danishcrown-toppings.dk/media/1229/tulip_topping_pizza_pepperoni.jpg"),
                        Product( id = 5, price = 35.0, title = "Sushi What ever (Cinq)", description = "Best ever", parameters = "300 g", image = "https://expertphotography.com/wp-content/uploads/2018/11/pizza-photography.jpg"),
                    )
                ),
                MenuCategory(
                    id = 3,
                    categoryName = "Drinks",
                    products = listOf(
                        Product( id = 1, price = 11.0, title = "Drink Some", description = "Best ever", parameters = "300 g", image = "https://i.pinimg.com/originals/3b/62/a2/3b62a28ab914e0af6a30b81b5e97fd89.jpg"),
                        Product( id = 2, price = 12.0, title = "Who knows?", description = "Best ever", parameters = "300 g", image = "https://i.pinimg.com/originals/4f/66/90/4f6690dddc5814051b97fdde037005a1.jpg"),
                        Product( id = 3, price = 6.0, title = "Laaazy", description = "Best ever", parameters = "300 g", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVeEbdGDg60Q1cNuIb_tLz6o9L-Jox3k2L8Q&usqp=CAU"),
                        Product( id = 4, price = 12.0, title = "Event more lazy", description = "Best ever", parameters = "300 g", image = "https://cdn-a.william-reed.com/var/wrbm_gb_food_pharma/storage/images/media/images/tempo-beverages-full-sku-line/11591015-1-eng-GB/Tempo-Beverages-Full-SKU-Line.jpg"),
                        Product( id = 5, price = 55.0, title = "Try this one, or not", description = "Best ever", parameters = "300 g", image = "https://static.abbottnutrition.com/cms-prod/ensure.com/img/ensure-original-products-tile.jpg"),
                    )
                ),
            )
        )
    }
}