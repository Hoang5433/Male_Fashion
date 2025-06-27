package DAO;

import DAO.Interface.IRepositoryBase;
import DAO.Interface.RowMapper;
import DTO.Category;
import DTO.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ColorDAO implements IRepositoryBase<Color> {
    private final GenericDAO genericDAL = new GenericDAO();
    private final RowMapper<Color> colorRowMapper = this::mapRowToColor;

    private Color mapRowToColor(ResultSet rs) throws SQLException {
        return new Color(
                rs.getLong("id"),
                rs.getString("colorName"),
                rs.getString("colorHex"),
                rs.getString("imageUrl")
        );
    }
    @Override
    public Color findById(Long id) {
        String sql = "SELECT * FROM color WHERE id = ?";
        return genericDAL.queryForObject(sql, colorRowMapper, id);
    }

    @Override
    public List<Color> findAll() {
        String sql = "SELECT * FROM color";
        return genericDAL.queryForList(sql, colorRowMapper);
    }

    @Override
    public Long create(Color color) {
        String sql = "INSERT INTO color (colorName, colorHex, imageUrl) VALUES (?, ?, ?)";
        return genericDAL.insert(sql, color.getColorName(), color.getColorHex(), color.getImageUrl());
    }

    @Override
    public boolean update(Color color) {
        String sql = "UPDATE color SET colorName = ?, colorHex = ?, imageUrl = ? WHERE id = ?";
        return genericDAL.update(sql, color.getColorName(), color.getColorHex(), color.getImageUrl(), color.getId());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM color WHERE id = ?";
        return genericDAL.delete(sql, id);
    }
}
