package com.mazasoft.quarkus.panache.resources;

import com.mazasoft.quarkus.panache.model.Publisher;
import com.mazasoft.quarkus.panache.model.PurchaseOrder;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/api/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PurchaseOrderResource {

    @GET
    public Collection<PurchaseOrder> getAllOrders() {
        return PurchaseOrder.listAll();
    }

    @GET
    @Path("{id}")
    public PurchaseOrder findOrderById(@PathParam("id") Long id) {
        return (PurchaseOrder) PurchaseOrder.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    @POST
    public Response persistOrder(PurchaseOrder purchaseOrder, @Context UriInfo uriInfo) {
        PurchaseOrder.persist(purchaseOrder);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(purchaseOrder.id));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteOrderById(@PathParam("id") Long id) {
        PurchaseOrder.deleteById(id);
    }
}
