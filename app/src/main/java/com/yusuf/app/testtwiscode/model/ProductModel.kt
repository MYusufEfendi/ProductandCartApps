package com.yusuf.app.testtwiscode.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusuf.app.testtwiscode.utils.StringUtil
import java.util.*

data class ProductResponse(
    val id: String,
    val cat_id: String,
    val sub_cat_id: String,
    val item_type_id: String,
    val item_price_id: String,
    val item_currency_id: String,
    val item_location_id: String,
    val condition_of_item_id: String,
    val deal_option_remark: String,
    val description: String,
    val highlight_info: String,
    val price: String,
    val deal_option_id: String,
    val brand: String,
    val business_mode: String,
    val is_sold_out: String,
    val title: String,
    val address: String,
    val lat: String,
    val lng: String,
    val status: String,
    val added_date: String,
    val added_user_id: String,
    val added_user_name: String,
    val updated_date: String,
    val updated_user_id: String,
    val updated_flag: String,
    val touch_count: String,
    val favourite_count: String,
    val is_paid: String,
    val is_available: String,
    val is_pre_order: String,
    val po_start: String,
    val po_end: String,
    val po_slot: String,
    val po_delivery: String,
    val is_halal: String,
    val weight: String,
    val location_id: String,
    val location_name: String,
    val location_type: String,
    val is_free: String,
    val pickup_time: String,
    val appear_duration: String,
    val stock: String,
    val is_food: String,
    val added_date_str: String,
    val paid_status: String,
    val photo_count: String,
    val default_photo: DefaultPhoto,
    val category: Category,
    val sub_category: SubCategory,
    val item_type: ItemType,
    val item_price_type: ItemPriceType,
    val item_currency: ItemCurrency,
    val item_location: ItemLocation,
    val condition_of_item: ConditionOfItem,
    val deal_option: DealOption,
    val user: User,
    val is_favourited: String,
    val is_owner: String,
//    val get_address: Map<String,Any>? = null
//    val get_delivery: List<GetDelivery> = emptyList(),
) {

    data class DefaultPhoto(
        val img_id: String,
        val img_parent_id: String,
        val img_type: String,
        val img_path: String,
        val img_width: String,
        val img_height: String,
        val img_desc: String,
    )

    data class Category(
        val cat_id: String,
        val cat_name: String,
        val cat_ordering: String,
        val status: String,
        val added_date: String,
        val is_food: String,
        val default_photo: DefaultPhoto,
        val default_icon: DefaultPhoto,
    )

    data class SubCategory(
        val id: String,
        val cat_id: String,
        val name: String,
        val status: String,
        val added_date: String,
        val added_user_id: String,
        val updated_date: String,
        val updated_user_id: String,
        val updated_flag: String,
        val default_photo: DefaultPhoto,
    )

    data class ItemType(
        val id: String,
        val name: String,
        val status: String,
        val added_date: String,
        val is_empty_object: String,
    )

    data class ItemPriceType(
        val id: String,
        val name: String,
        val status: String,
        val added_date: String,
    )

    data class ItemCurrency(
        val id: String,
        val currency_short_form: String,
        val currency_symbol: String,
        val status: String,
        val added_date: String,
        val is_empty_object: String,
    )

    data class ItemLocation(
        val id: String,
        val name: String,
        val lat: String,
        val lng: String,
        val status: String,
        val added_date: String,
        val is_empty_object: String
    )

    data class ConditionOfItem(
        val id: String,
        val name: String,
        val status: String,
        val added_date: String,
        val is_empty_object: String
    )

    data class DealOption(
        val id: String,
        val name: String,
        val status: String,
        val added_date: String,
        val is_empty_object: String
    )

    data class User(
        val user_id: String,
        val user_is_sys_admin: String,
        val facebook_id: String,
        val google_id: String,
        val phone_id: String,
        val apple_id: String,
        val user_name: String,
        val user_email: String,
        val user_phone: String,
        val user_address: String,
        val city: String,
        val user_about_me: String,
        val user_cover_photo: String,
        val user_profile_photo: String,
        val role_id: String,
        val status: String,
        val is_banned: String,
        val added_date: String,
        val device_token: String,
        val code: String,
        val overall_rating: String,
        val whatsapp: String,
        val messenger: String,
        val follower_count: String,
        val following_count: String,
        val email_verify: String,
        val facebook_verify: String,
        val google_verify: String,
        val phone_verify: String,
        val apple_verify: String,
        val rating_count: String,
        val is_followed: String,
        val rating_details: RatingDetails,
    ) {
        data class RatingDetails(
            val five_star_count: String,
            val five_star_percent: String,
            val four_star_count: String,
            val four_star_percent: String,
            val three_star_count: String,
            val three_star_percent: String,
            val two_star_count: String,
            val two_star_percent: String,
            val one_star_count: String,
            val one_star_percent: String,
            val total_rating_count: String,
            val total_rating_value: String,

            )
    }

    data class GetAddress(
        val id: String,
        val bs_items_id: String,
        val province_id: String,
        val province: String,
        val city_id: String,
        val city: String,
        val district_id: String,
        val district: String,
        val zip_code: String,
        val longitude: String,
        val latitude: String,
        val full_address: String,
        val contact_person: String,
        val name_address: String,
        val created_at: String,
        val updated_at: String,
        val deleted_at: String,
    )
    data class GetDelivery(
        val id: String,
        val bs_items_id:String,
        val delivery_name:String,
        val created_at: String,
        val updated_at: String,
        val deleted_at: String
    )

}


@Entity(tableName = "productTable")
data class ProductTable(
    @PrimaryKey
    val id: String,
    val title: String,
    val price: Int,
    val description: String,
    val location_name: String,
    val added_user_name: String,
    val is_halal: Int,
    val condition: String,
    val stock: Int,
    val weight: Double,
    val category: String,
    val category_id:String
){
    companion object {
        fun ResponseToTable(
            data: ProductResponse,
            stringUtil: StringUtil
        )=ProductTable(
            data.id,
            data.title,
            data.price.toInt(),
            data.description,
            data.location_name,
            data.added_user_name,
            data.is_halal.toInt(),
            data.condition_of_item.name,
            stringUtil.emptyStringToZero(data.stock),
            stringUtil.emptyStringToDouble(data.weight),
            data.category.cat_name,
            data.category.cat_id
        )
    }
}


