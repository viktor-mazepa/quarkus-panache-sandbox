package com.mazasoft.quarkus.panache.page;

import com.mazasoft.quarkus.panache.model.PurchaseOrder;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/page/purchase-orders")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class PurchaseOrderPage {

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance purchaseOrder(PurchaseOrder purchaseOrder);

    public static native TemplateInstance purchaseOrders(Collection<PurchaseOrder> purchaseOrders);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showPurchaseOrderById(@PathParam("id") Long id) {
    return Templates.purchaseOrder(PurchaseOrder.findById(id));
  }

  @GET
  public TemplateInstance showAllPurchaseOrders() {
    return Templates.purchaseOrders(PurchaseOrder.listAll());
  }
}
