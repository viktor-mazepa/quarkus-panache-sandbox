package com.mazasoft.quarkus.panache.page;

import com.mazasoft.quarkus.jpa.model.Customer;
import com.mazasoft.quarkus.panache.repositories.CustomerRepository;
import io.quarkus.panache.common.Sort;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("/page/customers")
@Produces(MediaType.TEXT_HTML)
@ApplicationScoped
public class CustomerPage {

  @Inject
  CustomerRepository repository;

  @CheckedTemplate
  public static class Templates {
    public static native TemplateInstance customer(Customer customer);

    public static native TemplateInstance customers(Collection<Customer> customers);
  }

  @GET
  @Path("{id}")
  public TemplateInstance showCustomerById(@PathParam("id") Long id) {
    return Templates.customer(repository.findById(id));
  }

  @GET
  public TemplateInstance showAllCustomers(@QueryParam("query") String query, @QueryParam("sort") @DefaultValue("id") String sort, @QueryParam("page") @DefaultValue("0") Integer pageIndex, @QueryParam("size") @DefaultValue("1000") Integer pageSize) {
    return Templates.customers(repository.find(query, Sort.by(sort)).page(pageIndex, pageSize).list())
      .data("query", query)
      .data("sort", sort)
      .data("pageIndex", pageIndex)
      .data("pageSize", pageSize);
  }
}
