package DAO;

import DAO.Interface.IRepositoryBase;
import DAO.Interface.RowMapper;
import DTO.Size;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SizeDAO implements IRepositoryBase<Size> {
    private final GenericDAO genericDAL = new GenericDAO();
    private final RowMapper<Size> sizeRowMapper = this::mapRowToSize;

    private Size mapRowToSize(ResultSet rs) throws SQLException {
        return new Size(
                rs.getLong("id"),
                rs.getString("sizeName"),
                rs.getString("description")
        );
    }
    @Override
    public Size findById(Long id) {
        String sql = "SELECT * FROM size WHERE id = ?";
        return genericDAL.queryForObject(sql, sizeRowMapper, id);
    }

    @Override
    public List<Size> findAll() {
        String sql = "SELECT * FROM size";
        return genericDAL.queryForList(sql, sizeRowMapper);
    }

    @Override
    public Long create(Size size) {
        String sql = "INSERT INTO size (sizeName, description) VALUES (?, ?)";
        return genericDAL.insert(sql, size.getSizeName(), size.getDescription());
    }

    @Override
    public boolean update(Size size) {
        String sql = "UPDATE size SET sizeName = ?, description = ? WHERE id = ?";
        return genericDAL.update(sql, size.getSizeName(), size.getDescription(), size.getId());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM size WHERE id = ?";
        return genericDAL.delete(sql, id);
    }
}
