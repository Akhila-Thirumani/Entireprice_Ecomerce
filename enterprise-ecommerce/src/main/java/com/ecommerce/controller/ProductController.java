package com.ecommerce.controller;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") int id) {

        try {
            Product product = productService.getProductById(id);

            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Product not found")
                        .build();
            }

            return Response.ok(product).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }

    @POST
    public Response createProduct(Product product) {

        try {
            if (product == null ||
                    product.getName() == null ||
                    product.getName().trim().isEmpty()) {

                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Product name is required")
                        .build();
            }

            productService.addProduct(product);

            return Response.status(Response.Status.CREATED)
                    .entity(product)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }
}