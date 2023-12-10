package org.resource;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import org.data.Fruit;
import org.repository.FruitRepository;

import java.util.List;
import java.util.Optional;

@Path("/fruits")
public class FruitResource {

    private final FruitRepository fruitRepository;

    public FruitResource(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @GET
    public Iterable<Fruit> findAll() {
        return fruitRepository.findAll();
    }


    @DELETE
    @Path("{id}")
    public void delete(long id) {
        fruitRepository.deleteById(id);
    }

    @POST
    @Path("/name/{name}/color/{color}")
    public Fruit create(String name, String color) {
        return fruitRepository.save(new Fruit(name, color));
    }

    @PUT
    @Path("/id/{id}/color/{color}")
    public Fruit changeColor(Long id, String color) {
        Optional<Fruit> optional = fruitRepository.findById(id);
        if (optional.isPresent()) {
            Fruit fruit = optional.get();
            fruit.setColor(color);
            return fruitRepository.save(fruit);
        }

        throw new IllegalArgumentException("No Fruit with id " + id + " exists");
    }

    @GET
    @Path("/color/{color}")
    public List<Fruit> findByColor(String color) {
        return fruitRepository.findByColor(color);
    }
}