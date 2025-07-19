package DAO;

import DAO.Interface.IRepositoryBase;
import DAO.Interface.RowMapper;
import DTO.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IRepositoryBase<Product> {
    private final GenericDAO genericDAL = new GenericDAO();
    private final RowMapper<Product> productRowMapper = this::mapRowToProduct;

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getLong("price"),
                rs.getInt("stockQuantity"),
                rs.getString("imageUrl"),
                rs.getInt("rating"),
                rs.getLong("categoryId"),
                rs.getLong("brandId"),
                new ArrayList<>(), // colors
                new ArrayList<>(), // tags
                new ArrayList<>()  // sizes
        );

        // Gán thêm danh sách colors, tags, sizes vào sản phẩm
        product.setColors(getColorsByProductId(product.getId()));
        product.setTags(getTagsByProductId(product.getId()));
        product.setSizes(getSizesByProductId(product.getId()));
        return product;
    }
    private List<Color> getColorsByProductId(Long productId) {
        String sql = "SELECT c.id, c.colorName, c.colorHex " +
                "FROM ProductColor pc " +
                "INNER JOIN Color c ON pc.color_id = c.id " +
                "WHERE pc.product_id = ?";
        return genericDAL.queryForList(sql, rs -> new Color(
                rs.getLong("id"),
                rs.getString("colorName"),
                rs.getString("colorHex")
        ), productId);
    }
    private List<Tag> getTagsByProductId(Long productId) {
        String sql = "SELECT t.id, t.tagName, t.description " +
                "FROM ProductTag pt " +
                "INNER JOIN Tag t ON pt.tag_id = t.id " +
                "WHERE pt.product_id = ?";
        return genericDAL.queryForList(sql, rs -> new Tag(
                rs.getLong("id"),
                rs.getString("tagName"),
                rs.getString("description")
        ), productId);
    }
    private List<Size> getSizesByProductId(Long productId) {
        String sql = "SELECT s.id, s.sizeName, s.description " +
                "FROM ProductSize ps " +
                "INNER JOIN Size s ON ps.size_id = s.id " +
                "WHERE ps.product_id = ?";
        return genericDAL.queryForList(sql, rs -> new Size(
                rs.getLong("id"),
                rs.getString("sizeName"),
                rs.getString("description")
        ), productId);
    }
    @Override
    public Product findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID sản phẩm không hợp lệ");
        }
        String sql = "SELECT * FROM Product WHERE id = ?";
        return genericDAL.queryForObject(sql, productRowMapper, id);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM Product";
        List<Product> products = genericDAL.queryForList(sql, productRowMapper);
        // Gán thêm danh sách colors, tags, sizes cho từng sản phẩm
        for (Product product : products) {
            product.setColors(getColorsByProductId(product.getId()));
            product.setTags(getTagsByProductId(product.getId()));
            product.setSizes(getSizesByProductId(product.getId()));
        }
        return products;
    }

    @Override
    public Long create(Product product) {
        String sql = "INSERT INTO Product (name, description, price, stockQuantity, imageUrl, rating, categoryId, brandId) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Long productId = genericDAL.insert(sql, product.getName(), product.getDescription(), product.getPrice(),
                                  product.getStockQuantity(), product.getImageUrl(), product.getRating(),
                                  product.getCategoryId(), product.getBrandId());
        addColorsToProduct(productId, product.getColors());
        addTagsToProduct(productId, product.getTags());
        addSizesToProduct(productId, product.getSizes());
        return productId;
    }

    @Override
    public boolean update(Product product) {
        String sql = "UPDATE Product SET " +
                     "name = ?, " +
                     "description = ?, " +
                     "price = ?, " +
                     "stockQuantity = ?, " +
                     "imageUrl = ?, " +
                     "rating = ?, " +
                     "categoryId = ?, " +
                     "brandId = ? " +
                     "WHERE id = ?";
        boolean isUpdated = genericDAL.update(sql, product.getName(), product.getDescription(), product.getPrice(),
                                 product.getStockQuantity(), product.getImageUrl(), product.getRating(),
                                 product.getCategoryId(), product.getBrandId(), product.getId());
        removeColorsFromProduct(product.getId());
        addColorsToProduct(product.getId(), product.getColors());

        removeTagsFromProduct(product.getId());
        addTagsToProduct(product.getId(), product.getTags());

        removeSizesFromProduct(product.getId());
        addSizesToProduct(product.getId(), product.getSizes());
        return isUpdated;
    }

    @Override
    public boolean delete(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID sản phẩm không hợp lệ");
        }
        String sql = "UPDATE Product SET isActive = 0 WHERE id = ?";
        return genericDAL.delete(sql, id);
    }
    private void addColorsToProduct(Long productId, List<Color> colors) {
        String sql = "INSERT INTO ProductColor (product_id, color_id) VALUES (?, ?)";
        for (Color color : colors) {
            genericDAL.update(sql, productId, color.getId());
        }
    }

    private void removeColorsFromProduct(Long productId) {
        String sql = "DELETE FROM ProductColor WHERE product_id = ?";
        genericDAL.update(sql, productId);
    }

    private void addTagsToProduct(Long productId, List<Tag> tags) {
        String sql = "INSERT INTO ProductTag (product_id, tag_id) VALUES (?, ?)";
        for (Tag tag : tags) {
            genericDAL.update(sql, productId, tag.getId());
        }
    }

    private void removeTagsFromProduct(Long productId) {
        String sql = "DELETE FROM ProductTag WHERE product_id = ?";
        genericDAL.update(sql, productId);
    }

    private void addSizesToProduct(Long productId, List<Size> sizes) {
        String sql = "INSERT INTO ProductSize (product_id, size_id) VALUES (?, ?)";
        for (Size size : sizes) {
            genericDAL.update(sql, productId, size.getId());
        }
    }

    private void removeSizesFromProduct(Long productId) {
        String sql = "DELETE FROM ProductSize WHERE product_id = ?";
        genericDAL.update(sql, productId);
    }
}
