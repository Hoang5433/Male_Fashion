package DAO;

import DAO.Interface.IRepositoryBase;
import DAO.Interface.RowMapper;
import DTO.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO implements IRepositoryBase<Category> {
    private final GenericDAO genericDAL = new GenericDAO();
    private final RowMapper<Category> categoryRowMapper = this::mapRowToCategory;

    private Category mapRowToCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getLong("id"),
                rs.getString("name")
        );
    }
    @Override
    public Category findById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        return genericDAL.queryForObject(sql, categoryRowMapper, id);
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return genericDAL.queryForList(sql, categoryRowMapper);
    }

    @Override
    public Long create(Category category) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        return genericDAL.insert(sql, category.getName());
    }

    @Override
    public boolean update(Category category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        return genericDAL.update(sql, category.getName(), category.getId());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM category WHERE id = ?";
        return genericDAL.delete(sql, id);
    }
}
