package internet_shop.application.service;

import internet_shop.application.DAO.ProductDAO;
import internet_shop.application.model.Product;

import java.util.List;

public class ProductService {

    ProductDAO productDAO = new ProductDAO();
    List<Product> generalProductList;

    public ProductService() {
        this.generalProductList = productDAO.getGeneralProductList();
    }

    public List<Product> getProducts() {
        return generalProductList;
    }

    public void insertInBucketByProductId(long id, int count) {
        productDAO.insertInUserBucket(id, count);
    }

    public Product getProductById(long id) {
        for (Product product : generalProductList) {
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

}
