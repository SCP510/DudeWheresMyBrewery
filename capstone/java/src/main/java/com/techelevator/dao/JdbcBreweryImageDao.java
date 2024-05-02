package com.techelevator.dao;

import com.techelevator.model.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcBreweryImageDao implements BreweryImageDao{

    private JdbcTemplate temp;
    public JdbcBreweryImageDao(JdbcTemplate temp) {
        this.temp = temp;
    }
    @Override
    public Image getById(int id) {
        String sql = "SELECT brewery_image_id, data, media_type FROM brewery_image WHERE image_id = ?;";
        Image image = null;
        SqlRowSet rs = temp.queryForRowSet(sql, id);
        if (rs.next()) {
            image = mapImage(rs);
        }
        return image;
    }

    @Override
    public boolean imageExists(int id) {
        String sql = "SELECT image_id FROM brewery_image WHERE brewery_image_id = ?;";
        SqlRowSet rs = temp.queryForRowSet(sql, id);
        return rs.next();
    }

    @Override
    public Image save(Image image, int breweryId) {
        if (image.getId() > 0) {
            this.update(image, breweryId);
            return image;
        } else {
            return this.saveNew(image, breweryId);
        }
    }
    private Image saveNew(Image image, int breweryId) {
        String sql = "INSERT INTO brewery_image (data, media_type, brewery_id) " +
                "VALUES (?, ?, ? ) RETURNING image_id;";
        Integer newId = this.temp.queryForObject(
                sql,
                Integer.class,
                (Object) image.getData(),
                image.getMediatype(),
                breweryId);
        return getById(newId);
    }
    private void update(Image image, int breweryId) {
        String sql = "UPDATE brewery_image SET data = ?, media_type = ? WHERE image_id = ?;";
        this.temp.update(sql, (Object) image.getData(),  image.getMediatype(), image.getId());
    }
    public Image mapImage(SqlRowSet rs) {
        Image newImage = new Image(
                rs.getInt("image_id"),
                (byte[]) rs.getObject("data"),
                rs.getString("mediatype")
        );
        return newImage;
    }
}
