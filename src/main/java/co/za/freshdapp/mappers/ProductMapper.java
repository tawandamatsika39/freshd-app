package co.za.freshdapp.mappers;

import co.za.freshdapp.models.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class ProductMapper implements ResultSetMapper<Product> {

    public Product map(int index, ResultSet r,
                    StatementContext ctx)
            throws SQLException {
        return new Product(
                r.getString("id"), r.getString("name"),
                r.getString("category"), r.getBigDecimal("price"),
                r.getInt("quantity"), r.getString("description"),
                r.getString("availability"), r.getString("supplierId"));
    }
}
