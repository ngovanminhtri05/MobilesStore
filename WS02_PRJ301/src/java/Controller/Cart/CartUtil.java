package Controller.Cart;

import Models.DTO.CartItem;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Swordlake
 */
public class CartUtil {

    public HashMap<String, CartItem> getCartFromCookie(Cookie cookieCart) {
        HashMap<String, CartItem> cart = new HashMap<>();
        Base64.Decoder base64Decoder = Base64.getDecoder();
        String encodedString = new String(base64Decoder.decode(cookieCart.getValue().getBytes()));
        String[] itemList = encodedString.split("\\|");
        for (String strItem : itemList) {
            String[] arrItemDetail = strItem.split(",");
            String itemId = arrItemDetail[0].trim();
            String itemName = arrItemDetail[1].trim();
            int quantity = Integer.parseInt(arrItemDetail[2].trim());
            double unitPrice = Double.parseDouble(arrItemDetail[3]);
            CartItem item = new CartItem(itemId, itemName, quantity, unitPrice);
            cart.put(itemId, item);
        }
        return cart;
    }

    public Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] arrCookies = request.getCookies();
        if (arrCookies != null) {
            for (Cookie cookie : arrCookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public void saveCartToCookie(HttpServletRequest request, HttpServletResponse response, String strItemsInCart) {
        String cookieName = "cart";
        Cookie cookieCart = getCookieByName(request, cookieName);
        if (cookieCart != null) {
            cookieCart.setValue(strItemsInCart);
        } else {
            cookieCart = new Cookie(cookieName, strItemsInCart);
        }
        cookieCart.setMaxAge(60 * 60 * 24); // Expires in 1 day
        response.addCookie(cookieCart);
    }

    public String convertCartToString(List<CartItem> itemsList) {
        StringBuilder strItemsInCart = new StringBuilder();
        for (CartItem item : itemsList) {
            strItemsInCart.append(item.toString()).append("|");
        }
        Base64.Encoder base64Encoder = Base64.getEncoder();
        return base64Encoder.encodeToString(strItemsInCart.toString().getBytes());
    }
}