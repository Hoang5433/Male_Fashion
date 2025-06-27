package DAO;

import DAO.Interface.IRepositoryBase;
import DAO.Interface.RowMapper;
import DTO.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TagDAO implements IRepositoryBase<Tag> {
    private final GenericDAO genericDAL = new GenericDAO();
    private final RowMapper<Tag> colorRowMapper = this::mapRowToTag;

    private Tag mapRowToTag(ResultSet rs) throws SQLException {
        return new Tag(
                rs.getLong("id"),
                rs.getString("tagName"),
                rs.getString("description")
        );
    }
    @Override
    public Tag findById(Long id) {
        String sql = "SELECT * FROM tag WHERE id = ?";
        return genericDAL.queryForObject(sql, colorRowMapper, id);
    }

    @Override
    public List<Tag> findAll() {
        String sql = "SELECT * FROM tag";
        return genericDAL.queryForList(sql, colorRowMapper);
    }

    @Override
    public Long create(Tag tag) {
        String sql = "INSERT INTO tag (tagName, description) VALUES (?, ?)";
        return genericDAL.insert(sql, tag.getTagName(), tag.getDescription());
    }

    @Override
    public boolean update(Tag tag) {
        String sql = "UPDATE tag SET tagName = ?, description = ? WHERE id = ?";
        return genericDAL.update(sql, tag.getTagName(), tag.getDescription(), tag.getId());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM tag WHERE id = ?";
        return genericDAL.delete(sql, id);
    }
}
