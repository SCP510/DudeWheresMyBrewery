package com.techelevator.dao;

import com.techelevator.model.Image;
import io.jsonwebtoken.lang.Strings;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.List;

public class JdbcBeerImageDao implements BeerImageDao{
    private JdbcTemplate temp;
    public JdbcBeerImageDao(JdbcTemplate temp) {
        this.temp = temp;
    }
    @Override
    public Image getById(int id) {
        String sql = "SELECT beer_image_id, data, media_type FROM beer_image WHERE image_id = ?;";
        Image image = null;
        SqlRowSet rs = temp.queryForRowSet(sql, id);
        if (rs.next()) {
            image = mapImage(rs);
        }
        return image;
    }

    @Override
    public boolean imageExists(int id) {
        String sql = "SELECT image_id FROM beer_image WHERE beer_image_id = ?;";
        SqlRowSet rs = temp.queryForRowSet(sql, id);
        return rs.next();
    }

    @Override
    public Image save(Image image, int beerId) {
        if (image.getId() > 0) {
            this.update(image, beerId);
            return image;
        } else {
            return this.saveNew(image, beerId);
        }
    }
    private Image saveNew(Image image, int beerId) {
        String sql = "INSERT INTO beer_image (data, media_type, beer_id) " +
                "VALUES (?, ?, ? ) RETURNING image_id;";
        Integer newId = this.temp.queryForObject(
                sql,
                Integer.class,
                (Object) image.getData(),
                image.getMediatype(),
                beerId);
        return getById(newId);
    }
    private void update(Image image, int beerId) {
        String sql = "UPDATE beer_image SET data = ?, media_type = ? WHERE image_id = ?;";
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
