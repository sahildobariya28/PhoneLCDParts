package com.phone.phonelcdparts.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductInfoDTO(
    @SerialName("attribute_set_id")
    val attributeSetId: Int,
    @SerialName("created_at")
    val createdAt: String,
//    @SerialName("custom_attributes")
//    val customAttributes: List<CustomAttribute>,
    @SerialName("extension_attributes")
    val extensionAttributes: ExtensionAttributes,
    @SerialName("id")
    val id: Int,
    @SerialName("media_gallery_entries")
    val mediaGalleryEntries: List<MediaGalleryEntry>,
    @SerialName("name")
    val name: String,
    @SerialName("price")
    val price: Double,
    @SerialName("sku")
    val sku: String,
    @SerialName("status")
    val status: Int,
    @SerialName("type_id")
    val typeId: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("weight")
    val weight: Double
)

@Serializable
data class StockItem(
    @SerialName("backorders")
    val backorders: Int,
    @SerialName("enable_qty_increments")
    val enableQtyIncrements: Boolean,
    @SerialName("is_decimal_divided")
    val isDecimalDivided: Boolean,
    @SerialName("is_in_stock")
    val isInStock: Boolean,
    @SerialName("is_qty_decimal")
    val isQtyDecimal: Boolean,
    @SerialName("item_id")
    val itemId: Int,
    @SerialName("low_stock_date")
    val lowStockDate: String,
    @SerialName("manage_stock")
    val manageStock: Boolean,
    @SerialName("max_sale_qty")
    val maxSaleQty: Int,
    @SerialName("min_qty")
    val minQty: Int,
    @SerialName("min_sale_qty")
    val minSaleQty: Int,
    @SerialName("notify_stock_qty")
    val notifyStockQty: Int,
    @SerialName("product_id")
    val productId: Int,
    @SerialName("qty")
    val qty: Int,
    @SerialName("qty_increments")
    val qtyIncrements: Int,
    @SerialName("show_default_notification_message")
    val showDefaultNotificationMessage: Boolean,
    @SerialName("stock_id")
    val stockId: Int,
    @SerialName("stock_status_changed_auto")
    val stockStatusChangedAuto: Int,
    @SerialName("use_config_backorders")
    val useConfigBackorders: Boolean,
    @SerialName("use_config_enable_qty_inc")
    val useConfigEnableQtyInc: Boolean,
    @SerialName("use_config_manage_stock")
    val useConfigManageStock: Boolean,
    @SerialName("use_config_max_sale_qty")
    val useConfigMaxSaleQty: Boolean,
    @SerialName("use_config_min_qty")
    val useConfigMinQty: Boolean,
    @SerialName("use_config_min_sale_qty")
    val useConfigMinSaleQty: Int,
    @SerialName("use_config_notify_stock_qty")
    val useConfigNotifyStockQty: Boolean,
    @SerialName("use_config_qty_increments")
    val useConfigQtyIncrements: Boolean
)

@Serializable
data class ExtensionAttributes(
    @SerialName("category_links")
    val categoryLinks: List<CategoryLink>,
    @SerialName("stock_item")
    val stockItem: StockItem,
    @SerialName("website_ids")
    val websiteIds: List<Int>
)

//@Serializable
//data class CustomAttribute(
//    @SerialName("attribute_code")
//    val attributeCode: String,
//    @SerialName("value")
//    val value: String
//)

@Serializable
data class CategoryLink(
    @SerialName("category_id")
    val categoryId: String,
    @SerialName("position")
    val position: Int
)