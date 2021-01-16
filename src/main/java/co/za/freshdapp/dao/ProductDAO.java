package co.za.freshdapp.dao;

import co.za.freshdapp.mappers.ProductMapper;
import co.za.freshdapp.models.Product;
import java.math.BigDecimal;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ProductDAO {

    @Mapper(ProductMapper.class)
    @SqlQuery("select * from Product where id = :id")
    Product findById(@Bind("id") String id);

    @Mapper(ProductMapper.class)
    @SqlQuery("select * from Product")
    List<Product> getAllProducts();

    @GetGeneratedKeys
    @SqlUpdate("insert into Product " +
            "(   id,\n" +
            "    name,\n" +
            "    category,\n" +
            "    description,\n" +
            "    availability,\n" +
            "    price,\n" +
            "    quantity,\n" +
            "    supplierId )" +
            "values (:id, :name, :category, :description, :availability," +
            " :price, :quantity, :supplierId)")
    int insertProduct(@Bind("id") String id, @Bind("name") String name,
                      @Bind("category") String category, @Bind("description") String description,
                      @Bind("availability") String availability,
                      @Bind("price") BigDecimal price, @Bind("quantity") int quantity,
                      @Bind("supplierId") String supplierId);

    @SqlQuery("delete from Product where id = :id")
    void deleteProduct(@Bind("id") String id);
}
