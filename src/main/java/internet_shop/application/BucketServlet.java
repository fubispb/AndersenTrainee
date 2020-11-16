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
import java.util.Map;

public class BucketServlet extends HttpServlet {

    private static final long serialVersionUID = 7335525561023969359L;
    BucketService bucketService;

    public BucketServlet() {
        this.bucketService = new BucketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Product, Integer> userBucket = bucketService.getBucketByUserId(1);
        req.setAttribute("bucket", userBucket);
        req.getRequestDispatcher("WEB-INF/view/goodlist.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int count = Integer.parseInt(req.getParameter("count"));
        long id = Long.parseLong(req.getParameter("id"));
        //productService.insertInBucketByProductId(id, count);
    }
}
