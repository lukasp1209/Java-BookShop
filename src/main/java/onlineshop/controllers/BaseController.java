package onlineshop.controllers;

import jakarta.servlet.http.HttpSession;
import onlineshop.Cart;
import onlineshop.merchandise.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller

public class BaseController {
    @Autowired
    Cart cart;

    /**
     * Looks up the requested parameter in  the session. If it doesn't exist, it uses the default value.
     * @param session      {@link jakarta.servlet.http.HttpSession}
     * @param paramName    {@link String}
     * @param paramValue   {@link Object}
     * @param defaultValue {@link Object}
     * @return sessionValue {@link Object}
     */
    protected Object getSessionParam(HttpSession session,
                                   String paramName,
                                   Object paramValue,
                                   Object defaultValue) {
        if (paramValue == null) {
            Object sessionValue = session.getAttribute(paramName);
            paramValue = sessionValue != null ? sessionValue : defaultValue;
        }
        session.setAttribute(paramName, paramValue);
        return paramValue;
    }

    /**
     * Loads the cart items from the cart object and stores the corresponding attributes in the view view.
     * @param view {@link Model}
     */
    protected void getCartItems(Model view) {
        List<CartItem> cartItems = cart.getItems();
        view.addAttribute("cartItems", cartItems);
        view.addAttribute("numOfCartItems", cart.getNumOfItems());
        view.addAttribute("grandTotal", cart.getGrandTotal());
    }


}
