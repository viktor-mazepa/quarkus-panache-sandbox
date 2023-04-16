package com.mazasoft.quarkus.panache.resources;

import com.mazasoft.quarkus.jpa.model.Customer;
import com.mazasoft.quarkus.panache.model.Book;
import com.mazasoft.quarkus.panache.repositories.CustomerRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collection;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerRepository customerRepository;

    @Inject
    public CustomerResource(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GET
    public Collection<Customer> getAllCustomers() {
        return customerRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Customer findArtistById(@PathParam("id") Long id) {
        return customerRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
    }


    @Transactional
    @POST
    public Response persistCustomer(Customer customer, @Context UriInfo uriInfo) {
        customerRepository.persist(customer);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(customer.getId()));
        return Response.created(builder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public void deleteCustomerById(@PathParam("id") Long id) {
        customerRepository.deleteById(id);
    }
}
