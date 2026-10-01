package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderController {

    private final OrderService orderService;

    public OrderController() {
        this.orderService = new OrderService();
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") int id) {

        try {
            Order order = orderService.getOrderById(id);

            if (order == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Order not found")
                        .build();
            }

            return Response.ok(order).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }

    @POST
    public Response createOrder(Order order) {

        try {
            if (order == null || order.getUserId() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Valid user ID is required")
                        .build();
            }

            orderService.createOrder(order);

            return Response.status(Response.Status.CREATED)
                    .entity(order)
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }

    @PUT
    @Path("/{id}/status")
    public Response updateOrderStatus(
            @PathParam("id") int id,
            String status) {

        try {
            if (status == null || status.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Order status is required")
                        .build();
            }

            orderService.updateOrderStatus(id, status);

            return Response.ok("Order status updated successfully")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error")
                    .build();
        }
    }
}