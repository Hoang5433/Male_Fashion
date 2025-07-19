package Servlet;

import java.io.*;
import java.util.List;

import DAO.*;
import DTO.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "Shop", value = "/Shop")
public class ShopServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        SizeDAO sizeDAO = new SizeDAO();
        ColorDAO colorDAO = new ColorDAO();
        TagDAO tagDAO = new TagDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Category> categoryList = categoryDAO.findAll();
        List<Brand> brandList = brandDAO.findAll();
        List<Size> sizeList = sizeDAO.findAll();
        List<Color> colorList = colorDAO.findAll();
        List<Tag> tagList = tagDAO.findAll();
        List<Product> productList = productDAO.findAll();
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("brandList", brandList);
        request.setAttribute("sizeList", sizeList);
        request.setAttribute("colorList", colorList);
        request.setAttribute("tagList", tagList);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/shop.jsp").forward(request, response);
    }

    public void destroy() {
    }
}