package com.example.appdbservice.utils;

public interface AppConstant {
    String BASE_PATH = "/api/open-fast-food";

    String UPLOAD_FILE_PATH = "src/main/java/ai/ecma/appfastfoodproject/";

    String USER_ROLE = "USER";

    String CONFIRMATION = "Account confirmation";

    String ADMIN_ROLE = "ADMIN";

    String AUTHORIZATION_HEADER = "Authorization";

    String BEARER_TOKEN = "Bearer ";
    String SALE_CONTROLLER_PATH = BASE_PATH + "/sale/";
    String CATEGORY_CONTROLLER_PATH = BASE_PATH + "/category/";
    String ATTACHMENT_CONTROLLER_PATH = BASE_PATH + "/attachment/";
    String FAVOURITE_CONTROLLER_PATH = BASE_PATH + "/favourite/";
    String PRODUCT_CONTROLLER_PATH = BASE_PATH + "/product/";
    String RECOMMENDED_PRODUCT_CONTROLLER_PATH = BASE_PATH + "/recommended-product/";
    String BRANCH_CONTROLLER_PATH = BASE_PATH + "/branch/";

    String ROLE_CONTROLLER_PATH = BASE_PATH + "/role/";

    String BASKET_CONTROLLER_PATH = BASE_PATH + "/basket/";

    String PRICE_SALE_CONTROLLER_PATH = BASE_PATH + "/price-sale/";
    String COUNT_SALE_CONTROLLER_PATH = BASE_PATH + "/count-sale/";

    String DELIVERY_SALE_CONTROLLER = "/delivery-sale/";

    String DELIVERY_PRICE_CONTROLLER_PATH = "/delivery-price/";


    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String VIEW = "view";

    String CLEAR = "clear";
    String VIEW_ONE = "view-one";

    String PERMISSIONS = "permissions";

    String ADD_PERMISSIONS_ROLE = "addPermissionsRole";
    String DELETE_PERMISSIONS_ROLE = "deletePermissionsRole";
    String UPLOAD_PATH = "upload";
    String DOWNLOAD_PATH = "download";

    String ADD_USER_ROLE = "add-user-role";
}
