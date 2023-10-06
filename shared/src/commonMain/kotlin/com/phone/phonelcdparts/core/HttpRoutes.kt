package com.phone.phonelcdparts.core

object HttpRoutes {

    //  local server
    private const val BASE_URL = "http://192.168.29.236/phonelcdparts/pub/"
    const val BAREAR_TOKEN = "pm46kzmjn6wp67e5pk79h652m22zd8v9"
    //    dev server
//    private const val BASE_URL = "https://dev.plcdparts.com/"
//    const val BAREAR_TOKEN = "b38mxcq7j32f1cl29uhgn0sivkip3lek"
    // Live server
//    private const val BASE_URL = "https://www.phonelcdparts.com/"
//    const val BAREAR_TOKEN = "0p1f52e1ogk559fjzf3gasx9j9q1uzzj"

    //data url
    const val PRODUCT = "${BASE_URL}rest/V1/products?searchCriteria[page_size]=100"
    const val PRODUCT_BY_NAME = "${BASE_URL}rest/V1/products?searchCriteria[page_size]=100"
    const val BANNER = "${BASE_URL}rest/V1/cmsBlock/id-9-block-1"
    const val BRAND = "${BASE_URL}rest/V1/cmsBlock/brands"
    const val BEST_SELLER = "${BASE_URL}rest/V1/swp_mobile/cms-bestseller"
    const val NEW_PRODUCT = "${BASE_URL}rest/V1/swp_mobile/cms-newproducts"
    const val PRODUCT_INFO = "${BASE_URL}rest/V1/products/"
    const val CATEGORY_BY_PARENT_ID = "${BASE_URL}rest/V1/categories/list?searchCriteria[page_size]=${Int.MAX_VALUE}&searchCriteria[filterGroups][0][filters][0][field]=parent_id&searchCriteria[filterGroups][0][filters][0][value]="
    const val CATEGORY_IMG = "${BASE_URL}media/catalog/category/"
    const val CART = "${BASE_URL}rest/V1/carts/mine"
    const val CART_ITEMS = "${BASE_URL}rest/V1/carts/mine/items"
    const val QUOTE_ID = "${BASE_URL}rest/V1/carts/mine"
    const val INSERT_CART_ITEMS = "${BASE_URL}rest/V1/carts/mine/items"
    const val ORDER_HISTORY = "${BASE_URL}rest/V1/orders"
    const val USER_INFO = "${BASE_URL}rest/V1/customers/me"
    const val POSTS = "https://jsonplaceholder.typicode.com/posts"


    //media url
    const val PRODUCT_MEDIA_URL = "${BASE_URL}media/catalog/product"
    const val BANNER_MEDIA_URL = "${BASE_URL}media/"




//    dev server
//    dev url : https://dev.plcdparts.com/rest/V1
//    dev token : jqaez76gd5a6ei8c1pzeph6bpmoyqpfa
//    local url : http://192.168.29.236/phonelcdparts/pub/rest/V1
//    local url : pm46kzmjn6wp67e5pk79h652m22zd8v9



//    private const val BASE_URL = "https://dev.plcdparts.com/"
//    const val BAREAR_TOKEN = "b38mxcq7j32f1cl29uhgn0sivkip3lek"
//
//    const val PRODUCT = "${this.BASE_URL}rest/V1/products?searchCriteria[page_size]=20"
//    const val BANNER = "${this.BASE_URL}rest/V1/cmsBlock/id-9-block-1"
//    const val BRAND = "${this.BASE_URL}rest/V1/cmsBlock/brands"
//    const val BEST_SELLER = "${this.BASE_URL}rest/V1/swp_mobile/cms-bestseller"
//    const val NEW_PRODUCT = "${this.BASE_URL}rest/V1/swp_mobile/cms-newproducts"
//    const val PRODUCT_INFO = "${BASE_URL}rest/V1/products/"
//    const val MEDIA_URL = "${BASE_URL}media/"
//    const val CATEGORY_BY_PARENT_ID = "${BASE_URL}rest/V1/categories/list?searchCriteria[page_size]=${Int.MAX_VALUE}&searchCriteria[filterGroups][0][filters][0][field]=parent_id&searchCriteria[filterGroups][0][filters][0][value]="
//    const val CATEGORY_IMG = "${BASE_URL}media/catalog/category/"
//    const val CART = "${BASE_URL}rest/V1/carts/mine"
//    const val CART_ITEMS = "${BASE_URL}rest/V1/carts/mine/items"
//    const val ORDER_HISTORY = "${BASE_URL}rest/V1/orders"
//    const val USER_INFO = "${BASE_URL}rest/V1/customers/me"
//    val POSTS = "https://jsonplaceholder.typicode.com/posts"
//
//    // media url
//    const val PRODUCT_MEDIA_URL = "${BASE_URL}media/catalog/product"
//    const val BANNER_MEDIA_URL = "${BASE_URL}media/"



    // Live server
//    private const val BASE_URL = "https://www.phonelcdparts.com/"
//    const val BAREAR_TOKEN = "0p1f52e1ogk559fjzf3gasx9j9q1uzzj"
//
//    const val PRODUCT = "${this.BASE_URL}rest/V1/products?searchCriteria[page_size]=20"
//    const val BANNER = "${this.BASE_URL}rest/V1/cmsBlock/id-9-block-1"
//    const val BRAND = "${this.BASE_URL}rest/V1/cmsBlock/brands"
//    const val BEST_SELLER = "${this.BASE_URL}rest/V1/swp_mobile/cms-bestseller"
//    const val NEW_PRODUCT = "${this.BASE_URL}rest/V1/swp_mobile/cms-newproducts"
//    const val PRODUCT_INFO = "${BASE_URL}rest/V1/products/"
//    const val MEDIA_URL = "${BASE_URL}media/"
//    const val CATEGORY_BY_PARENT_ID = "${BASE_URL}rest/V1/categories/list?searchCriteria[page_size]=${Int.MAX_VALUE}&searchCriteria[filterGroups][0][filters][0][field]=parent_id&searchCriteria[filterGroups][0][filters][0][value]="
//    const val CATEGORY_IMG = "${BASE_URL}media/catalog/category/"
//    const val CART = "${BASE_URL}rest/V1/carts/mine"
//    const val CART_ITEMS = "${BASE_URL}rest/V1/carts/mine/items"

}

//get all category using level
//http://192.168.29.236/phonelcdparts/pub/rest/V1/categories/list?searchCriteria[page_size]=20&searchCriteria[filterGroups][0][filters][0][field]=level&searchCriteria[filterGroups][0][filters][0][value]=1
//http://192.168.29.236/phonelcdparts/pub/rest/V1/categories/list?searchCriteria[page_size]=2000
//http://192.168.29.236/phonelcdparts/pub/rest/V1/categories/1