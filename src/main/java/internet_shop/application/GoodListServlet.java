package internet_shop.application;

import internet_shop.application.model.Product;
import internet_shop.application.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodListServlet extends HttpServlet {

    private static final long serialVersionUID = -5293374759761138358L;
    ProductService productService;

    public GoodListServlet() {
        this.productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listOfProducts = productService.getProducts();
        req.setAttribute("product", listOfProducts);
        req.getRequestDispatcher("WEB-INF/view/goodlist.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int count = Integer.parseInt(req.getParameter("count"));
        long id = Long.parseLong(req.getParameter("id"));
        productService.insertInBucketByProductId(id, count);
    }
}
