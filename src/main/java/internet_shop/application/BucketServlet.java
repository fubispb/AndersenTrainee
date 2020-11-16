package internet_shop.application;

import internet_shop.application.model.User;
import internet_shop.application.service.BucketService;
import internet_shop.application.service.ProductService;
import internet_shop.application.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BucketServlet extends HttpServlet {

    private static final long serialVersionUID = 7335525561023969359L;
    private BucketService bucketService;
    private ProductService productService;
    private UserService userService;
    private User user;

    public BucketServlet() {
        this.userService = new UserService();
        this.bucketService = BucketService.getInstance();
        this.productService = ProductService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = userService.getUserById(1);
        req.setAttribute("bucket", user.getBucket());
        req.getRequestDispatcher("WEB-INF/view/bucket.jsp").forward(req, resp);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        int count = Integer.parseInt(req.getParameter("count"));
//        long id = Long.parseLong(req.getParameter("id"));
//        bucketService.insertInBucketByProductId(id, count);
//        resp.sendRedirect("bucket");
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        bucketService.removeProductFromBucketByProductId(user.getId(), id);
        userService.deleteProductFromBucketByName(user, productService.getProductById(id));
        resp.sendRedirect("bucket");
    }



}
