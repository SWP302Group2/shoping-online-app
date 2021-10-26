/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

/**
 *
 * @author hai
 */
public enum ServletMaterial {
    //Servlet
    SEARCH_NAME("searchName"),
    MANAGE_ACCOUNT("manageAccount"),
    UPDATE_ACCOUNT("updateAccount"),
    UPDATE_PROFILE("updateProfile"),
    MANAGE_ACCOUNT_STATUS("manageAccountStatus"),
    SHOP("shop"),
    SEARCH_PRODUCT("searchProduct"),
    UPDATE_CART("updateCart"),
    VIEW_CART("viewCart"),
    
    //Page
    DASHBOARD_PAGE("dashboardJSP"),
    LOGIN_PAGE("loginJSP"),
    PROFILE_PAGE("profileJSP"),
    SIGNUP_PAGE("signupJSP"),
    HOME("Home"),
    SHOP_PAGE("shopJSP"),
    CART_PAGE("viewCartJSP")
    
    ;
    
    private String url;

    private ServletMaterial() {
    }

    private ServletMaterial(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }


}
