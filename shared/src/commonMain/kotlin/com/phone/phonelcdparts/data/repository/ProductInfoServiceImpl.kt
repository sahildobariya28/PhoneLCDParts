package com.phone.phonelcdparts.data.repository

import com.phone.phonelcdparts.core.HttpRoutes
import com.phone.phonelcdparts.data.model.BannerDTO
import com.phone.phonelcdparts.data.model.CategoryLink
import com.phone.phonelcdparts.data.model.ExtensionAttributes
import com.phone.phonelcdparts.data.model.MediaGalleryEntry
import com.phone.phonelcdparts.data.model.ProductInfoDTO
import com.phone.phonelcdparts.data.model.StockItem
import com.phone.phonelcdparts.data.remote.ProductInfoService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class ProductInfoServiceImpl() : ProductInfoService {

    private val client = HttpClient {
        install(HttpTimeout) {
            requestTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            connectTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
            socketTimeoutMillis = HttpTimeout.INFINITE_TIMEOUT_MS
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            headers.append("Authorization", "Bearer ${HttpRoutes.BAREAR_TOKEN}")
        }
    }

    override suspend fun getProductInfo(sku: String): Pair<ProductInfoDTO?, HttpStatusCode?> {

        return try {

            val response = client.get {
                url("${HttpRoutes.PRODUCT_INFO}$sku")
            }
            if (response.status.isSuccess()) {
                val banners = parseProductInfo(response.body())

                Pair(banners, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {

            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    override suspend fun getProductMedia(sku: String): Pair<List<MediaGalleryEntry>?, HttpStatusCode?> {
        return try {

            val response = client.get {
                url("${HttpRoutes.PRODUCT_INFO}$sku/media")
            }
            if (response.status.isSuccess()) {
                val banners = parseMediaGallery(response.body())

                Pair(banners, response.status)
            } else {
                Pair(null, response.status)
            }

        } catch (e: Exception) {

            Pair(null, HttpStatusCode.InternalServerError)
        }
    }

    private fun parseProductInfo(json: String): ProductInfoDTO? {
        val jsonObject = Json.parseToJsonElement(json).jsonObject

        val id = jsonObject["id"].toString().toInt()
        val sku = jsonObject["sku"].toString().replace("\"", "")
        val name = jsonObject["name"].toString().replace("\"", "")
        val attributeSetId = jsonObject["attribute_set_id"].toString().toInt()
        val priceString = jsonObject["price"]?.toString()

        val price: Double = if (priceString != null && priceString != "null") {
            priceString.toDouble()
        } else {
            0.0
        }
        val status = jsonObject["status"].toString().toInt()
        val visibility = jsonObject["visibility"].toString().toInt()
        val typeId = jsonObject["type_id"].toString().replace("\"", "")
        val createdAt = jsonObject["created_at"].toString().replace("\"", "")
        val updatedAt = jsonObject["updated_at"].toString().replace("\"", "")
        val weightString = jsonObject["weight"]?.toString()

        val weight = if (weightString != null && weightString != "null") {
            weightString.toDouble()
        } else {
            0.0
        }

        val mediaGalleryEntries = parseMediaGallery(jsonObject["media_gallery_entries"]?.jsonArray)
        val extensionAttributes =
            parseExtensionAttributes(jsonObject["extension_attributes"]?.jsonObject)

        return extensionAttributes?.let {
            ProductInfoDTO(
                id = id,
                sku = sku,
                name = name,
                attributeSetId = attributeSetId,
                price = price,
                status = status,
                visibility = visibility,
                typeId = typeId,
                createdAt = createdAt,
                updatedAt = updatedAt,
                weight = weight,
                mediaGalleryEntries = mediaGalleryEntries,
                extensionAttributes = it
            )
        }
    }

    private fun parseMediaGallery(json: JsonArray?): List<MediaGalleryEntry> {
        val listTest = mutableListOf<MediaGalleryEntry>()
        json?.forEach {
            val id = it.jsonObject["id"].toString().toInt()
            val mediaType = it.jsonObject["media_type"].toString().replace("\"", "")
            val label = it.jsonObject["label"]?.toString()
            val position = it.jsonObject["position"].toString().toInt()
            val disabled = it.jsonObject["disabled"].toString().toBoolean()
            val types = parseTypes(it.jsonObject["types"]?.jsonArray)
            val file = it.jsonObject["file"].toString().replace("\"", "")

            val mediaGalleryEntry = MediaGalleryEntry(
                id = id,
                mediaType = mediaType,
                label = label,
                position = position,
                disabled = disabled,
                types = types,
                file = file
            )
            listTest.add(mediaGalleryEntry)
        }
        return listTest
    }

    private fun parseTypes(json: JsonArray?): List<String> {
        return json?.map { it.toString() } ?: emptyList()
    }

    private fun parseExtensionAttributes(json: JsonObject?): ExtensionAttributes? {
        if (json == null) return null

        val websiteIds =
            json["website_ids"]?.jsonArray?.map { it.toString().toInt() } ?: emptyList()
        val categoryLinks = parseCategoryLinks(json["category_links"]?.jsonArray)
        val stockItem = parseStockItem(json["stock_item"]?.jsonObject)

        return stockItem?.let {
            ExtensionAttributes(
                websiteIds = websiteIds,
                categoryLinks = categoryLinks,
                stockItem = it
            )
        }
    }

    private fun parseCategoryLinks(json: JsonArray?): List<CategoryLink> {
        val listTest = mutableListOf<CategoryLink>()
        json?.forEach {
            val categoryId = it.jsonObject["category_id"].toString().replace("\"", "")
            val position = it.jsonObject["position"].toString().toInt()

            val categoryLink = CategoryLink(
                categoryId = categoryId,
                position = position
            )
            listTest.add(categoryLink)
        }
        return listTest
    }

    private fun parseStockItem(json: JsonObject?): StockItem? {
        if (json == null) return null

        val itemId = json["item_id"].toString().toInt()
        val productId = json["product_id"].toString().toInt()
        val stockId = json["stock_id"].toString().toInt()
        val qty = json["qty"].toString().toInt()
        val isInStock = json["is_in_stock"].toString().toBoolean()
        val isQtyDecimal = json["is_qty_decimal"].toString().toBoolean()
        val showDefaultNotificationMessage =
            json["show_default_notification_message"].toString().toBoolean()
        val useConfigMinQty = json["use_config_min_qty"].toString().toBoolean()
        val minQty = json["min_qty"].toString().toInt()
        val useConfigMinSaleQty = json["use_config_min_sale_qty"].toString().toInt()
        val minSaleQty = json["min_sale_qty"].toString().toInt()
        val useConfigMaxSaleQty = json["use_config_max_sale_qty"].toString().toBoolean()
        val maxSaleQty = json["max_sale_qty"].toString().toInt()
        val useConfigBackorders = json["use_config_backorders"].toString().toBoolean()
        val backorders = json["backorders"].toString().toInt()
        val useConfigNotifyStockQty = json["use_config_notify_stock_qty"].toString().toBoolean()
        val notifyStockQty = json["notify_stock_qty"].toString().toInt()
        val useConfigQtyIncrements = json["use_config_qty_increments"].toString().toBoolean()
        val qtyIncrements = json["qty_increments"].toString().toInt()
        val useConfigEnableQtyInc = json["use_config_enable_qty_inc"].toString().toBoolean()
        val enableQtyIncrements = json["enable_qty_increments"].toString().toBoolean()
        val useConfigManageStock = json["use_config_manage_stock"].toString().toBoolean()
        val manageStock = json["manage_stock"].toString().toBoolean()
        val lowStockDate = json["low_stock_date"].toString().replace("\"", "")
        val isDecimalDivided = json["is_decimal_divided"].toString().toBoolean()
        val stockStatusChangedAuto = json["stock_status_changed_auto"].toString().toInt()

        return StockItem(
            itemId = itemId,
            productId = productId,
            stockId = stockId,
            qty = qty,
            isInStock = isInStock,
            isQtyDecimal = isQtyDecimal,
            showDefaultNotificationMessage = showDefaultNotificationMessage,
            useConfigMinQty = useConfigMinQty,
            minQty = minQty,
            useConfigMinSaleQty = useConfigMinSaleQty,
            minSaleQty = minSaleQty,
            useConfigMaxSaleQty = useConfigMaxSaleQty,
            maxSaleQty = maxSaleQty,
            useConfigBackorders = useConfigBackorders,
            backorders = backorders,
            useConfigNotifyStockQty = useConfigNotifyStockQty,
            notifyStockQty = notifyStockQty,
            useConfigQtyIncrements = useConfigQtyIncrements,
            qtyIncrements = qtyIncrements,
            useConfigEnableQtyInc = useConfigEnableQtyInc,
            enableQtyIncrements = enableQtyIncrements,
            useConfigManageStock = useConfigManageStock,
            manageStock = manageStock,
            lowStockDate = lowStockDate,
            isDecimalDivided = isDecimalDivided,
            stockStatusChangedAuto = stockStatusChangedAuto
        )
    }
}