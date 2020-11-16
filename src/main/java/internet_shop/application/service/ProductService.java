package internet_shop.application.service;

import internet_shop.application.DAO.BucketDAO;
import internet_shop.application.DAO.ProductDAO;
import internet_shop.application.model.Product;

import java.util.List;
import java.util.Objects;

public class ProductService {

    private ProductDAO productDAO = new ProductDAO();
    private List<Product> generalProductList;
    private static ProductService instance;

    private ProductService() {
        this.generalProductList = productDAO.getGeneralProductList();
    }

    public static ProductService getInstance() {
        if (Objects.isNull(instance)) instance = new ProductService();
        return instance;
    }

    public List<Product> getProducts() {
        return generalProductList;
    }

    public Product getProductById(long id) {
        for (Product product : generalProductList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}
