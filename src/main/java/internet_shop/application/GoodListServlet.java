package internet_shop.application;

import internet_shop.application.model.Product;
import internet_shop.application.service.BucketService;
import internet_shop.application.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodListServlet extends HttpServlet {

    private static final long serialVersionUID = -5293374759761138358L;
    private ProductService productService;
    private BucketService bucketService;

    public GoodListServlet() {
        this.productService = ProductService.getInstance();
        this.bucketService = BucketService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> listOfProducts = productService.getProducts();
        req.setAttribute("product", listOfProducts);
        req.getRequestDispatcher("WEB-INF/view/goodlist.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int count = Integer.parseInt(req.getParameter("count"));
        long id = Long.parseLong(req.getParameter("id"));
        bucketService.insertInBucketByProductId(id, count);
        resp.sendRedirect("goodlist");
    }
}
