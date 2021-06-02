package co.za.freshdapp.resources;


import co.za.freshdapp.dao.ProductDAO;
import co.za.freshdapp.models.Product;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.ahus1.keycloak.dropwizard.User;
import io.dropwizard.auth.Auth;
import org.skife.jdbi.v2.DBI;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductDAO productDAO;

    public ProductResource(DBI jdbi) {
        productDAO = jdbi.onDemand(ProductDAO.class);
    }

    @POST
    public int create(@Auth User auth, Product product) {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        return productDAO.insertProduct(product.getId(), product.getName(), product.getCategory(),
                product.getDescription(), product.getAvailability(), product.getPrice(),
                product.getQuantity(), product.getSupplierId());

    }

    @GET
    @Path("{id}")
    public Product findById(@Auth User auth, @PathParam("id") String id) {
        return productDAO.findById(id);
    }

    @DELETE
    @Path("{id}")
    public boolean deleteById(@Auth User auth, @PathParam("id") String id) {
        return true;
    }
}
