package DAO;

import DAO.Interface.IRepositoryBase;
import DAO.Interface.RowMapper;
import DTO.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BrandDAO implements IRepositoryBase<Brand> {
    private final GenericDAO genericDAL = new GenericDAO();
    private final RowMapper<Brand> brandRowMapper = this::mapRowToBrand;

    private Brand mapRowToBrand(ResultSet rs) throws SQLException {
        return new Brand(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("image_url")
        );
    }

    @Override
    public Brand findById(Long id) {
        String sql = "SELECT * FROM brand WHERE id = ?";
        return genericDAL.queryForObject(sql, brandRowMapper, id);
    }

    @Override
    public List<Brand> findAll() {
        try {
            String sql = "SELECT * FROM brand";
            return genericDAL.queryForList(sql, brandRowMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long create(Brand brand) {
        String sql = "INSERT INTO brand (name, description, image_url) VALUES (?, ?, ?)";
        return genericDAL.insert(sql, brand.getName(), brand.getDescription(), brand.getImageUrl());
    }

    @Override
    public boolean update(Brand brand) {
        String sql = "UPDATE brand SET name = ?, description = ?, image_url = ? WHERE id = ?";
        return genericDAL.update(sql, brand.getName(), brand.getDescription(), brand.getImageUrl(), brand.getId());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM brand WHERE id = ?";
        return genericDAL.delete(sql, id);
    }
}
